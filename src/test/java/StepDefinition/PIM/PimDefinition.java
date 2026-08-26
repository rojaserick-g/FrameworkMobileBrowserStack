package StepDefinition.PIM;

import ObjectPage.PimPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;

public class PimDefinition {
    private final PimPage pimPage = new PimPage();

    @And("el usuario ingresa al modulo PIM")
    public void elUsuarioIngresaAlModuloPIM() {
        pimPage.ingresarModuloPim();
    }

    @When("the user enters {string} in PIM Employee Name field")
    public void theUserEntersInEmployeeNameField(String nombre) {
        pimPage.escribirNombreEmpleado(nombre);
    }

    @When("the user enters employee id {string}")
    public void theUserEntersEmployeeId(String id) {
        pimPage.escribirEmployeeId(id);
    }

    @When("clicks Search")
    public void clicksSearch() {
        pimPage.clickSearch();
    }

    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        pimPage.validarResultadosVisibles();
    }

    @Then("every result should contain {string}")
    public void everyResultShouldContain(String nombre) {
        pimPage.validarNombreEmpleado(nombre);
    }

    @Then("the system should return {string} record\\(s)")
    public void theSystemShouldReturnRecordS(String cantidad) {
        pimPage.validarCantidadRegistros(cantidad);
    }

    @When("the user searches PIM employee {string}")
    public void theUserSearchesEmployee(String nombre) {
        pimPage.escribirNombreEmpleado(nombre);
        pimPage.clickSearch();
    }

    @Then("the results should be identical for the same employee {string}")
    public void theResultsShouldBeIdenticalForTheSameEmployee(String nombreEsperado) {
        pimPage.validarResultadosVisibles();
        pimPage.validarNombreEmpleado(nombreEsperado);
    }

    @When("the user selects employment status {string}")
    public void theUserSelectsEmploymentStatus(String estado) {

        pimPage.seleccionarEmploymentStatus(estado);

    }

    @Then("every result should have employment status {string}")
    public void everyResultShouldHaveEmploymentStatus(String estado) {

        pimPage.validarResultadosVisibles();

    }

    @When("the user selects include option {string}")
    public void theUserSelectsIncludeOption(String opcion) {

        pimPage.seleccionarInclude(opcion);

    }

    @Then("results should belong to {string}")
    public void resultsShouldBelongTo(String opcion) {

        pimPage.validarResultadosVisibles();

    }

    @When("the user selects job title {string}")
    public void theUserSelectsJobTitle(String cargo) {
        pimPage.seleccionarJobTitle(cargo);

    }

    @Then("every result should have job title {string}")
    public void everyResultShouldHaveJobTitle(String cargo) {
        pimPage.validarResultadosVisibles();

    }

    @When("the user selects sub unit {string}")
    public void theUserSelectsSubUnit1(String subUnit) {
        pimPage.seleccionarSubUnit(subUnit);

    }

    @Then("every result should belong to sub unit {string}")
    public void everyResultShouldBelongToSubUnit(String subUnit) {
        pimPage.validarResultadosVisibles();

    }

    @When("the user selects supervisor {string}")
    public void theUserSelectsSupervisor(String supervisor) {
        pimPage.seleccionarSupervisor(supervisor);

    }

    @Then("every result should report to supervisor {string}")
    public void everyResultShouldReportToSupervisor(String supervisor) {
        pimPage.validarResultadosVisibles();

    }

    @When("the user enters employee name {string}")
    public void theUserEntersEmployeeName1(String employeeName) {
        pimPage.ingresarNombreEmpleado(employeeName);

    }

    @When("selects employment status {string}")
    public void selectsEmploymentStatus(String status) {
        if (!status.trim().isEmpty()) {
            pimPage.seleccionarEmploymentStatus(status);
        }

    }

    @When("selects job title {string}")
    public void selectsJobTitle(String jobTitle) {
        if (!jobTitle.trim().isEmpty()) {
            pimPage.seleccionarJobTitle(jobTitle);
        }

    }

    @When("selects sub unit {string}")
    public void selectsSubUnit(String subUnit) {
        if (!subUnit.trim().isEmpty()) {
            pimPage.seleccionarSubUnit(subUnit);
        }

    }

    @Then("every result should match all selected criteria")
    public void everyResultShouldMatchAllSelectedCriteria() {
        pimPage.validarResultadosFiltros();

    }

    @io.cucumber.java.en.Given("the user performs a search using:")
    public void theUserPerformsASearchUsing(io.cucumber.datatable.DataTable dataTable) {
        java.util.List<java.util.Map<String, String>> datos = dataTable.asMaps(String.class, String.class);
        String nombre = datos.get(0).get("employeeName");
        String estado = datos.get(0).get("status");
        pimPage.escribirNombreEmpleado(nombre);
        if (!estado.trim().isEmpty()) {
            pimPage.seleccionarEmploymentStatus(estado);
        }
        pimPage.clickSearch();

    }

    @When("the user clicks Reset")
    public void theUserClicksReset() {
        pimPage.clickReset();

    }

    @Then("all search fields should be cleared")
    public void allSearchFieldsShouldBeCleared() {
        pimPage.validarCamposReseteados();

    }

    @Then("default values should be restored")
    public void defaultValuesShouldBeRestored() {
        pimPage.validarCamposReseteados();

    }

    @When("the user navigates to page {string}")
    public void theUserNavigatesToPage(String pagina) {
        pimPage.navegarPagina(pagina);

    }

    @Then("page {string} should be displayed")
    public void pageShouldBeDisplayed(String pagina) {
        pimPage.validarPagina(pagina);

    }

    @Then("employee records should be loaded")
    public void employeeRecordsShouldBeLoaded() {
        pimPage.validarRegistrosPagina();

    }

    @Then("no script should be executed")
    public void noScriptShouldBeExecuted() {
        pimPage.validarNoEjecucionScript();

    }

    @Then("the application should remain stable")
    public void theApplicationShouldRemainStable() {
        pimPage.validarAplicacionEstable();

    }

    @When("the user enters {string} into Employee Name")
    public void theUserEntersIntoEmployeeName(String maliciousInput) {
        pimPage.escribirNombreEmpleado(maliciousInput);

    }

    @Then("unauthorized records should not be returned")
    public void unauthorizedRecordsShouldNotBeReturned() {
        pimPage.validarSQLInjection();

    }

    @Given("a new employee is created with:")
    public void aNewEmployeeIsCreatedWith(DataTable dataTable) {

        List<Map<String, String>> datos =
                dataTable.asMaps(String.class, String.class);

        String nombre = datos.get(0).get("firstName");
        String apellido = datos.get(0).get("lastName");

        pimPage.clickAdd();
        pimPage.ingresarNuevoEmpleado(nombre, apellido);

        // Guardar el ID antes del Save
        pimPage.guardarEmployeeId();

        // Guardar el empleado
        pimPage.guardarEmpleado();
    }

    @When("the user searches using generated employee id")
    public void theUserSearchesUsingGeneratedEmployeeId() {
        pimPage.buscarEmpleadoCreado();
    }

    @Then("the created employee should be displayed")
    public void theCreatedEmployeeShouldBeDisplayed() {
        pimPage.validarEmpleadoCreado();

    }

    @Given("employee {string} exists")
    public void employeeExists(String employeeId) {
        pimPage.setEmployeeIdGenerado(employeeId);
        pimPage.buscarEmpleado(employeeId);
    }

    @When("the employee is deleted")
    public void theEmployeeIsDeleted() {

        pimPage.eliminarEmpleado();

    }

    @When("searches by employee id {string}")
    public void searchesByEmployeeId(String employeeId) {

        pimPage.buscarEmpleado(employeeId);

    }

    @Then("no records should be found")
    public void noRecordsShouldBeFound() {
        pimPage.validarEmpleadoEliminado();
    }

    @When("the user opens employee {string} from the search results")
    public void theUserOpensEmployeeFromTheSearchResults(String employeeName) {
        pimPage.escribirNombreEmpleado(employeeName);
        pimPage.clickSearch();
        pimPage.abrirDetalleEmpleado();
    }

    @Then("employee detail page should display:")
    public void employeeDetailPageShouldDisplay(DataTable dataTable) {

        List<Map<String, String>> datos =
                dataTable.asMaps(String.class, String.class);

        String nombre = datos.get(0).get("value");
        String employeeId = datos.get(1).get("value");

        pimPage.validarInformacionEmpleado(nombre, employeeId);
    }
}