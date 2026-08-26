package cl.framework.mobile.driver;

import io.appium.java_client.android.AndroidDriver;

public class DriverFactory {

    private static AndroidDriverManager androidDriverManager;

    public static void initializeDriver() {

        System.out.println("Entré a DriverFactory");

        androidDriverManager = new AndroidDriverManager();

        System.out.println("Objeto creado");

        androidDriverManager.startDriver();

        System.out.println("Método startDriver ejecutado");
    }

    public static AndroidDriver getDriver() {
        return androidDriverManager.getDriver();
    }
}