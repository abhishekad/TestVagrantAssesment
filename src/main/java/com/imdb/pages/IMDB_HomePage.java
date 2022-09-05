package com.imdb.pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IMDB_HomePage extends Base {

    private static By text_SEARCHBOX = By.id("suggestion-search");
    private static By link_AjaxSuggesetion = By.xpath(("//*[@id=\"react-autowhatever-1--item-0\"]/a"));
    private static By button_SEARCHICON = By.id("iconContext-magnify");

    public static void enterTextToSearchBox(String content){
        driver.findElement(text_SEARCHBOX).sendKeys(content);
    }

    public static void clickSearchButton(){
        driver.findElement(button_SEARCHICON).click();
    }

    public static void clickAjaxSuggestion(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(link_AjaxSuggesetion)).click();
    }
}
