package testBase;

import org.apache.commons.io.FileUtils;
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
    }

    // Method to clear screenshots folder before new test execution
    public void clearScreenshotsFolder() {
        File folder = new File("./screenshots");
        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isFile()) {
                    file.delete(); // Delete each file
                }
            }
            logger.info("Screenshots folder cleared.");
        } else {
            folder.mkdirs(); // Create the folder if it doesn't exist
            logger.info("Screenshots folder created.");
        }
    }


    // Method to capture screenshots during test execution
    public static String captureScreenshot(String tname) {
        if (driver == null) {
            System.err.println("Driver is not initialized");
            return null;
        }

        // Generate timestamp for screenshot filename
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        if (driver instanceof TakesScreenshot) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            // Create directory for screenshots if not exists
            File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Define the screenshot file path
            String target = screenshotDir + "/" + tname + "_" + timestamp + ".png";
            File targetFile = new File(target);

            try {
                FileHandler.copy(source, targetFile);
                return targetFile.getAbsolutePath(); // Return path of screenshot
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;  // If driver doesn't support screenshot capture
        }
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


