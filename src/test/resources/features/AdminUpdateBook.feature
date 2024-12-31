Feature: Admin Update Book

  Scenario: Admin updates an existing book
    Given Admin has the base URI set to "http://localhost:7081"
    And Admin authenticate as "admin" with password "password"
    When Admin tries to update a book with id 1, title "Updated Title", and author "Updated Author"
    Then Admin should receive a successful response with status code 200
