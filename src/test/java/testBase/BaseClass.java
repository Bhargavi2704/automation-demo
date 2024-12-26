package testBase;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

import pageObjects.LoginPage;
import utilities.ConfigReader;

public class BaseClass {

    // Declare WebDriver and Logger as static
    public static WebDriver driver;
    public static Logger logger;

    // List to store references of ChromeDriver instances
    private static List<ChromeDriver> chromeDrivers = new ArrayList<>();

    // Initialize ConfigReader
    static ConfigReader config = new ConfigReader();

    // Setup method with @BeforeClass for initializing WebDriver and Logger
    @BeforeClass
    public static void setup() {
        // Initialize the logger
        logger = LogManager.getLogger(BaseClass.class);

        // Get the URL from the config file
        String url = config.getProperty("url");

        // Initialize the ChromeDriver and add it to the list of controlled browsers
        driver = new ChromeDriver();
        chromeDrivers.add((ChromeDriver) driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait
        driver.get(url);  // Open the URL
        driver.manage().window().maximize();  // Maximize the window
    }

    public void login() throws InterruptedException {
        String username = config.getProperty("username");
        String password = config.getProperty("password");
        LoginPage login = new LoginPage(driver);
        login.setUserName(username);
        login.clickNext();
        login.setPassword(password);
        login.clickLogin();
        login.clckSessionYes();
        Thread.sleep(100);
//        takeScreenshot("Login Page");
    }


    // Method to capture screenshots during test execution
    public static String captureScreen(String tname) {
        if (driver == null) {
            System.err.println("Driver is not initialized");
            return null;
        }

        // Generate a timestamp for screenshot filename
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // Check if driver can take a screenshot
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

            // Create a directory for screenshots if it doesn't exist
            File screenshotsDir = new File(System.getProperty("user.dir") + File.separator + "screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            // Define the target filepath for the screenshot
            String targetFilepath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";
            File targetFile = new File(targetFilepath);

            try {
                // Copy the screenshot to the target location
                FileHandler.copy(sourceFile, targetFile);
                System.out.println("Screenshot saved to path: " + targetFilepath);
                return targetFilepath;
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to save screenshot to path: " + targetFilepath);
                return null;
            }
        } else {
            System.err.println("Driver does not support screenshot capture");
            return null;
        }
    }

    // Teardown method with @AfterClass to quit the driver after tests
//    @AfterClass
//    public static void teardown() {
//        try {
//            if (driver != null) {
//                driver.quit();  // Close the driver
//                logger.info("Automation Chrome driver has been closed.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
