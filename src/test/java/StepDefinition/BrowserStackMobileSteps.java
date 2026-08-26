package StepDefinition;

import Constant.Navegador;
import Control.DriverContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Steps para verificar pruebas móviles en BrowserStack (Android e iOS)
 */
public class BrowserStackMobileSteps {

    @Given("El navegador móvil se conecta a BrowserStack en {string} con la URL {string}")
    public void conectarBrowserStackMovil(String plataforma, String url) {
        Navegador navegador = resolverNavegadorMovil(plataforma);
        DriverContext.setUp(navegador, url);

        WebDriver driver = DriverContext.getDriver();
        assertNotNull("El driver móvil debería estar inicializado", driver);
        assertTrue("El driver móvil debe ser RemoteWebDriver", driver instanceof RemoteWebDriver);

        RemoteWebDriver remoteDriver = (RemoteWebDriver) driver;
        System.out.println("📱 Session ID móvil: " + remoteDriver.getSessionId());
    }

    @Then("La sesión móvil debe ejecutarse en {string}")
    public void verificarSesionMovil(String plataforma) {
        RemoteWebDriver remoteDriver = (RemoteWebDriver) DriverContext.getDriver();
        assertNotNull("El driver móvil no debe ser null", remoteDriver);

        String expectedPlatform = plataforma.trim().toLowerCase();
        String platformName = String.valueOf(remoteDriver.getCapabilities().getCapability("platformName")).toLowerCase();
        String browserName = remoteDriver.getCapabilities().getBrowserName().toLowerCase();

        assertTrue("La plataforma remota debe coincidir con el dispositivo solicitado",
                platformName.contains(expectedPlatform));

        if (expectedPlatform.contains("android")) {
            Assert.assertEquals("chrome", browserName);
        } else if (expectedPlatform.contains("ios")) {
            Assert.assertEquals("safari", browserName);
        }

        System.out.println("✅ Sesión móvil verificada en " + plataforma);
        System.out.println("   platformName=" + platformName);
        System.out.println("   browserName=" + browserName);
    }

    @Then("La página móvil debe mostrar el título {string}")
    public void verificarTituloMovil(String expectedTitle) {
        WebDriver driver = DriverContext.getDriver();
        assertNotNull("El driver móvil no debe ser null", driver);

        String actualTitle = driver.getTitle();
        System.out.println("🌐 Título móvil: " + actualTitle);
        assertTrue("El título de la página móvil no coincide",
                actualTitle != null && actualTitle.contains(expectedTitle));
    }

    private Navegador resolverNavegadorMovil(String plataforma) {
        String normalized = plataforma.trim().toLowerCase();
        if (normalized.contains("android")) {
            return Navegador.Android;
        }
        if (normalized.contains("ios")) {
            return Navegador.IOS;
        }

        throw new IllegalArgumentException("Plataforma móvil no reconocida: " + plataforma);
    }
}

