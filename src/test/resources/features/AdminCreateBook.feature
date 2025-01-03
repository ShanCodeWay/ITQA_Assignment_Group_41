Feature: Admin Create Book-205116E

  Scenario: Admin tries to create a book without both title and author
    Given Admin has the base URI set to "http://localhost:7081"
    And Admin authenticate as "admin" with password "password"
    When Admin tries to create a book without a title and without an author
    Then Admin should receive a failed response with status code 400 and error message "Title and Author are required"

  Scenario: Admin tries to create a book without an ID (assuming ID is mandatory)
    Given Admin has the base URI set to "http://localhost:7081"
    And Admin authenticate as "admin" with password "password"
    When Admin tries to create a book without an ID but with title "Book Without ID" and author "Author Name"
    Then Admin should receive a failed response with status code 400 and error message "ID is required"

  Scenario: Admin tries to create a book with invalid data types (e.g., number as title)
    Given Admin has the base URI set to "http://localhost:7081"
    And Admin authenticate as "admin" with password "password"
    When Admin tries to create a book with title "1234" and author "Author Name"
    Then Admin should receive a failed response with status code 400 and error message "Invalid data types"

  Scenario: Verify the API returns 401 for unauthorized access
    Given Admin has the base URI set to "http://localhost:7081"
    When Admin tries to create a book with title "Test Book 05", and author "Author 05" without credentials
    Then Admin should receive a failed response with status code 401

  Scenario: Admin tries to create a book with the wrong password
    Given Admin has the base URI set to "http://localhost:7081"
    And Admin authenticate as "admin" with password "wrongpassword"
    When Admin tries to create a book with title "Test Book" and author "Test Author"
    Then Admin should receive a failed response with status code 401 and error message "Invalid credentials"

  Scenario: User without access tries to create a book
    Given User has the base URI set to "http://localhost:7081"
    And User authenticate as "user" with password "password"
    When User try to create a book with title "Test Book 05" and author "Author 05"
    Then User should receive a failed response with status code 403 and error message "Access denied" for operation


