package StepDefinition;

import ObjectPage.HomePage;
import io.cucumber.java.en.Then;

public class HomeDefinition {
    private final HomePage homePage = new HomePage();

    @Then("se valida el mensaje Logged In Successfully")
    public void seValidaElMensaje() {
        homePage.validarMsgBienvenida();
    }

}
