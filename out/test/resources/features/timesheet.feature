@TimeSheet
Feature: Employee Timesheet Management

  As a Time Administrator
  I want to manage employee timesheets
  So that I can control and review worked hours

  Background:
    Given open the browser and navigate to "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And the user is logged into OrangeHRM with user "Admin" and password "admin123"
    And the user navigates to Employee Timesheets page

  @Test-01
  Scenario Outline: Search timesheet by employee name
    When the user enters "<employeeName>" in Employee Name field
    And clicks View
    Then the timesheet for "<employeeName>" should be displayed

    Examples:
      | employeeName |
      | Charlotte Smith |
      | John Smith |
      | Paul Collings |

  @Test-02
  Scenario Outline: Validate employee autocomplete suggestions
    When the user types "<partialName>" in Employee Name field
    Then employee suggestions containing "<partialName>" should be displayed

    Examples:
      | partialName |
      | Li |
      | Jo |
      | Pa |

  @Test-03
  Scenario Outline: View employee timesheet for a specific period
    When the user searches employee "<employeeName>"
    And the user clicks in view button
    And selects timesheet period "<period>"
    Then the system should display the timesheet for "<period>"

    Examples:
      | employeeName   | period |
      | Char           | 2026-01-05 |
      | John Smith     | 2026-02-02 |
  @Test-04
  Scenario Outline: View an existing timesheet
    Given employee "<employeeName>" has a timesheet for "<period>"
    When the user opens the timesheet
    Then worked hours should be displayed

    Examples:
      | employeeName   | period |
      | Charlotte Smith | 2026-01-05 |

  @Test-05
  Scenario Outline: Validate behavior when timesheet does not exist
    When the user searches employees "<employeeName>"
    And selects period "<period>"
    Then a no timesheet available message should be displayed

    Examples:
      | employeeName              | period |
      | Hiago Aguiar da Silva     | 2030-01-01 |

  @Test-06
  Scenario Outline: Navigate to previous timesheet period
    Given employee "<employeeName>" timesheet is displayed
    When the user clicks Previous
    Then the previous period timesheet should be displayed

    Examples:
      | employeeName |
      | Hiago Aguiar da Silva |


  @Test-07
  Scenario Outline: Navigate to next timesheet period
    Given employee "<employeeName>" timesheet is displayed
    When the user clicks Next
    Then the next period timesheet should be displayed

    Examples:
      | employeeName |
      | Charlotte Smith |

  @Test-08
  Scenario Outline: Validate daily worked hours
    Given employee "<employeeName>" has a timesheet
    When the timesheet is displayed
    Then employee "<employeeName>" hours recorded for "<period>" should be greater than or equal to 0

    Examples:
      | employeeName    | period     |
      | Charlotte Smith | 2026-17-07 |

  Scenario Outline: Validate total weekly hours calculation
    Given employee "<employeeName>" timesheet is displayed
    When all daily hours are summed
    Then the calculated total should equal displayed total hours

    Examples:
      | employeeName |
      | Charlotte Smith |
      | John Smith |

  Scenario Outline: Validate project allocation hours
    Given employee "<employeeName>" has project entries
    When project "<project>" is displayed
    Then project hours should be included in total hours

    Examples:
      | employeeName | project |
      | Charlotte Smith | ACME |
      | John Smith | Internal Support |

  Scenario Outline: Update worked hours in timesheet
    Given employee "<employeeName>" has editable timesheet
    When the user updates "<hours>" hours on "<day>"
    And saves the timesheet
    Then the updated hours should be displayed

    Examples:
      | employeeName | day | hours |
      | Charlotte Smith | Monday | 8 |
      | Charlotte Smith | Tuesday | 6 |

  Scenario Outline: Save timesheet modifications
    Given employee "<employeeName>" timesheet is open
    When the user modifies worked hours
    And clicks Save
    Then a successful save message should be displayed

    Examples:
      | employeeName |
      | Charlotte Smith |

  Scenario Outline: Prevent negative hours registration
    Given employee "<employeeName>" timesheet is editable
    When the user enters "<hours>" hours
    And saves the timesheet
    Then a validation message should be displayed

    Examples:
      | employeeName | hours |
      | Charlotte Smith | -1 |
      | Charlotte Smith | -5 |

  Scenario Outline: Prevent invalid hour formats
    Given employee "<employeeName>" timesheet is editables
    When the user enters "<invalidValue>"
    Then validation should prevent saving

    Examples:
      | employeeName | invalidValue |
      | Charlotte Smith | ABC |
      | Charlotte Smith | @@ |
      | Charlotte Smith | ### |

  Scenario Outline: Reset employee timesheet search criteria
    Given the user searched for employee "<employeeName>"
    When the user clicks Resets
    Then employee search criteria should be cleared

    Examples:
      | employeeName |
      | Charlotte Smith |

  Scenario Outline: Validate project totals calculation
    Given employee "<employeeName>" has multiple projects
    When the project rows are summed
    Then the project total should match displayed total

    Examples:
      | employeeName |
      | Charlotte Smith |

  Scenario Outline: Create a new employee timesheet
    Given employee "<employeeName>" does not have a timesheet for "<period>"
    When the administrator creates a timesheet
    Then the timesheet should be successfully created

    Examples:
      | employeeName | period |
      | John Smith | 2026-10-05 |

  Scenario Outline: Validate timesheet persistence after refresh
    Given employee "<employeeName>" timesheet exists
    When the user updates worked hours
    And refreshes the browser
    Then the saved hours should remain unchanged

    Examples:
      | employeeName |
      | Charlotte Smith |

  Scenario Outline: Search timesheet for non-existing employee
    When the user searches employee "<employeeName>"
    Then no employee should be found

    Examples:
      | employeeName |
      | EmployeeDoesNotExist |

  Scenario Outline: Prevent SQL Injection in employee search
    When the user enters "<payload>" in Employee Name field
    And clicks View
    Then unauthorized timesheets should not be displayed
    And the application should remain stable

    Examples:
      | payload |
      | ' OR 1=1 -- |
      | admin' -- |
      | ' UNION SELECT * FROM USERS -- |

  Scenario Outline: Prevent XSS execution in employee search
    When the user enters "<payload>" in Employee Name field
    And clicks View
    Then no script should execute
    And the application should remain operational

    Examples:
      | payload |
      | <script>alert('xss')</script> |
      | x |

  Scenario Outline: Create and validate employee timesheet
    Given employee "<employeeName>" existss
    When a new timesheet is created for "<period>"
    And hours are entered and saved
    Then the timesheet should be available for future searches

    Examples:
      | employeeName | period |
      | Charlotte Smith | 2026-08-03 |

  Scenario Outline: Update and validate timesheet
    Given employee "<employeeName>" has an existing timesheet
    When worked hours are updated
    And the timesheet is saved
    Then updated values should be displayed after reopening the timesheet

    Examples:
      | employeeName |
      | Charlotte Smith |

  Scenario Outline: Submit and approve employee timesheet
    Given employee "<employeeName>" completed a timesheet
    When the manager approves the timesheet
    Then the timesheet status should become Approved

    Examples:
      | employeeName |
      | Charlotte Smith |