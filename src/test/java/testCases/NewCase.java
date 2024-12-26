package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HomePage;
//import pageObjects.MycasePage;
import pageObjects.LoginPage;
import pageObjects.NewCasePage;
import testBase.BaseClass;
import utilities.ConfigReader;

@Listeners(utilities.ExtentReportManager.class)
public class NewCase extends BaseClass{

    ConfigReader config = new ConfigReader();
    @Test
    public void Newcase_testCase() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        login();
        logger.info("Logged in successfully.");
        HomePage homePage = new HomePage(driver);
        homePage.clickNewcase();
        homePage.tempDataWindowNo();
        homePage.caseSiteChangePopUpYES();
        logger.info("Navigated to New Case page.");

    }
}