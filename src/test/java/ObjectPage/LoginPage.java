package ObjectPage;


import org.junit.Assert;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Control.BaseController;

public class LoginPage extends BaseController {

    public static SearchContext driver;

    public LoginPage() {
        initPage();
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")
    private WebElement nombreUsuario;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")
    private WebElement passwordUsuario;

    @FindBy(css = ".orangehrm-login-button")
    private WebElement btnLogin;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/span")
    private WebElement msgErrorUser;

    @FindBy(xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/span")
    private WebElement msgErrorPass;


    public void escribirUsersname(String nUsuario) {
        try {
            if (visualizarElemento(this.nombreUsuario, 10)) {
                this.nombreUsuario.clear();
                this.nombreUsuario.sendKeys(nUsuario);
            } else {
                Assert.fail("No se encuentra el campo Nombre de Usuario en la página");
            }
        } catch (Exception e) {
            Assert.fail("Error al escribir en el campo de usuario: " + e.getMessage());
        }
    }

    public void escribirPassword(String pass) {
        try {
            this.passwordUsuario.clear();
            this.passwordUsuario.sendKeys(pass);
        } catch (Exception e) {
            Assert.fail("Error al escribir en el campo de password: " + e.getMessage());
        }
    }

    public void clickBtnLogin() {
        try {
            this.btnLogin.click();
        } catch (Exception e) {
            Assert.fail("Error al hacer click en el botón Login: " + e.getMessage());
        }
    }

    public void validarMsgUsuarioInvalido(){
        try {
            if(this.msgErrorUser.isDisplayed()){
                System.out.println("Mensaje de usuario inválido visible");
            }else{
                System.out.println("Error al visualizar el mensaje de usuario inválido!");
            }
        }catch(Exception e){
            Assert.fail("Error al detectar el mensaje de usuario inválido: "+e.getMessage());
        }
    }

    public void validarMsgPasswordInvalido(){
        try {
            if(this.msgErrorPass.isDisplayed()){
                System.out.println("Mensaje de password inválido visible");
        }
            else{
                System.out.println("Error al visualizar el mensaje de password inválido!");
            }
        }catch(Exception e){
            Assert.fail("Error al detectar el mensaje de usuario inválido: "+e.getMessage());
        }
    }


}
