package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features/Login.feature:5", // Feature file location
        glue = {"hooks","stepDefinitions"}, // Hooks and corresponding step definitions to execute
        publish = true, // Publishing report is enabled
        // Different Test Report formats
        plugin = {"pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-html-report.html"})// Maven Cucumber Reporter)
public class TestRunnerTestNGUnitTest extends AbstractTestNGCucumberTests {

}
