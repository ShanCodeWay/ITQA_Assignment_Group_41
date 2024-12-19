Feature: Test POST API

  Scenario: Create a valid book
    Given the API is running
    When I send a POST request with valid book data
    Then I should get a 201 response code

  Scenario: Create a book with invalid data
    Given the API is running
    When I send a POST request with missing book title
    Then I should get a 400 response code
