package equinix.pageObjects;

import org.openqa.selenium.WebDriver;

public class PageFactoryObject {
   WikiHomePage wikiHomePage;
    /*** Getters ***/

    public WikiHomePage getWikiHomePage() {
        return wikiHomePage;
    }

    public PageFactoryObject(WebDriver driver){
        wikiHomePage = new WikiHomePage(driver);


    }





}
