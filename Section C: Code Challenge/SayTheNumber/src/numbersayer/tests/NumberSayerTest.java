package numbersayer.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import numbersayer.NumberSayer;

/* This is the test class for the 'sayNumber()' method of the 
 * 'NumberSayer' class. It is a direct refactoring of my previous 
 * 'NumberSayerTest' class (Foster, 2022). 
 * 
 * Note: Using random numbers within the given range would be ideal, 
 * but this would require implementing yet another algorithm, which 
 * would itself need to be tested.
 *  
 * Thus, "manual" test cases were used, using two arrays - one storing
 * input numbers, and the other storing their expected outputs. Method 
 * 'compareOutputToExpected()' is used in a loop to compare the array 
 * values (and new values can be added to arrays for more test cases. */
public class NumberSayerTest {
	
	/* Tests input data against expected output data. Passes the data, 
	 * along with a 'NumberSayer', to 'compareOutputToExpected()' via 
	 * a loop. */
	@Test
	public void testSayNumber() {
		// Stores test input numbers.
		long[] numbers = new long[] {
				-999999999999999L, -90376000010012L, -12000013L, 
				-1033286, -20000, -1001, -165, -50, -43, -15, -14, -11,
				-5, 0, 5, 11, 14, 15, 43, 50, 165, 1001, 20000, 1033286,
				12000013L, 90376000010012L, 999999999999999L
		};
		// Stores expected output Strings.
		String[] expectedOutputs = new String[] {
				"Minus nine hundred and ninety-nine trillion, nine "
				+ "hundred and ninety-nine billion, nine hundred and "
				+ "ninety-nine million, nine hundred and ninety-nine "
				+ "thousand, nine hundred and ninety-nine.", "Minus "
				+ "ninety trillion, three hundred and seventy-six "
				+ "billion, ten thousand and twelve.", "Minus twelve"
				+ " million and thirteen.", "Minus one million, "
				+ "thirty-three thousand, two hundred and eighty-six.", 
				"Minus twenty thousand.", "Minus one thousand and one.",
				"Minus one hundred and sixty-five.", "Minus fifty.", 
				"Minus forty-three.", "Minus fifteen.", "Minus fourteen.",
				"Minus eleven.", "Minus five.", "Zero.", "Five.", 
				"Eleven.", "Fourteen.", "Fifteen.", "Forty-three.", 
				"Fifty.", "One hundred and sixty-five.", "One thousand"
				+ " and one.", "Twenty thousand.", "One million, "
				+ "thirty-three thousand, two hundred and eighty-six.",
				"Twelve million and thirteen.", "Ninety trillion, three "
				+ "hundred and seventy-six billion, ten thousand and "
				+ "twelve.", "Nine hundred and ninety-nine trillion, "
				+ "nine hundred and ninety-nine billion, nine hundred "
				+ "and ninety-nine million, nine hundred and ninety-nine "
				+ "thousand, nine hundred and ninety-nine."
		};
		NumberSayer numberSayer  = new NumberSayer();
		
		for (int i = 0; i < numbers.length; i++) {
			compareOutputToExpected(numberSayer, 
					                expectedOutputs[i], 
					                numbers[i]);
		}
	};
	
	/* Compares the output of 'sayNumber()' to the expected output in the second
	 * array, using the input values in the first array. */
	void compareOutputToExpected(NumberSayer numberSayer, 
			                     String expectedOutput, 
			                     long number) {
		assertEquals(expectedOutput, numberSayer.sayNumber(number));
		
	};

}

/////////////////////////////// THE END ////////////////////////////////

// REFERENCES:

// Foster, J.E. (2022). NumberSayer.java (Version 1). [online] Github.com. Available at: https://github.com/J-E-Foster/Hyperiondev-Take-Home-Test/tree/master/Section%20C:%20Code%20Challenge [Accessed 02 July 2022]. 


////////////////////////////////////////////////////////////////////////

