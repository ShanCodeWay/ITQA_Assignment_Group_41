Feature: Admin Update Book-205074V

  Scenario: Admin successfully updates a book
    Given The API server is running
    And Admin authenticate as "admin" with password "password" for update operations
    When Admin updates a book with ID 3, title "Updated Title", and author "Updated Author"
    Then Admin should receive a successful response with status code 200 for operation

  Scenario: Admin tries to update a book with mismatched ID
    Given The API server is running
    And Admin authenticate as "admin" with password "password" for update operations
    When Admin tries to update a book with mismatched ID 2, title "Some Title", and author "Some Author"
    Then Admin should receive a failed response with status code 404 and error message "Book id is not matched" for operation

  Scenario: Admin tries to update a book with missing title
    Given The API server is running
    And Admin authenticate as "admin" with password "password" for update operations
    When Admin updates a book with ID 1, missing title, and author "Updated Author"
    Then Admin should receive a failed response with status code 400 and error message "Title cannot be null" for operation

  Scenario: Admin tries to update a book with missing author
    Given The API server is running
    And Admin authenticate as "admin" with password "password" for update operations
    When Admin updates a book with ID 1, title "Updated Title", and missing author
    Then Admin should receive a failed response with status code 400 and error message "Author cannot be null" for operation

  Scenario: Admin tries to update a book with duplicate title
    Given The API server is running
    And Admin authenticate as "admin" with password "password" for update operations
    When Admin updates a book with ID 1, duplicate title "Duplicate Title", and author "Updated Author"
    Then Admin should receive a failed response with status code 400 and error message "Book Title Already Exists" for operation

  Scenario: Admin tries to update a book with missing title and missing author
    Given The API server is running
    And Admin authenticate as "admin" with password "password" for update operations
    When Admin updates a book with ID 1, missing title, and missing author
    Then Admin should receive a failed response with status code 400 and error message "Mandatory parameters should not be null" for operation

  Scenario: User tries to update a book
    Given The API server is running
    And User authenticate as "user" with password "password" for update operations
    When User updates a book with ID 1, title "User Update", and author "User Author"
    Then User should receive a failed response with status code 403 and error message "User not permitted" for operation

