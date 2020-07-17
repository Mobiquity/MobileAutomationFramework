package Utilities;

/*Created by: Enosh Justin
  Date: 25 April 2020*/

import Reporting.ExtentManager;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Setup.TestSetup.driver;
import static Setup.TestSetup.platform;

public class TestUtils {

    private File jsonTestData;
    /*--------------------------------------------------
                Native  Methods
    --------------------------------------------------*/
    public static void elementIsDisplayed(MobileElement element, String elementName){
        try {
            if(element.isDisplayed()) {
                ExtentManager.getTest().log(LogStatus.PASS, elementName + " is Displayed");
            }
        }catch (Exception e) {
            ExtentManager.getTest().log(LogStatus.INFO, e.getMessage());
            ExtentManager.getTest().log(LogStatus.FAIL, "Could not locate: " + elementName);
        }

    }


    public static void elementIsNotDisplayed(MobileElement element, String elementName) {
        try {
            if(element.isDisplayed()) {
                ExtentManager.getTest().log(LogStatus.FAIL, elementName + " is Displayed");
            }
        }catch (Exception e) {
            ExtentManager.getTest().log(LogStatus.PASS, "element " + elementName + " is not displayed");
        }
    }


    public static void verifyButtonStatus(MobileElement element,String buttonName, boolean expectedBtnStatus) {
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
            ExtentManager.getTest().log(LogStatus.FAIL, buttonName + " is " + btnState);
        }
    }

    public static void textVerification(MobileElement element, String expectedText){
        try{
            if(element.isDisplayed()){
                if(element.getText().equals(expectedText)) {
                    ExtentManager.getTest().log(LogStatus.PASS,"Element is displayed, \n Element Text is " + "'" + element.getText() + "'");
                } else {
                    ExtentManager.getTest().log(LogStatus.FAIL, "Expected Text is " + "'" + expectedText + "'" + " but got " + "'" + element.getText() + "'" );
                }
            }
        } catch (Exception e){
            ExtentManager.getTest().log(LogStatus.INFO, e.getMessage());
            ExtentManager.getTest().log(LogStatus.FAIL, "Element is not displayed hence cannot verify text");
        }
    }


    public static void verifyElementPresent(List<MobileElement> element, String elementName)
    {
        if(element.size()>0){
            ExtentManager.getTest().log(LogStatus.PASS,elementName+" is displayed.");
        }
        else{
            ExtentManager.getTest().log(LogStatus.FAIL,elementName+" isn't displayed."+element);
        }
    }


    public static void elementNotPresent(List<MobileElement> element, String elementName){
        try {
            if (element.size()==0) {
                ExtentManager.getTest().log(LogStatus.PASS, elementName+" isn't displayed now.");
            }
        }catch (Exception e){
            ExtentManager.getTest().log(LogStatus.FAIL,elementName+" is displayed."+element);
        }
    }

    public static void clickOnButton(MobileElement element, String buttonName) {
        try {
            element.click();
            ExtentManager.getTest().log(LogStatus.PASS,"Click on "+ buttonName);
        } catch (Exception e) {
            try {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", element);
                ExtentManager.getTest().log(LogStatus.PASS,"Tap on "+ buttonName);
            } catch (Exception e2) {
                e.printStackTrace();
                ExtentManager.getTest().log(LogStatus.FAIL,"Unable to tap "+buttonName);
            }
        }
    }

    public static void enterText(MobileElement element, String textToEnter, String fieldName){
        element.clear();
        if(platform.equalsIgnoreCase("android")){
            element.clear();
        } else {
            element.sendKeys(Keys.BACK_SPACE);
        }
        element.sendKeys(textToEnter);
        ExtentManager.printLogs("Entered "+ textToEnter + " in " + fieldName);
    }

    public static void isElementSelected(MobileElement element, String fieldName, Boolean expectedStatus){
        Boolean elementStatus = element.isSelected();
        if(elementStatus.equals(expectedStatus)){
            ExtentManager.getTest().log(LogStatus.PASS, fieldName + "  selection status is " + expectedStatus);
        } else {
            ExtentManager.getTest().log(LogStatus.FAIL, fieldName + " expected status is " + expectedStatus + " ,But got " + elementStatus);
        }
    }


    public static void waitForMobileElement(int seconds, MobileElement element){
        FluentWait<AppiumDriver<?>> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void fluentWait(int seconds){
        FluentWait<AppiumDriver<?>> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(seconds)).pollingEvery(Duration.ofSeconds(2));
    }

    public static void iOSScroll(String scrollView, String direction){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<>();
        MobileElement element = (MobileElement) driver.findElement(By.className(scrollView));
        params.put("direction", direction);
        params.put("element", ((MobileElement) element).getId());
        js.executeScript("mobile: swipe", params);
    }

    public static void androidScroll(String scrollableList, String textToScroll) {
        ((AndroidDriver<?>)driver).findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\""+scrollableList+"\")).scrollIntoView("
                        + "new UiSelector().textContains(\""+textToScroll+"\").instance(0))"));
    }

    /*-------------------------------------------------
                    Web  Methods
    --------------------------------------------------*/

    public static void webElementIsDisplayed(WebElement element, String elementName){
        try {
            if(element.isDisplayed()) {
                ExtentManager.getTest().log(LogStatus.PASS, elementName + " is Displayed");
            }
        }catch (Exception e) {
            ExtentManager.getTest().log(LogStatus.INFO, e.getMessage());
            ExtentManager.getTest().log(LogStatus.FAIL, "Could not locate: " + elementName);
        }
    }

    public static void webTextVerification(WebElement element, String expectedText){
        try{
            if(element.isDisplayed()){
                if(element.getText().equals(expectedText)) {
                    ExtentManager.getTest().log(LogStatus.PASS,"Element is displayed, \nElement Text is " + "'" + element.getText() + "'");
                } else {
                    ExtentManager.getTest().log(LogStatus.FAIL, "Expected Text is " + "'" + expectedText + "'" + " but got " + "'" + element.getText() + "'" );
                }
            }
        } catch (NoSuchElementException e){
            ExtentManager.getTest().log(LogStatus.INFO, e.getMessage());
            ExtentManager.getTest().log(LogStatus.FAIL,"Element is not displayed hence cannot verify text");
        }
    }

    public static void webInputTextVerification(WebElement element, String attribute, String expectedText) {
        String elementText = element.getAttribute(attribute);
        try {
            if (element.isDisplayed()) {
                if (elementText.equals(expectedText)) {
                    ExtentManager.getTest().log(LogStatus.PASS, "Element is displayed, Element Text is " + "'" + elementText + "'");
                } else {
                    ExtentManager.getTest().log(LogStatus.FAIL, "Expected Text is " + "'" + expectedText + "'" + " but got " + "'" + elementText + "'");
                }
            }
        } catch (NoSuchElementException e){
            ExtentManager.getTest().log(LogStatus.INFO, e.getMessage());
            ExtentManager.getTest().log(LogStatus.FAIL,"Element is not displayed hence cannot verify text");
        }
    }


    public static void webVerifyButtonStatus(WebElement element,String buttonName, Boolean isEnabled) {
        Boolean buttonStatus = element.isEnabled();
        String btnState;
        if(buttonStatus.equals(true)){
            btnState = "enabled";
        } else {
            btnState = "disabled";
        }
        if(buttonStatus.equals(isEnabled)){
            ExtentManager.getTest().log(LogStatus.PASS, buttonName + " is " + btnState);
        } else {
            ExtentManager.getTest().log(LogStatus.FAIL, buttonName + " is " + btnState);
        }
    }

    public static void webClickOnButton(WebElement element, String buttonName) {
        try {
            element.click();
            ExtentManager.getTest().log(LogStatus.PASS,"Tap on "+ buttonName);
        } catch (Exception e) {
            try {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", element);
                ExtentManager.getTest().log(LogStatus.PASS, "Tap on " + buttonName);
            } catch (Exception e2) {
                e.printStackTrace();
                ExtentManager.getTest().log(LogStatus.FAIL, "Unable to tap on " + buttonName);
            }
        }
    }


    public static void webFieldEnterText(WebElement element, String textToEnter, String fieldName){
        element.clear();
        element.sendKeys(textToEnter);
        driver.hideKeyboard();
        ExtentManager.printLogs("Entered "+ textToEnter + " in " + fieldName);
    }

    public static void isWebElementSelected(WebElement element, String fieldName, Boolean expectedStatus){
        Boolean elementStatus = element.isSelected();
        if(elementStatus.equals(expectedStatus)){
            ExtentManager.getTest().log(LogStatus.PASS, fieldName + "  selection status is " + expectedStatus);
        } else {
            ExtentManager.getTest().log(LogStatus.FAIL, fieldName + " expected status is " + expectedStatus + " ,But got " + elementStatus);
        }
    }

    public static void waitForWebElement(int seconds, WebElement element){
        FluentWait<AppiumDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        //Initially bellow given if condition will check ready state of page.
        if (js.executeScript("return document.readyState").toString().equals("complete")){
            return;
        }
        //This loop will rotate for 25 times to check If page Is ready after every 1 second.
        //You can replace your value with 25 If you wants to Increase or decrease wait time.
        for (int i=0; i<60; i++){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {}
            //To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")){
                break;
            }
        }
    }

    public static void scrollIntoView(WebElement element) throws Exception{
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

    public static void webElementIsNotDisplayed(WebElement element, String elementName){
        try {
            if(element.isDisplayed()) {
                ExtentManager.getTest().log(LogStatus.FAIL, elementName + " is Displayed");
            }
        }catch (Exception e) {
            ExtentManager.getTest().log(LogStatus.PASS, "element " + elementName + " is not displayed");
        }
    }
}

