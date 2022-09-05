package com.imdb.pages;

import base.Base;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MovieSearchPage extends Base {

    private static By ReleaseDate = By.xpath("//*[@id=\"__next\"]/main/div/section[1]/div/section/div/div[1]/section[9]/div[2]/ul/li[1]/div");
    private static By imdb_CountryOfOrigin = By.xpath("//*[@id='__next']/main/div/section[1]/div/section/div/div[1]/section[9]/div[2]/ul/li[2]/div/ul/li/a");

    static WebDriverWait wait = new WebDriverWait(driver, 10);
    public static String getIMDBReleaseDate(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,6000)", "");
        //js.executeScript("arguments[0].scrollIntoView(true);", ReleaseDate);
        wait.until(ExpectedConditions.presenceOfElementLocated(ReleaseDate));
        String imdbReleaseDate = driver.findElement(ReleaseDate).getText();
        logger.log(Status.PASS,"IMDB release date fetched successfully");
        return imdbReleaseDate;
    }

    public static String getIMDBCountryOfOrigin(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,6000)", "");
        //js.executeScript("arguments[0].scrollIntoView(true);", imdb_CountryOfOrigin);
        wait.until(ExpectedConditions.presenceOfElementLocated(imdb_CountryOfOrigin));
        String imdbCountryOfOrigin = driver.findElement(imdb_CountryOfOrigin).getText();
        logger.log(Status.PASS,"IMDB Country fetched successfully");
        return imdbCountryOfOrigin;
    }

}
