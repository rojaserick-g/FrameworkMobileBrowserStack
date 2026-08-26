package cl.framework.mobile.driver;

import cl.framework.mobile.config.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;

public class AndroidDriverManager {

    private AndroidDriver driver;

    public void startDriver() {

        try {

            System.out.println("Conectando con Android...");
            System.out.println("Iniciando Appium...");

            UiAutomator2Options options = new UiAutomator2Options();

            options.setPlatformName("Android");
            options.setDeviceName(
                    ConfigReader.getProperty("deviceName")
            );
            options.setPlatformVersion(
                    ConfigReader.getProperty("platformVersion")
            );
            options.setAutomationName(
                    ConfigReader.getProperty("automationName")
            );

            options.setAppPackage(
                    "com.darkempire78.opencalculator"
            );

            options.setAppActivity(
                    ".activities.MainActivity"
            );

            driver = new AndroidDriver(
                    new URL(
                            ConfigReader.getProperty("appiumServer")
                    ),
                    options
            );

            System.out.println("=================================");
            System.out.println("Driver conectado correctamente.");
            System.out.println(
                    "Dispositivo: "
                            + ConfigReader.getProperty("deviceName")
            );
            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println(
                    "ERROR REAL AL INICIAR ANDROID DRIVER:"
            );

            e.printStackTrace();

            throw new RuntimeException(
                    "No se pudo iniciar AndroidDriver",
                    e
            );
        }
    }

    public AndroidDriver getDriver() {
        return driver;
    }
}