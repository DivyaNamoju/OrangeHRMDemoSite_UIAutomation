package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Base page to instantiate the page elements
 */
public class BasePage {

    BasePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
