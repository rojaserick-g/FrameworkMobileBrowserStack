package Control;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;

/**
 * Gestiona la carga y validación de credenciales de BrowserStack
 * desde el archivo browserstack.properties
 */
public class BrowserStackCredentials {
    private static final Properties properties = new Properties();
    private static boolean isLoaded = false;
    private static final String CONFIG_FILE = "browserstack.properties";

    static {
        loadCredentials();
    }

    /**
     * Carga las credenciales del archivo browserstack.properties
     */
    private static void loadCredentials() {
        if (isLoaded) return;

        try {
            Path configPath = Paths.get(CONFIG_FILE);

            if (!Files.exists(configPath)) {
                System.out.println("⚠️  Archivo " + CONFIG_FILE + " no encontrado");
                System.out.println("📋 Se espera un archivo en: " + configPath.toAbsolutePath());
                return;
            }

            try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
                properties.load(fis);
                isLoaded = true;
                System.out.println("✅ Configuración de BrowserStack cargada desde: " + 
                                 configPath.toAbsolutePath());
            }

        } catch (IOException e) {
            System.out.println("❌ Error cargando browserstack.properties: " + e.getMessage());
        }
    }

    /**
     * Obtiene el username de BrowserStack
     */
    public static String getUsername() {
        return getProperty("browserstack.username", System.getenv("BROWSERSTACK_USERNAME"));
    }

    /**
     * Obtiene el access key de BrowserStack
     */
    public static String getAccessKey() {
        return getProperty("browserstack.access_key", System.getenv("BROWSERSTACK_ACCESS_KEY"));
    }

    /**
     * Obtiene el nombre del navegador
     */
    public static String getBrowserName() {
        return getProperty("browserstack.browser.name", "Chrome");
    }

    /**
     * Obtiene la versión del navegador
     */
    public static String getBrowserVersion() {
        return getProperty("browserstack.browser.version", "latest");
    }

    /**
     * Obtiene el nombre del OS
     */
    public static String getOSName() {
        return getProperty("browserstack.os.name", "Windows");
    }

    /**
     * Obtiene la versión del OS
     */
    public static String getOSVersion() {
        return getProperty("browserstack.os.version", "11");
    }

    /**
     * Obtiene el nombre del dispositivo Android
     */
    public static String getAndroidDeviceName() {
        return getProperty("browserstack.mobile.android.device.name", "Samsung Galaxy S24");
    }

    /**
     * Obtiene la versión del sistema operativo Android
     */
    public static String getAndroidOSVersion() {
        return getProperty("browserstack.mobile.android.os.version", "14.0");
    }

    /**
     * Obtiene el navegador para Android
     */
    public static String getAndroidBrowserName() {
        return getProperty("browserstack.mobile.android.browser.name", "Chrome");
    }

    /**
     * Obtiene el nombre del dispositivo iOS
     */
    public static String getIOSDeviceName() {
        return getProperty("browserstack.mobile.ios.device.name", "iPhone 15");
    }

    /**
     * Obtiene la versión del sistema operativo iOS
     */
    public static String getIOSOSVersion() {
        return getProperty("browserstack.mobile.ios.os.version", "17.0");
    }

    /**
     * Obtiene el navegador para iOS
     */
    public static String getIOSBrowserName() {
        return getProperty("browserstack.mobile.ios.browser.name", "Safari");
    }

    /**
     * Obtiene el nombre del proyecto
     */
    public static String getProjectName() {
        return getProperty("browserstack.project.name", "simpleTestAutomation");
    }

    /**
     * Obtiene el nombre del build
     */
    public static String getBuildName() {
        return getProperty("browserstack.build.name", "Test Build");
    }

    /**
     * Obtiene el nombre de la sesión
     */
    public static String getSessionName() {
        return getProperty("browserstack.session.name", "Test Session");
    }

    /**
     * Obtiene si BrowserStack Local está habilitado
     */
    public static boolean isLocalEnabled() {
        String value = getProperty("browserstack.local", "false");
        return value.equalsIgnoreCase("true");
    }

    /**
     * Indica si BrowserStack debug está habilitado
     */
    public static boolean getDebugEnabled() {
        String value = getProperty("browserstack.debug", "false");
        return value.equalsIgnoreCase("true");
    }

    /**
     * Indica si BrowserStack network logs están habilitados
     */
    public static boolean isNetworkLogsEnabled() {
        String value = getProperty("browserstack.network.logs", "false");
        return value.equalsIgnoreCase("true");
    }

    /**
     * Nivel de logs para consola remota
     */
    public static String getConsoleLogsLevel() {
        return getProperty("browserstack.console.logs", "info");
    }

    /**
     * Obtiene una propiedad con fallback a variable de entorno
     */
    private static String getProperty(String key, String defaultValue) {
        String envVar = key.toUpperCase().replace(".", "_");
        String envValue = System.getenv(envVar);

        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }

        String propValue = properties.getProperty(key);
        if (propValue != null && !propValue.isEmpty() && !propValue.startsWith("YOUR_")) {
            return propValue;
        }

        return defaultValue;
    }

    /**
     * Valida que las credenciales estén configuradas
     */
    public static boolean validateCredentials() {
        String username = getUsername();
        String accessKey = getAccessKey();

        if (username == null || username.isEmpty() || username.startsWith("YOUR_")) {
            System.out.println("❌ ERROR: Username de BrowserStack no configurado");
            System.out.println("   Edita: " + new File(CONFIG_FILE).getAbsolutePath());
            System.out.println("   O configura: BROWSERSTACK_USERNAME");
            return false;
        }

        if (accessKey == null || accessKey.isEmpty() || accessKey.startsWith("YOUR_")) {
            System.out.println("❌ ERROR: Access Key de BrowserStack no configurado");
            System.out.println("   Edita: " + new File(CONFIG_FILE).getAbsolutePath());
            System.out.println("   O configura: BROWSERSTACK_ACCESS_KEY");
            return false;
        }

        return true;
    }

    /**
     * Imprime la configuración actual (sin mostrar credenciales sensibles)
     */
    public static void printConfig() {
        System.out.println("\n========== BrowserStack Configuration ==========");
        System.out.println("Username: " + maskSensitive(getUsername()));
        System.out.println("Access Key: " + maskSensitive(getAccessKey()));
        System.out.println("Browser: " + getBrowserName() + " " + getBrowserVersion());
        System.out.println("OS: " + getOSName() + " " + getOSVersion());
        System.out.println("Android: " + getAndroidDeviceName() + " | " + getAndroidOSVersion() + " | " + getAndroidBrowserName());
        System.out.println("iOS: " + getIOSDeviceName() + " | " + getIOSOSVersion() + " | " + getIOSBrowserName());
        System.out.println("Project: " + getProjectName());
        System.out.println("Build: " + getBuildName());
        System.out.println("Local: " + isLocalEnabled());
        System.out.println("================================================\n");
    }

    /**
     * Enmascara valores sensibles para no mostrarlos en logs
     */
    private static String maskSensitive(String value) {
        if (value == null || value.isEmpty()) return "NOT_SET";
        if (value.startsWith("YOUR_")) return "NOT_CONFIGURED";
        return "***" + value.substring(Math.max(0, value.length() - 4));
    }

    /**
     * Recarga las credenciales (útil si se cambia el archivo)
     */
    public static void reload() {
        isLoaded = false;
        loadCredentials();
    }
}
