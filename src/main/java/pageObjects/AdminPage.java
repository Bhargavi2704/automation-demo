package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class AdminPage extends BasePage{



    public AdminPage(WebDriver driver) {
        super(driver);
    }




    //selecting value in the admin page for the form configurations table and I will assign this optiontextvalue in testcase
    public void formconfigurationvalues(String optionText) {
        // Construct the dynamic XPath based on the input text
        String dynamicXpath = "//ul[@id='ulFC']/li/a[text()='" + optionText + "']";

        // Locate the option dynamically
        WebElement optionElement = driver.findElement(By.xpath(dynamicXpath));

        // Click the option
        optionElement.click();
    }

}