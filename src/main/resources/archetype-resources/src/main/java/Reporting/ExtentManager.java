package Reporting;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ExtentManager {
    public static ExtentReports extent;
    public static ExtentTest test;

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

    public synchronized static ExtentReports getReporter(){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        if(extent == null){
            extent =new ExtentReports(System.getProperty("user.dir")+"/Reports/"+"TestResults_"+timeStamp+".html");
        }
        return extent;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized void endTest() {
        getReporter().endTest(extentTestMap.get((int) Thread.currentThread().getId()));
    }


    public static synchronized void startTest(String testName, String desc) {
        test = getReporter().startTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
    }


    public static synchronized void printLogs(String logs){
        getTest().log(LogStatus.INFO,logs);
    }

}
