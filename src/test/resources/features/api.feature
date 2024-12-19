Feature: API Tests
  Validate REST API endpoints for https://reqres.in/.

  Scenario: Test GET request to retrieve users
    Given the endpoint "/api/users?page=2" is available
    When I send a GET request
    Then I should receive a response with status code 200

  Scenario: Test POST request to create a new user
    Given the endpoint "/api/users" is available
    When I send a POST request with body:
      | name  | job  |
      | John  | QA   |
    Then I should receive a response with status code 201

  Scenario: Test DELETE request to remove a user
    Given the endpoint "/api/users/2" is available
    When I send a DELETE request
    Then I should receive a response with status code 204
