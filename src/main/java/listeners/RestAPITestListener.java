package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class RestAPITestListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(RestAPITestListener.class);
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logger logger = LoggerFactory.getLogger(result.getInstance().getClass());
        String testName = getTestName(result);
        logger.info(testName + " : Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable t = result.getThrowable();
        String cause = "";
        if (t != null)
            cause = t.getMessage();
        logger.error(getTestName(result) + " : Test Failed : " + cause);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logger logger = LoggerFactory.getLogger(result.getInstance().getClass());
        logger.info(getTestName(result) + " : Test Skipped");
    }
}
