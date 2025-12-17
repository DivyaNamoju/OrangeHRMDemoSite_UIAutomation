package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/java/features/", // Feature file locations
        glue = {"hooks","stepDefinitions"}, // Hooks and corresponding step definitions to execute
        publish = true, // Publishing report is enabled
        // Different Test Report formats
        plugin = {"pretty",
        "json:target/cucumber.json",
        "html:target/cucumber-html-report"} // Maven Cucumber Reporter
        )
public class TestRunnerTestNGParallel extends AbstractTestNGCucumberTests {

    @Test
    @DataProvider(parallel = true)
    public Object[][] runCucumber() {
        // This will run the Cucumber tests with TestNG
        return super.scenarios();
    }
}
