package cl.framework.mobile.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserStackManager {

    private AndroidDriver driver;

    public void startDriver() {

        try {

            System.out.println("=================================");
            System.out.println("Conectando con BrowserStack...");
            System.out.println("=================================");

            String username = System.getenv("BROWSERSTACK_USERNAME");
            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

            if (username == null || accessKey == null) {
                throw new RuntimeException(
                        "Faltan las credenciales de BrowserStack"
                );
            }

            UiAutomator2Options options = new UiAutomator2Options();

            options.setPlatformName("Android");
            options.setAutomationName("UiAutomator2");

            options.setApp(
                    "bs://f1994a610bf34c1a67e743ef1f0793945d515a1f"
            );

            options.setDeviceName("Google Pixel 7");
            options.setPlatformVersion("13.0");

            Map<String, Object> bstackOptions = new HashMap<>();

            bstackOptions.put("userName", username);
            bstackOptions.put("accessKey", accessKey);
            bstackOptions.put(
                    "projectName",
                    "MobileAutomationFramework"
            );
            bstackOptions.put(
                    "buildName",
                    "Android BrowserStack"
            );
            bstackOptions.put(
                    "sessionName",
                    "Android Sample App"
            );

            options.setCapability(
                    "bstack:options",
                    bstackOptions
            );

            driver = new AndroidDriver(
                    new URL(
                            "https://hub-cloud.browserstack.com/wd/hub"
                    ),
                    options
            );

            System.out.println("=================================");
            System.out.println(
                    "BrowserStack conectado correctamente."
            );
            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println(
                    "ERROR AL CONECTAR CON BROWSERSTACK:"
            );

            e.printStackTrace();

            throw new RuntimeException(
                    "No se pudo iniciar BrowserStack",
                    e
            );
        }
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public void quitDriver() {

        if (driver != null) {
            driver.quit();
        }
    }
}