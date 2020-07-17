package Utilities;

import Reporting.ExtentManager;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static Setup.TestSetup.driver;

public class TestUtils {

    private File jsonTestData;

    public static void elementIsDisplayed(WebElement element, String elementName){
        try {
            if(element.isDisplayed()) {
                ExtentManager.getTest().log(LogStatus.PASS, elementName + " is Displayed");
            }
        }catch (NoSuchElementException e) {
            ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
            ExtentManager.getTest().log(LogStatus.FAIL, e.getMessage());
        }
    }


    public static void elementIsNotDisplayed(WebElement element, String elementName) {
        try {
            if(element.isDisplayed()) {
                ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
                ExtentManager.getTest().log(LogStatus.FAIL, elementName + " is Displayed");
            }
        }catch (Exception e) {
            ExtentManager.getTest().log(LogStatus.PASS, "element " + elementName + " is not displayed");
        }
    }


    public static void verifyButtonStatus(WebElement element,String buttonName, boolean expectedBtnStatus) {
        try{
            Boolean buttonStatus = element.isEnabled();
            String btnState;
            if(buttonStatus.equals(true)){
                btnState = "enabled";
            } else {
                btnState = "disabled";
            }
            if(buttonStatus.equals(expectedBtnStatus)){
                ExtentManager.getTest().log(LogStatus.PASS, buttonName + " is " + btnState);
            } else {
                ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
                ExtentManager.getTest().log(LogStatus.FAIL, buttonName + " is " + btnState);
            }
        } catch (Exception e){
            ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
            ExtentManager.getTest().log(LogStatus.FAIL, e.getMessage());
        }
    }

    public static void textVerification(WebElement element, String expectedText){
        try{
            if(element.isDisplayed()){
                if(StringUtils.normalizeSpace(element.getText()).equals(expectedText)) {
                    ExtentManager.getTest().log(LogStatus.PASS,"Element is displayed, \n Element Text is " + "'" + element.getText() + "'");
                } else {
                    ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
                    ExtentManager.getTest().log(LogStatus.FAIL, "Expected Text is " + "'" + expectedText + "'" + " but got " + "'" + element.getText() + "'" );
                }
            }
        } catch (NoSuchElementException e){
            ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
            ExtentManager.getTest().log(LogStatus.FAIL, e.getMessage());
        }
    }


    public static void verifyElementPresent(List<WebElement> element, String elementName) {
        try{
            if(element.size()>0){
                ExtentManager.getTest().log(LogStatus.PASS,elementName+" is displayed.");
            }
        } catch (Exception e){
            ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
            ExtentManager.getTest().log(LogStatus.FAIL,elementName+" isn't displayed."+element);
        }
    }


    public static void elementNotPresent(List<WebElement> element, String elementName){
        try {
            if (element.size()==0) {
                ExtentManager.getTest().log(LogStatus.PASS, elementName+" isn't displayed now.");
            }
        }catch (Exception e){
            ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
            ExtentManager.getTest().log(LogStatus.FAIL,elementName+" is displayed."+element);
        }
    }

    public static void clickOnButton(WebElement element, String buttonName) {
        try {
            element.click();
            ExtentManager.getTest().log(LogStatus.PASS, "Tap on " + buttonName);
        } catch (Exception e) {
            try {
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
                ExtentManager.getTest().log(LogStatus.PASS, "Tap on " + buttonName);
            } catch (Exception e2) {
                ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
                ExtentManager.getTest().log(LogStatus.FAIL, e2.getMessage());
            }
        }
    }

    public static void enterText(WebElement element, String textToEnter, String fieldName){
        try{
            element.clear();
            element.sendKeys(textToEnter);
            driver.hideKeyboard();
            ExtentManager.getTest().log(LogStatus.PASS, "Entered "+ textToEnter + " in " + fieldName);
        } catch (Exception e2){
            ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
            ExtentManager.getTest().log(LogStatus.FAIL, e2.getMessage());
        }
    }

    public static void isElementSelected(WebElement element, String fieldName, Boolean expectedStatus){
        Boolean elementStatus = element.isSelected();
        if(elementStatus.equals(expectedStatus)){
            ExtentManager.getTest().log(LogStatus.PASS, fieldName + "  selection Status is " + expectedStatus);
        } else {
            ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
            ExtentManager.getTest().log(LogStatus.FAIL, fieldName + " expected Status is " + expectedStatus + " ,But got " + elementStatus);
        }
    }

    public static FluentWait<? extends  AppiumDriver<?>> waitForElement(int timeout){
        FluentWait<? extends AppiumDriver<?>> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);
        return wait;
    }

    public static void waitForAbsence(int timeout, WebElement element){
        try{
            FluentWait<? extends AppiumDriver<?>> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofSeconds(3));
            wait.until((Function<AppiumDriver<?>, Boolean>) driver -> !element.isDisplayed());
        }catch (Exception ignored){}
    }

    public static FluentWait<? extends  AppiumDriver<?>> fluentWait(int timeout){
        FluentWait<? extends AppiumDriver<?>> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(3));
        return wait;
    }

    public static void implicitWait(int timeout){
        ((WebDriver)driver).manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public static void iOSScroll(String direction){
        try{
            Map<String, Object> params = new HashMap<>();
            MobileElement element = (MobileElement) driver.findElement(By.className("XCUIElementTypeTable"));
            params.put("direction", direction);
            params.put("element", element.getId());
            ((JavascriptExecutor)driver).executeScript("mobile: swipe", params);
        } catch (Exception e){
            ExtentManager.printLogs("Something went wrong " + e);
        }
    }

    public static void androidScroll(String scrollableList, String textToScroll) {
        try{
            driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().resourceId(\""+scrollableList+"\")).scrollIntoView("
                            + "new UiSelector().textContains(\""+textToScroll+"\").instance(0))"));
        } catch (Exception e){
            ExtentManager.printLogs("Something went wrong " + e);
        }
    }

    public static void scrollIntoView(WebElement element) {
        try{
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            implicitWait(5);
            ExtentManager.getTest().log(LogStatus.PASS, "scrolled to element");
        } catch (Exception e){
            ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
            ExtentManager.getTest().log(LogStatus.FAIL, e.getMessage());
        }
    }

    public static void inputTextVerification(WebElement element, String attribute, String expectedText) {
        try {
            String elementText = element.getAttribute(attribute);
            if (!elementText.isEmpty()) {
                if (elementText.equals(expectedText)) {
                    ExtentManager.getTest().log(LogStatus.PASS, "Element is displayed, Element Text is " + "'" + elementText + "'");
                } else {
                    ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
                    ExtentManager.getTest().log(LogStatus.FAIL, "Expected Text is " + "'" + expectedText + "'" + " but got " + "'" + elementText + "'");
                }
            }
        } catch (NoSuchElementException e){
            ExtentManager.printLogs(ExtentManager.getTest().addBase64ScreenShot("data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).toString());
            ExtentManager.getTest().log(LogStatus.FAIL,e.getMessage());
        }
    }
}

