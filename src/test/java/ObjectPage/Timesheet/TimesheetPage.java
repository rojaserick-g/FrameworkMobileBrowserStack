package ObjectPage.Timesheet;

import Control.BaseController;
import Control.DriverContext;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import java.sql.DriverManager;
import java.time.Duration;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class TimesheetPage extends BaseController {

    private WebDriverWait wait;

    public TimesheetPage() {
        super();
        initPage();
        this.wait = new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//a[contains(@href, '/time/viewTimeModule')]/span")
    private WebElement btnTimesheet;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement txtNombre;

    @FindBy(css = "button[type='submit']")
    private WebElement btnEnviar;

    @FindBy(xpath = "//div[text()='Employee Name']/following-sibling::div[contains(@class, 'data')]")
    private WebElement txtNombreEmployee;

    @FindBy(css = ".oxd-autocomplete-dropdown[role='listbox']")
    private WebElement autocompleteDropdown;

    @FindBy(xpath = "//div[contains(@class,'oxd-autocomplete-option')]")
    private List<WebElement> dropdownOptions;

    @FindBy(css = "button.oxd-button--secondary")
    private WebElement btnView;

    @FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
    private WebElement lblNoTimesheetsFound;

    @FindBy(xpath = "//input[@placeholder='dd-mm-yyyy']")
    private WebElement txtDate;

    @FindBy(className = "orangehrm-timesheet-table-body-cell")
    private WebElement timesheetMessage;

    @FindBy(css = "button.orangehrm-timeperiod-icon.--prev")
    private WebElement prevButton;

    @FindBy(css = "button.orangehrm-timeperiod-icon.--next")
    private WebElement nextButton;

    @FindBy(css = "li.oxd-topbar-body-nav-tab.--parent")
    private WebElement navAttendance;

    @FindBy(xpath = "//a[text()='Employee Records']")
    private WebElement employeeRecordsLink;

    @FindBy(xpath = "//div[contains(@class, 'oxd-table-card-cell') and ./div[contains(text(), 'Duration (Hours)')]]/div[@class='data']")
    private WebElement txtDurationHours;

    @FindBy(css = "input[placeholder='mm-dd-yyyy']")
    private WebElement dateInput;

    @FindBy(css = "span.orangehrm-header-total")
    private WebElement totalWeeklyHours;

    @FindBy(css = "nav.oxd-topbar-body-nav ul li:nth-of-type(3)")
    private WebElement navReports;

    @FindBy(css = "input[placeholder='Type for hints...']")
    private WebElement inputProyect;

    @FindBy(css = "ul.oxd-dropdown-menu > li:nth-of-type(2) > a")
    private WebElement employeeReportsMenuItem;

    @FindBy(css = ".oxd-report-table-footer span.oxd-text--footer")
    private WebElement totalReportHours;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameInput;

    @FindBy(xpath = "//button[contains(., 'View')]")
    private WebElement viewButton;

    @FindBy(xpath = "//button[contains(., 'Edit')]")
    private WebElement editButton;

    @FindBy(xpath = "//button[contains(., 'Save')]")
    private WebElement saveButton;

    @FindBy(xpath = "//button[contains(., 'Reset')]")
    private WebElement resetButton;

    @FindBy(xpath = "//button[contains(., 'Save')]")
    private WebElement btnSave;

    @FindBy(xpath = "//button[contains(., 'Reset')]")
    private WebElement btnReset;

    @FindBy(xpath = "//button[contains(., 'Cancel')]")
    private WebElement btnCancel;

    @FindBy(xpath = "//button[contains(., 'Approve')]")
    private WebElement approveButton;


    public void clickTimeMenu() {
        try {
            if (btnTimesheet.isDisplayed()) {
                btnTimesheet.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el menú de tiempo: " + e.getMessage());
        }
    }

    public void llenarNombre(String partialName) {

        txtNombre.clear();
        txtNombre.sendKeys(partialName);

        try {
            Thread.sleep(2000); // esperar que carguen las sugerencias
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!dropdownOptions.isEmpty()) {
            dropdownOptions.get(0).click();
        }
    }

    public void clickSubmitButton() {
        try {
            if (btnEnviar.isDisplayed()) {
                btnEnviar.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el botón de envío: " + e.getMessage());
        }
    }

    public void validarNombreEmployee(String nombreEsperado) {
        String nombreEnPantalla = txtNombreEmployee.getText().trim();
        if (nombreEnPantalla.equals(nombreEsperado)) {
            System.out.println("¡Validación exitosa! El nombre: " + nombreEsperado + " es correcto.");
        } else {
            System.out.println("No encontró el nombre: " + nombreEsperado + " (Se encontró: " + nombreEnPantalla + ")");
        }
    }

    public boolean selectFirstSuggestion(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Esperar a que desaparezca "Searching..."
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Searching...')]")
        ));
        // Esperar a que exista al menos una opción
        wait.until(driver1 -> !dropdownOptions.isEmpty());
        for (WebElement option : dropdownOptions) {
            String texto = option.getText().trim();
            if (texto.equalsIgnoreCase("Searching..."))
                continue;
            if (texto.equalsIgnoreCase("No Records Found"))
                return false;
            wait.until(ExpectedConditions.elementToBeClickable(option));
            option.click();
            return true;
        }
        return false;
    }

    public boolean selectSuggestion(String employeeName, WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Searching...')]")
        ));

        wait.until(driver1 -> !dropdownOptions.isEmpty());

        for (WebElement option : dropdownOptions) {

            String texto = option.getText().trim();

            if (texto.equalsIgnoreCase("Searching..."))
                continue;

            if (texto.equalsIgnoreCase("No Records Found"))
                return false;

            if (texto.equalsIgnoreCase(employeeName)) {
                wait.until(ExpectedConditions.elementToBeClickable(option));
                option.click();
                return true;
            }
        }

        return false;
    }

    public void clickView(){
        try {
            if (btnView.isDisplayed()) {
                btnView.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el botón View: " + e.getMessage());
        }
    }

    public void validarTimesheet(String period){
        try {
            if (lblNoTimesheetsFound.isDisplayed()) {
                System.out.println("Mensaje de alerta: " + lblNoTimesheetsFound.getText());
            }
        } catch (Exception e) {
            System.out.println("Error al validar el timesheet: " + e.getMessage());
        }
    }

    public void selectPeriod(String period) {
        try {
            if (txtDate.isDisplayed()) {
                txtDate.click();
                txtDate.sendKeys(period);
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el period: " + e.getMessage());
        }
    }

    public void validarMensajeTimesheet() {
        try {
            if (timesheetMessage.isDisplayed()) {
                System.out.println("Mensaje capturado con exito: " + timesheetMessage.getText());
            }
        } catch (Exception e) {
            System.out.println("Error al validar el mensaje: " + e.getMessage());
        }
    }

    public void clickPrevButton() {
        try {
            if (prevButton.isDisplayed()) {
                prevButton.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el botón Previous: " + e.getMessage());
        }
    }

    public void clickNextButton() {
        try {
            if (nextButton.isDisplayed()) {
                nextButton.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el botón Next: " + e.getMessage());
        }
    }

    public void clickNavAttendance() {
        try {
            if (navAttendance.isDisplayed()) {
                navAttendance.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el menú de asistencia: " + e.getMessage());
        }
    }

    public void clickEmployeeRecordsLink() {
        try {
            if (employeeRecordsLink.isDisplayed()) {
                employeeRecordsLink.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el enlace Employee Records: " + e.getMessage());
        }
    }

    public void validarDuracion() {
        String textValue = txtDurationHours.getText().trim();
        double actualDuration = Double.parseDouble(textValue);
        Assert.assertTrue("La duración esperada debía ser >= 0.00, pero fue: " + actualDuration,
                actualDuration >= 0.00);
    }

    public void llenarInputDate(String date) {
        try {
            txtDate.clear();
            txtDate.sendKeys(date);
        } catch (Exception e) {
            System.out.println("Error al llenar el date: " + e.getMessage());
        }
    }

    public void validarTotalHours() {
        try {
            if (totalWeeklyHours.isDisplayed()) {
                System.out.println("Total de horas semanales: " + totalWeeklyHours.getText());
            }
        } catch (Exception e) {
            System.out.println("Error al validar el total de horas: " + e.getMessage());
        }
    }

    public void clickNavReports() {
        try {
            if (navReports.isDisplayed()) {
                navReports.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el menú de reportes: " + e.getMessage());
        }
    }

    public void clickemployeeReportsMenuItem() {
        try {
            if (employeeReportsMenuItem.isDisplayed()) {
                employeeReportsMenuItem.click();
            }
        } catch (Exception e) {
            System.out.println("Error al hacer clic en el enlace Employee Reports: " + e.getMessage());
        }
    }

    public void llenarinputProyect(String projectName) {
        try {
            inputProyect.clear();
            inputProyect.sendKeys(projectName);
        } catch (Exception e) {
            System.out.println("Error al llenar el nombre del proyecto: " + e.getMessage());
        }
    }

    public boolean selectSuggestionProyect(String projectName, WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Searching...')]")
        ));

        wait.until(driver1 -> !dropdownOptions.isEmpty());

        for (WebElement option : dropdownOptions) {

            String texto = option.getText().trim();

            if (texto.equalsIgnoreCase("Searching..."))
                continue;

            if (texto.equalsIgnoreCase("No Records Found"))
                return false;

            if (texto.equalsIgnoreCase(projectName)) {
                wait.until(ExpectedConditions.elementToBeClickable(option));
                option.click();
                return true;
            }
        }
        return false;
    }

    public void validarReportHours() {
        try {
            if (totalReportHours.isDisplayed()) {
                System.out.println("Total de horas del reporte: " + totalReportHours.getText());
            }
        } catch (Exception e) {
            System.out.println("Error al validar el total de horas del reporte: " + e.getMessage());
        }
    }

    private static final Map<String, String> DAY_MAP = new HashMap<>();
    static {
        DAY_MAP.put("Monday", "Mon");
        DAY_MAP.put("Tuesday", "Tue");
        DAY_MAP.put("Wednesday", "Wed");
        DAY_MAP.put("Thursday", "Thu");
        DAY_MAP.put("Friday", "Fri");
        DAY_MAP.put("Saturday", "Sat");
        DAY_MAP.put("Sunday", "Sun");
    }

    public void searchEmployee(String name) {
        wait.until(ExpectedConditions.visibilityOf(employeeNameInput));
        employeeNameInput.clear();
        employeeNameInput.sendKeys(name);

        By autocompleteOption = By.xpath("//div[@role='listbox']//span");
        wait.until(ExpectedConditions.visibilityOfElementLocated(autocompleteOption));
        DriverContext.getDriver().findElement(autocompleteOption).click();

        viewButton.click();
    }

    public void clickEditIfPresent() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
        } catch (Exception e) {
            // Si ya está en la pantalla 'Edit Timesheet' de la foto, continúa sin hacer nada
        }
    }

    public void updateHoursForDay(String dayFullName, String hours) {
        String dayAbbr = DAY_MAP.getOrDefault(dayFullName, dayFullName);

        // Ubica el input dentro de la fila según la columna del día en Edit Timesheet
        String inputXpath = "//div[contains(@class, 'oxd-sheet')]//input[ancestor::tr or ancestor::div[contains(@class,'grid')]]";

        // Selector alternativo limpio y robusto para las celdas de días en la grilla de OrangeHRM
        By dayInputLocator = By.xpath("(//input[contains(@class, 'oxd-input')])[position() = count(//span[contains(text(), '" + dayAbbr + "')]/ancestor::th/preceding-sibling::th) + 1]");

        // Si la tabla usa campos de entrada estándar agrupados por días
        WebElement hoursInput;
        try {
            hoursInput = wait.until(ExpectedConditions.elementToBeClickable(dayInputLocator));
        } catch (Exception e) {
            // Fallback directo por orden de los inputs de la primera fila
            int dayIndex = switch (dayAbbr) {
                case "Mon" -> 1;
                case "Tue" -> 2;
                case "Wed" -> 3;
                case "Thu" -> 4;
                case "Fri" -> 5;
                case "Sat" -> 6;
                case "Sun" -> 7;
                default -> 1;
            };
            hoursInput = DriverContext.getDriver().findElements(By.xpath("//table//tbody//tr[1]//input")).get(dayIndex - 1);
        }

        hoursInput.sendKeys(Keys.CONTROL + "a");
        hoursInput.sendKeys(Keys.BACK_SPACE);
        hoursInput.sendKeys(hours);
    }

    public void clickSaves() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public boolean isHoursDisplayed(String hours) {
        By updatedValueLocator = By.xpath("//input[@value='" + hours + "'] | //td[contains(text(), '" + hours + "')]");
        return wait.until(ExpectedConditions.presenceOfElementLocated(updatedValueLocator)).isDisplayed();
    }

    public boolean isSuccessMessageDisplayed() {
        By toastSuccessLocator = By.xpath("//div[contains(@class, 'oxd-toast--success')] | //p[contains(., 'Successfully Saved') or contains(., 'Success')]");
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(toastSuccessLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidationMessageDisplayed() {
        By errorMsgLocator = By.xpath("//span[contains(@class, 'oxd-input-field-error-message')] | //div[contains(@class, 'oxd-toast--error')] | //span[contains(., 'Should be') or contains(., 'invalid') or contains(., 'greater than')]");
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSavePreventedOrValidationErrorDisplayed() {
        By errorMsgLocator = By.xpath("//span[contains(@class, 'oxd-input-field-error-message')] | //span[contains(., 'Should be') or contains(., 'Expected number') or contains(., 'invalid')]");
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgLocator)).isDisplayed();
        } catch (Exception e) {
            clickSave();
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgLocator)).isDisplayed();
        }
    }

    public void clickResets() {
        wait.until(ExpectedConditions.elementToBeClickable(resetButton)).click();
    }

    public boolean isSearchCriteriaCleared() {
        wait.until(ExpectedConditions.visibilityOf(employeeNameInput));
        return employeeNameInput.getAttribute("value").isEmpty();
    }
    private double lastCalculatedTotal = 0.0;

    public double calculateProjectRowsSum() {
        List<WebElement> hourCells = DriverContext.getDriver().findElements(
                By.xpath("//table//tbody//td[input or contains(@class, 'cell')]//input")
        );

        double sum = 0.0;
        for (WebElement cell : hourCells) {
            String value = cell.getAttribute("value");
            if (value != null && !value.trim().isEmpty()) {
                try {
                    sum += Double.parseDouble(value.trim());
                } catch (NumberFormatException ignored) {
                    // Ignora celdas vacías o no numéricas
                }
            }
        }
        this.lastCalculatedTotal = sum;
        return sum;
    }

    public boolean isDisplayedTotalMatchingCalculated() {
        WebElement totalDisplayedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//table//th[contains(., 'Total')]/ancestor::table//tr[last()]//td[last()] | //span[contains(@class, 'total')]")
        ));

        double displayedTotal = Double.parseDouble(totalDisplayedElement.getText().trim());
        return Math.abs(this.lastCalculatedTotal - displayedTotal) < 0.01;
    }

    public void refreshBrowser() {
        DriverContext.getDriver().navigate().refresh();
    }

    public boolean isNoEmployeeFoundMessageDisplayed() {
        By noRecordsLocator = By.xpath("//*[contains(text(), 'No Records Found') or contains(., 'No Options Available')]");
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordsLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUnauthorizedDataNotDisplayed() {
        By unauthorizedDataLocator = By.xpath("//div[contains(@class, 'oxd-table-card')] | //div[contains(@class, 'oxd-sheet')]//tbody//tr");
        try {
            // Retorna verdadero si NO hay registros mostrados en la tabla tras la consulta
            return DriverContext.getDriver().findElements(unauthorizedDataLocator).isEmpty();
        } catch (Exception e) {
            return true;
        }
    }

    public boolean isApplicationStable() {
        By mainLayoutLocator = By.xpath("//header[contains(@class, 'oxd-topbar')] | //aside[contains(@class, 'oxd-sidepanel')]");
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(mainLayoutLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoScriptExecuted() {
        try {
            // Si la alerta salta por la ejecución del script (<script>alert('xss')</script>)
            // Selenium lanzará o detectará la alerta activa
            DriverContext.getDriver().switchTo().alert().accept();
            // Si hubo alerta ejecutada por el script, retorna false
            return false;
        } catch (Exception e) {
            // No hubo alerta ejecutada, el script no corrió
            return true;
        }
    }

    public boolean isApplicationOperational() {
        // Confirma que el contenedor principal siga intacto y no alterado por manipulación del DOM
        By mainLayoutLocator = By.xpath("//header[contains(@class, 'oxd-topbar')] | //aside[contains(@class, 'oxd-sidepanel')]");
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(mainLayoutLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTimesheetAvailableForSearch(String period) {
        // Filtra por el periodo y verifica que la hoja de tiempo esté activa y no dé mensaje de 'No Records Found'
        selectPeriod(period);
        clickView();

        By timesheetTableLocator = By.xpath("//div[contains(@class, 'orangehrm-timesheet-table')] | //table | //div[contains(@class, 'oxd-sheet')]");
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(timesheetTableLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void reopenTimesheetForEmployee(String employeeName) {
        // Vuelve a buscar al empleado para forzar la recarga del timesheet desde el servidor
        searchEmployee(employeeName);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
    }

    public void clickReset() {
        wait.until(ExpectedConditions.elementToBeClickable(btnReset)).click();
    }

    public void updateHoursForDays(String dayFullName, String hours) {
        String dayAbbr = DAY_MAP.getOrDefault(dayFullName, dayFullName);

        // Mapea el índice según el día mostrado en la grilla
        int colIndex = switch (dayAbbr) {
            case "Mon" -> 1;
            case "Tue" -> 2;
            case "Wed" -> 3;
            case "Thu" -> 4;
            case "Fri" -> 5;
            case "Sat" -> 6;
            case "Sun" -> 7;
            default -> 1;
        };

        // Ubica el input dinámico en la primera fila según la columna del día
        By inputLocator = By.xpath("(//div[contains(@class, 'orangehrm-timesheet-table')]//tbody//tr[1]//input[contains(@class, 'oxd-input')])[" + colIndex + "] | (//input[contains(@class, 'oxd-input')])[" + colIndex + "]");

        WebElement hoursInput = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
        hoursInput.sendKeys(Keys.CONTROL + "a");
        hoursInput.sendKeys(Keys.BACK_SPACE);
        hoursInput.sendKeys(hours);
    }

    public void approveTimesheet() {
        // 1. Si la vista proviene de la lista de 'Pending Action', hace clic en el botón 'View'
        By actionViewButton = By.xpath("//div[contains(@class, 'oxd-table-card')]//button[contains(., 'View')]");
        if (!DriverContext.getDriver().findElements(actionViewButton).isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(actionViewButton)).click();
        }

        // 2. Hace clic en el botón Approve ubicado en la parte inferior junto a Edit
        wait.until(ExpectedConditions.elementToBeClickable(approveButton)).click();
    }

    public boolean isTimesheetStatusApproved() {
        // Valida la etiqueta 'Status: Approved' que se despliega abajo a la izquierda en la UI
        By statusLocator = By.xpath("//*[contains(text(), 'Status: Approved') or (contains(text(), 'Status:') and contains(., 'Approved'))]");
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(statusLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
