package Pages;

import Setup.TestSetup;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Utilities.TestUtils.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;


public class Homepage extends TestSetup {

    @iOSXCUITFindAll(@iOSXCUITBy(xpath="(//*[@name=\"Login\"])[1]"))
    @AndroidFindAll(@AndroidBy(id="btnName"))
    public List<WebElement> btnLoginHomepage;

    @iOSXCUITFindBy(accessibility="\uF0F3")
    @AndroidFindBy(id="icon_notification")
    public WebElement iconNotificationHomepage;

    public void verifyElementsOfHomePage(){
        waitForElement(60).until(visibilityOfAllElements(btnLoginHomepage));
        elementIsDisplayed(iconNotificationHomepage, "Notification icon");
        textVerification(btnLoginHomepage.get(0),"Login");
    }
}