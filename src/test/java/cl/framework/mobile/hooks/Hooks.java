package cl.framework.mobile.hooks;

import cl.framework.mobile.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void beforeScenario() {

        System.out.println("=================================");
        System.out.println("Iniciando escenario...");
        System.out.println("=================================");

        DriverFactory.initializeDriver();
    }

    @After
    public void afterScenario() {

        System.out.println("=================================");
        System.out.println("Finalizando escenario...");
        System.out.println("=================================");
    }
}
