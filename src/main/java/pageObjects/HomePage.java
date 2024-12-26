package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"NC\"]")
    WebElement lckNewCase;
    @FindBy(xpath = "//*[@id=\"confirmPopUp\"]/div")
    WebElement autosaveTempDataWindowNo;
    @FindBy(xpath = "//div[@id='CaseSiteChangePopup']//div[@class='popup-inner']")
    WebElement caseSitePopup;
    @FindBy(xpath = "//a[@onclick='ValidateNewCaseSiteAndOpenCaseForm()']")
    WebElement caseSitePopupYES;

    public void clickNewcase() {
        clickElement(lckNewCase);
    }

    public void tempDataWindowNo() {
        try {
            boolean tempWin = autosaveTempDataWindowNo.isDisplayed();
            if(tempWin = true) {
                driver.findElement(By.xpath("//*[@id=\"confirmNo\"]")).click();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void caseSiteChangePopUpYES() {
        try {
            boolean casesite = caseSitePopup.isDisplayed();
            if (casesite = true) {
                caseSitePopupYES.click();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

