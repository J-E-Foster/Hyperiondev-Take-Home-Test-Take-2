package numbersayer.tests;

/* Imports from the suite API (Alex T, 2021 & Gupta, 2022) instead of 
 * importing 'runner.RunWith' and 'runners.Suite' as we would do in 
 * JUnit4 (Dimsta, 2013 & Tuturialspoint.com 2022). */
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/* This is the test suite for all tests relating to the 'numberSayer' 
 * package (i.e. 'MainTest()' and 'NumberSayerTest()'). Its runner can
 * be found in the 'AllTestsRunner' class. 
 * 
/* '@Suite' and '@SelectClasses' replace the usual '@RunWith' and 
 * '@SuiteClasses' annotations of JUnit4 (Alex T, 2021 & Gupta, 2022). */
@Suite
@SelectClasses({ 
	MainTest.class, 
	NumberSayerTest.class 
})

/* This method should be kept empty - it is only a holder for the above
 * annotations (Junit team, 2017). */
public class AllTests {	
}

//////////////////////////// THE END ////////////////////////////////////

/* JUnit4:
 * 
 * import org.junit.runner.RunWith;
 * import org.junit.runners.Suite;
 * import org.junit.runners.Suite.SuiteClasses;
 * 
 * @RunWith(Suite.class)
 * @SuiteClasses({ 
 * MainTest.class, 
 * NumberSayerTest.class 
 * })
 * 
 */

////////////////////////////////////////////////////////////////////////

// REFERENCES:

// Alex T (2021). How to write a test suite with junit 5 for eclipse? [online] Stackoverflow.com. Available at: https://stackoverflow.com/questions/66022322/how-to-write-a-test-suite-with-junit-5-for-eclipse [Accessed 03 July 2022]. 
// Dimsta, K. (2013). JUnit Suite Test Example. [online] Examples.javacodegeeks.com. Available at: https://examples.javacodegeeks.com/core-java/junit/junit-suite-test-example/ [Accessed 03 July 2022].
// Gupta, L. (2022). JUnit 5 Test Suites. [online] Howtodoinjava.com. Available at: https://howtodoinjava.com/junit5/junit5-test-suites-examples/ [Accessed 03 July 2022].
// Junit-team (2017). Aggregating tests in suites. [online] Github.com. Available at: https://github.com/junit-team/junit4/wiki/Aggregating-tests-in-suites [Accessed 03 July 2022]. 
// Tutorialspoint.com (2022). JUnit - Suite Test. [online] Available at: https://www.tutorialspoint.com/junit/junit_suite_test. [Accessed 03 July 2022].


////////////////////////////////////////////////////////////////////////
