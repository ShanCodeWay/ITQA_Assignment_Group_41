Feature: User Creates a Book

  Scenario: User creates a new book when it does not exist
    Given User has the base URI set to the "http://localhost:7081"
    And User authenticate as the "user" with password "password"
    When User tries to create a book with title "The Pragmatic Programmer" and author "Andrew Hunt"
    Then User should receive a successful response with status code the 201

  Scenario: User tries to create a book with missing title
    Given User has the base URI set to "http://localhost:7081"
    And User authenticate as "user" with password "password"
    When User tries to create a book with title "" and author "Robert C. Martin"
    Then User should receive a failed response with status code 400 and error message "Title is required"

