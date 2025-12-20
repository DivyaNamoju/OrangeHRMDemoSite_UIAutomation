package hooks;

import baseClass.DriverManager;
import baseClass.EnvParams;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Hooks_backup {

    protected static WebDriver driver;
    public static Logger log;

    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @Before
    public void setupDriver()
    {
        log = LogManager.getLogger(this.getClass());
        System.out.println("Initializing WebDriver ==> " + EnvParams.BROWSER);
        driver = switch(EnvParams.BROWSER.toLowerCase()){
            case "chrome" -> new ChromeDriver();
            case "edge" -> new EdgeDriver();
            case "firefox" -> new FirefoxDriver();
            case "headless"->{
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                yield new ChromeDriver(chromeOptions);
            }
            default -> throw new IllegalArgumentException("Please provide appropriate browser name");
        };
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
