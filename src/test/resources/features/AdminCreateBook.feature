Feature: Admin Create Book-205034B

  Scenario: Verify the API returns 401 for unauthorized access
    Given Admin has the base URI set to "http://localhost:7081"
    When Admin tries to create a book with title "Test Book 05", and author "Author 05" without credentials
    Then Admin should receive a failed response with status code 401

  Scenario: Admin tries to create a book with the wrong password
    Given Admin has the base URI set to "http://localhost:7081"
    And Admin authenticate as "admin" with password "wrongpassword"
    When Admin tries to create a book with title "Test Book" and author "Test Author"
    Then Admin should receive a failed response with status code 401 and error message "Invalid credentials"

  # Scenario to retrieve a book without providing an ID
  Scenario: Admin tries to retrieve a book with an empty ID
    Given Admin has set the base URI to "http://localhost:7081"
    And Admin is authenticated with username "admin" and password "password"
    When Admin tries to retrieve a book with an empty ID
    Then Admin should receive a failed response with status code 400 and error message "ID is required"

