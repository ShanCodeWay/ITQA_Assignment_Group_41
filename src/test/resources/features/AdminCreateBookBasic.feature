Feature: Admin Create Book Basic-205022L

  Scenario: Admin creates a new book when it does not exist
  Given Admin has the base URI set to "http://localhost:7081"
  And Admin authenticate as "admin" with password "password"
  When Admin tries to create a book with title "Unique Book" and author "Author Name"
  Then Admin should receive a successful response with status code 201

  Scenario: Admin tries to create a book that already exists
  Given Admin has the base URI set to "http://localhost:7081"
  And Admin authenticate as "admin" with password "password"
  When Admin tries to create a book with title "Unique Book" and author "Author Name"
  Then Admin should receive a Book Already Exists response with status code 208

  Scenario: Admin tries to create a book without a title
  Given Admin has the base URI set to "http://localhost:7081"
  And Admin authenticate as "admin" with password "password"
  When Admin tries to create a book without a title and with author "Author Name"
  Then Admin should receive a failed response with status code 400 and error message "Title is required"

  Scenario: Admin tries to create a book without an author
  Given Admin has the base URI set to "http://localhost:7081"
  And Admin authenticate as "admin" with password "password"
  When Admin tries to create a book with title "Book Without Author" and without an author
  Then Admin should receive a failed response with status code 400 and error message "Author is required"