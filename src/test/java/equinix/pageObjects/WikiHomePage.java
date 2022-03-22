package equinix.pageObjects;

import equinix.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WikiHomePage extends Utils {

    public WikiHomePage(WebDriver driver) {
        super(driver);
    }

    //======= Page Objects ===========
    @FindBy(css = ".vector-search-box-input")
    private WebElement inputSearchBox;

    @FindBy(css = ".firstHeading.mw-first-heading")
    private WebElement txtSearchText;

    @FindBy(css = ".mw-search-result>div>a")
    private List<WebElement> linkSearchResult;

    @FindBy(css = ".infobox-data")
    private List<WebElement> txtDOB;

    @FindBy(xpath = "//tr/th/span[contains(text(),'Spouse')]/parent::th/following-sibling::td")
    private List<WebElement> txtSpouseDetails;


    // =========== Page Actions ===========

    public WikiHomePage selectLanguageFromSideBar(String language){
        driver.findElement(
                By.xpath(".//ul[@class='vector-menu-content-list']/li/a/span[contains(text(),'"+language+"')]")
        ).click();
        System.out.println("Selected Language is : "+language);
        return this;
    }

    public WikiHomePage searchTextInWiki(String searchText){
        this.inputSearchBox.sendKeys(searchText+"\n");
        System.out.println("Searching for the text : "+ searchText);
        return this;
    }

    public WikiHomePage selectFirstSearchResult(){
        linkSearchResult.get(0).click();
        return this;
    }

    public WikiHomePage verifySearchResultTextInCorrectLocale(String expectedText){
        String actualText = this.txtSearchText.getText();
        Assert.assertEquals("Locale doesn't match with expected text.",expectedText,actualText);
        return this;
    }

    public WikiHomePage verifyBirthDay(String expectedDOB){
        System.out.println(" DOB "+txtDOB.get(0).getText());
        Assert.assertTrue("Date of Birth didn't match",txtDOB.get(0).getText().contains(expectedDOB));
        return this;
    }

    public WikiHomePage verifySpouseDetails(String expectedSpouse){
        if(txtSpouseDetails.size()>0 && StringUtils.isNotEmpty(expectedSpouse)){
            System.out.println("Spouse Details :\n"+txtSpouseDetails.get(0).getText());
            Assert.assertTrue("Spouse details didn't match", txtSpouseDetails.get(0).getText().contains(expectedSpouse));
        }
        return this;
    }


}
