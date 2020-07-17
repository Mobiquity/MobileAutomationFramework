package Tests;

import Setup.TestSetup;
import org.testng.annotations.Test;

import static Interfaces.ClassObjects.homepage;
import static Utilities.TestUtils.clickOnButton;


public class TestHomepage extends TestSetup {


    @Test(description = "Verify homepage scenarios", groups={"homepage","regression"})
    public void verifyAppIntroductionPages(){
        homepage.verifyElementsOfHomePage();
        clickOnButton(homepage.btnLoginHomepage.get(0),"Homepage Login button");
    }
}