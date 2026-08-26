package Control;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuración centralizada para BrowserStack
 * Permite gestionar capabilities y opciones de forma centralizada
 */
public class BrowserStackConfig {
    private static final Map<String, String> defaultCapabilities = new HashMap<>();

    static {
        // Configuración por defecto
        defaultCapabilities.put("browserName", "Chrome");
        defaultCapabilities.put("browserVersion", getOrDefault("BS_BROWSER_VERSION", "latest"));
        defaultCapabilities.put("os", getOrDefault("BS_OS", "Windows"));
        defaultCapabilities.put("osVersion", getOrDefault("BS_OS_VERSION", "11"));
        defaultCapabilities.put("project", getOrDefault("BS_PROJECT", "simpleTestAutomation"));
        defaultCapabilities.put("buildName", getOrDefault("BS_BUILD", "Test Build"));
        defaultCapabilities.put("sessionName", getOrDefault("BS_SESSION_NAME", "Test Session"));
    }

    /**
     * Obtiene una capability con valor por defecto
     */
    public static String getCapability(String key) {
        return defaultCapabilities.getOrDefault(key, "");
    }

    /**
     * Establece una capability
     */
    public static void setCapability(String key, String value) {
        defaultCapabilities.put(key, value);
    }

    /**
     * Obtiene todas las capabilities
     */
    public static Map<String, String> getAllCapabilities() {
        return new HashMap<>(defaultCapabilities);
    }

    /**
     * Helper para obtener variables de entorno o usar default
     */
    private static String getOrDefault(String envVar, String defaultValue) {
        String value = System.getenv(envVar);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    /**
     * Imprime la configuración actual
     */
    public static void printConfig() {
        System.out.println("=== BrowserStack Configuration ===");
        defaultCapabilities.forEach((key, value) ->
            System.out.println(key + ": " + value)
        );
        System.out.println("==================================");
    }
}
