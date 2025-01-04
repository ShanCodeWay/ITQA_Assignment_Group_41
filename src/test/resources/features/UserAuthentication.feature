Feature: User Authentication and Authorization Testing-205116E

  Background:
    Given User has the base URI set to "http://localhost:7081"

  Scenario: Verify User authentication and access
    Given User authenticate as "user" with password "password"
    When User try to create a book with title "UserBook" and author "UserAuthor"
    Then User should receive a successful response with status code 201
    When User try to update a book with id 2, title "UpdatedTitle" and author "UpdatedAuthor"
    Then User should receive a response with status code 403
    When User try to delete a book with id 2
    Then User should receive a response with status code 403

  Scenario: Verify invalid authentication
    Given User authenticate as "invalidUser" with password "wrongPassword"
    When User try to create a book with title "InvalidBook" and author "InvalidAuthor"
    Then User should receive a response with status code 401
