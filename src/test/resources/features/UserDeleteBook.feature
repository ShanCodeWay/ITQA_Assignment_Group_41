Feature: User Delete Book - 205034B

  Scenario: User deletes a book
    Given User has the base URI set to "http://localhost:7081"
    And User authenticate as "user" with password "password"
    When User tries to delete a book with id 17
    Then User should receive a successful delete response with status code 401
