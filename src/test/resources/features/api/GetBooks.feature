Feature: Get all books
  Scenario: Fetch all books
    Given the API is up and running
    When I send a GET request to "/api/books"
    Then I should receive a 200 OK status
