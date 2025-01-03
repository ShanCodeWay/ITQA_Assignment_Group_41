Feature: Search candidate in Recruitment module - 205034B


  Scenario: Search a candidate by name
    Given the user is on the OrangeHRM recruitment page
    When the user searches for a candidate with name "John Doe"
    Then the candidate details should be displayed
