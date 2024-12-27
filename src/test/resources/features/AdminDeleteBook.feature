Feature: Admin Delete Book

  Scenario: Admin deletes a book
    Given Admin has the base URI set to "http://localhost:7081"
    And Admin authenticate as "admin" with password "password"
    When Admin tries to delete a book with id 1
    Then Admin should receive a successful response with status code 200
