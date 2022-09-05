package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.DateUtils;
import utils.ExtentReportManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;
    public static Properties prop;
    public static ExtentReports report = ExtentReportManager.getReportInstance();
    public static ExtentTest logger;

    SoftAssert softAssert = new SoftAssert();

    public Base() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\src\\test\\resources\\ObjectRepository\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            reportFail(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            reportFail(e.getMessage());
        }
    }

    public static WebDriver setupDriver(String website) {
        String browser = prop.getProperty("Browser");


        // To launch chrome browser
        if (browser.equalsIgnoreCase("chrome")) {
            // To launch in Chrome browser
            //This is the change I am Making to update chrome driver
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\chromedriver.exe");
                    //"F:\\CodingAssesment\\TestVagrant\\src\\test\\resources\\Drivers\\chromedriver.exe");
                    //
            //System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);// options
        }

        // To launch in Firefox browser
        else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(new FirefoxProfile());
            options.addPreference("dom.webnotifications.enabled", false);
            driver = new FirefoxDriver(options);

        }

        // To launch in Edge browser
        else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver",
                    System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\msedgedriver.exe");
            EdgeOptions options = new EdgeOptions();
            // options.addArguments("--disable-notifications");
            driver = new EdgeDriver(options);
        }

        if (website.equalsIgnoreCase("imdb")){
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            driver.get(prop.getProperty("imdburl"));

        }
        else if (website.equalsIgnoreCase("wiki")) {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            driver.get(prop.getProperty("wikiurl"));
        }
        return driver;
    }


    /**
     * Reporting Functions
     */
    public static void reportFail(String reportString) {
        logger.log(Status.FAIL, reportString);
        takeScreenShots();
        Assert.fail();
    }

    public static void reportPass(String reportString) {
        logger.log(Status.PASS, reportString);
    }

    /**********************************************************************************************
     * Capturing ScreenShots
     *********************************************************************************************/

    public static void takeScreenShots() {
        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
        File source = takeScreenshot.getScreenshotAs(OutputType.FILE);

        File dest = new File(
                System.getProperty("user.dir") + "\\ScreenShot\\ScreenShots" + DateUtils.getTimeStamp() + ".png");

        try {
            FileUtils.copyFile(source, dest);
            logger.addScreenCaptureFromPath(
                    System.getProperty("user.dir") + "\\ScreenShot\\ScreenShots" + DateUtils.getTimeStamp() + ".png");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
