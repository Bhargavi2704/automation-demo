package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void LoginProcess(String optionText, String optionValue, String optionbtn) {
        String LoginXpath = "//div[@class='inputrow']//input[@type='" + optionText + "']";

        // Enter the value in the input field
        enterText(LoginXpath, optionValue);

        // Now interact with the next button
        String NextbtnXpath = "//div[@class='inputrow']//input[@type='" + optionbtn + "']";
        WebElement nextButton = driver.findElement(By.xpath(NextbtnXpath));
        clickElement(nextButton);  // Clicking the Next button
    }

}
