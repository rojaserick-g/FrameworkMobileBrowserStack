package StepDefinition.Timesheet;

import Control.DriverContext;
import ObjectPage.Timesheet.TimesheetPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class TimesheetDefinition {

    private final TimesheetPage timesheetPage = new TimesheetPage();
    private String lastUpdatedHours;
    private String targetPeriod;

    // Background
    @And("the user navigates to Employee Timesheets page")
    public void theUserNavigatesToEmployeeTimesheetsPage() throws InterruptedException {
        Thread.sleep(2000);
        timesheetPage.clickTimeMenu();
    }

    // Escenario 1
    @When("the user enters {string} in Employee Name field")
    public void theUserEntersInEmployeeNameField(String nombre) {
        timesheetPage.llenarNombre(nombre);
    }

    @And("clicks View")
    public void clicksView() {
        timesheetPage.clickSubmitButton();
    }

    @Then("the timesheet for {string} should be displayed")
    public void theTimesheetForShouldBeDisplayed(String nombre) {
        timesheetPage.validarNombreEmployee(nombre);
    }

    // Escenario 2
    @When("the user types {string} in Employee Name field")
    public void theUserTypesInEmployeeNameField(String nombre) {
        timesheetPage.llenarNombre(nombre);
    }

    @Then("employee suggestions containing {string} should be displayed")
    public void employee_suggestions_containing_should_be_displayed(String partialName) throws InterruptedException {
        Thread.sleep(5000);
        boolean seleccionado = timesheetPage.selectFirstSuggestion(DriverContext.getDriver());
        Assert.assertTrue("No fue posible seleccionar una sugerencia.", seleccionado);
    }

    // Escenario 3
    @When("the user searches employee {string}")
    public void theUserSearchesEmployee(String partialName) {
        timesheetPage.llenarNombre(partialName);
        timesheetPage.selectSuggestion(partialName, DriverContext.getDriver());
    }

    @And("the user clicks in view button")
    public void theUserClicksInViewButton() {
        timesheetPage.clickView();
    }

    @And("selects timesheet period {string}")
    public void selectsTimesheetPeriod(String period) {
        timesheetPage.selectPeriod(period);
    }

    @Then("the system should display the timesheet for {string}")
    public void theSystemShouldDisplayTheTimesheetFor(String period) throws InterruptedException {
        Thread.sleep(5000);
        timesheetPage.validarTimesheet(period);
    }

    @And("selects period {string}")
    public void selectsPeriod(String period) {
        timesheetPage.selectPeriod(period);
    }

    @When("the user searches employees {string}")
    public void theUserSearchesEmployees(String partialName) {
        timesheetPage.llenarNombre(partialName);
        timesheetPage.selectSuggestion(partialName, DriverContext.getDriver());
        timesheetPage.clickView();
    }

    @Then("a no timesheet available message should be displayed")
    public void noTimesheetAvailableMessage() {
        timesheetPage.validarMensajeTimesheet();
    }

    @Given("employee {string} timesheet is displayed")
    public void employeeTimesheetIsDisplayed(String employeeName) {
        timesheetPage.llenarNombre(employeeName);
        timesheetPage.selectSuggestion(employeeName, DriverContext.getDriver());
        timesheetPage.clickView();
    }

    @And("the user clicks Previous")
    public void theUserClicksPrevious() {
        timesheetPage.clickPrevButton();
    }

    @Then("the previous period timesheet should be displayed")
    public void thePreviousPeriodTimesheetShouldBeDisplayed() {
        timesheetPage.validarMensajeTimesheet();
    }

    @And("the user clicks Next")
    public void theUserClicksNext() {
        timesheetPage.clickNextButton();
    }

    @Then("the next period timesheet should be displayed")
    public void theNextPeriodTimesheetShouldBeDisplayed() {
        timesheetPage.validarMensajeTimesheet();
    }

    @Given("employee {string} has a timesheet")
    public void employeeHasATimesheet(String employeeName) {
        timesheetPage.llenarNombre(employeeName);
        timesheetPage.selectSuggestion(employeeName, DriverContext.getDriver());
        timesheetPage.clickView();
    }

    @When("the timesheet is displayed")
    public void theTimesheetIsDisplayed() {
        timesheetPage.validarMensajeTimesheet();
        timesheetPage.clickNavAttendance();
        timesheetPage.clickEmployeeRecordsLink();
    }

    @Then("employee {string} hours recorded for {string} should be greater than or equal to 0")
    public void employeeHoursRecordedForDayShouldBeGreaterThanOrEqualTo(String employeeName, String period) {
        timesheetPage.llenarNombre(employeeName);
        timesheetPage.llenarInputDate(period);
        timesheetPage.clickView();
        timesheetPage.validarDuracion();
    }

    @When("all daily hours are summed")
    public void allDailyHoursAreSummed() {
        timesheetPage.validarDuracion();
    }

    @Then("the calculated total should equal displayed total hours")
    public void theCalculatedTotalShouldEqualDisplayedToTotalHours() {
        timesheetPage.validarTotalHours();
    }

    @Given("employee {string} has project entries")
    public void employeeHasProjectEntries(String employeeName) throws InterruptedException {
        timesheetPage.clickNavReports();
        timesheetPage.clickemployeeReportsMenuItem();
        Thread.sleep(3000);
        timesheetPage.llenarNombre(employeeName);
        timesheetPage.selectSuggestion(employeeName, DriverContext.getDriver());
    }

    @When("project {string} is displayed")
    public void projectIsDisplayed(String projectName) {
        timesheetPage.llenarinputProyect(projectName);
        timesheetPage.selectSuggestionProyect(projectName, DriverContext.getDriver());
        timesheetPage.clickView();
    }

    @Then("project hours should be included in total hours")
    public void projectHoursShouldBeIncludedInTotalHours() {
        timesheetPage.validarReportHours();
    }

    @Given("employee {string} has editable timesheet")
    public void employeeHasEditableTimesheet(String employeeName) {
        timesheetPage.searchEmployee(employeeName);
        timesheetPage.clickEditIfPresent();
    }

    @When("the user updates {string} hours on {string}")
    public void theUserUpdatesHoursOnDay(String hours, String day) {
        this.lastUpdatedHours = hours;
        timesheetPage.updateHoursForDay(day, hours);
    }

    @And("saves the timesheet")
    public void savesTheTimesheet() {
        timesheetPage.clickSave();
    }

    @Then("the updated hours should be displayed")
    public void theUpdatedHoursShouldBeDisplayed() {
        boolean isDisplayed = timesheetPage.isHoursDisplayed(lastUpdatedHours);
        Assert.assertTrue("Las horas actualizadas no se muestran correctamente en la pantalla.", isDisplayed);
    }

    @Given("employee {string} timesheet is open")
    public void employeeTimesheetIsOpen(String employeeName) {
        timesheetPage.searchEmployee(employeeName);
        timesheetPage.clickEditIfPresent();
    }

    @When("the user modifies worked hours")
    public void theUserModifiesWorkedHours() {
        timesheetPage.updateHoursForDay("Monday", "8");
    }

    @And("clicks Save")
    public void clicksSave() {
        timesheetPage.clickSave();
    }

    @Then("a successful save message should be displayed")
    public void aSuccessfulSaveMessageShouldBeDisplayed() {
        boolean isSuccess = timesheetPage.isSuccessMessageDisplayed();
        Assert.assertTrue("El mensaje de guardado exitoso no se mostró en pantalla.", isSuccess);
    }

    @Given("employee {string} timesheet is editable")
    public void employeeTimesheetIsEditable(String employeeName) {
        timesheetPage.searchEmployee(employeeName);
        timesheetPage.clickEditIfPresent();
    }

    @When("the user enters {string} hours")
    public void theUserEntersHours(String hours) {
        timesheetPage.updateHoursForDay("Monday", hours);
    }

    @Then("a validation message should be displayed")
    public void aValidationMessageShouldBeDisplayed() {
        boolean isErrorDisplayed = timesheetPage.isValidationMessageDisplayed();
        Assert.assertTrue("No se mostró el mensaje de validación esperado para horas negativas.", isErrorDisplayed);
    }

    @Given("employee {string} timesheet is editables")
    public void employeeTimesheetIsEditables(String employeeName) {
        timesheetPage.searchEmployee(employeeName);
        timesheetPage.clickEditIfPresent();
    }

    @When("the user enters {string}")
    public void theUserEnters(String invalidValue) {
        timesheetPage.updateHoursForDay("Monday", invalidValue);
    }

    @Then("validation should prevent saving")
    public void validationShouldPreventSaving() {
        boolean isPrevented = timesheetPage.isSavePreventedOrValidationErrorDisplayed();
        Assert.assertTrue("El sistema permitió ingresar un formato de hora inválido sin mostrar validación.", isPrevented);
    }

    @Given("the user searched for employee {string}")
    public void searchedForEmployee(String employeeName) {
        timesheetPage.searchEmployee(employeeName);
    }

    @When("the user clicks Resets")
    public void clicksResetButton() {
        timesheetPage.clickReset();
    }

    @Then("employee search criteria should be cleared")
    public void employeeSearchCriteriaShouldBeCleared() {
        boolean isCleared = timesheetPage.isSearchCriteriaCleared();
        Assert.assertTrue("El campo de búsqueda de empleado no se limpió tras presionar Reset.", isCleared);
    }

    @Given("employee {string} has multiple projects")
    public void employeeHasMultipleProjects(String employeeName) {
        timesheetPage.searchEmployee(employeeName);
        timesheetPage.clickEditIfPresent();
    }

    @When("the project rows are summed")
    public void sumProjectRows() {
        timesheetPage.calculateProjectRowsSum();
    }

    @Then("the project total should match displayed total")
    public void projectTotalShouldMatchDisplayedTotal() {
        boolean isMatch = timesheetPage.isDisplayedTotalMatchingCalculated();
        Assert.assertTrue("La suma de las filas no coincide con el total desplegado en pantalla.", isMatch);
    }

    @Given("employee {string} does not have a timesheet for {string}")
    public void employeeDoesNotHaveTimesheetForPeriod(String employeeName, String period) {
        this.targetPeriod = period;
        timesheetPage.searchEmployee(employeeName);
    }

    @When("the administrator creates a timesheet")
    public void administratorCreatesTimesheet() {
        timesheetPage.selectPeriod(this.targetPeriod);
    }

    @Then("the timesheet should be successfully created")
    public void timesheetShouldBeSuccessfullyCreated() {
        boolean isCreated = timesheetPage.isSuccessMessageDisplayed();
        Assert.assertTrue("La hoja de tiempo no fue creada correctamente para el empleado.", isCreated);
    }

    @Given("employee {string} timesheet exists")
    public void employeeTimesheetExists(String employeeName) {
        timesheetPage.searchEmployee(employeeName);
        timesheetPage.clickEditIfPresent();
    }

    @When("the user updates worked hours")
    public void theUserUpdatesWorkedHours() {
        this.lastUpdatedHours = "8";
        timesheetPage.updateHoursForDay("Monday", this.lastUpdatedHours);
        timesheetPage.clickSave();
    }

    @And("refreshes the browser")
    public void refreshesTheBrowser() {
        timesheetPage.refreshBrowser();
    }

    @Then("the saved hours should remain unchanged")
    public void theSavedHoursShouldRemainUnchanged() {
        boolean isDisplayed = timesheetPage.isHoursDisplayed(this.lastUpdatedHours);
        Assert.assertTrue("Las horas guardadas no se mantuvieron tras recargar la página.", isDisplayed);
    }

    @Then("no employee should be found")
    public void noEmployeeShouldBeFound() {
        boolean noFound = timesheetPage.isNoEmployeeFoundMessageDisplayed();
        Assert.assertTrue("Se esperaba que no se encontrara ningún empleado, pero la alerta o sugerencia vacía no se desplegó.", noFound);
    }

    @Then("unauthorized timesheets should not be displayed")
    public void unauthorizedTimesheetsShouldNotBeDisplayed() {
        boolean noUnauthorizedData = timesheetPage.isUnauthorizedDataNotDisplayed();
        Assert.assertTrue("La consulta con SQL Injection devolvió o expuso datos no autorizados.", noUnauthorizedData);
    }

    // Cambia la anotación para incluir "in timesheet module" u otra frase diferenciadora:
    @And("the timesheet application should remain stable")
    public void theApplicationShouldRemainStableInTimesheet() {
        boolean isStable = timesheetPage.isApplicationStable();
        Assert.assertTrue("La aplicación se desestabilizó o arrojó un error no controlado tras el intento de inyección.", isStable);
    }

    @Then("no script should execute")
    public void noScriptShouldExecute() {
        boolean noXSS = timesheetPage.isNoScriptExecuted();
        Assert.assertTrue("Se detectó la ejecución de un script XSS reflejado.", noXSS);
    }

    @And("the application should remain operational")
    public void theApplicationShouldRemainOperational() {
        boolean isOperational = timesheetPage.isApplicationOperational();
        Assert.assertTrue("La aplicación no quedó operativa tras el intento de inyección XSS.", isOperational);
    }

    @Given("employee {string} existss")
    public void employeeExists(String employeeName) {
        timesheetPage.searchEmployee(employeeName);
    }

    @When("a new timesheet is created for {string}")
    public void newTimesheetIsCreatedForPeriod(String period) {
        this.targetPeriod = period;
        timesheetPage.selectPeriod(period);
        timesheetPage.clickEditIfPresent();
    }

    @And("hours are entered and saved")
    public void hoursAreEnteredAndSaved() {
        this.lastUpdatedHours = "8";
        timesheetPage.updateHoursForDay("Monday", this.lastUpdatedHours);
        timesheetPage.clickSave();
    }

    @Then("the timesheet should be available for future searches")
    public void timesheetShouldBeAvailableForFutureSearches() {
        boolean isAvailable = timesheetPage.isTimesheetAvailableForSearch(this.targetPeriod);
        Assert.assertTrue("La hoja de tiempo creada no se encuentra disponible en búsquedas posteriores.", isAvailable);
    }

    private String currentEmployeeName;

    @Given("employee {string} has an existing timesheet")
    public void employeeHasAnExistingTimesheet(String employeeName) {
        this.currentEmployeeName = employeeName;
        timesheetPage.searchEmployee(employeeName);
        timesheetPage.clickEditIfPresent();
    }

    @When("worked hours are updated")
    public void workedHoursAreUpdated() {
        this.lastUpdatedHours = "8";
        timesheetPage.updateHoursForDay("Monday", this.lastUpdatedHours);
    }

    @And("the timesheet is saved")
    public void theTimesheetIsSaved() {
        timesheetPage.clickSave();
    }

    @Then("updated values should be displayed after reopening the timesheet")
    public void updatedValuesShouldBeDisplayedAfterReopeningTheTimesheet() {
        timesheetPage.reopenTimesheetForEmployee(this.currentEmployeeName);
        boolean isDisplayed = timesheetPage.isHoursDisplayed(this.lastUpdatedHours);
        Assert.assertTrue("Los valores actualizados no se mostraron al reabrir la hoja de tiempo.", isDisplayed);
    }

    @Given("employee {string} completed a timesheet")
    public void employeeCompletedATimesheet(String employeeName) {
        timesheetPage.searchEmployee(employeeName);
    }

    @When("the manager approves the timesheet")
    public void theManagerApprovesTheTimesheet() {
        timesheetPage.approveTimesheet();
    }

    @Then("the timesheet status should become Approved")
    public void theTimesheetStatusShouldBecomeApproved() {
        boolean isApproved = timesheetPage.isTimesheetStatusApproved();
        Assert.assertTrue("El estado de la hoja de tiempo no cambió a 'Approved'.", isApproved);
    }
}