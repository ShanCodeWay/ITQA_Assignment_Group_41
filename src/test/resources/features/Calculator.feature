Feature: Calculator

  Scenario: Add two numbers
    Given I have entered 50 into the calculator
    And I have entered 70 into the calculator
    When I press add
    Then the result should be 120 on the screen

  Scenario: Subtract two numbers
    Given I have entered 90 into the calculator
    And I have entered 42 into the calculator
    When I press subtract
    Then the result should be 48 on the screen