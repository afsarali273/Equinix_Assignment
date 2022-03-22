package equinix.frameworkcore;

import org.openqa.selenium.WebDriver;

public class BaseClass {

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

   private WebDriver driver;

    protected <T> T getPage(Class<T> clasz){
       return new PageFactoryObject(this.driver).getPage(clasz);
    }
}
