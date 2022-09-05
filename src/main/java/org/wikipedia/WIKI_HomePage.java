package org.wikipedia;

import base.Base;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WIKI_HomePage extends Base {

    private static By SEARCHBOX_WIKI = By.id("searchInput");
    public static By BUTTON_WIKISEARCH = By.xpath("//i[contains(text(),'Search')]");

    static WebDriverWait wait = new WebDriverWait(driver, 10);

    public static void enterTextToWikiSearchBar(String movie){
        wait.until(ExpectedConditions.elementToBeClickable(SEARCHBOX_WIKI));
        driver.findElement(SEARCHBOX_WIKI).sendKeys(movie);
        logger.log(Status.PASS,"Movie Name entered on Search Box");
    }

    public static void clickWikiSearchIcon(){
        wait.until(ExpectedConditions.elementToBeClickable(BUTTON_WIKISEARCH));
        driver.findElement(BUTTON_WIKISEARCH).click();
        logger.log(Status.PASS,"Movie Searched Successfully");
    }
}
