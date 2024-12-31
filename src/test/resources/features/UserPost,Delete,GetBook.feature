Feature: Create, Retrieve, and Delete a Book

  Scenario: Admin creates, retrieves, and deletes a book
    Given Admin has the base URI set to "http://example.com"
    And Admin authenticate as "admin" with password "password"
    When Admin tries to create a book with title "Book Title" and author "Book Author"
    Then Admin should receive a successful response with status code 201
    And Admin should retrieve the book details with title "Book Title" and author "Book Author"
    When Admin tries to delete the book with id 1
    Then Admin should receive a successful response with status code 200
    And Admin should verify that the book is deleted and receive a 404 response when retrieving the book
