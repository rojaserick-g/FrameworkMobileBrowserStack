package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Runner para ejecutar únicamente escenarios móviles iOS en BrowserStack.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/browserstack_mobile.feature",
        glue = {"StepDefinition"},
        tags = "@ios",
        plugin = {
                "pretty",
                "html:target/browserstack-ios-report.html",
                "json:target/browserstack-ios-report.json"
        },
        monochrome = true
)
public class BrowserStackIOSTest {
}


