Feature: User Creates a Book - 205010A

  Scenario: User creates a new book when it does not exist
    Given User has the base URI set to the "http://localhost:7081"
    And User authenticate as the "user" with password "password"
    When User tries to create a book with title "The Pragmatic Programmer" and author "Andrew Hunt"
    Then User should receive a successful response with status code the 201

  Scenario: User tries to create a book that already exists
    Given User has the base URI set to "http://localhost:7081"
    And User authenticate as the "user" with password "password"
    When User tries to create a book with title "The Pragmatic Programmer" and author "Andrew Hunt"
    Then User should receive a failed response with status code 208 and error message "Book Already Exists" for operation

  Scenario: User tries to create a book with missing title
    Given User has the base URI set to "http://localhost:7081"
    And User authenticate as the "user" with password "password"
    When User tries to create a book without a title and with author "Robert C. Martin"
    Then User should receive a failed response with status code 400 and error message "Title is required" for operation

  Scenario: User tries to create a book without an author
    Given User has the base URI set to "http://localhost:7081"
    And User authenticate as the "user" with password "password"
    When User tries to create a book with title "Book Without Author" and without an author
    Then User should receive a failed response with status code 400 and error message "Author is required" for operation

  Scenario: User tries to create a book without both title and author
    Given User has the base URI set to "http://localhost:7081"
    And User authenticate as the "user" with password "password"
    When User tries to create a book without a title and without an author
    Then User should receive a failed response with status code 400 and error message "Title and Author are required" for operation

  Scenario: User tries to create a book without an ID (assuming ID is mandatory)
    Given User has the base URI set to "http://localhost:7081"
    And User authenticate as the "user" with password "password"
    When User tries to create a book without an ID but with title "Book Without ID" and author "Author Name"
    Then User should receive a failed response with status code 400 and error message "ID is required" for operation

  Scenario: User tries to create a book with invalid data types (e.g., number as title)
    Given User has the base URI set to "http://localhost:7081"
    And User authenticate as the "user" with password "password"
    When User tries to create a book with title 1234 and author "Author Name"
    Then User should receive a failed response with status code 400 and error message "Invalid data types" for operation

  Scenario: User tries to create a book with invalid or additional parameters
    Given User has the base URI set to "http://localhost:7081"
    And User authenticate as the "user" with password "password"
    When User tries to create a book with title "Valid Title", author "Valid Author", and an invalid parameter "invalidParam" with value "1234"
    Then User should receive a failed response with status code 400 and error message "Invalid parameter: invalidParam" for operation

  Scenario: Verify the API returns 401 for unauthorized access
    Given User has the base URI set to "http://localhost:7081"
    When User tries to create a book with title "Test Book 05", and author "Author 05" without credentials
    Then User should receive a failed response with status code 401 and error message "Unauthorized access" for operation

  Scenario: User tries to create a book with the wrong password
    Given User has the base URI set to "http://localhost:7081"
    And User authenticate as the "user" with password "wrongpassword"
    When User tries to create a book with title "Test Book" and author "Test Author"
    Then User should receive a failed response with status code 401 and error message "Invalid credentials" for operation