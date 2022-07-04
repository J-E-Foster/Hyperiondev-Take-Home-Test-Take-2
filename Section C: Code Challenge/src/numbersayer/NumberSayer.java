package numbersayer;

/* This class aims to provide an original Java solution to the coding 
 * challenge "Say the Number" (Edabit.com, 2022) via a single instance
 * method 'sayNumber()'. The method converts any integer in range +-
 * 999,999,999,999,999 into text.
 * 
 * My previous attempt merely adapted the three-digit-group solution 
 * proposed by Carr (2007). This 2nd attempt is my own implementation, 
 * and utilizes a recursive instance method to convert the number.
 * 
 * The idea of using static, final arrays to store the text needed to
 * assemble the number text, as well as using the hundreds part of the
 * number to determine which suffix to add after scale number words, 
 * was however kept, albeit modified to suit the new implementation. */
public class NumberSayer {
	// STATIC VARIABLES:
	/* The next four arrays contain all numbers, that in combination, 
	 * make up all numbers up to 999 trillion (Carr, 2007)(modern 
	 * short-scale numbering is used, so "1000,000,000" = "one billion",
	 * and  "1000,000,000,000" = "one trillion", etc.- Wikipedia.org,
	 * 2022). Final static variables are used because their values are
	 * never changed by any NumberSayer instance (Kugathasan, 2016). */
	static final String[] zeroToNine = new String[] { 
			"zero", "one", "two", "three", "four", "five", "six", 
			"seven", "eight", "nine", 
	};
	static final String[] tenToNineteen = new String[] {
			"ten", "eleven", "twelve", "thirteen", "fourteen", 
			"fifteen", "sixteen", "seventeen", "eighteen", "nineteen"	
	};
	static final String[] tyS = new String[] { 
			"twenty", "thirty", "forty", "fifty", "sixty", "seventy", 
			"eighty", "ninety"
	};
	static final String[] scaleNumbers = new String[] {
			"hundred", "thousand", "million", "billion", "trillion"
	};
	// INSTANCE VARIABLES:
	/* Stores the return value of 'sayNumber()'- i.e. the text-number 
	 * assembled using the arrays above. Before final return, it is 
	 * used to create a return string that includes capitalization and
	 * a full stop, after which its value is reset to an empty string.
	 * This prevents having to create a new 'NumberSayer' just to say
	 * a new number (and makes testing more compact). */
	String numberInWords;
	/* Enables assemblage of final return string, and reset of 
	 * 'numberInWords', by keeping track of the recursion depth of 
	 * 'sayNumber()' (initialized at minus one, incremented upon each
	 * method call, and decremented upon each return, so final return
	 * is marked by its decrement to minus one again). */
	int recursionDepth;
	
	// CONSTRUCTOR:
	public NumberSayer() {
		numberInWords = "";
		recursionDepth = -1;
	}
	// INSTANCE METHOD:
	/* Returns a String containing the standard English text for any 
	 * positive or negative integer - smaller than 999 trillion, and 
	 * larger than -999 trillion (using short-scale numbering) - 
	 * complete with punctuation and hyphens.
	 * 
	 * The integer is classified by its size, and (using simple elif 
	 * statements), the appropriate words are added to 'numberInWords'
	 * by simply fetching them from the static arrays, using recursion
	 * if necessary for numbers >= 20.
	 * 
	 * Recursive pattern: Numbers are assigned a size-proportionate 
	 * divisor, and their their largest number word/s (e.g. the "one
	 * thousand" in "1234") added to 'numberInWords' according to the
	 * quotient. The remainder is then passed into recursion (for 
	 * numbers greater than or equal to 1000, the quotient itself is
	 * first passed into recursion), thus yielding smaller and smaller
	 * quotients/remainders, and adding the corresponding text and 
	 * punctuation along the way, until finally, units are added, 
	 * and the method returns.
	 * 
	 * Maximum depth of the recursion tree: six (case numbers have 
	 * length of 15 with all digits non-zero). */
	public String sayNumber(long number) {
		/* Increments going into, and decrements coming out of, each
		 * call (first call depth = zero). The final return is marked
		 * by its reset to its initial value - minus one. */
		recursionDepth += 1;
		
		// NEGATIVE NUMBERS:
		if (number < 0) {
			numberInWords += "minus ";
			/* Converts number to a positive value for processing. */
			number *= -1L;
		}
		// NUMBERS ZERO TO NINE:
		// Fetches the number from the 'zeroToNine' array.
		if (number >= 0 && number < 10) {
			numberInWords += (zeroToNine[(int)number] + " ");
			
		// NUMBERS 10 TO 19:
		// Fetches the number from the 'tenToNineteen' array.
		} else if (number >= 10 && number < 20) {
			numberInWords += (tenToNineteen[(int)number % 10] + " ");
		
		// NUMBERS 20 TO 99:
		/* Fetches the "ty" part of the number from the 'tyS' array, 
		 * then recursion adds the units part of the number, if any 
		 * (i.e. if the number/10 has a remainder). */
		} else if (number >= 20 && number < 100) {
			numberInWords += (tyS[((int)number / 10) - 2] + "-");
			
			if (number % 10 != 0) {
				sayNumber(number % 10);
			}			
		// NUMBERS 100 TO 1000:
		/* Fetches the count of the hundreds part of the number (i.e.
		 * the number/100) from the 'zeroToNine' array, then adds 
		 * "hundred' from the 'scaleNumbers' array. Recursion then 
		 * adds the "tys", tens, and unit parts (if the number/100 has
		 * any remainder). */
		} else if (number >= 100 && number < 1000) {
			numberInWords += (zeroToNine[(int)number / 100] + " ");
			numberInWords += (scaleNumbers[0] + " ");

			if (number % 100 != 0) {
				numberInWords += "and ";
				sayNumber(number % 100);
			}
		// NUMBERS 1000 TO 1000,000,000,000,000:
		/* Groups numbers into 1000x intervals, which determine the 
		 * divisor and scale to be used when adding words and 
		 * punctuation. */
		} else if (number >= 1000 && number < 1000000000000000L) {
			long divisor;
			int scale = 0;
			
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
			/* Adds the hundreds, "tys", tens, and units part of the 
			 * scale number via recursion (i.e. the "one hundred and 
			 * twenty-four" in "one hundred and twenty-four billion"). */
			sayNumber(number / divisor);

			int length = numberInWords.length();
			
			/* Removes a "-" from the end of the return string before
			 * adding the scale number word (in case the last added 
			 * number was a "ty" number, thus preventing strings such
			 * as "twenty-thousand"). */
			if (numberInWords.charAt(length - 1) == '-') {
				numberInWords = numberInWords.substring(0, length - 1) 
						      + " ";
			}
			/* Adds the scale number word (e.g. "thousand", "million",
			 * etc.) from 'scaleNumbers' array using 'scale' set above. */
			numberInWords += (scaleNumbers[scale]);
			
			/* Determines which suffix to add after the scale number 
			 * word (adapted from Carr, 2007). If there is no remainder
			 * after dividing, it is a "clean" scale number (e.g. 
			 * "fifty-four billion"), so a space is added (needed when
			 * adding the final punctuation before final return. */
			if (number % divisor == 0) {
				numberInWords += " ";
			/* If there is a hundreds part to the number, a comma is 
			 * added (e.g. "sixty-one thousand, two hundred and two"). */
			} else if (number % divisor >= 100){
				numberInWords += ", ";
			/* If there is no hundreds part, and "and" is added (e.g. 
			 * "four million and twenty-five"). */
			} else if (number % divisor < 100){
				numberInWords += " and ";
			}
			// Adds rest of number via recursion, in case of remainder. 
			if (number % divisor != 0) {
				sayNumber(number % divisor);
			}
		}
		// Marks coming out of recursion. 
		recursionDepth -= 1;
		/* Adds capital and full stop to the final string and resets 
		 * 'numberInWords' upon final return (i.e. the recursion depth
		 * is minus one again). Reset of 'numberInWords' is needed 
		 * case the method is called again on the same object. */
		if (recursionDepth == -1) {
			int finalLength = numberInWords.length();
			String returnString = numberInWords.substring(0, 1).toUpperCase()
				                + numberInWords.substring(1, finalLength - 1) 
				                + ".";
			numberInWords ="";
			
			return returnString;
		} else {
			return numberInWords;
		}			
	};
		
}

///////////////////////////// THE END /////////////////////////////////

// REFERENCES:
  
// Carr, R. (2007). Convert A Number into Words. [online] Blackwasp.co.uk. Available at: http://www.blackwasp.co.uk/NumberToWords.aspx [Accessed 22 Jun 2022]. 
// Edabit.com (2021). Say the Number! [online] Available at: https://edabit.com/challenge/4E9gTrRWErpTCA2FQ [Accessed 02 July 2022]. 
// Foster, J.E. (2022). NumberSayer.java (Version 1). [online] Github.com. Available at: https://github.com/J-E-Foster/Hyperiondev-Take-Home-Test/tree/master/Section%20C:%20Code%20Challenge [Accessed 02 July 2022].
// Kugathasan, A. (2016). When to use static variables/methods and when to use instance variables/methods in Java? [online] Stackoverflow.com. Available at: https://stackoverflow.com/questions/21155438/when-to-use-static-variables-methods-and-when-to-use-instance-variables-methods#:~:text=use%20static%20variables%20when%20%3A%20The,E.g.%20number%20of%20students.&text=is%20not%20useful-,Show%20activity%20on%20this%20post.,need%20to%20know%20the%20variable. [Accessed 02 July 2022].
// Wikipedia.org (2022). Long and Short Scales. [online] Available at: https://en.wikipedia.org/wiki/Long_and_short_scales [Accessed 02 July 2022]. 


///////////////////////////////////////////////////////////////////////
