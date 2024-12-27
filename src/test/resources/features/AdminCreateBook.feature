Feature: Admin Create Book

  Scenario: Admin creates a new book when it does not exist
    Given Admin has the base URI set to "http://localhost:7081"
    And Admin authenticate as "admin" with password "password"
    When Admin tries to create a book with title "Unique Book" and author "Author Name"
    Then Admin should receive a successful response with status code 200

  Scenario: Admin tries to create a book that already exists
    Given Admin has the base URI set to "http://localhost:7081"
    And Admin authenticate as "admin" with password "password"
    When Admin tries to create a book with title "Unique Book" and author "Author Name"
    Then BUG DETECTED: Admin should see a log that the book already exists
