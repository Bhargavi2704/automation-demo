package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;

//import ch.qos.logback.classic.Level;

public class NewCasePage extends BasePage {

    Logger logger = LogManager.getLogger(this.getClass());
    public NewCasePage(WebDriver driver) {
        super(driver);
    }

    //using below method we can send value to first name, middle name, last name,suffix etc. text area kind of fields
    public void TextareaField(String fieldName, String inputText) {
        try {
            // Construct the dynamic XPath using the field name
            String fieldXPath = "//div[@class='reporterinformationform']//div[@class='reportcolumn-one']//input[@name='" + fieldName + "']";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fieldXPath)));
            // Locate the element using the dynamic XPath
            WebElement inputField = driver.findElement(By.xpath(fieldXPath));

            // Perform the sendKeys action to enter the text
//            inputField.sendKeys(inputText);
            // Performed the entertext method which we called in basepage for sending value in text area fields
            enterText(inputField,inputText);

            // Log success
            logger.info("Successfully entered text '" + inputText + "' in the field with name '" + fieldName + "'.");
        } catch (NoSuchElementException e) {
            // Handle case where the element is not found
            logger.error("The field with name '" + fieldName + "' was not found on the page.");
            throw new RuntimeException("Field not found: " + fieldName, e);
        } catch (Exception e) {
            // Handle any other exceptions
            logger.error("An error occurred while entering text in the field with name '" + fieldName + "': " + e.getMessage());
            throw new RuntimeException("Failed to enter text in field: " + fieldName, e);
        }
    }

}