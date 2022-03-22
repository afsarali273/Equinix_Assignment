package equinix.frameworkcore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.InvocationTargetException;


public class PageFactoryObject {
   WebDriver driver;
    /*** Getters ***/

    public PageFactoryObject(WebDriver driver){
        this.driver=driver;
    }

    public  <T> T getPage(Class<T> clz)  {
        try {
            T obj = clz.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
            PageFactory.initElements(driver, obj);
            return obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
