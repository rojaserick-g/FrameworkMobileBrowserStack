package cl.framework.mobile.runners;

import cl.framework.mobile.driver.BrowserStackManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BrowserStackSmokeTest {

    @Test
    public void conectarConBrowserStack() {

        BrowserStackManager browserStackManager =
                new BrowserStackManager();

        try {

            System.out.println("=================================");
            System.out.println("Conectando con BrowserStack...");
            System.out.println("=================================");

            browserStackManager.startDriver();

            assertNotNull(
                    "El driver de BrowserStack no fue creado",
                    browserStackManager.getDriver()
            );

            System.out.println("BrowserStack conectado correctamente.");

            WebDriverWait wait = new WebDriverWait(
                    browserStackManager.getDriver(),
                    Duration.ofSeconds(30)
            );

            // Esperar el botón de búsqueda de Wikipedia
            var searchButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.id("org.wikipedia.alpha:id/search_container")
                    )
            );

            searchButton.click();

            System.out.println("Se abrió la búsqueda de Wikipedia.");

            // Escribir Chile
            var searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("org.wikipedia.alpha:id/search_src_text")
                    )
            );

            searchBox.sendKeys("Chile");

            System.out.println("Se escribió: Chile");

            Thread.sleep(3000);

            // Verificar que existen resultados
            var resultados = browserStackManager.getDriver()
                    .findElements(By.className("android.widget.TextView"));

            assertTrue(
                    "No se encontraron resultados",
                    resultados.size() > 0
            );

            System.out.println("Resultados encontrados: "
                    + resultados.size());

            System.out.println("=================================");
            System.out.println("TEST BROWSERSTACK COMPLETADO");
            System.out.println("SESSION ID: "
                    + browserStackManager.getDriver().getSessionId());
            System.out.println("=================================");

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            throw new RuntimeException(
                    "La prueba fue interrumpida",
                    e
            );

        } finally {

            browserStackManager.quitDriver();
        }
    }
}