package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Runner para ejecutar tests SOLO en BrowserStack
 * Ejecuta ÚNICAMENTE el archivo leave_List_Managemet.feature
 *
 * Uso: gradle test --tests BrowserStackRunner
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"StepDefinition"},
        tags = "@Leave",
        plugin = {
                "pretty",
                "html:target/browserstack-report.html",
                "json:target/browserstack-report.json"
        },
        monochrome = true
)
public class BrowserStackRunner {
}

