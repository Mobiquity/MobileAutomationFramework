package Pages;

/*Created by: Enosh Justin
  Date: 25 April 2020*/

import Setup.TestSetup;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static Utilities.TestUtils.*;


public class Homepage extends TestSetup {

    @WithTimeout(time = 30,chronoUnit = ChronoUnit.SECONDS)
    @iOSXCUITFindAll(@iOSXCUITBy(xpath="(//*[@name=\"Login\"])[1]"))
    @AndroidFindAll(@AndroidBy(id="btnName"))
    public List<MobileElement> btnLoginHomepage;

    @WithTimeout(time = 30,chronoUnit = ChronoUnit.SECONDS)
    @iOSXCUITFindBy(accessibility="\uF0F3")
    @AndroidFindBy(id="icon_notification")
    public MobileElement iconNotificationHomepage;

    public void verifyElementsOfHomePage(){
        waitForMobileElement(30,btnLoginHomepage.get(0));
        elementIsDisplayed(iconNotificationHomepage, "Notification icon");
        textVerification(btnLoginHomepage.get(0),"Login");
    }
}