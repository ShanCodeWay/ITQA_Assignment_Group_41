Feature: Sample API Test

  Scenario: Test GET request to retrieve users
    Given the endpoint "/api/users?page=2" is available
    When I send a GET request
    Then I should receive a response with status code 200
