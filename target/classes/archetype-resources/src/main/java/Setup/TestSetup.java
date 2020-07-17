package Setup;

import Utilities.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import java.io.*;
import java.net.ServerSocket;
import java.net.URL;


public class TestSetup {

    public static AppiumDriver<?> driver;
    public static String platform = System.getProperty("platform");
    public static String env=System.getProperty("env");
    public String testDataType = System.getProperty("testDataType");
    public static File jsonTestData;
    public static File jsonExpectedResults;
    public Assertion hardAssert = new Assertion();
    public SoftAssert softAssert = new SoftAssert();
    public static String packageName="<packageName>";
    private AppiumDriverLocalService service;

    public TestSetup()
    {
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    private void setupAppiumServer(){
        AppiumServiceBuilder builder;
        DesiredCapabilities cap;
        //Set Capabilities
        cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");

        //Build the Appium service
        builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        Log.info("Appium Server started...");
    }


    private boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    private void initJsonTestData(){
        if (testDataType.equalsIgnoreCase("real")) {
            jsonTestData = new File(System.getProperty("user.dir")+"//src//test//java//Testdata//staticData.json");
        } else {
            jsonTestData = new File(System.getProperty("user.dir")+"//src//test//java//Testdata//staticMockData.json");
        }
        jsonExpectedResults = new File(System.getProperty("user.dir")+"//src//test//java//Testdata//expectedResults.json");
    }


    public void initiateDriver() throws Exception {
        if (driver != null) {
            driver.launchApp();
            System.out.println("Driver already exits!");
        } else {
            String serverUrl = "http://127.0.0.1:4723/wd/hub";
            DeviceSettings deviceCaps  = new DeviceSettings();
            if (platform.equalsIgnoreCase("iOS")) {
                driver = new IOSDriver<>(new URL(serverUrl), deviceCaps.capabilities());
            } else {
                driver = new AndroidDriver<>(new URL(serverUrl), deviceCaps.capabilities());
            }
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void startServer(){
        int port = 4723;
        if(!checkIfServerIsRunnning(port)) {
            setupAppiumServer();
        }
    }

    @BeforeClass(alwaysRun = true)
    public void initialize() throws Exception{
        initJsonTestData();
        initiateDriver();
        Log.startLog(this.getClass().getSimpleName());
    }

    @AfterClass(alwaysRun = true)
    public void terminateApp() {
        driver.closeApp();
        Log.endLog();
    }

    @AfterSuite(alwaysRun = true)
    public void TeardownTest() throws Exception{
        driver.quit();
        service.stop();
        Process pid = Runtime.getRuntime().exec("ps -A | grep appium | awk '{print $1}'");
        if(pid != null){
            Runtime.getRuntime().exec("pkill " + pid);
        }

    }
}
