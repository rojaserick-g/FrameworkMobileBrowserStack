package ObjectPage.Leave;

import Control.BaseController;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeavePage extends BaseController {

    public LeavePage() {
        PageFactory.initElements(Control.DriverContext.getDriver(), this);
    }
    // MAIN NAVIGATION
    @FindBy(xpath = "//span[normalize-space()='Leave']")
    public WebElement leaveMenuButton;

    @FindBy(xpath = "//a[normalize-space()='Leave List']")
    public WebElement leaveListMenuOption;

    // LEAVE LIST FILTERS
    @FindBy(xpath = "//label[text()='From Date']/ancestor::div[1]/following-sibling::div//input")
    public WebElement fromDateInput;

    @FindBy(xpath = "//label[text()='To Date']/ancestor::div[1]/following-sibling::div//input")
    public WebElement toDateInput;

    @FindBy(xpath = "//label[contains(normalize-space(),'Show Leave')]/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-multiselect-wrapper')]")
    public WebElement leaveStatusDropdown;

    private final By leaveStatusDropdownLocator = By.xpath(
            "//label[contains(normalize-space(),'Show Leave')]/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-multiselect-wrapper')]"
    );

    private final By leaveStatusRemoveButtonsLocator = By.xpath(
            "//label[contains(normalize-space(),'Show Leave')]/ancestor::div[contains(@class,'oxd-input-group')]//span[contains(@class,'oxd-chip')]//i[contains(@class,'oxd-chip-icon')]"
    );

    private final By dropdownOptionsLocator = By.xpath("//div[@role='option']");

    @FindBy(xpath = "//label[text()='Leave Type']/ancestor::div[1]/following-sibling::div//div[contains(@class,'oxd-select-wrapper')]")
    public WebElement leaveTypeDropdown;

    @FindBy(xpath = "//div[contains(@class, 'oxd-input-group')][descendant::label[contains(., 'Employee Name')]]//input")
    public WebElement employeeNameInput;

    @FindBy(xpath = "//label[text()='Sub Unit']/ancestor::div[1]/following-sibling::div//div[contains(@class, 'oxd-select-wrapper')]")
    public WebElement subUnitDropdown;

    @FindBy(xpath = "//label[contains(.,'Include Past Employees')]/following::span[contains(@class,'oxd-switch-input')][1]")
    public WebElement includePastEmployeesSwitch;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    public WebElement resetButton;

    // SELECTED STATUS CHIPS
    @FindBy(xpath = "//div[contains(@class, 'oxd-input-group')][descendant::label[contains(., 'Show Leave')]]//span[contains(@class, 'oxd-chip')]//i[contains(@class, 'oxd-chip-icon')]")
    private List<WebElement> removeSelectedStatusButtons;

    // DROPDOWN AND AUTOCOMPLETE OPTIONS
    @FindBy(xpath = "//div[@role='option']")
    private List<WebElement> dropdownOptions;

    @FindBy(xpath = "//div[contains(@class,'oxd-autocomplete-option')]")
    private List<WebElement> autocompleteOptions;

    @FindBy(xpath = "//div[contains(@class,'oxd-autocomplete-dropdown')]")
    private WebElement autocompleteDropdown;

    // RESULT GRID
    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[contains(@class,'oxd-table-card')]")
    private List<WebElement> resultRows;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@role='row']")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-header')]//div[@role='columnheader']")
    private List<WebElement> tableHeaders;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@role='row']/div[@role='cell'][1]")
    private List<WebElement> leaveDateCells;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@role='row']/div[@role='cell'][2]")
    private List<WebElement> employeeNameCells;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@role='row']/div[@role='cell'][3]")
    private List<WebElement> leaveTypeCells;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@role='row']/div[@role='cell'][4]")
    private List<WebElement> leaveBalanceCells;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@role='row']/div[@role='cell'][5]")
    private List<WebElement> numberOfDaysCells;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@role='row']/div[@role='cell'][6]")
    private List<WebElement> leaveStatusCells;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-body')]//div[@role='row']/div[@role='cell'][7]")
    private List<WebElement> commentsCells;

    @FindBy(xpath = "//span[normalize-space()='No Records Found']")
    private WebElement noRecordsFoundMessage;

    // VALIDATION AND TOAST MESSAGES
    @FindBy(xpath = "//span[contains(@class,'oxd-input-field-error-message')]")
    private List<WebElement> validationMessages;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast')]")
    private WebElement toastMessage;

    // ACTION BUTTONS
    @FindBy(xpath = "//button[normalize-space()='Approve']")
    private List<WebElement> approveButtons;

    @FindBy(xpath = "//button[normalize-space()='Reject']")
    private List<WebElement> rejectButtons;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    private List<WebElement> cancelButtons;

    @FindBy(xpath = "//button[contains(@class,'oxd-icon-button')]")
    private List<WebElement> actionIconButtons;

    // PAGINATION
    @FindBy(xpath = "//button[contains(@class,'oxd-pagination-page-item')]")
    private List<WebElement> paginationButtons;

    // GENERIC DRIVER AND WAIT METHODS
    public WebDriver getDriver() {
        return Control.DriverContext.getDriver();
    }

    public WebDriverWait getWait(int seconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
    }

    public void waitForElementToBeClickable(WebElement element) {
        getWait(10).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementToBeVisible(WebElement element) {
        getWait(10).until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    public void clearAndType(WebElement element, String value) {
        waitForElementToBeClickable(element);
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(value);
    }

    // NAVIGATION METHODS
    public void navigateToLeaveList() {
        WebDriverWait wait = getWait(30);
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("dashboard"),
                    ExpectedConditions.urlContains("viewEmployeeList"),
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Leave']"))
            ));
        } catch (Exception e) {
            System.out.println("Dashboard was not fully loaded before navigating to Leave.");
        }
        try {
            WebElement leaveMenu = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[normalize-space()='Leave']")
                    )
            );
            leaveMenu.click();
            wait.until(ExpectedConditions.urlContains("leave"));
        } catch (Exception e) {
            System.out.println("Could not click Leave menu. Trying direct Leave List URL.");
            getDriver().navigate().to(
                    "https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewLeaveList"
            );
        }
        wait.until(ExpectedConditions.visibilityOf(searchButton));
    }

    // FILTER INPUT METHODS
    public void enterFromDate(String fromDate) {
        clearAndType(fromDateInput, fromDate);
    }

    public void enterToDate(String toDate) {
        clearAndType(toDateInput, toDate);
    }

    public void enterEmployeeName(String employeeName) {
        clearAndType(employeeNameInput, employeeName);
    }

    public void selectEmployeeFromAutocomplete(String employeeName) {
        WebDriverWait wait = getWait(10);
        clearAndType(employeeNameInput, employeeName);
        By optionLocator = By.xpath(
                "//div[contains(@class,'oxd-autocomplete-option')]//span[contains(normalize-space(),'" + employeeName + "')]"
        );
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();
    }

    public boolean areAutocompleteSuggestionsDisplayed() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOfAllElements(autocompleteOptions));
            return !autocompleteOptions.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getAutocompleteSuggestions() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOfAllElements(autocompleteOptions));
            return autocompleteOptions
                    .stream()
                    .map(WebElement::getText)
                    .filter(text -> text != null && !text.trim().isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // XPATH HELPER
    private String xpathLiteral(String text) {
        if (!text.contains("'")) {
            return "'" + text + "'";
        }
        if (!text.contains("\"")) {
            return "\"" + text + "\"";
        }
        String[] parts = text.split("'");
        StringBuilder xpath = new StringBuilder("concat(");
        for (int i = 0; i < parts.length; i++) {
            xpath.append("'").append(parts[i]).append("'");

            if (i < parts.length - 1) {
                xpath.append(", \"'\", ");
            }
        }
        xpath.append(")");
        return xpath.toString();
    }

    // DROPDOWN METHODS
    public void selectDropdownOption(WebElement dropdown, String optionText) {
        WebDriverWait wait = getWait(20);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
        wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        dropdown.click();
        By optionLocator = By.xpath(
                "//div[@role='option'][.//*[normalize-space()=" + xpathLiteral(optionText) + "] or normalize-space()=" + xpathLiteral(optionText) + "]"
        );
        try {
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
            try {
                wait.until(ExpectedConditions.elementToBeClickable(option));
                option.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", option);
            }
            System.out.println("Dropdown option selected: " + optionText);
        } catch (TimeoutException e) {
            List<WebElement> availableOptions = getDriver().findElements(dropdownOptionsLocator);
            System.out.println("Could not find dropdown option: " + optionText);
            System.out.println("Available dropdown options:");
            for (WebElement option : availableOptions) {
                System.out.println("- " + option.getText());
            }
            throw e;
        }
    }

    public void openLeaveStatusDropdown() {
        WebDriverWait wait = getWait(20);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(leaveStatusDropdownLocator)
        );
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdown));
            dropdown.click();
        } catch (Exception e) {
            try {
                new Actions(getDriver()).moveToElement(dropdown).click().perform();
            } catch (Exception ex) {
                js.executeScript("arguments[0].click();", dropdown);
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownOptionsLocator));
    }

    public void selectLeaveStatus(String status) {
        WebDriverWait wait = getWait(20);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        openLeaveStatusDropdown();
        By optionLocator = By.xpath(
                "//div[@role='option'][.//*[normalize-space()=" + xpathLiteral(status) + "] or normalize-space()=" + xpathLiteral(status) + "]"
        );
        try {
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
            try {
                wait.until(ExpectedConditions.elementToBeClickable(option));
                option.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", option);
            }
            System.out.println("Leave status selected: " + status);
        } catch (TimeoutException e) {
            List<WebElement> availableOptions = getDriver().findElements(dropdownOptionsLocator);
            System.out.println("Could not find leave status: " + status);
            System.out.println("Available leave status options:");
            for (WebElement option : availableOptions) {
                System.out.println("- " + option.getText());
            }
            throw e;
        }
    }

    public void selectLeaveType(String leaveType) {
        selectDropdownOption(leaveTypeDropdown, leaveType);
    }

    public void selectSubUnit(String subUnit) {
        selectDropdownOption(subUnitDropdown, subUnit);
    }

    public List<String> getDropdownOptions(WebElement dropdown) {
        WebDriverWait wait = getWait(10);
        wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        dropdown.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(dropdownOptions));
        List<String> options = dropdownOptions
                .stream()
                .map(WebElement::getText)
                .filter(text -> text != null && !text.trim().isEmpty())
                .map(String::trim)
                .collect(Collectors.toList());
        dropdown.click();
        return options;
    }

    public List<String> getAvailableLeaveStatuses() {
        openLeaveStatusDropdown();
        return getDriver()
                .findElements(dropdownOptionsLocator)
                .stream()
                .map(WebElement::getText)
                .filter(text -> text != null && !text.trim().isEmpty())
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public List<String> getAvailableLeaveTypes() {
        return getDropdownOptions(leaveTypeDropdown);
    }

    public List<String> getAvailableSubUnits() {
        return getDropdownOptions(subUnitDropdown);
    }

    public boolean isLeaveStatusAvailable(String status) {
        return getAvailableLeaveStatuses().contains(status);
    }

    public boolean isLeaveTypeAvailable(String leaveType) {
        return getAvailableLeaveTypes().contains(leaveType);
    }

    public boolean isSubUnitAvailable(String subUnit) {
        return getAvailableSubUnits().contains(subUnit);
    }

    // LEAVE STATUS CHIP METHODS
    public void removeSelectedLeaveStatuses() {
        WebDriverWait wait = getWait(10);
        try {
            List<WebElement> buttons = getDriver().findElements(leaveStatusRemoveButtonsLocator);
            while (!buttons.isEmpty()) {
                WebElement button = buttons.get(0);
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                buttons = getDriver().findElements(leaveStatusRemoveButtonsLocator);
            }
            System.out.println("Selected leave statuses were removed.");
        } catch (Exception e) {
            System.out.println("No selected leave statuses were found or they were already removed.");
        }
    }

    public void selectMultipleLeaveStatuses(String status1, String status2) {
        removeSelectedLeaveStatuses();
        selectLeaveStatus(status1);
        selectLeaveStatus(status2);
    }

    public void selectAllLeaveStatuses(List<String> statuses) {
        removeSelectedLeaveStatuses();
        for (String status : statuses) {
            try {
                selectLeaveStatus(status);
            } catch (Exception e) {
                System.out.println("Could not select leave status: " + status);
            }
        }
    }

    // BUTTON METHODS
    public void clickSearchButton() {
        clickElement(searchButton);
    }

    public void clickResetButton() {
        clickElement(resetButton);
    }

    public void resetFilters() {
        try {
            clickResetButton();
        } catch (Exception e) {
            System.out.println("Filters could not be reset or reset was not required.");
        }
    }

    // SEARCH METHODS
    public void searchLeaveRequestByEmployee(String employeeName) {
        enterEmployeeName(employeeName);
        clickSearchButton();
    }

    public void searchLeaveRequestByLeaveType(String leaveType) {
        selectLeaveType(leaveType);
        clickSearchButton();
    }

    public void searchLeaveRequestByLeaveStatus(String status) {
        removeSelectedLeaveStatuses();
        selectLeaveStatus(status);
        clickSearchButton();
    }

    public void searchLeaveRequestBySubUnit(String subUnit) {
        selectSubUnit(subUnit);
        clickSearchButton();
    }

    public void searchLeaveRequestByDateRange(String fromDate, String toDate) {
        enterFromDate(fromDate);
        enterToDate(toDate);
        clickSearchButton();
    }

    public void searchLeaveRequestWithMultipleFilters(
            String fromDate,
            String toDate,
            String status,
            String leaveType,
            String employeeName,
            String subUnit
    ) {
        enterFromDate(fromDate);
        enterToDate(toDate);

        removeSelectedLeaveStatuses();
        selectLeaveStatus(status);

        selectLeaveType(leaveType);
        enterEmployeeName(employeeName);
        selectSubUnit(subUnit);

        clickSearchButton();
    }

    // RESULT GRID METHODS
    public boolean areResultsDisplayed() {
        try {
            WebDriverWait wait = getWait(10);

            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfAllElements(resultRows),
                    ExpectedConditions.visibilityOf(noRecordsFoundMessage)
            ));

            return !resultRows.isEmpty();

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoRecordsFoundDisplayed() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOf(noRecordsFoundMessage));
            return noRecordsFoundMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getTableHeaders() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOfAllElements(tableHeaders));
            return tableHeaders
                    .stream()
                    .map(WebElement::getText)
                    .filter(text -> text != null && !text.trim().isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<String> getLeaveDatesFromGrid() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOfAllElements(leaveDateCells));
            return leaveDateCells
                    .stream()
                    .map(WebElement::getText)
                    .filter(text -> text != null && !text.trim().isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<String> getEmployeeNamesFromGrid() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOfAllElements(employeeNameCells));
            return employeeNameCells
                    .stream()
                    .map(WebElement::getText)
                    .filter(text -> text != null && !text.trim().isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<String> getLeaveTypesFromGrid() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOfAllElements(leaveTypeCells));
            return leaveTypeCells
                    .stream()
                    .map(WebElement::getText)
                    .filter(text -> text != null && !text.trim().isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<String> getLeaveBalancesFromGrid() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOfAllElements(leaveBalanceCells));
            return leaveBalanceCells
                    .stream()
                    .map(WebElement::getText)
                    .filter(text -> text != null && !text.trim().isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<String> getNumberOfDaysFromGrid() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOfAllElements(numberOfDaysCells));
            return numberOfDaysCells
                    .stream()
                    .map(WebElement::getText)
                    .filter(text -> text != null && !text.trim().isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<String> getLeaveStatusesFromGrid() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOfAllElements(leaveStatusCells));
            return leaveStatusCells
                    .stream()
                    .map(WebElement::getText)
                    .filter(text -> text != null && !text.trim().isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<String> getCommentsFromGrid() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOfAllElements(commentsCells));
            return commentsCells
                    .stream()
                    .map(WebElement::getText)
                    .filter(text -> text != null)
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // GRID VALIDATIONS
    public boolean doesGridContainEmployee(String employeeName) {
        List<String> employees = getEmployeeNamesFromGrid();
        return employees
                .stream()
                .anyMatch(employee -> employee.contains(employeeName));
    }

    public boolean doAllRowsHaveLeaveType(String leaveType) {
        List<String> leaveTypes = getLeaveTypesFromGrid();
        if (leaveTypes.isEmpty()) {
            return false;
        }
        return leaveTypes
                .stream()
                .allMatch(type -> type.equalsIgnoreCase(leaveType));
    }

    public boolean doAllRowsHaveStatus(String status) {
        List<String> statuses = getLeaveStatusesFromGrid();
        if (statuses.isEmpty()) {
            return false;
        }
        return statuses
                .stream()
                .allMatch(rowStatus -> rowStatus.equalsIgnoreCase(status));
    }

    public boolean doAllRowsHaveEitherStatus(String status1, String status2) {
        List<String> statuses = getLeaveStatusesFromGrid();
        if (statuses.isEmpty()) {
            return false;
        }
        return statuses
                .stream()
                .allMatch(rowStatus ->
                        rowStatus.equalsIgnoreCase(status1)
                                || rowStatus.equalsIgnoreCase(status2)
                );
    }

    public boolean areExpectedGridHeadersDisplayed(List<String> expectedHeaders) {
        List<String> actualHeaders = getTableHeaders();
        return actualHeaders.containsAll(expectedHeaders);
    }

    public boolean areLeaveRequestBasicColumnsDisplayed() {
        List<String> headers = getTableHeaders();
        return headers.contains("Date")
                && headers.contains("Employee Name")
                && headers.contains("Leave Type")
                && headers.contains("Leave Balance")
                && headers.contains("Status");
    }

    // VALIDATION AND MESSAGE METHODS
    public List<String> getValidationMessages() {
        try {
            return validationMessages
                    .stream()
                    .map(WebElement::getText)
                    .filter(text -> text != null && !text.trim().isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean isValidationMessageDisplayed() {
        return !getValidationMessages().isEmpty();
    }

    public String getToastMessage() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOf(toastMessage));
            return toastMessage.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isToastMessageDisplayed() {
        return !getToastMessage().isEmpty();
    }

    // LEAVE REQUEST ACTIONS
    public void approveFirstPendingRequest() {
        WebDriverWait wait = getWait(10);
        if (approveButtons.isEmpty()) {
            throw new RuntimeException("No Approve button was found.");
        }
        wait.until(ExpectedConditions.elementToBeClickable(approveButtons.get(0)));
        approveButtons.get(0).click();
    }

    public void rejectFirstPendingRequest() {
        WebDriverWait wait = getWait(10);
        if (rejectButtons.isEmpty()) {
            throw new RuntimeException("No Reject button was found.");
        }
        wait.until(ExpectedConditions.elementToBeClickable(rejectButtons.get(0)));
        rejectButtons.get(0).click();
    }

    public void cancelFirstLeaveRequest() {
        WebDriverWait wait = getWait(10);
        if (cancelButtons.isEmpty()) {
            throw new RuntimeException("No Cancel button was found.");
        }
        wait.until(ExpectedConditions.elementToBeClickable(cancelButtons.get(0)));
        cancelButtons.get(0).click();
    }

    public void openFirstLeaveRequestFromGrid() {
        WebDriverWait wait = getWait(10);
        if (tableRows.isEmpty()) {
            throw new RuntimeException("No leave request row was found.");
        }
        WebElement firstRow = tableRows.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(firstRow));
        firstRow.click();
    }

    public boolean areLeaveRequestDetailsDisplayed() {
        try {
            WebDriverWait wait = getWait(10);
            By detailsLocator = By.xpath(
                    "//*[contains(normalize-space(),'Leave Request') or contains(normalize-space(),'Request Details') or contains(normalize-space(),'Leave Details')]"
            );
            WebElement details = wait.until(ExpectedConditions.visibilityOfElementLocated(detailsLocator));
            return details.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // PAGINATION
    public void navigateToPage(String pageNumber) {
        WebDriverWait wait = getWait(10);
        By pageLocator = By.xpath(
                "//button[contains(@class,'oxd-pagination-page-item') and normalize-space()='" + pageNumber + "']"
        );
        WebElement pageButton = wait.until(ExpectedConditions.elementToBeClickable(pageLocator));
        pageButton.click();
    }

    public boolean isPageDisplayed(String pageNumber) {
        try {
            By activePageLocator = By.xpath(
                    "//button[contains(@class,'oxd-pagination-page-item') and contains(@class,'--active') and normalize-space()='" + pageNumber + "']"
            );
            WebElement activePage = getWait(10).until(ExpectedConditions.visibilityOfElementLocated(activePageLocator));
            return activePage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPaginationDisplayed() {
        try {
            return !paginationButtons.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    // BROWSER AND STABILITY
    public void refreshBrowser() {
        getDriver().navigate().refresh();
    }

    public boolean isApplicationStable() {
        try {
            WebDriverWait wait = getWait(10);
            wait.until(ExpectedConditions.visibilityOf(searchButton));
            return searchButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // SECURITY VALIDATION
    public boolean isUnauthorizedDataDisplayed() {
        List<String> employees = getEmployeeNamesFromGrid();
        if (employees.isEmpty()) {
            return false;
        }
        return employees
                .stream()
                .anyMatch(employee -> employee.toLowerCase().contains("admin"));
    }

    public boolean wasScriptExecuted() {
        try {
            Alert alert = getDriver().switchTo().alert();
            alert.dismiss();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    // COMPATIBILITY METHODS WITH PREVIOUS STEP DEFINITION
    public WebElement getAutocompleteDropdown() {
        return autocompleteDropdown;
    }

    public WebElement getTxtEmployeeName() {
        return employeeNameInput;
    }

    public List<WebElement> obtenerCeldasLeaveType() {
        return leaveTypeCells;
    }

    public List<WebElement> obtenerCeldasLeaveStatus() {
        return leaveStatusCells;
    }

    public List<WebElement> obtenerCeldasLeaveStatusColumn() {
        return leaveStatusCells;
    }

    public List<WebElement> obtenerBotonesQuitarEstados() {
        return removeSelectedStatusButtons;
    }

    public void limpiarFiltros() {
        resetFilters();
    }
}