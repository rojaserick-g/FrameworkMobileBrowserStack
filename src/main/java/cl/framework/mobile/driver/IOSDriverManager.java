package cl.framework.mobile.driver;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URL;

public class IOSDriverManager {

    private IOSDriver driver;

    public void startDriver() {

        try {

            System.out.println("=================================");
            System.out.println("Conectando con iOS...");
            System.out.println("Iniciando XCUITest...");
            System.out.println("=================================");

            XCUITestOptions options = new XCUITestOptions();

            options.setPlatformName("iOS");
            options.setAutomationName("XCUITest");

            // Preparado para dispositivo iOS en BrowserStack
            options.setDeviceName("iPhone 15");
            options.setPlatformVersion("17");

            // La app se configurará cuando tengamos el App ID de BrowserStack
            // options.setApp("bs://XXXXXXXX");

            String username = System.getenv("BROWSERSTACK_USERNAME");
            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

            if (username == null || accessKey == null) {
                throw new RuntimeException(
                        "Faltan las variables BROWSERSTACK_USERNAME y BROWSERSTACK_ACCESS_KEY"
                );
            }

            options.setCapability("bstack:options", new java.util.HashMap<String, Object>() {{
                put("userName", username);
                put("accessKey", accessKey);
                put("projectName", "MobileAutomationFramework");
                put("buildName", "iOS Automation");
                put("sessionName", "iOS Test");
            }});

            driver = new IOSDriver(
                    new URL("https://hub-cloud.browserstack.com/wd/hub"),
                    options
            );

            System.out.println("=================================");
            System.out.println("Driver iOS conectado correctamente.");
            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println("Error al iniciar el Driver iOS.");
            e.printStackTrace();
        }
    }

    public IOSDriver getDriver() {
        return driver;
    }
}