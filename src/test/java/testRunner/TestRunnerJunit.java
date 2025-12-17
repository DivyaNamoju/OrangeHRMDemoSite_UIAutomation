package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/Login.feature:10", // Feature file locations
        glue = {"hooks","stepDefinitions"}, // Hooks and corresponding step definitions to execute
        publish = true, // Publishing report is enabled
        // Different Test Report formats
        plugin = {"pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-html-report"}) // Maven Cucumber Reporter
public class TestRunnerJunit {

}
