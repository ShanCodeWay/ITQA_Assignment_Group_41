Feature: Verify dashboard widgets
  Scenario: Check the presence of widgets
    Given the user is on the dashboard page
    When the page loads
    Then the widgets should be visible
