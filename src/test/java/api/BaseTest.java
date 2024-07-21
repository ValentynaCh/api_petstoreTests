package api;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import listeners.RestAPITestListener;
import utils.DatabaseDumpGenerator;


@Listeners(RestAPITestListener.class)
public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public void generateDatabase() {
        Class<DatabaseDumpGenerator> clazz = DatabaseDumpGenerator.class;
    }

    @AfterSuite
    public void printReport(ITestContext context) {
        int total = context.getAllTestMethods().length;
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();

        logger.info("Total number of test cases: " + total);
        logger.info("Number of test cases Passed: " + passed);
        logger.info("Number of test cases Failed: " + failed);
        logger.info("Number of test cases Skipped: " + skipped);
    }
}
