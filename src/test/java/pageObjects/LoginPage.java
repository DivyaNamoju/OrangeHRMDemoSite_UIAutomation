package pageObjects;


import hooks.Hooks;

import static baseClass.BaseClass.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;
import static hooks.Hooks.log;
import static org.testng.AssertJUnit.assertEquals;

public class LoginPage extends BasePage {

    WebDriver driver = Hooks.getDriver();

    @FindBy(xpath="//input[@name='username']") WebElement usernameInput;

    @FindBy(xpath="//input[@name='password']") WebElement passwordInput;

    @FindBy(xpath="//button[text()=' Login ']") WebElement loginButton;

    @FindBy(xpath="//p[text()='Invalid credentials']")WebElement warningMessage;

    @FindBy(css="div.orangehrm-login-footer-sm a") List<WebElement> footerLinks;

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }
    public void loadURL()
    {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
        log.info("Navigated to application URL");
    }
    public void enterUsername(String username)
    {
        usernameInput.sendKeys(username);
        log.info("Enter username : " + username);
    }
    public void enterPassword(String password)
    {
        passwordInput.sendKeys(password);
        log.info("Entered password : " + password);
    }
    public void clickOnLogin()
    {
        if(loginButton.isEnabled()) {
            loginButton.click();
        }
        log.info("Clicked in Login button");
    }
    public boolean isWarningMessagePresent()
    {
        boolean isMessagePresent = warningMessage.isDisplayed();
        log.info("Warning message : " + isMessagePresent);
        return isMessagePresent;
    }
    public String warningMessageText()
    {
        String message = warningMessage.getText();
        log.info(message);
        return message;
    }
    public void validateSocialMediaLinks()
    {
        log.info("Number of footer links : {}", footerLinks.size());
        for(int i=0;i<footerLinks.size();i++)
        {
            footerLinks.get(i).click(); // window should have 2 tabs now
            Object[] windowHandles = driver.getWindowHandles().toArray();
            driver.switchTo().window((String)windowHandles[1]);// Switch to the other tab
            log.info("Current URL : " + driver.getCurrentUrl() + " Index : " + i);
            switch(i){
                case 0: assertEquals(LINKEDIN_URL,driver.getCurrentUrl());
                case 1: assertEquals(FACEBOOK_URL,driver.getCurrentUrl());
                case 2: assertEquals(X_URL,driver.getCurrentUrl());
                case 3: assertEquals(YOUTUBE_URL,driver.getCurrentUrl());
            }
            driver.close();
            driver.switchTo().window((String)windowHandles[0]); // Switch to the main tab
        }
    }
}
