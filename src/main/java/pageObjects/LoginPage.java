package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"txtLoginUserID\"]")
    WebElement txtUsername;
    @FindBy(xpath = "//*[@id=\"btnLoginNext\"]")
    WebElement lckNext;
    @FindBy(xpath = "//*[@id=\"txtLoginPassword\"]")
    WebElement txtPassword;
    @FindBy(xpath = "//*[@id=\"btnLoginNext\"]")
    WebElement lckLogin;
    @FindBy(xpath = "//*[@id=\"divConfirm\"]/div")
    WebElement sesionWidow;
    @FindBy(xpath = "//a[normalize-space()='Yes']")
    WebElement lckYes;

    // Conformation auto save window elements
    @FindBy(xpath = "//div[@id='confirmPopUp']//div[@class='coformationlost']")
    WebElement tempData;
    @FindBy(xpath = "//a[@id='confirmNo']")
    WebElement autoNo;


    public void setUserName(String username) {
        enterText(txtUsername, username);  // Using BasePage's enterText method
    }

    public void clickNext() {
        clickElement(lckNext);  // Using BasePage's clickElement method
    }

    public void setPassword(String pwd) {
        enterText(txtPassword, pwd);  // Using BasePage's enterText method
    }

    public void clickLogin() {
        clickElement(lckLogin);  // Using BasePage's clickElement method
    }

    public String windowtext() {
        return sesionWidow.getText();
    }

    public void clckSessionYes() {
        try {
            boolean swin = sesionWidow.isDisplayed();
            if (swin = true) {
                lckYes.click();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
