package hooks;

import baseClass.DriverManager;
import baseClass.EnvParams;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static baseClass.EnvParams.BROWSER;
import static baseClass.EnvParams.EXECUTION_ENV;

/**
 * Before and After methods that will execute before and after each test scenario
 */

public class Hooks {

    protected static WebDriver driver;
    public static Logger log;

    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @Before
    public void setupDriver()
    {
        log = LogManager.getLogger(this.getClass());
        log.info("Initializing WebDriver ==> {}", EnvParams.BROWSER);
        if(!EXECUTION_ENV.equalsIgnoreCase("local"))
        {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setPlatform(Platform.WIN11);
            desiredCapabilities.setBrowserName(BROWSER);

            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), desiredCapabilities);
            } catch (MalformedURLException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        else {
            driver = switch (BROWSER.toLowerCase()) {
                case "chrome" -> new ChromeDriver();
                case "edge" -> new EdgeDriver();
                case "firefox" -> new FirefoxDriver();
                case "headless" -> {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless=new");
                    yield new ChromeDriver(chromeOptions);
                }
                default -> throw new IllegalArgumentException("Please provide appropriate browser name");
            };
        }
        DriverManager.setDriver(driver);
    }

    @After
    public void quitDriver(Scenario scenario)
    {
        if(scenario.isFailed())
        {
            log.info("Scenario {} is Failed", scenario.getName());
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            try {
                String fileName = "screenshots/" + scenario.getName() + ".png";
                // This step will attach the screenshot to the report
                scenario.attach(screenshotBytes,"image/png", "File scenario screenshot");
                // This step will copy the screenshot to the screenshots folder
                Files.write(Paths.get(fileName), screenshotBytes);
            }
            catch (IOException e) {
                log.info(e.getLocalizedMessage());
            }
        }
        else {
            log.info("Scenario {} is Passed", scenario.getName());
        }
        if(driver!=null) {
            driver.quit();
            System.out.println("WebDriver closed");
            DriverManager.unload();
        }
    }
}
