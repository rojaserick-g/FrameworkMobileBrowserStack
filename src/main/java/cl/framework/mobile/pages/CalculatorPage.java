package cl.framework.mobile.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CalculatorPage extends BasePage {

    public CalculatorPage(AndroidDriver driver) {
        super(driver);
    }

    public void presionarNumero(String numero) {
        driver.findElement(By.xpath(
                "//android.widget.Button[@text='" + numero + "']"
        )).click();
    }

    public void presionarSuma() {
        driver.findElement(By.xpath(
                "//android.widget.Button[@text='+']"
        )).click();
    }

    public void presionarIgual() {
        driver.findElement(By.xpath(
                "//android.widget.Button[@text='=']"
        )).click();
    }

    public String obtenerResultado() {

        List<WebElement> elementos = driver.findElements(
                By.xpath("//*[@text]")
        );

        for (WebElement elemento : elementos) {

            String texto = elemento.getAttribute("text");
            String clase = elemento.getAttribute("className");

            System.out.println(
                    "ELEMENTO -> clase: [" + clase + "] texto: [" + texto + "]"
            );

            if (texto != null
                    && !texto.trim().isEmpty()
                    && !clase.contains("Button")
                    && texto.trim().matches("-?\\d+(\\.\\d+)?")) {

                System.out.println(
                        "RESULTADO ENCONTRADO = [" + texto.trim() + "]"
                );

                return texto.trim();
            }
        }

        return "";
    }
}