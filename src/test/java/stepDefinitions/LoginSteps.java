package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import pageObjects.LoginPage;

import java.time.Duration;
import java.util.Objects;

import static baseClass.BaseClass.BASE_URL;
import static baseClass.BaseClass.DASHBOARD_URL;
import static org.testng.AssertJUnit.assertEquals;

public class LoginSteps{
    WebDriver driver = Hooks.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    FluentWait<WebDriver> wait = new FluentWait<>(driver).
            withTimeout(Duration.ofSeconds(3000)).
            pollingEvery(Duration.ofSeconds(50)).
            ignoring(NoSuchElementException.class);
    @Given("user is on application login page")
    public void user_is_on_application_login_page() throws InterruptedException {
        loginPage.loadURL();
        assertEquals(BASE_URL, driver.getCurrentUrl());
    }
    @When("user enters username {string}")
    public void user_enters_username(String string) {
       loginPage.enterUsername(string);
    }
    @When("user enters password {string}")
    public void user_enters_password(String string) {
        loginPage.enterPassword(string);
    }
    @When("clicks on Login button")
    public void clicks_on_login_button() {
        loginPage.clickOnLogin();

    }
    @Then("user should navigate to the application dashboard")
    public void user_should_navigate_to_the_application_dashboard() {
        wait.until(dr -> Objects.equals(dr.getCurrentUrl(), DASHBOARD_URL));
        assertEquals(DASHBOARD_URL, driver.getCurrentUrl());
    }

    @Then("a warning is displayed")
    public void aWarningIsDisplayed(String docString) {
        if(loginPage.isWarningMessagePresent())
        {
            assertEquals("Warning message displayed is not as expected",docString,loginPage.warningMessageText());
        }
        else
            Assert.fail();
    }


    @When("user should navigate to the appropriate social media accounts")
    public void userShouldNavigateToTheAppropriateSocialMediaAccounts() {
        loginPage.validateSocialMediaLinks();
    }
}
