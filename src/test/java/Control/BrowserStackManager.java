package Control;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestiona la conexión y configuración de WebDriver con BrowserStack
 * Usa el protocolo W3C con capabilities específicas de BrowserStack
 */
public class BrowserStackManager {

    private static String buildBrowserStackUrl(String username, String accessKey) {
        String encodedUsername = URLEncoder.encode(username, StandardCharsets.UTF_8);
        String encodedAccessKey = URLEncoder.encode(accessKey, StandardCharsets.UTF_8);
        return "https://" + encodedUsername + ":" + encodedAccessKey + "@hub.browserstack.com/wd/hub";
    }

    private static Map<String, Object> createCommonBstackOptions() {
        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("projectName", BrowserStackCredentials.getProjectName());
        bstackOptions.put("buildName", BrowserStackCredentials.getBuildName());
        bstackOptions.put("sessionName", BrowserStackCredentials.getSessionName());
        bstackOptions.put("local", BrowserStackCredentials.isLocalEnabled());
        bstackOptions.put("debug", BrowserStackCredentials.getDebugEnabled());
        bstackOptions.put("networkLogs", BrowserStackCredentials.isNetworkLogsEnabled());
        bstackOptions.put("consoleLogs", BrowserStackCredentials.getConsoleLogsLevel());
        return bstackOptions;
    }

    /**
     * Crea un WebDriver conectado a BrowserStack con configuración personalizada
     */
    public static WebDriver createBrowserStackDriver(String browserName, String browserVersion, 
                                                      String osName, String osVersion) 
            throws MalformedURLException {
        
        if (!BrowserStackCredentials.validateCredentials()) {
            throw new RuntimeException("BrowserStack credentials are not properly configured");
        }

        String username = BrowserStackCredentials.getUsername();
        String accessKey = BrowserStackCredentials.getAccessKey();
        String browserStackUrl = buildBrowserStackUrl(username, accessKey);

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> bstackOptions = createCommonBstackOptions();
        bstackOptions.put("osVersion", osVersion);
        bstackOptions.put("osName", osName);

        // Asignar capabilities
        options.setCapability("bstack:options", bstackOptions);
        options.setCapability("browserVersion", browserVersion);
        options.setCapability("browserName", browserName);
        options.setCapability("platformName", osName);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔗 CONECTANDO A BROWSERSTACK");
        System.out.println("=".repeat(60));
        System.out.println("📱 Usuario: " + username);
        System.out.println("🌐 Browser: " + browserName + " v" + browserVersion);
        System.out.println("💻 OS: " + osName + " " + osVersion);
        System.out.println("📦 Proyecto: " + BrowserStackCredentials.getProjectName());
        System.out.println("🏗️  Build: " + BrowserStackCredentials.getBuildName());
        System.out.println("📝 Sesión: " + BrowserStackCredentials.getSessionName());
        System.out.println("=".repeat(60));
        System.out.println("✅ Abriendo sesión remota en BrowserStack...");

        RemoteWebDriver driver = new RemoteWebDriver(new URL(browserStackUrl), options);
        
        // Imprimir Session ID para rastreo
        String sessionId = driver.getSessionId().toString();
        System.out.println("✅ SESIÓN CREADA");
        System.out.println("   Session ID: " + sessionId);
        System.out.println("   URL Dashboard: https://automate.browserstack.com/builds");
        System.out.println("=".repeat(60) + "\n");
        
        return driver;
    }

    /**
     * Crea un WebDriver conectado a BrowserStack para pruebas móviles en dispositivos reales
     */
    public static WebDriver createMobileBrowserStackDriver(String platformName, String deviceName,
                                                           String osVersion, String browserName)
            throws MalformedURLException {

        if (!BrowserStackCredentials.validateCredentials()) {
            throw new RuntimeException("BrowserStack credentials are not properly configured");
        }

        String username = BrowserStackCredentials.getUsername();
        String accessKey = BrowserStackCredentials.getAccessKey();
        String browserStackUrl = buildBrowserStackUrl(username, accessKey);

        MutableCapabilities capabilities = new MutableCapabilities();
        Map<String, Object> bstackOptions = createCommonBstackOptions();
        bstackOptions.put("deviceName", deviceName);
        bstackOptions.put("osVersion", osVersion);
        bstackOptions.put("realMobile", true);

        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("bstack:options", bstackOptions);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔗 CONECTANDO A BROWSERSTACK (MÓVIL)");
        System.out.println("=".repeat(60));
        System.out.println("📱 Usuario: " + username);
        System.out.println("📲 Plataforma: " + platformName);
        System.out.println("📱 Dispositivo: " + deviceName);
        System.out.println("🌐 Navegador: " + browserName);
        System.out.println("📦 Proyecto: " + BrowserStackCredentials.getProjectName());
        System.out.println("🏗️  Build: " + BrowserStackCredentials.getBuildName());
        System.out.println("📝 Sesión: " + BrowserStackCredentials.getSessionName());
        System.out.println("=".repeat(60));
        System.out.println("✅ Abriendo sesión móvil remota en BrowserStack...");

        RemoteWebDriver driver = new RemoteWebDriver(new URL(browserStackUrl), capabilities);

        String sessionId = driver.getSessionId().toString();
        System.out.println("✅ SESIÓN MÓVIL CREADA");
        System.out.println("   Session ID: " + sessionId);
        System.out.println("   URL Dashboard: https://automate.browserstack.com/builds");
        System.out.println("=".repeat(60) + "\n");

        return driver;
    }

    public static WebDriver createAndroidBrowserStackDriver() throws MalformedURLException {
        return createMobileBrowserStackDriver(
                "Android",
                BrowserStackCredentials.getAndroidDeviceName(),
                BrowserStackCredentials.getAndroidOSVersion(),
                BrowserStackCredentials.getAndroidBrowserName()
        );
    }

    public static WebDriver createIOSBrowserStackDriver() throws MalformedURLException {
        return createMobileBrowserStackDriver(
                "iOS",
                BrowserStackCredentials.getIOSDeviceName(),
                BrowserStackCredentials.getIOSOSVersion(),
                BrowserStackCredentials.getIOSBrowserName()
        );
    }

    /**
     * Crea un WebDriver usando la configuración del archivo properties
     */
    public static WebDriver createBrowserStackDriver() throws MalformedURLException {
        return createBrowserStackDriver(
            BrowserStackCredentials.getBrowserName(),
            BrowserStackCredentials.getBrowserVersion(),
            BrowserStackCredentials.getOSName(),
            BrowserStackCredentials.getOSVersion()
        );
    }

    /**
     * Crea un WebDriver con navegador y versión específicos
     */
    public static WebDriver createBrowserStackDriver(String browserName, String browserVersion) 
            throws MalformedURLException {
        return createBrowserStackDriver(browserName, browserVersion, 
                                       BrowserStackCredentials.getOSName(),
                                       BrowserStackCredentials.getOSVersion());
    }

    /**
     * Verifica si las credenciales están configuradas y disponibles
     */
    public static boolean isBrowserStackConfigured() {
        return BrowserStackCredentials.validateCredentials();
    }
}
