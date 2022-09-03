package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.DateUtils;
import utils.ExtentReportManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {

    public static WebDriver driver;
    public static Properties prop;
    public ExtentReports report = ExtentReportManager.getReportInstance();
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

    /**
     * Reporting Functions
     */
    public static void reportFail(String reportString) {
        logger.log(Status.FAIL, reportString);
        takeScreenShots();
        Assert.fail();
    }

    public void reportPass(String reportString) {
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
