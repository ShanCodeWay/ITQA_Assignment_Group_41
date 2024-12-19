Feature: Dashboard UI Tests
  Validate widgets visibility and navigation on the dashboard.

  Scenario: Validate the visibility of dashboard widgets
    Given I am on the OrangeHRM dashboard page
    Then I should see the "Quick Launch" widget
    And I should see the "Pending Leave Requests" widget

  Scenario: Verify navigation from a widget
    Given I am on the OrangeHRM dashboard page
    When I click on "Apply Leave" in the "Quick Launch" widget
    Then I should navigate to the "Leave" page
