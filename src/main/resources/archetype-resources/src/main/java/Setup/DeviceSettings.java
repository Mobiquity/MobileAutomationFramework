package Setup;

/*Created by: Enosh Justin
  Date: 25 April 2020*/

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static Setup.TestSetup.*;


public class DeviceSettings {

    DesiredCapabilities capabilities = new DesiredCapabilities();

    private  String target = System.getProperty("target");
    public static String deviceName= System.getProperty("deviceName");
    private  String avdName= System.getProperty("avdName");

    public DesiredCapabilities capabilities() throws Exception{
        switch (target){
            case "avd":
                return emulatorCaps();
            case "androidDevice":
                return androidDeviceCaps();
            case "simulator":
                return simulatorCaps();
            case "iPhone":
                return iOSDeviceCaps();
        }
        return null;
    }


    public DesiredCapabilities androidDeviceCaps() throws Exception{
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","auto");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("appPackage", packageName);
        capabilities.setCapability("appActivity", ".view.activity.MainActivity");
        capabilities.setCapability("appWaitActivity", ".view.activity.MainActivity");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("newCommandTimeout", 120);
        capabilities.setCapability("app",System.getProperty("user.dir")+"/src/main/resources/Applications/test.apk");
        return capabilities;
    }


    private DesiredCapabilities emulatorCaps() throws Exception{
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","emulator");
        capabilities.setCapability("avd", avdName);
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("appPackage", packageName);
        capabilities.setCapability("appActivity", ".view.activity.MainActivity");
        capabilities.setCapability("appWaitActivity", ".view.activity.MainActivity");
        capabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, System.getProperty("user.dir")+"/src/main/resources/Driver/chromedriver");
        capabilities.setCapability("app",System.getProperty("user.dir")+"/src/main/resources/Applications/test.apk");
        return capabilities;
    }


    public DesiredCapabilities iOSDeviceCaps(){
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion","11.4.1");
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid","auto");
        capabilities.setCapability("usePrebuiltWDA", true);
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("newCommandTimeout", 120);
        capabilities.setCapability("xcodeConfigFile", System.getProperty("user.dir")+"/src/main/resources/appium.xcconfig");
        capabilities.setCapability("showXcodeLog", false);
        capabilities.setCapability("app",System.getProperty("user.dir")+"/src/main/resources/Applications/BPI.ipa");
        capabilities.setCapability("bundleId", packageName);
        return capabilities;
    }

    private DesiredCapabilities simulatorCaps(){
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion","11.4.1");
        capabilities.setCapability("deviceName","iPhone simulator");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("newCommandTimeout", 120);
        capabilities.setCapability("app",System.getProperty("user.dir")+"/src/main/resources/Applications/BPI.ipa");
        capabilities.setCapability("bundleId", packageName);
        return capabilities;
    }

}
