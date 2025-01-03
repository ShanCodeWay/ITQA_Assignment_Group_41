Feature: Recruitment - Add a candidate - 205034B

  Scenario: Add a candidate successfully
    Given I am logged into the OrangeHRM application
    When I navigate to the Recruitment module
    And I click on the "Add Candidate" button
    And I fill in the candidate details
    And I click on the "Save" button
    Then The candidate should be added successfully
