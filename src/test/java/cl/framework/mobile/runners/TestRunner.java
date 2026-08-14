package cl.framework.mobile.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "cl.framework.mobile",
        plugin = {
                "pretty"
        },
        monochrome = true
)
public class TestRunner {
}