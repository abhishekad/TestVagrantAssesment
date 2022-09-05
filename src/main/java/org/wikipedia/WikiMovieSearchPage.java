package org.wikipedia;

import base.Base;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;

public class WikiMovieSearchPage extends Base {

    private static By WikiReleaseDate = By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[1]/tbody/tr[12]/td/div");
    private static By WikiCountry = By.xpath("//*[@id=\"mw-content-text\"]/div[1]/table[1]/tbody/tr[14]/td");

    public static String getWikiReleaseDate(){
        String wikiRlsDate = driver.findElement(WikiReleaseDate).getText();
        logger.log(Status.PASS,"Wikipedia release date fetched successfully");
        return wikiRlsDate;
    }

    public static String getWikiCountry() {
        String country = driver.findElement(WikiCountry).getText();
        logger.log(Status.PASS,"Wikipedia country fetched successfully");
        return country;
    }
}
