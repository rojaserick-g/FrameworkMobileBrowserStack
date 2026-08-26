package ObjectPage;
import Control.BaseController;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import Control.DriverContext;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PimPage extends BaseController {

    public PimPage() {
        initPage();
    }

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement menuPim;
    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")
    private WebElement txtEmployeeName;
    @FindBy(xpath = "//div[@role='listbox']//span")
    private List<WebElement> listaEmpleados;
    @FindBy(xpath = "//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    private WebElement txtEmployeeId;
    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")
    private WebElement btnSearch;
    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[1]")
    private WebElement cmbEmploymentStatus;
    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[2]")
    private WebElement cmbInclude;
    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[3]")
    private WebElement cmbJobTitle;
    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[4]")
    private WebElement cmbSubUnit;
    @FindBy(xpath = "//label[text()='Supervisor Name']/../following-sibling::div//input")
    private WebElement txtSupervisor;
    @FindBy(xpath = "//div[@role='listbox']//span")
    private List<WebElement> listaSupervisor;
    @FindBy(xpath = "//div[@role='listbox']//span")
    private List<WebElement> listaSubUnit;
    @FindBy(xpath = "//div[@role='listbox']//span")
    private java.util.List<WebElement> listaJobTitle;
    @FindBy(xpath = "//div[@role='listbox']//span")
    private java.util.List<WebElement> listaInclude;
    @FindBy(xpath = "//div[@role='listbox']//span")
    private java.util.List<WebElement> listaEmploymentStatus;
    //validamos
    @FindBy(xpath = "//div[@class='oxd-table-body']")
    private WebElement tablaResultados;
    @FindBy(xpath = "//div[contains(@class,'oxd-table-card')]")
    private List<WebElement> filasResultados;
    @FindBy(xpath = "//div[contains(@class,'orangehrm-horizontal-padding')]//span")
    private WebElement lblRecordsFound;
    @FindBy(xpath = "//*[normalize-space()='No Records Found']")
    private WebElement lblNoRecords;
    @FindBy(xpath = "//div[contains(@class,'oxd-table-card')]")
    private java.util.List<WebElement> registrosEncontrados;
    @FindBy(xpath = "//button[@type='reset']")
    private WebElement btnReset;
    @FindBy(name = "firstName")
    private WebElement txtFirstNameDetalle;
    @FindBy(xpath = "//button[.//i[contains(@class,'bi-pencil-fill')]]")
    private WebElement btnEditar;
    @FindBy(xpath = "//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    private WebElement txtEmployeeIdDetalle;
    @FindBy(xpath = "//button[contains(@class,'oxd-pagination-page-item')]")
    private List<WebElement> paginas;
    @FindBy(xpath = "//div[contains(@class,'oxd-table-card')]")
    private List<WebElement> registrosPagina;
    @FindBy(xpath = "//button[text()=' Add ']")
    private WebElement btnAdd;
    @FindBy(name = "firstName")
    private WebElement txtFirstName;
    @FindBy(name = "lastName")
    private WebElement txtLastName;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSave;
    @FindBy(xpath = "//label[text()='Employee Id']/../following-sibling::div/input")
    private WebElement txtEmployeeIdNuevo;
    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement btnEmployeeList;
    @FindBy(xpath = "//i[contains(@class,'bi-trash')]")
    private WebElement btnDelete;
    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    private WebElement btnYesDelete;


    public void ingresarModuloPim() {
        try {
            visualizarElemento(menuPim, 10);
            menuPim.click();
        } catch (Exception e) {
            Assert.fail("Error al ingresar al módulo PIM: " + e.getMessage());
        }
    }

    public void escribirNombreEmpleado(String nombre) {
        try {
            visualizarElemento(txtEmployeeName, 10);
            txtEmployeeName.clear();
            txtEmployeeName.sendKeys(nombre);
        } catch (Exception e) {
            Assert.fail("Error al ingresar nombre: " + e.getMessage());
        }
    }

    public void escribirEmployeeId(String id) {
        try {
            visualizarElemento(txtEmployeeId, 10);
            txtEmployeeId.clear();
            txtEmployeeId.sendKeys(id);
        } catch (Exception e) {
            Assert.fail("Error al ingresar employee id: " + e.getMessage());
        }
    }

    public void clickSearch() {
        try {

            visualizarElemento(btnSearch, 10);
            btnSearch.click();

            Thread.sleep(2000);

        } catch (Exception e) {
            Assert.fail("Error al presionar Search: " + e.getMessage() );
        }
    }

    public void seleccionarEmploymentStatus(String estado) {
        try {
            visualizarElemento(cmbEmploymentStatus, 10);
            cmbEmploymentStatus.click();
            Thread.sleep(1000);
            for (WebElement opcion : listaEmploymentStatus) {
                if (opcion.getText().trim().equalsIgnoreCase(estado)) {
                    opcion.click();
                    break;
                }
            }

        } catch (Exception e) {

            Assert.fail("Error seleccionando Employment Status: " + e.getMessage());
        }
    }

    public void seleccionarInclude(String opcion) {
        try {
            visualizarElemento(cmbInclude, 10);
            cmbInclude.click();
            Thread.sleep(1000);
            for (WebElement item : listaInclude) {
                if (item.getText().trim().equalsIgnoreCase(opcion)) {
                    item.click();
                    break;
                }
            }

        } catch (Exception e) {
            Assert.fail("Error seleccionando Include: " + e.getMessage());
        }
    }

    public void seleccionarJobTitle(String cargo) {
        try {
            visualizarElemento(cmbJobTitle, 10);
            cmbJobTitle.click();
            Thread.sleep(1000);
            for (WebElement opcion : listaJobTitle) {
                if (opcion.getText().trim().equalsIgnoreCase(cargo)) {
                    opcion.click();
                    break;
                }
            }

        } catch (Exception e) {
            Assert.fail("Error seleccionando Job Title: " + e.getMessage());
        }
    }

    public void seleccionarSubUnit(String subUnit) {
        try {
            visualizarElemento(cmbSubUnit, 10);
            cmbSubUnit.click();
            Thread.sleep(1000);
            for (WebElement opcion : listaSubUnit) {
                if (opcion.getText().trim().equalsIgnoreCase(subUnit)) {
                    opcion.click();
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail("Error seleccionando Sub Unit: " + e.getMessage());
        }
    }

    public void bajarHastaResultados() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverContext.getDriver();
            js.executeScript("window.scrollBy(0,700)");
            Thread.sleep(1000);
        } catch (Exception e) {
            Assert.fail("Error al hacer scroll: " + e.getMessage());
        }
    }

    public void validarResultadosVisibles() {
        try {
            visualizarElemento(tablaResultados, 10);
            Assert.assertTrue("La tabla de resultados no se muestra.",
                    tablaResultados.isDisplayed()
            );
        } catch (Exception e) {
            Assert.fail("Error al validar la tabla: " + e.getMessage());
        }
    }

    public void validarNombreEmpleado(String nombreEsperado) {
        try {
            System.out.println("Cantidad de filas: " + filasResultados.size());
            for (WebElement fila : filasResultados) {
                System.out.println("Fila: " + fila.getText());
            }
            Assert.assertFalse("La búsqueda no retornó resultados.", filasResultados.isEmpty());
            boolean encontrado = false;
            for (WebElement fila : filasResultados) {
                String texto = fila.getText();
                System.out.println(texto);
                if (texto.toLowerCase().contains(nombreEsperado.toLowerCase())) {
                    encontrado = true;
                    break;
                }
            }
            Assert.assertTrue("No se encontró el empleado: " + nombreEsperado, encontrado);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void validarCantidadRegistros(String cantidadEsperada) {
        try {
            bajarHastaResultados();
            if (cantidadEsperada.equals("1")) {
                Assert.assertFalse("Se esperaba un registro y no se encontró ninguno.", registrosEncontrados.isEmpty());
                System.out.println("Se encontró un registro correctamente.");
            } else {
                WebDriverWait wait = new WebDriverWait(
                        DriverContext.getDriver(),
                        Duration.ofSeconds(10)
                );

                wait.until(ExpectedConditions.visibilityOf(lblNoRecords));
                Assert.assertTrue("Se esperaba el mensaje 'No Records Found'.", lblNoRecords.isDisplayed());
                System.out.println("No se encontraron registros, validación correcta.");
            }
        } catch (Exception e) {
            Assert.fail("Error validando cantidad de registros: " + e.getMessage());
        }
    }

    public void seleccionarSupervisor(String supervisor) {
        try {
            visualizarElemento(txtSupervisor, 10);
            txtSupervisor.clear();
            txtSupervisor.sendKeys(supervisor);
            Thread.sleep(2000);
            for (WebElement opcion : listaSupervisor) {
                if (opcion.getText().trim().equalsIgnoreCase(supervisor)) {
                    opcion.click();
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail("Error seleccionando Supervisor: " + e.getMessage());
        }
    }

    public void ingresarNombreEmpleado(String nombre) {
        txtEmployeeName.clear();
        txtEmployeeName.sendKeys(nombre);
    }

    public void validarResultadosFiltros() {
        try {
            visualizarElemento(tablaResultados, 10);
            Assert.assertTrue("La tabla de resultados no se muestra.", tablaResultados.isDisplayed());

            Assert.assertFalse("No se encontraron registros.", registrosEncontrados.isEmpty());
        } catch (Exception e) {
            Assert.fail("Error validando resultados: " + e.getMessage());
        }
    }

    public void clickReset() {
        try {
            visualizarElemento(btnReset, 10);
            btnReset.click();
        } catch (Exception e) {
            Assert.fail("Error al presionar Reset: " + e.getMessage());

        }

    }

    public void validarCamposReseteados() {
        try {
            Assert.assertEquals("", txtEmployeeName.getAttribute("value"));
            Assert.assertEquals("", txtEmployeeId.getAttribute("value"));
        } catch (Exception e) {
            Assert.fail("Error validando campos reseteados: " + e.getMessage());

        }
    }

    public void abrirDetalleEmpleado() {
        try {

            WebDriverWait wait = new WebDriverWait(
                    DriverContext.getDriver(),
                    Duration.ofSeconds(10));

            WebElement botonEditar = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//i[contains(@class,'bi-pencil-fill')]]")));

            botonEditar.click();

        } catch (Exception e) {
            Assert.fail("Error al abrir el detalle del empleado: " + e.getMessage());
        }
    }

    public void validarDetalleEmpleado(String nombre, String id) {
        try {
            visualizarElemento(txtFirstNameDetalle, 10);
            Assert.assertEquals(
                    nombre,
                    txtFirstNameDetalle.getAttribute("value"));
            Assert.assertEquals(
                    id,
                    txtEmployeeIdDetalle.getAttribute("value"));
        } catch (Exception e) {
            Assert.fail("Error validando detalle del empleado: " + e.getMessage());
        }
    }

    //metodo para navegar
    public void navegarPagina(String pagina) {
        try {
        } catch (Exception e) {
            Assert.fail("Error navegando: " + e.getMessage());
        }
    }

    //validar pagina
    public void validarPagina(String pagina) {
        try {
            visualizarElemento(tablaResultados, 10);
            Assert.assertTrue("La tabla no se muestra.", tablaResultados.isDisplayed());
        } catch (Exception e) {
            Assert.fail("Error validando página: " + e.getMessage());

        }

    }

    // validar registro
    public void validarRegistrosPagina() {
        try {
            Assert.assertFalse("No existen registros en la página.", registrosEncontrados.isEmpty());
        } catch (Exception e) {
            Assert.fail("Error validando registros: " + e.getMessage());

        }
    }

    public void validarNoEjecucionScript() {
        try {
            visualizarElemento(tablaResultados, 10);
            Assert.assertTrue("La aplicación ejecutó un script o dejó de responder.", tablaResultados.isDisplayed());

        } catch (Exception e) {
            Assert.fail("Error validando protección XSS: " + e.getMessage());

        }

    }

    public void validarAplicacionEstable() {
        try {
            Assert.assertTrue("La aplicación salió del módulo PIM.", DriverContext.getDriver().getCurrentUrl().contains("pim"));

        } catch (Exception e) {
            Assert.fail("La aplicación no permanece estable: " + e.getMessage());
        }
    }

    public void validarSQLInjection() {
        try {
            visualizarElemento(tablaResultados, 10);
            Assert.assertTrue("La aplicación devolvió un resultado inesperado.", tablaResultados.isDisplayed());
        } catch (Exception e) {
            Assert.fail("Error validando SQL Injection: " + e.getMessage());
        }

    }

    //abrir add employee
    public void clickAdd() {
        try {
            visualizarElemento(btnAdd, 10);
            btnAdd.click();
        } catch (Exception e) {
            Assert.fail("Error al abrir Add Employee: " + e.getMessage());
        }
    }

    public void volverEmployeeList() {
        try {
            visualizarElemento(btnEmployeeList, 10);
            btnEmployeeList.click();
        } catch (Exception e) {
            Assert.fail("Error al volver a Employee List: " + e.getMessage());
        }
    }

    // Variables de la clase
    private String firstNameGenerado;
    private String lastNameGenerado;
    private String employeeIdGenerado;

    // ingresar nombre
    public void ingresarNuevoEmpleado(String nombre, String apellido) {
        try {
            visualizarElemento(txtFirstName, 10);

            // Guardar los datos para validarlos después
            firstNameGenerado = nombre;
            lastNameGenerado = apellido;

            txtFirstName.clear();
            txtFirstName.sendKeys(nombre);

            txtLastName.clear();
            txtLastName.sendKeys(apellido);

        } catch (Exception e) {
            Assert.fail("Error ingresando empleado: " + e.getMessage());
        }
    }

    // guardar empleado
    public void guardarEmpleado() {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverContext.getDriver(),
                    Duration.ofSeconds(20));

            // Esperar que desaparezca el loader
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.className("oxd-form-loader")));

            // Esperar que el botón realmente sea clickeable
            WebElement botonGuardar = wait.until(
                    ExpectedConditions.elementToBeClickable(btnSave));

            botonGuardar.click();
            // Esperar que cambie a Personal Details
            wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));

            System.out.println("URL después del Save: "
                    + DriverContext.getDriver().getCurrentUrl());

        } catch (Exception e) {
            Assert.fail("Error guardando empleado: " + e.getMessage());
        }
    }
    // guardar Employee ID generado
    public void guardarEmployeeId() {
        try {
            visualizarElemento(txtEmployeeIdNuevo, 10);

            String id = txtEmployeeIdNuevo.getAttribute("value");

            if (id == null || id.trim().isEmpty()) {
                Assert.fail("El Employee ID quedó vacío.");
            }

            employeeIdGenerado = id;

            System.out.println("Employee ID generado: " + employeeIdGenerado);

        } catch (Exception e) {
            Assert.fail("Error obteniendo Employee ID: " + e.getMessage());
        }
    }

    // asignar Employee ID desde el feature
    public void setEmployeeIdGenerado(String employeeId) {
        this.employeeIdGenerado = employeeId;
    }

    // buscar empleado por parámetro
    public void buscarEmpleado(String employeeId) {
        volverEmployeeList();
        escribirEmployeeId(employeeId);
        clickSearch();
    }

    // buscar el empleado creado
    public void buscarEmpleadoCreado() {
        try {

            System.out.println("Buscando Employee ID: " + employeeIdGenerado);

            volverEmployeeList();

            WebDriverWait wait = new WebDriverWait(
                    DriverContext.getDriver(),
                    Duration.ofSeconds(20));

            // Esperar que cargue Employee List
            wait.until(ExpectedConditions.urlContains("viewEmployeeList"));

            WebElement campoEmployeeId = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input")));

            campoEmployeeId.clear();
            campoEmployeeId.sendKeys(employeeIdGenerado);

            WebElement botonSearch = wait.until(
                    ExpectedConditions.elementToBeClickable(btnSearch));
            botonSearch.click();
            wait.until(ExpectedConditions.visibilityOf(tablaResultados));

        } catch (Exception e) {
            Assert.fail("Error buscando empleado: " + e.getMessage());
        }
    }

    // validar creación
    public void validarEmpleadoCreado() {
        try {

            Thread.sleep(3000);

            List<WebElement> filas = DriverContext.getDriver().findElements(
                    By.xpath("//div[contains(@class,'oxd-table-card')]"));
            Assert.assertFalse(
                    "No se encontró el empleado creado.",
                    filas.isEmpty());
        } catch (Exception e) {
            Assert.fail("Error validando empleado creado: " + e.getMessage());
        }
    }

    // validar eliminación
    public void validarEmpleadoEliminado() {
        try {
            visualizarElemento(lblNoRecords, 10);
            Assert.assertTrue("El empleado aún existe.", lblNoRecords.isDisplayed());
        } catch (Exception e) {
            Assert.fail("Error validando eliminación: " + e.getMessage());
        }
    }

    // eliminar empleado
    public void eliminarEmpleado() {
        try {
            volverEmployeeList();
            escribirEmployeeId(employeeIdGenerado);
            clickSearch();
            WebDriverWait wait = new WebDriverWait(
                    DriverContext.getDriver(),
                    Duration.ofSeconds(10));

            WebElement botonEliminar = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//i[contains(@class,'bi-trash')]")));
            botonEliminar.click();
            WebElement botonConfirmar = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='Yes, Delete']")));
            botonConfirmar.click();

        } catch (Exception e) {
            Assert.fail("Error eliminando empleado: " + e.getMessage());
        }
    }

    public void abrirEmpleado() {
        try {

            visualizarElemento(btnEditar, 10);
            btnEditar.click();

        } catch (Exception e) {
            Assert.fail("Error abriendo empleado: " + e.getMessage());
        }
    }

    // validar información del empleado
    public void validarInformacionEmpleado(String nombre, String employeeId) {

        try {

            Thread.sleep(2000);

            System.out.println("URL = " + DriverContext.getDriver().getCurrentUrl());

            List<WebElement> firstNames =
                    DriverContext.getDriver().findElements(By.name("firstName"));

            List<WebElement> lastNames =
                    DriverContext.getDriver().findElements(By.name("lastName"));

            List<WebElement> employeeIds =
                    DriverContext.getDriver().findElements(By.xpath("//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input"));

            System.out.println("Cantidad FirstName: " + firstNames.size());
            System.out.println("Cantidad LastName: " + lastNames.size());
            System.out.println("Cantidad EmployeeId: " + employeeIds.size());

            for (int i = 0; i < firstNames.size(); i++) {
                System.out.println("FirstName[" + i + "] = " + firstNames.get(i).getAttribute("value"));
            }

            for (int i = 0; i < lastNames.size(); i++) {
                System.out.println("LastName[" + i + "] = " + lastNames.get(i).getAttribute("value"));
            }

            for (int i = 0; i < employeeIds.size(); i++) {
                System.out.println("EmployeeId[" + i + "] = " + employeeIds.get(i).getAttribute("value"));
            }

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}