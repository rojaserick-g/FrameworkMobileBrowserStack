package StepDefinition;

import Constant.Constant;
import Control.BrowserStackCredentials;
import Control.DriverContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Hooks {

    private Scenario scenario;
    private static final String tomarCapturaPantalla;
    private static boolean credentialsValidated = false;

    static {
        tomarCapturaPantalla = System.getProperty("evidence", "fullEvidence");
    }

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        Constant.scenarioStep = scenario;
        Constant.build_name = "Nombre de Proyecto";

        if (!credentialsValidated) {
            System.out.println("\n📋 Validando configuración de BrowserStack...");
            BrowserStackCredentials.printConfig();
            credentialsValidated = true;
        }
    }

    @After
    public void tearDown() {
        WebDriver driver = DriverContext.getDriver();

        if (driver != null) {
            try {
                // Solo enviar estado si la sesión es remota (RemoteWebDriver en BrowserStack)
                if (driver instanceof RemoteWebDriver && !(driver.getClass().getSimpleName().equals("ChromeDriver"))) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;

                    String status = scenario.isFailed() ? "failed" : "passed";
                    String reason = scenario.isFailed() ? "Scenario failed" : "Scenario passed";

                    String script = String.format(
                            "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"%s\", \"reason\": \"%s\"}}",
                            status, reason
                    );

                    js.executeScript(script);

                    if (scenario.isFailed()) {
                        System.out.println("❌ Escenario marcado como FAILED en BrowserStack");
                    } else {
                        System.out.println("✅ Escenario marcado como PASSED en BrowserStack");
                    }
                } else {
                    System.out.println("ℹ Ejecución Local: Se omite la actualización de estado en BrowserStack.");
                }

            } catch (Exception e) {
                System.out.println("⚠ No fue posible actualizar el estado en BrowserStack: " + e.getMessage());
            } finally {
                DriverContext.quitDriver();
            }
        }
    }

    public void generarEvidencia(String imageRefName) {
        if (DriverContext.getDriver() == null) {
            return;
        }

        byte[] screenShot = ((TakesScreenshot) DriverContext.getDriver()).getScreenshotAs(OutputType.BYTES);
        this.scenario.attach(screenShot, "image/png", imageRefName);
    }

    @AfterStep
    public void capturaEvidencia() {
        if (DriverContext.getDriver() == null) {
            return;
        }

        if (scenario.isFailed()) {
            generarEvidencia("[FAIL] Step ScreenShots");
        } else if (tomarCapturaPantalla.equalsIgnoreCase("fullEvidence")) {
            generarEvidencia("[SUCCESS] Step ScreenShots");
        }
    }
}