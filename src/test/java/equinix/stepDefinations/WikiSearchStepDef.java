package equinix.stepDefinations;

import equinix.frameworkcore.BaseClass;
import equinix.pageObjects.WikiHomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import equinix.frameworkcore.DriverFactory;
import io.cucumber.java.en.Given;
import java.util.concurrent.TimeUnit;

public class WikiSearchStepDef extends BaseClass {

    private static final String WIKI_URL="https://en.wikipedia.org/wiki/Main_Page";
    public WebDriver driver;
    private String searchItem;

    @Given("I am on the wiki main page")
    public void i_am_on_the_TodoPage() throws Exception {

        DriverFactory driverFactory = DriverFactory.getInstance();
        driverFactory.setDriver("Chrome");
        driver = driverFactory.getDriver();
        setDriver(driver);
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

            getPage(WikiHomePage.class)
                    .selectLanguageFromSideBar(language)
                    .searchTextInWiki(searchItem)
                    .verifySearchResultTextInCorrectLocale(searchResultInDiffLocale)
                    .hardWait(1);

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

            getPage(WikiHomePage.class)
                    .searchTextInWiki(celebritiesName)
                    .hardWait(2);

            getPage(WikiHomePage.class)
                    .selectFirstSearchResult()
                    .verifyBirthDay(birthdate)
                    .verifySpouseDetails(spouseName);

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
