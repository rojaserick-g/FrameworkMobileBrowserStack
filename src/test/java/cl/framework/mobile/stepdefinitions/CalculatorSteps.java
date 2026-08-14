package cl.framework.mobile.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import cl.framework.mobile.driver.DriverFactory;
import cl.framework.mobile.pages.CalculatorPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorSteps {

    private CalculatorPage calculatorPage;

    @Given("que abro la aplicación")
    public void queAbroLaAplicacion() {

        calculatorPage = new CalculatorPage(
                DriverFactory.getDriver()
        );
    }

    @When("realizo la operación {int} más {int}")
    public void realizoLaOperacion(int numero1, int numero2) {

        calculatorPage.presionarNumero(String.valueOf(numero1));
        calculatorPage.presionarSuma();
        calculatorPage.presionarNumero(String.valueOf(numero2));
        calculatorPage.presionarIgual();
    }

    @Then("el resultado debería ser {int}")
    public void validoElResultado(int resultadoEsperado) {

        String resultado = calculatorPage.obtenerResultado();

        assertTrue(
                resultado.contains(String.valueOf(resultadoEsperado)),
                "Se esperaba " + resultadoEsperado +
                        " pero se obtuvo " + resultado
        );
    }
}