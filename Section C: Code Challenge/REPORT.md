<h2>Space Complexity of the "NumberSayer" algorithm</h2> 

Note: This report concerns my second solution to the "Say The Number" coding challenge (Edabit.com, 2021) , but directly uses material and cites sources included in my previous report regarding my first solution (Foster, 2022).  The space complexity analysis itself was however redone, as the second solution implements an entirely different (recursive) algorithm.

## Space Complexity and Big-O notation

Just like time complexity is a measure of how long an algorithm takes to run relative to its input size, so space complexity is a measure of the **the total amount of memory that an algorithm occupies relative to its input size** (Baeldung, 2022).  In terms of worst-case time complexity, we can express this using big-O notation (CSDojo, 2018 & Hyperiondev, 2021), which will give us an idea of the upper-bound of an algorithm's memory growth rate (i.e. it could grow more slowly, but not more than the upper-bound) (Baeldung 2022).

This is often confused with auxiliary space, which is the extra or temporary spaced an algorithm uses. But because time complexity is defined as the total growth relative to the input size, **we need to consider auxiliary space and the memory occupied by the input, as the input gets larger** (CSDojo, 2018 & Geeksforgeeks, 2021).

Thus to get an idea of the overall worst-case time complexity, we can consider the big-O space of required by all the variables present in the algorithm (this includes the input) (Baeldung, 2022 & CSDojo 2018).

## Method

We have two main types of variables we need to check in this algorithm, namely constants and arrays:

Constants occupy a fixed amount of space, which differs depending on the data types, but in terms of big-O, they occupy O(1)/constant space - i.e. they remain a constant - c - relative to the input size (CSDojo, 2018). For 
primitives such as int and long, reassigning values does not mean that more memory is assigned (Sauer, 2010).
However, for immutable constants, such as Strings, reassigning values **does** create a new object in memory
(EJK, 2013).

Arrays, which are a collection of constants, typically have a space complexity of O(n), where n is the size of the array. Thus as n grows, the array's space complexity grows with it in a linear fashion. But if n itself is a constant - c - (i.e. when an array always has a fixed size), then we again have a space complexity of O(1) (CSDojo, 2018).

## Analysis

### Let's consider the big-O's of our algorithm variables:

```
public class NumberSayer {
    static final String[] zeroToNine = new String[] { ---------------------------> These three arrays always 
            "zero", "one", "two", "three", "four", "five", "six",                  stay exactly the same for all  
            "seven", "eight", "nine",                                              NumberSayer instances and
    };                                                                             always occupy constant space 
    static final String[] tenToNineteen = new String[] {                           for all input sizes.
            "ten", "eleven", "twelve", "thirteen", "fourteen",                     Thus:  O(1) + O(1) + O(1)	
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"                   =  c1  +  c2  +  c3
    };                                                                                  =  c4  
    static final String[] tyS = new String[] {                                          = O(1)
            "twenty", "thirty", "forty", "fifty", "sixty", "seventy",                   
            "eighty", "ninety"
    };
    static final String[] scaleNumbers = new String[] {
            "hundred", "thousand", "million", "billion", "trillion"
    };
    String numberInWords;  ------------------------------------------------------> String = c. Thus: O(1)
    int recursionDepth;  --------------------------------------------------------> int = c. Thus: O(1)

    public NumberSayer() {
        numberInWords = ""; -----------------------------------------------------> String = c. Thus: O(1)                                                    
        recursionDepth = -1;
    }

    public String sayNumber(long number) { --------------------------------------> INPUT long = c. Thus: O(1)
        recursionDepth += 1;                                                      

        if (number < 0) {
            numberInWords += "minus "; ------------------------------------------> String = c, plus "minus ". 
            number *= -1L;                                                         Thus: O(1) + O(1) = c1 + c2 
        }                                                                                            = c3 = O(1)
        if (number >= 0 && number < 10) { ---------------------------------------> Here, we have five conditions 
            numberInWords += (zeroToNine[(int)number] + " ");                      but we only have to analyze
        } else if (number >= 10 && number < 20) {                                  the condition with the worst-
            numberInWords += (tenToNineteen[(int)number % 10] + " ");              case memory usage.
        } else if (number >= 20 && number < 100) {
            numberInWords += (tyS[((int)number / 10) - 2] + "-");                  This is for numbers >= 1000                                   
                                                                                   below. 
            if (number % 10 != 0) {
                sayNumber(number % 10);
            }
        } else if (number >= 100 && number < 1000) {
            numberInWords += (zeroToNine[(int)number / 100] + " "); 
            numberInWords += (scaleNumbers[0] + " ");

            if (number % 100 != 0) {
                numberInWords += "and "; 
                sayNumber(number % 100);
            }
        } else if (number >= 1000 && number < 1000000000000000L) {
            long divisor; -------------------------------------------------------> long = c. Thus O(1)                                                         
            int scale = 0; ------------------------------------------------------> scale = c. Thus O(1)

            if (number < 1000000) {
                divisor = 1000;
                scale = 1;
            } else if (number >= 1000000 & number < 1000000000) {
                divisor = 1000000;
                scale = 2;
            } else if (number >= 1000000000 & number < 1000000000000L) {
                divisor = 1000000000;
                scale = 3;
            } else {
                divisor = 1000000000000L;
                scale = 4;
            }
            sayNumber(number / divisor);

            int length = numberInWords.length(); --------------------------------> int = c. Thus: O(1)

            if (numberInWords.charAt(length - 1) == '-') { ----------------------> char = c. Thus: O(1)
                numberInWords = numberInWords.substring(0, length - 1) ----------> String = c, plus two Strings
                              + " ";                                               for substring() and for " ".
            }                                                                      Thus O(1) + O(1) + O(1)
            numberInWords += (scaleNumbers[scale]);                                    = c1  +  c2  + c3                                   
                                                                                       = c4  = O(1)
            if (number % divisor == 0) { ----------------------------------------> Only one of these conditions
                numberInWords += " ";                                              can be true. In each case:
            } else if (number % divisor >= 100){                                   String = c, plus one concat.
                numberInWords += ", ";                                             Thus: O(1) + O(1) = c1 + c2
            } else if (number % divisor < 100){                                                      = c3 = O(1)                                     
                numberInWords += " and ";                                           
            }
            if (number % divisor != 0) {
                sayNumber(number % divisor);
            }
        }
        recursionDepth -= 1;

        if (recursionDepth == -1) {
            int finalLength = numberInWords.length(); --------------------------> int = c. Thus: O(1)
            String returnString = numberInWords.substring(0, 1).toUpperCase() --> String = c, plus a String
                                + numberInWords.substring(1, finalLength - 1)     for each substring(), for 
                                + ".";                                            toUpperCase(), and for ".".
            numberInWords =""; -------------------------------------------------> String = c.                                                    
                                                                                  Thus: O(1) + O(1) + O(1)   
            return returnString;                                                      + O(1) + O(1) + O(1)
        } else {                                                                      =  c1  +  c2  +  c3                                                                       
            return numberInWords;                                                     =  c4  +  c5  +  c6                                              
        }                                                                             =  c7                                                                                                                                                                                   
    };                                                                                = O(1)                

}
```

### Result

If we now add up all the big-O's for the variables, we get: 14 x O(1), i.e. 14 constants, **which add up to another constant**.

And the big-O complexity of a constant value is always O(1), whether we are evaluating time or space complexity,  thus giving us, in this case, **a final worst case space complexity of O(1)** (CSDojo, 2018).

This makes sense as even if the input number grows, it occupies constant space within the method, and there are no arrays that actually grow in size relative to the input,  hence we have a constant space complexity, no matter the size of the user's input number.

However, this is a recursive function, and we have to take into acount the memory occupied by each recurisive 
call on the call stack (Geeksforgeeks.com, 2021).  Each recursive call represents a stack frame on the memory call
stack (More, 2022), so the worst-case space complexity of a recursive method is directly proportional to maximum depth of its recursion tree. Suppose each recursive call of a method takes O(n) space. If the maximum recursion depth is m then space complexity of the method would be O(nm) (More, 2022 &
Geeksforgeeks.com, 2021).

**Thus we need to find the maximum recursive depth.**

To test this, the <code>recursionDepth</code> attribute was used to add markers to the code. Worst-case recursion depth for each possible input length appeared when all digits were non-zero - with a maximum worst case of depth six for input length 15, but would continue to grow if longer numbers were included in the method's range. 

The following graph illustrates the data:

![](Report-images/sayNumber()_Recursion_Depth_Graph.jpg)

As can be seen, although the worst-case depth remained constant for the intervals created by grouping numbers and recurring according to size, the overall relationship between number length (expressed here as log10  of the number) and maximum recursion depth creates a clear linear relationship when viewed **across** intervals, from
length log10 3 onwards: For each 1000x increase in the input number, the potential worst-case recursive depth increases by one. **Thus, as the input size grows, the potential maximum recursion depth grows along with it at a constant rate.**

So while <code>sayNumber</code> method itself might have a space complexity of O(1),  the maximum recursion depth **grows with the input size** n, and we thus have a space complexity of O(1*n), which of course is just O(n).<br /><br />


### Conclusion

**All evidence points toward a worst-case space complexity of O(n) for this algorithm.**<br /><br /><br /><br />






### References:

[1] Baeldung.com (2021). Understanding Space Complexity. [online] Available at: https://www.baeldung.com/cs/space-complexity [Accessed 23 Jun 2022].<br />
[2] CS Dojo (2018). Introduction to Big O Notation and Time Complexity (Data Structures & Algorithms #7). [video] Youtube.com. Available at: https://www.youtube.com/watch?v=D6xkbGLQesk&ab_channel=CSDojo [Accessed 23 Jun 2022].<br />
[3] Edabit.com (2021). Say the Number! [online] Available at: https://edabit.com/challenge/4E9gTrRWErpTCA2FQ [Accessed 02 July 2022].<br /> 
[4] EJK (2013). Can you change a String object after you create it? [online] Stackoverflow.com. Available at: https://stackoverflow.com/questions/14411399/can-you-change-a-string-object-after-you-create-it [Accessed 03 July].<br />
[5] Foster, J.E. (2022). Space Complexity of the "number-to-words" algorithm. [online] Github.com. Available at: https://github.com/J-E-Foster/Hyperiondev-Take-Home-Test/blob/master/Section%20C:%20Code%20Challenge/REPORT.md [Accessed 03 July 2022].<br />
[6] Geeksforgeeks.com (2021). What does ‘Space Complexity’ mean? [online] Available at: https://www.geeksforgeeks.org/g-fact-86/#:~:text=Auxiliary%20Space%20is%20the%20extra,and%20space%20used%20by%20input. [Accessed 23 Jun 2022].<br />
[7] Hyperiondev (2021). Introduction to Computer Science Fundamentals and Big O Notation. p.6-14.
[8] More, N. (2022). Algorithm/Insights. [online] Ideserve.co.in. Available at: https://www.ideserve.co.in/learn/time-and-space-complexity-of-recursive-algorithms#:~:text=To%20conclude%2C%20space%20complexity%20of,would%20be%20O(nm). [Accessed 04 July].<br />
[9] Sauer, J. (2010). declaring/initializing primitives equal to creating new objects. [online] Stackoverflow.com. Available at: https://stackoverflow.com/questions/2875131/declaring-initializing-primitives-equal-to-creating-new-objects#:~:text=No%2C%20assigning%20primitive%20values%20does%20not%20create%20any%20objects.&text=A%20variable%20is%20%22declared%22%20when,assigned%20a%20value%20during%20declaration. [Accessed 03 July 2022].
