Feature: Book API Testing

  Background:
    Given I have the base URI set to "http://localhost:7081"
    And I authenticate as "admin" with password "password"

  # Scenario for creating a new book (POST)
  Scenario: Admin user creates a new book
    Given I create a book with title "The Great Gatsby" and author "F. Scott Fitzgerald"
    Then I should receive a successful response with status code 201

  # Scenario for updating an existing book (PUT)
  Scenario: Admin user updates a book
    Given I update the book with id 1, title "1984", and author "George Orwell"
    Then I should receive a successful response with status code 200

  # Scenario for deleting a book (DELETE)
  Scenario: Admin user deletes a book
    Given I delete the book with id 1
    Then I should receive a successful response with status code 200

  # Scenario for retrieving a single book (GET)
  Scenario: Admin user retrieves a book by ID
    Given I retrieve the book with id 1
    Then I should receive a successful response with status code 200

  # Scenario for retrieving all books (GET)
  Scenario: Admin user retrieves all books
    Given I retrieve all books
    Then I should receive a successful response with status code 200
