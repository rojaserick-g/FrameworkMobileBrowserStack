package StepDefinition;

import ObjectPage.LoginPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginDefinition {
    private final LoginPage loginPage = new LoginPage();

    @Given("")

    @And("ingresar el usuario {string}")
    public void ingresarElUsuario(String nombreUsuario) {

        loginPage.escribirUsersname(nombreUsuario);
    }

    @And("ingresar la pass {string}")
    public void ingresarLaPass(String pass) {
        loginPage.escribirPassword(pass);
    }

    @When("presiono el boton Login")
    public void presionoElBotonLogin() {
        loginPage.clickBtnLogin();
        try {
            Thread.sleep(2000); // Espera de 2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("se valida el mensaje de login invalido")
    public void seValidaElMensajeDeLoginInvalido() {
        loginPage.validarMsgUsuarioInvalido();
    }

    @Then("se valida el mensaje de pass invalido")
    public void seValidaElMensajeDePassInvalido() {
        loginPage.validarMsgPasswordInvalido();

    }

}