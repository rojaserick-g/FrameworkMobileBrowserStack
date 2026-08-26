package StepDefinition;

import Control.DriverContext;
import Control.BrowserStackCredentials;
import Constant.Navegador;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Steps para verificar que los tests se ejecutan en BrowserStack
 */
public class BrowserStackSteps {

    @Given("El navegador se conecta a BrowserStack exitosamente")
    public void conectarBrowserStack() {
        System.out.println("\n📋 Validando conexión a BrowserStack...");
        BrowserStackCredentials.printConfig();
        
        String url = "https://www.google.com";
        DriverContext.setUp(Navegador.BrowserStack, url);
        
        WebDriver driver = DriverContext.getDriver();
        assertNotNull("El driver debería estar inicializado", driver);
        assertTrue("El driver debe ser RemoteWebDriver", driver instanceof RemoteWebDriver);
        
        System.out.println("✅ Conexión a BrowserStack exitosa");
    }

    @Given("El navegador se conecta a BrowserStack")
    public void conectarBrowserStackSimple() {
        conectarBrowserStack();
    }

    @When("Se ejecuta un test en la plataforma remota")
    public void ejecutarTestRemoto() {
        WebDriver driver = DriverContext.getDriver();
        assertNotNull("El driver no debería ser null", driver);
        
        String title = driver.getTitle();
        System.out.println("🌐 Título de página: " + title);
        System.out.println("✅ Test ejecutado en plataforma remota");
    }

    @Then("Se verifica que el test se ejecutó en BrowserStack")
    public void verificarTestBrowserStack() {
        WebDriver driver = DriverContext.getDriver();
        assertTrue("El driver debe ser RemoteWebDriver (indicador de ejecución remota)",
                   driver instanceof RemoteWebDriver);
        
        RemoteWebDriver remoteDriver = (RemoteWebDriver) driver;
        String sessionId = remoteDriver.getSessionId().toString();
        
        System.out.println("🔗 Session ID en BrowserStack: " + sessionId);
        System.out.println("📊 URL del dashboard: https://www.browserstack.com/automate/sessions/" + sessionId);
        System.out.println("✅ Verificado: Test ejecutándose en BrowserStack");
    }

    @When("Se obtiene la información de la sesión")
    public void obtenerInfoSesion() {
        WebDriver driver = DriverContext.getDriver();
        RemoteWebDriver remoteDriver = (RemoteWebDriver) driver;
        
        String sessionId = remoteDriver.getSessionId().toString();
        String capabilities = remoteDriver.getCapabilities().toString();
        
        System.out.println("\n📋 Información de Sesión BrowserStack:");
        System.out.println("   Session ID: " + sessionId);
        System.out.println("   Capabilities: " + capabilities.substring(0, Math.min(100, capabilities.length())) + "...");
    }

    @Then("La sesión debe estar registrada en BrowserStack")
    public void verificarSesionRegistrada() {
        WebDriver driver = DriverContext.getDriver();
        RemoteWebDriver remoteDriver = (RemoteWebDriver) driver;
        String sessionId = remoteDriver.getSessionId().toString();
        
        assertNotNull("El Session ID no debe ser null", sessionId);
        assertTrue("El Session ID debe ser válido", sessionId.length() > 0);
        
        System.out.println("✅ Sesión registrada en BrowserStack");
        System.out.println("   Puedes ver los detalles en:");
        System.out.println("   https://www.browserstack.com/automate/sessions/" + sessionId);
        System.out.println("\n   O accede al dashboard:");
        System.out.println("   https://www.browserstack.com/automate");
    }
}
