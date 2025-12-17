package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.ResetPasswordPage;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ResetPasswordSteps {

    WebDriver driver = Hooks.getDriver();
    ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
    @Given("user clicks on Forgot your Password link")
    public void user_clicks_on_forgot_your_password_link() {
        resetPasswordPage.clickOnForgotPassword();
    }

    @Then("Reset Password dialog is displayed")
    public void reset_password_dialog_is_displayed() {
        assertTrue(resetPasswordPage.isResetPasswordFormDisplayed());
    }
    @When("clicks on Reset Password button")
    public void clicks_on_reset_password_button() {
        resetPasswordPage.clickOnResetPassword();
    }

    @Then("Reset Password link sent successfully dialog is displayed with note")
    public void reset_password_link_sent_successfully_dialog_is_displayed_with_note(String docString) {
        assertTrue(resetPasswordPage.isPasswordResetLinkSent());
        assertEquals(docString, resetPasswordPage.getNoteMessageDisplayed());
    }

    @When("user enters {string} username")
    public void userEntersUsername(String scenario) {
        resetPasswordPage.enterUsernameToResetPassword(scenario);
    }
}
