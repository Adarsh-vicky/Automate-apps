package tests.cucumber_runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;


@CucumberOptions(
        features = {"src/test/resources/bdd_features"},
        glue = {"object_library.bdd_steps"},
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
