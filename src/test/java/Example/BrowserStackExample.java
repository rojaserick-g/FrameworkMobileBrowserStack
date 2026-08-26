package Example;

import Control.DriverContext;
import Control.BrowserStackManager;
import Control.BrowserStackConfig;
import Constant.Navegador;
import org.openqa.selenium.WebDriver;

/**
 * Ejemplo de cómo usar BrowserStack en tus tests
 * Este archivo es solo para referencia y no se ejecuta automáticamente
 */
public class BrowserStackExample {

    /**
     * Ejemplo 1: Uso básico en tus tests
     * 
     * En tu hook o setup:
     */
    public void ejemploConfiguracionBasica() {
        // Tu configuración normal
        String url = "https://tuapp.com";
        
        // Simplemente usa Navegador.BrowserStack
        DriverContext.setUp(Navegador.BrowserStack, url);
    }

    /**
     * Ejemplo 2: Configurar antes de ejecutar
     * 
     * En la línea de comandos:
     * export BROWSERSTACK_USERNAME="your_username"
     * export BROWSERSTACK_ACCESS_KEY="your_access_key"
     * export BS_BROWSER_VERSION="latest"
     * export BS_OS="Windows"
     * export BS_OS_VERSION="11"
     * export BS_PROJECT="Mi Proyecto"
     * export BS_BUILD="Build #123"
     * mvn test
     */
    public void ejemploConVariablesDeEntorno() {
        // Las variables de entorno se leen automáticamente
        BrowserStackConfig.printConfig();
    }

    /**
     * Ejemplo 3: Usar BrowserStack Local (para testing local)
     * 
     * Requiere descargar BrowserStack Local desde:
     * https://www.browserstack.com/local-testing/downloads
     * 
     * Luego ejecutar en otra terminal:
     * ./BrowserStackLocal --key YOUR_ACCESS_KEY
     */
    public void ejemploConLocalTesting() {
        // Después de ejecutar BrowserStack Local, configura:
        // export BROWSERSTACK_LOCAL=true
        // Y tus tests se conectarán automáticamente a tu localhost
    }

    /**
     * Ejemplo 4: Diferentes navegadores y sistemas operativos
     * 
     * En tus feature files o configuración:
     */
    public void ejemploMultiplasPlatformas() {
        // Windows 11 + Chrome
        System.setProperty("BS_OS", "Windows");
        System.setProperty("BS_OS_VERSION", "11");
        System.setProperty("BS_BROWSER_VERSION", "latest");

        // Mac Monterey + Safari
        // System.setProperty("BS_OS", "OS X");
        // System.setProperty("BS_OS_VERSION", "Monterey");

        // Windows 10 + Chrome específica
        // System.setProperty("BS_BROWSER_VERSION", "120.0");
    }

    /**
     * Ejemplo 5: Tags en Cucumber para ejecutar solo con BrowserStack
     * 
     * En tu archivo .feature:
     * 
     * @browserstack
     * Scenario: Test en BrowserStack
     *   Given El usuario está en la app
     *   When Realiza una acción
     *   Then Se verifica el resultado
     * 
     * En tu Runner.java:
     * @CucumberOptions(
     *     tags = "@browserstack"
     * )
     */
    public void ejemploConTagsCucumber() {
        // Solo ejecuta scenarios marcados con @browserstack
    }

    /**
     * Ejemplo 6: Manejo de errores
     */
    public void ejemploManejoDeerrores() {
        try {
            if (!BrowserStackManager.isBrowserStackConfigured()) {
                System.out.println("⚠️  BrowserStack no configurado");
                System.out.println("Configura: BROWSERSTACK_USERNAME y BROWSERSTACK_ACCESS_KEY");
                return;
            }
            
            WebDriver driver = BrowserStackManager.createBrowserStackDriver("Chrome", "latest");
            // Tu test aquí
            driver.quit();
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Ejemplo 7: Cambiar capabilities dinámicamente
     */
    public void ejemploDinamico() {
        // Cambiar project name
        BrowserStackConfig.setCapability("project", "Mi Nuevo Proyecto");
        
        // Cambiar build
        BrowserStackConfig.setCapability("buildName", "Build Noche #42");
        
        // Ver la configuración
        BrowserStackConfig.printConfig();
    }
}
