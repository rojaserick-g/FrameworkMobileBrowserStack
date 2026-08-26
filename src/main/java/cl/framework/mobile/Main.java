package cl.framework.mobile;

import cl.framework.mobile.driver.DriverFactory;

public class Main {

    public static void main(String[] args) {

        System.out.println("Inicio");

        DriverFactory.initializeDriver();

        System.out.println("Fin");

    }
}