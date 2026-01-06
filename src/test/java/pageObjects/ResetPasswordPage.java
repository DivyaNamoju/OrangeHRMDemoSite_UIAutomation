package pageObjects;

import baseClass.BaseClass;
import static hooks.Hooks.log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Reset password page locators and methods
 */

public class ResetPasswordPage extends BasePage {

    @FindBy(className = "orangehrm-login-forgot-header") WebElement forgotPasswordLink;

    @FindBy(className = "orangehrm-forgot-password-title") WebElement resetPasswordForm;

    @FindBy(xpath = "//button[text()=' Reset Password ']") WebElement resetPassword;

    @FindBy(name = "username") WebElement usernameInput;

    @FindBy(className = "orangehrm-forgot-password-title") WebElement resetPasswordLinkSentMessage;

    @FindBy(xpath = "(//p[contains(@class,'oxd-text--p')])[7]") WebElement noteInResetPasswordLinkSentMessage;

    public ResetPasswordPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickOnForgotPassword()
    {
        forgotPasswordLink.click();
        log.info("Clicked on Forgot Password link");
    }
    public boolean isResetPasswordFormDisplayed()
    {
        log.info("Reset Password form displayed");
        return resetPasswordForm.isDisplayed();
    }
    public void clickOnResetPassword()
    {
        resetPassword.click();
        log.info("Clicked on Reset password button");
    }
    public void enterUsernameToResetPassword(String scenario)
    {
        String username = scenario.equalsIgnoreCase("valid")? BaseClass.VALID_USERNAME:BaseClass.INVALID_USERNAME;
        usernameInput.sendKeys(username);
        log.info("Entered username : " + username);
    }
    public boolean isPasswordResetLinkSent()
    {
        log.info("Reset Password link sent successfully");
        return resetPasswordLinkSentMessage.isDisplayed();
    }
    public String getNoteMessageDisplayed()
    {
        String message = noteInResetPasswordLinkSentMessage.getText();
        log.info(message);
        return message;
    }
}
