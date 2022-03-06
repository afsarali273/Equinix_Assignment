package equinix.stepDefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import equinix.frameworkcore.DriverFactory;
import equinix.pageObjects.PageFactoryObject;
import io.cucumber.java.en.Given;
import equinix.utils.Utils;
import org.openqa.selenium.WebElement;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class WikiSearchStepDef {

    private static final String WIKI_URL="https://en.wikipedia.org/wiki/Main_Page";
    public static PageFactoryObject pagefactory;
    public WebDriver driver;
    private String searchItem;

    @Given("I am on the wiki main page")
    public void i_am_on_the_TodoPage() throws Exception {

        DriverFactory driverFactory = DriverFactory.getInstance();
        driverFactory.setDriver("Chrome");
        driver = driverFactory.getDriver();
        pagefactory = new PageFactoryObject(driver);
        driverFactory.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driverFactory.getDriver().get(WIKI_URL);

    }

    @When("^I search text (.*) in wikipedia$")
    public void searchInWiki(String searchItem){
        System.out.println(" Searching for "+ searchItem);
        this.searchItem=searchItem;
    }

    @When("I shall see below results for various locale")
    public void validateSearchItem(DataTable data){

        data.asMaps().stream().forEach(row-> {

           String language =  row.get("locale");
           String searchResultInDiffLocale = row.get("text_to_verify");

            pagefactory.getWikiHomePage().selectLanguageFromSideBar(language);
            pagefactory.getWikiHomePage().searchTextInWiki(searchItem);
            new Utils(driver).hardWait(1);
            pagefactory.getWikiHomePage().verifySearchResultTextInCorrectLocale(searchResultInDiffLocale);

            driver.navigate().back();
            driver.navigate().back();

        });
    }

    @When("I search for below celebrities to validate their dob and spouse details")
    public void celebritiesDetailsValidate(DataTable data){
        data.asMaps().stream().forEach(row-> {
            String celebritiesName =  row.get("celebrities_name");
            String birthdate = row.get("birthdate");
            String spouseName = row.get("spouse_name");

            pagefactory.getWikiHomePage().searchTextInWiki(celebritiesName);
            new Utils(driver).hardWait(2);
            pagefactory.getWikiHomePage().selectFirstSearchResult();

            //check for Date of Births
            pagefactory.getWikiHomePage().verifyBirthDay(birthdate);

            //Check for Spouses
            pagefactory.getWikiHomePage().verifySpouseDetails(spouseName);

            driver.navigate().back();
            driver.navigate().back();
            System.out.println("===========");
        });
    }


    @After
    public void AfterScenario(Scenario scenario) {
        driver.quit();
    }


}
