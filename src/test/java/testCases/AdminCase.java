package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.AdminPage;
import pageObjects.FieldSetupPage;
import pageObjects.HomePage;
//import pageObjects.MycasePage;
import pageObjects.NewCasePage;
import testBase.BaseClass;
import utilities.ConfigReader;
import org.openqa.selenium.WebDriver;

@Listeners(utilities.ExtentReportManager.class)
public class AdminCase extends BaseClass{


    ConfigReader config = new ConfigReader();
    @Test
    public void AdminCase_testCase() throws InterruptedException {

        login();
        logger.info("Logged in successfully.");
        HomePage homePage = new HomePage(driver);
        homePage.SetupIcon("Admin Console");
        AdminPage adminPage = new AdminPage(driver);
        adminPage.formconfigurationvalues("Field Setup");
        FieldSetupPage fieldSetup = new FieldSetupPage(driver);
        fieldSetup.FieldSetupvalues("Component Information");
        fieldSetup.FieldSetupvaluestable("Verbatim");
        fieldSetup.setVerbatimHideNo();
        fieldSetup.clickCloseNSave();
//		login();
//        mandatoryfields();
//        NewCasePage newcasePage = new NewCasePage(driver);
//        newcasePage.caseToYourSelfYes();
        Thread.sleep(3000);


    }
}