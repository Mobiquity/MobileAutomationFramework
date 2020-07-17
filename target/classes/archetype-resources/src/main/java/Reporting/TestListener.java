package Reporting;

import Utilities.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener extends Setup.TestSetup implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getDescription();
    }

    //Before starting all tests, below method runs.
    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("AppiumDriver", driver);
        Log.startLog(iTestContext.getClass().getName());
    }

    //After ending all tests, below method runs.
    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.endLog();
        //Do tier down operations for extentreports reporting!
        Reporting.ExtentManager.endTest();
        Reporting.ExtentManager.getReporter().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestMethodName((iTestResult)));
        //Start operation for extentreports.
        Reporting.ExtentManager.startTest(iTestResult.getMethod().getDescription(),"");
    }


    @Override
    public void onTestSuccess(ITestResult iTestResult) {

            Log.info("Testcase " +  getTestMethodName(iTestResult) + " passed");
            Reporting.ExtentManager.getTest().log(LogStatus.PASS, "Test passed");

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            Log.info("Testcase for " + getTestMethodName(iTestResult) + " failed.");

            //Get driver from BaseTest and assign to local webdriver variable.
            Object testClass = iTestResult.getInstance();

            //driver = ((Setup.TestSetup) testClass).initiateDriver();
            //Take base64Screenshot screenshot.
            String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)driver).
                    getScreenshotAs(OutputType.BASE64);

            //Extentreports log and screenshot operations for failed tests.
            Reporting.ExtentManager.getTest().log(LogStatus.FAIL,"Test Failed",
                    Reporting.ExtentManager.getTest().addBase64ScreenShot(base64Screenshot));
            Reporting.ExtentManager.getTest().log(LogStatus.INFO,iTestResult.getThrowable().getMessage());
        } catch (Exception e){
            System.out.println("Something Went Wrong" + e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        //Extentreports log operation for skipped tests.
        Reporting.ExtentManager.getTest().log(LogStatus.SKIP, iTestResult.getMethod().getDescription() + "skipped");
        Log.info(iTestResult.getName() + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Testcase " +  getTestMethodName(iTestResult) + " failed but it is in defined success ratio");
    }

}