package numbersayer.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import numbersayer.Main;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

/* This is the test class for the 'getUserInput()' method of the 'Main'
 * class. */
public class MainTest {
	// Streams back up System in and out (KrzyH, 2020 & dfa, 2009).
	private final InputStream sysInBackup = System.in;
	private final PrintStream sysOutBackup = System.out;
	
	/* Uses 'getRandomNumber()' and 'compareSystemOutToExpected()'
     * to respectively generate random invalid and valid user input, 
     * and compare the expected output results to the actual System.out
     * values generated by 'getUserInput()'. */
	@Test
	public void testGetUserInput() {
		/* Stores the expected error output of 'getUserInput()' when
		 * entering non-numerical chars, numbers starting with zero,
		 * and numbers out of range into its while-loop (in that order). */
		String expectedOutput = "\nPlease enter any whole number smaller "
				              + "than 1,000,000,000,000,000,\nor larger"
				              + " than -1,000,000,000,000,000 (no comma"
				              + " or space separators):"
			                  + System.lineSeparator()
			                  + "\nYour number contains non-numerical "
			                  + "characters, decimals, or spaces.\n"
			                  + "Please remove all non-numerical characters "
			                  + "or spaces, and re-enter your number:" 
				              + System.lineSeparator()
				              + "\nYour number starts with a zero.\n"
				              + "Please re-enter your number:" 
				              + System.lineSeparator()
			                  + "\nYour number exceeds the valid limit of "
			                  + "+-999,999,999,999,999.\n"
				              + "Please enter a number within range:"
			                  + System.lineSeparator();
		
		for (int i = 0; i < 10; i++) {
			/* Creates random number Strings via 'getRandomNumber()',
			 * representing non-numerical, zero-starting, out-of-range, 
			 * and valid input.*/
			String randomNonNumerical = getRandomNumber(1, 15, false, true);
			String zeroStarter = getRandomNumber(2, 15, true, false);
			String outOfRange = getRandomNumber(16, 19, false, false);
			String validInput = getRandomNumber(1, 15, false, false);
			
			/* Constructs an input String to simulate each type of 
			 * invalid input (which should each trigger a specific error
			 * message, and cause the method's while-loop to request 
			 * new input), plus a valid entry (which should cause 
			 * 'getUserInput()' to return). This string becomes the new
			 * System.in below (Note: a System line separator should be
			 *  used instead of "/n" - Chowrasia, 2018). */
			String input = randomNonNumerical + System.lineSeparator()
			             + zeroStarter + System.lineSeparator()
			             + outOfRange + System.lineSeparator() 
			             + validInput;
			
			/* Creates new input and output streams that respectively
			 * simulate System in and capture System out (dfa, 2009). */
			ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			// Sets System in and out to new streams (KrzyH, 2020 & Cook, 2020).
			System.setIn(in);
			System.setOut(new PrintStream(out));
			
			Scanner simScanner = new Scanner(System.in);
			
			/* Calls 'getUserInput()' which reads the 'input' String 
			 * above as System.in to simulate repeated invalid, and 
			 * final valid, user input, and captures the method's 
			 * System output response (i.e. the while-loop error 
			 * messages) using 'out'. */
			Main.getUserInput(simScanner);
			
			compareSystemOutToExpected(expectedOutput, out);
		}	
	};
	
	
	/* Restores System in and out after each test 
	 * (KrzyH, 2020 & dfa, 2009). */
	@AfterEach
	public void restore() {
		System.setIn(sysInBackup);
		System.setOut(new PrintStream(sysOutBackup));
	};
	
	/* Compares the actual System output, captured by 'out', to the
	 * expected output for each type of input (valid and invalid). 
	 * Test fails if any input doesn't perform exactly as expected
	 * (Cook, 2020 & dfa 2009) */
	void compareSystemOutToExpected(String expectedOutput,
			                        ByteArrayOutputStream out) {	
		 
		assertEquals(expectedOutput, out.toString());
	};
	
	/* Returns random valid and invalid number Strings. Parameters 
	 * 'minLength' and 'maxLength' respectively set the range of the 
	 * length of the return String; 'zeroStarter' and 'nonNumerical' 
	 * respectively set options for returning number Strings starting
	 * with zero or containing non-numerical chars. */
	String getRandomNumber(int minLength,
			               int maxlength, 
			               boolean zeroStarter,
			               boolean nonNumerical) {
		// Creates random generator (Docs.oracle.com (2022).
		Random randomGen = new Random();
		/* Creates random output String length in range 'minLength' 
		 * <= n < 'maxLength' (EDToaster, 2015). */
		int randomLength = randomGen.nextInt((maxlength + 1) - minLength) 
				                             + minLength;
		// Determines if negative number will be generated.
		boolean negative = randomGen.nextBoolean();
		/* Case 'nonNumerical, determines whether to add a 
		 * non-numerical char.*/
		boolean insert;
		/* Case 'insert', determines whether to add a non-numerical 
		 * char occurring before or after the ASCII numerical range. */
		boolean before;
		// Helps construct the final return String.
		StringBuilder number = new StringBuilder();
		
		if (negative) {
			number.append('-');
		}
		// Adds 1st char to return string according to param option:
		// STARTS WITH ZERO:
		if (zeroStarter) {
			number.append('0');
		// VALID INPUT: adds random non-zero numerical char.
		} else if (!nonNumerical) {
			number.append((char)(randomGen.nextInt(58 - 49) + 49));
		// NON-NUMERICAL: adds random non-numerical char.
		} else {
			number.append((char)(randomGen.nextInt(48 - 32) + 32));
		}
		// Builds the rest of the string up to a random length in range:
		for (int i = 0; i < randomLength - 1; i++) {
			// CASE NON NUMERICAL:
			if (nonNumerical) {
				/* Randomly determines whether to add non-num char. */
				insert = randomGen.nextBoolean();
				
				if (insert) {
					/* Randomly determines whether to add a non-num 
					 * char occurring before or after the ASCII 
					 * numerical range. */
					before = randomGen.nextBoolean();
					
					if (before) {
						// Adds random non-num char (before num range).
						number.append((char)(randomGen.nextInt(48 - 32)
								             + 32));
					} else {
						// Adds random non-num char (after num range).
						number.append((char)(randomGen.nextInt(127 - 58) 
								             + 58));
					}
				// If 'insert' is false, adds random numerical char.
				} else {
					number.append((char)(randomGen.nextInt(57 - 48) 
							             + 48));
				}
			// CASE VALID INPUT:
			} else {
				// Adds random numerical char.
				number.append((char)(randomGen.nextInt(57 - 48) + 48));
			}	
		}
		return String.valueOf(number);
	};
	
}

//////////////////////////// THE END //////////////////////////////////

// REFERENCES:

// Chowrasia, A. (2018). System.lineSeparator() method in Java With Examples. [online] Geeksforgeeks.com. Available at: https://www.geeksforgeeks.org/system-lineseparator-method-in-java-with-examples/ [Accessed 03 July 2022].
// Cook, J. (2020). Unit Testing of System.out.println() with JUnit. [online] Baeldung.com. Available at: https://www.baeldung.com/java-testing-system-out-println [Accessed 03 July 2022].
// dfa (2009) & Community (ed.)(2020). JUnit test for System.out.println(). [online] Stackoverflow.com. Available at: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println/1119559#1119559 [Accessed 03 July 2022]. 
// Docs.oracle.com (2022). Class Random. [online] Available at: https://docs.oracle.com/javase/8/docs/api/java/util/Random.html [Accessed 03 July 2022].
// EDToaster (2015) & Mortensen, P (ed.)(2020). How do I get a random number with a negative number in range? [duplicate] [online] Stackoverflow.com. Available at: https://stackoverflow.com/questions/27976857/how-do-i-get-a-random-number-with-a-negative-number-in-range [Accessed 03 July 2022].
// KrzyH (2020) & informatik01 (ed.)(2013). JUnit testing with simulated user input. [online] Stackoverflow.com. Available at: https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input [Accessed 03 July 2022].


///////////////////////////////////////////////////////////////////////
