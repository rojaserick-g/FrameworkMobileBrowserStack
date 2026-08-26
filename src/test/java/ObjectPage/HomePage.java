package ObjectPage;

import Control.BaseController;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseController {

    public HomePage() {
        initPage();
    }
    
    //@FindBy(xpath = "//button[contains(text(),'Congratulations student. You successfully logged in!')]")
    //private WebElement msgBienvenida;

    @FindBy(xpath = "//*[text() = 'Congratulations student. You successfully logged in!']")
    public WebElement msgBienvenida;


    public void validarMsgBienvenida(){
        try {
            if(this.msgBienvenida.isDisplayed()){
                System.out.println("EL mensaje es visible!");
            }else{
                System.out.println("Error al  visualizar el mensaje!");
            }
        }catch(Exception e){
            System.out.println("Error al detectar el mensaje de bienvenida: "+e.getMessage());
        }
    }

}
