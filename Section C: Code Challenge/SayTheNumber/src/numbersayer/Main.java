package numbersayer;

import java.util.Scanner;

/* This is the main application class that implements 'NumberSayer' and
 * 'sayNumber()'. It includes a single static method 'getUserInput()',
 * that receives and validates the user's number. */
public class Main {
	/* Creates a 'NumberSayer', gets user input via 'getUserInput()', 
	 * and calls 'sayNumber()', passing the validated input as param. */ 
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean sayAnotherNumber = true;
	    NumberSayer numberSayer  = new NumberSayer();
	    
	    while (sayAnotherNumber) {
	    	long number = getUserInput(input);
		    
		    System.out.println("\nYour number in words is:\n\n"
			           + numberSayer.sayNumber(number));
		    
		    sayAnotherNumber = getAnswer(input);
	    } 
	    System.out.println("\nGoodbye!");
	    input.close();
	    System.exit(0);
	};
	
	/* Receives and validates user input by checking for: non-numerical
	 * chars, zero-starting numbers, and numbers out of range. */ 
	public static long getUserInput(Scanner input) {
		String userNumber;
		System.out.println("\nPlease enter any whole number smaller than "
				           + "1,000,000,000,000,000,\n"
						   + "or larger than -1,000,000,000,000,000 "
						   + "(no comma or space separators):");
	
		while (true) {
			userNumber = input.nextLine();
			
			/* Checks that input matches a string containing one or 
			 * none "-", plus one or more digits (Vogel, 2022). If it
			 * doesn't, the input contains non-numerical chars. */
			if (userNumber.matches("-?[0-9]+") == false) {
				System.out.println("\nYour number contains non-numerical "
						           + "characters, decimals, or spaces.\n"
								   + "Please remove all non-numerical "
								   + "characters or spaces, and re-enter "
								   + "your number:");
				continue;
				
			/* If no non-numerical chars, checks if input matches a 
			 * string starting with one or none "-" and a zero, plus 
			 * one or more digits (Vogel 2022). */
			} else if (userNumber.matches("^(-?0)[0-9]+")) {
				System.out.println("\nYour number starts with a zero.\n"
						           + "Please re-enter your number:");
				continue;
			}
			/* Checks if first input char is "-" (to help validate 
			 * range below). */
			char firstChar = userNumber.charAt(0);
			int length = userNumber.length();
			
			// Checks if input exceeds max range of 15 chars.
			if (firstChar != '-' && length > 15 ||
				 firstChar == '-' && length > 16) {
				System.out.println("\nYour number exceeds the valid "
						           + "limit of +-999,999,999,999,999.\n"
						           + "Please enter a number within range:");
				continue;	
			}
			break;
		}
		return Long.parseLong(userNumber);		
	};
	
	/* Asks if user would like to enter another number.
	 * If no, returns false to the 'sayAnotherNumber boolean
	 * in the main method. */
	public static boolean getAnswer(Scanner input) {
		String answer;
	
		while (true) {
			System.out.println("\nSay another number? Y/N");
		    answer = input.nextLine();
		    
	    	if (answer.equalsIgnoreCase("Y")) {
		    	return true;
		    } else if (answer.equalsIgnoreCase("N")) {
		    	return false;
		    } else {
		    	continue;
		    }
	    } 
	};
	
}

///////////////////////////// THE END //////////////////////////////////

// REFERENCES:

// Vogel, L. (2022). Regular Expressions in Java - Tutorial. [online] Vogella.com. Available at: https://www.vogella.com/tutorials/JavaRegularExpressions/article.html [Accessed 03 July].


////////////////////////////////////////////////////////////////////////

