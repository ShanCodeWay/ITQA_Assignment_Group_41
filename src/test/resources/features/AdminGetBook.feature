Feature: Admin Retrieve Book

  Scenario: Admin retrieves an existing book
    Given Admin has set the base URI to "http://localhost:7081"
    And Admin is authenticated with username "admin" and password "password"
    When Admin tries to retrieve the book with title "Existing Book"
    Then Admin should receive a successful response with status code 200 and book details

  Scenario: Admin tries to retrieve a non-existent book
    Given Admin has set the base URI to "http://localhost:7081"
    And Admin is authenticated with username "admin" and password "password"
    When Admin tries to retrieve a non-existent book with title "NonExistent Book"
    Then Admin should receive a failed response to get book with status code 404 and error message "Book not found"

  Scenario: Admin retrieves a book without providing a title
    Given Admin has set the base URI to "http://localhost:7081"
    And Admin is authenticated with username "admin" and password "password"
    When Admin tries to retrieve the book with title ""
    Then Admin should receive a failed response to get book with status code 400 and error message "Title is required"

  Scenario: Admin tries to retrieve a book with invalid data type for title
    Given Admin has set the base URI to "http://localhost:7081"
    And Admin is authenticated with username "admin" and password "password"
    When Admin tries to retrieve the book with title "1234"
    Then Admin should receive a failed response to get book with status code 400 and error message "Invalid data type for title"
