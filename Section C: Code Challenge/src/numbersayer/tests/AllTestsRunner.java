package numbersayer.tests;

/* Instead of importing from 'runner' ('.JUnitCore', '.Result', and
 * .notification.Failure') we import from the 'launcher' API (Bechtold
 * et al, 2021 & Kolesnikov, 2018).*/
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.launcher.listeners.TestExecutionSummary.Failure;

/* This is the test runner class for the 'AllTests' suite. It can be run
 * from a command terminal without having to use Eclipse (Dimsta, 2013). */
public class AllTestsRunner {
	
	/* Instead of using 'JUnitCore.runClasses()', and the 'Failure' and 
	 * 'Result' classes, to run all tests in the suit, we use 'launcher'
	 * API. Here, the 'TestExecutionSummary' object basically replaces 
	 * the 'Result' object (Bechtold et al, 2021 & Kolesnikov, 2018). */
	public static void main(String args[]) {
		/* This is a request to execute tests from the 'AllTests' suite
		 * (Bechtold et al, 2021). */  
		LauncherDiscoveryRequest request = 
		LauncherDiscoveryRequestBuilder.request()
			.selectors(selectClass(AllTests.class))
			.build();
		
		/* This listener is used to retrieve the 'TestExecutionSummary' 
		 * object, which contains the test results (Bechtold et al, 2021). */ 
		SummaryGeneratingListener listener = new 
		SummaryGeneratingListener();
		
		/* The 'Laucher' registers the listener and executes the request.
		 * (Bechtold et al, 2021). */
		Launcher launcher = LauncherFactory.create();
		launcher.registerTestExecutionListeners(listener);
		launcher.execute(request);
		
		// This is a summary of the test results (Bechtold et al, 2021).
		TestExecutionSummary summary = listener.getSummary();
		long testFoundCount = summary.getTestsFoundCount();
		long testPassedCount = summary.getTestsSucceededCount();
		long testFailedCount = summary.getTestsFailedCount();
		
		// Prints out all tests results.
		System.out.println("Total tests executed: " + testFoundCount);
		System.out.println("Total tests passed: " + testPassedCount);
		System.out.println("Total tests failed: " + testFailedCount);
		System.out.println();
		
		// Gets list of failures from summary (JUnit.org, 2022).
	    for (Failure failure : summary.getFailures()) {
	    	System.out.println("FAILED CASE:");
	    	/*Gets the 'Throwable' causing the failure (JUnit.org, 2022)*,
	    	 * and prints its error message (Docs.oracle.com, 2022). */
			 System.out.println(failure.getException().getMessage()); 
			 System.out.println();
		};
	}
		
}

////////////////////////////THE END ////////////////////////////////////

/* JUnit4:
 * 
 * import org.junit.runner.JUnitCore;
 * import org.junit.runner.Result;
 * import org.junit.runner.notification.Failure;
 * 
 * public static void main(String[] args) {
 *		Result result = JUnitCore.runClasses(AllTests.class);
 * 		
 *		for (Failure failure : result.getFailures()) {
 *			System.out.println(failure.toString());
 *		}
 *		if (result.wasSuccessful()) {
 *			System.out.println("All tests passed successfully!");
 *		}
 *	};
 * 
 */

////////////////////////////////////////////////////////////////////////

// REFERENCES:

// Bechtold, S. et al. (2021) JUnit5 User Guide, Chapter 6. [online] Junit.org. Available at: https://junit.org/junit5/docs/current/user-guide/#launcher-api [Accessed 03 July 2022].
// Dimsta, K. (2013). JUnit Suite Test Example. [online] Examples.javacodegeeks.com. Available at: https://examples.javacodegeeks.com/core-java/junit/junit-suite-test-example/ [Accessed 03 July 2022].
// Docs.oracle.com (2022). Class Throwable. [online] Available at: https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html?is-external=true [Accessed 03 July 2022].
// JUnit.org (2022). Interface TestExecutionSummary. [online] Available at: https://junit.org/junit5/docs/5.0.1/api/org/junit/platform/launcher/listeners/TestExecutionSummary.html [Accessed 03 July 2022].
// JUnit.org (2022)*. Interface TestExecutionSummary.Failure. [online] Available at: https://junit.org/junit5/docs/5.0.1/api/org/junit/platform/launcher/listeners/TestExecutionSummary.Failure.html [Accessed 03 July 2022].
// Kolesnikov, V. (2018). What's the equivalent of org.junit.runner.JUnitCore.runClasses in Junit 5? [online] Stackoverflow.com. Available at: https://stackoverflow.com/questions/39111501/whats-the-equivalent-of-org-junit-runner-junitcore-runclasses-in-junit-5 [Accessed 03 July 2022].
// Tutorialspoint.com (2022). JUnit - Suite Test. [online] Available at: https://www.tutorialspoint.com/junit/junit_suite_test. [Accessed 03 July 2022].


////////////////////////////////////////////////////////////////////////
