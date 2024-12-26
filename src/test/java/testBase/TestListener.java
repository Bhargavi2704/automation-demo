package testBase;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        // Log the start of the test
        logger.info(result.getName() + " test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log test success and add custom logic like screenshot capture if required
        logger.info(result.getName() + " PASSED");
        // You can add screenshot for passed tests as well (optional)
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log test failure and take screenshot
        logger.error(result.getName() + " FAILED");
        // Add logic to capture screenshot for failed tests (You may want to capture screenshots in BaseClass method)
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log test skipped
        logger.warn(result.getName() + " SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // You can log here if any tests fail within success percentage
        logger.info(result.getName() + " failed but within success percentage");
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
        // Initialization before all tests start
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        // Cleanup after all tests are completed
    }
}
