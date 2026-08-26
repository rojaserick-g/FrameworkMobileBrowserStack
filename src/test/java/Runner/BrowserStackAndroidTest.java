package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Runner para ejecutar únicamente escenarios móviles Android en BrowserStack.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/browserstack_mobile.feature",
        glue = {"StepDefinition"},
        tags = "@android",
        plugin = {
                "pretty",
                "html:target/browserstack-android-report.html",
                "json:target/browserstack-android-report.json"
        },
        monochrome = true
)
public class BrowserStackAndroidTest {
}


