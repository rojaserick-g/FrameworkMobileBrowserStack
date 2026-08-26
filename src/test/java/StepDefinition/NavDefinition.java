package StepDefinition;

import Constant.Navegador;
import Control.DriverContext;
import Control.NavSelector;
import io.cucumber.java.en.Given;
import org.junit.Assert;

public class NavDefinition {

    @Given("open the browser and navigate to {string}")
    public void openTheBrowserAndNavigateTo(String url)

            throws InterruptedException {
        String nav =
                System.getProperty("nav", "browserstack");

        Navegador navegador =
                NavSelector.seleccionNavegador(nav);
        DriverContext.setUp(navegador, url); // Reverted to original call
        Thread.sleep(5000);
        String urlActual =
                DriverContext.getDriver()
                        .getCurrentUrl();
        System.out.println(
                "URL Actual: "
                        + urlActual
        );
        Assert.assertEquals(
                url,
                urlActual
        );
    }
}