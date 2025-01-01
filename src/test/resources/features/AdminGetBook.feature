Feature: Admin Retrieve Book

  # Scenario to retrieve all books
  Scenario: Admin retrieves all books
    Given Admin has set the base URI to "http://localhost:7081"
    And Admin is authenticated with username "admin" and password "password"
    When Admin tries to retrieve all books
    Then Admin should receive a successful response with status code 200 and a list of books

  # Scenario to retrieve an existing book by ID
  Scenario: Admin retrieves an existing book by ID
    Given Admin has set the base URI to "http://localhost:7081"
    And Admin is authenticated with username "admin" and password "password"
    When Admin tries to retrieve the book with ID 1
    Then Admin should receive a successful response with status code 200 and book details

  # Scenario to retrieve a non-existent book by ID
  Scenario: Admin tries to retrieve a non-existent book by ID
    Given Admin has set the base URI to "http://localhost:7081"
    And Admin is authenticated with username "admin" and password "password"
    When Admin tries to retrieve a non-existent book with ID 9999
    Then Admin should receive a failed response with status code 404 and error message "Book not found"

  # Scenario to retrieve a book without providing an ID
  Scenario: Admin tries to retrieve a book with an empty ID
    Given Admin has set the base URI to "http://localhost:7081"
    And Admin is authenticated with username "admin" and password "password"
    When Admin tries to retrieve a book with an empty ID
    Then Admin should receive a failed response with status code 400 and error message "ID is required"

#  # Scenario to retrieve a book with invalid data type for ID
#  Scenario: Admin tries to retrieve a book with ID "abc"
#    Given Admin has set the base URI to "http://localhost:7081"
#    And Admin is authenticated with username "admin" and password "password"
#    When Admin tries to retrieve a book with ID abc
#    Then Admin should receive a failed response with status code 400 and error message "Invalid data type for ID"
