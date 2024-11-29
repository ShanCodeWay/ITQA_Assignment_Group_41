Feature: Login Page UI
  Scenario: Valid Login
    Given the user is on the login page
    When the user enters valid credentials
    Then the user is redirected to the dashboard
