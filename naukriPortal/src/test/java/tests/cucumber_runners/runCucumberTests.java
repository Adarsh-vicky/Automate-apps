package tests.cucumber_runners;

import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        features = {"src/test/resources/bdd_features"},
        glue = {"object_library.bdd_steps"},
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)

public class runCucumberTests {
}
