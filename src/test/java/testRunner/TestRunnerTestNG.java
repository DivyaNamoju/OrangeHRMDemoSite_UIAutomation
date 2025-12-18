package testRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/java/features/Login.feature", // Feature file location
        glue = {"hooks","stepDefinitions"}, // Hooks and corresponding step definitions to execute
        publish = true, // Publishing report is enabled
        tags = "", // select features with specific tags to run
        // Different Test Report formats
        plugin = {"pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-html-report.html"})// Maven Cucumber Reporter)
public class TestRunnerTestNG extends AbstractTestNGCucumberTests {

}
