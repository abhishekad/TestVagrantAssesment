package Tests;

import base.Base;
import com.aventstack.extentreports.Status;
import com.imdb.pages.IMDB_HomePage;
import com.imdb.pages.MovieSearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.wikipedia.WIKI_HomePage;
import org.wikipedia.WikiMovieSearchPage;

import java.net.MalformedURLException;

public class MovieDetails extends Base {
    static  String IMDBTITLE= "IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows";
    static String WIKITITLE= "Wikipedia";


    @Test
    public static void validateReleaseDate(){
        logger = report.createTest("Validate Release Date");
        setupDriver("imdb");
        Assert.assertEquals(IMDBTITLE,driver.getTitle(),"IMDB TITLE DOESN'T MATCH");
        IMDB_HomePage.enterTextToSearchBox("Pushpa: The Rise - Part 1");
        IMDB_HomePage.clickAjaxSuggestion();
        String imdbReleaseDate = MovieSearchPage.getIMDBReleaseDate();
        setupDriver("wiki");
        Assert.assertEquals(WIKITITLE,driver.getTitle(),"WIKI TITLE DOESN'T MATCH");
        WIKI_HomePage.enterTextToWikiSearchBar("Pushpa: The Rise - Part 1");
        WIKI_HomePage.clickWikiSearchIcon();
        String wikiReleaseDate = WikiMovieSearchPage.getWikiReleaseDate();

        try {
            Assert.assertEquals(imdbReleaseDate,wikiReleaseDate,"Release Date Doesn't Match");
            reportPass("Release Date doesn't match");
        }
        catch(Exception e){
            reportFail(e.getMessage());
        }

    }
    @Test
    public static void validateCountryOfOrigin(){
        logger = report.createTest("Validate Country of origin");
        logger.log(Status.INFO,"Started the Test");
        setupDriver("imdb");
        Assert.assertEquals(IMDBTITLE,driver.getTitle(),"IMDB TITLE DOESN'T MATCH");
        IMDB_HomePage.enterTextToSearchBox("Pushpa: The Rise - Part 1");
        IMDB_HomePage.clickAjaxSuggestion();
        String imdbCountryOfOrigin = MovieSearchPage.getIMDBCountryOfOrigin();
        setupDriver("wiki");
        Assert.assertEquals(IMDBTITLE,driver.getTitle(),"IMDB TITLE DOESN'T MATCH");
        WIKI_HomePage.enterTextToWikiSearchBar("Pushpa: The Rise - Part 1");
        WIKI_HomePage.clickWikiSearchIcon();
        String wikiCountry = WikiMovieSearchPage.getWikiCountry();
        try {
            Assert.assertEquals(imdbCountryOfOrigin, wikiCountry, "Country Of Origin Doesn't Match");
            reportPass("Country of Origin doesn't match");
        }
        catch(Exception e){
            reportFail(e.getMessage());
            }
        report.flush();
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
