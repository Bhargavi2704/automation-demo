package pageObjects;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;



public class FieldSetupPage extends BasePage {

    Logger logger = LogManager.getLogger(this.getClass());

    public FieldSetupPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[normalize-space()='Component Information']")
    private WebElement lckComponentInfo;

    @FindBy(xpath = "//body[1]/form[1]/div[3]/div[21]/div[2]/aside[2]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[7]/td[1]")
    private WebElement verbatim;

    @FindBy(xpath = "//select[@id='ddlHideYes']")
    WebElement hideVerbtim;

    @FindBy(xpath = "//a[@id='btnSaveFields']")
    private WebElement lckCloseNSave;

    //selecting values from side bar i.e; example case information,references, case notes, component information in the field set up page
    public void FieldSetupvalues(String componentInfo) {
        // Construct the dynamic XPath based on the input component information
        String dynamicXpath = "//*[@id='main']/div[21]/div[2]/aside[1]/table/tbody/tr/td/a[text()='" + componentInfo + "']";

        try {
            // Use the already initialized wait instance from BasePage
            WebElement componentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));

            // Click the element
            componentElement.click();
            logger.info(componentElement + "is clicked");
        } catch (NoSuchElementException e) {
            System.out.println("Component information not found: " + componentInfo);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while selecting the component information.");
            e.printStackTrace();
        }


    }

    //selecting values after selecting value from side bar then another table with values is displayed
    public void FieldSetupvaluestable(String text) {
        try {
            // Construct dynamic XPath based on the text value you want to search for
            String rowXpath = "//*[@id='tabFieldSetup']/tbody/tr[td[text()='" + text + "']]";  // Locate the row containing the text

            // Now locate the pencil icon inside the corresponding row
            String pencilIconXpath = rowXpath + "/td/a[1]/img";  // Construct XPath for the pencil icon in the same row

            // Wait for the row to be visible
            WebElement rowElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rowXpath)));

            // Hover over the row element to make sure the pencil icon becomes visible
            Actions actions = new Actions(driver);
            actions.moveToElement(rowElement).perform();
            logger.info("Hovered over the" + rowElement);
            // Now wait for the pencil icon to be clickable and click it
            WebElement pencilIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pencilIconXpath)));
            pencilIcon.click();
//		        System.out.println("Pencil icon clicked successfully.");
            logger.info("Hovered over the" + rowElement + "and" + pencilIcon + "is clicked for that" + rowElement  );
        } catch (NoSuchElementException e) {
            System.out.println("No element found with the text: " + text);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while interacting with the element.");
            e.printStackTrace();
        }
    }

    //checking verbatim field value whether it is no or not if it is not no then this method will set it to NO.
    public String setVerbatimHideNo() {
        try {
            wait.until(ExpectedConditions.visibilityOf(hideVerbtim));

            // Check the current value
            Select select = new Select(hideVerbtim);
            String currentValue = select.getFirstSelectedOption().getText();

            if (currentValue.equalsIgnoreCase("No")) {
                // Click 'Cancel' if already set to 'No'
                driver.findElement(By.xpath("//a[@onclick='ResetFieldSetupData()']")).click();
                logger.info("The 'Hide Verbatim' is already set to 'No'. Clicked the 'Cancel' button.");
            } else {
                // Set to 'No'
                select.selectByValue("No");
                logger.info("Successfully set 'Hide Verbatim' to 'No'.");
            }
            return currentValue;
        } catch (Exception e) {
            logger.error("Failed to set or validate 'Hide Verbatim': " + e.getMessage());
            throw e; // Rethrow exception if necessary
        }
    }


    public boolean clickCloseNSave() {
        try {
            // Wait for the 'Close & Save' button to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(lckCloseNSave)).click();
            logger.info("Successfully clicked on 'Close & Save'.");

            return true; // Indicating success
        } catch (Exception e) {
            logger.error("Failed to click on 'Close & Save': " + e.getMessage());
            return false; // Indicating failure
        }
    }

}