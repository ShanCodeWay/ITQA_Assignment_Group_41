package steps.admin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import steps.CommonValidationSteps;

import java.util.List;

import static io.restassured.RestAssured.given;

public class AdminCreateBookSteps {

    private Response lastResponse;
    private String currentUsername;
    private String currentPassword;
    private final CommonValidationSteps commonValidationSteps;

    public AdminCreateBookSteps(CommonValidationSteps commonValidationSteps) {
        this.commonValidationSteps = commonValidationSteps;
    }

    @Given("Admin has the base URI set to {string}")
    public void Admin_have_the_base_uri_set_to(String baseUri) {
        RestAssured.baseURI = baseUri;
        System.out.println("Base URI is set to: " + baseUri);
    }

    @Given("Admin authenticate as {string} with password {string}")
    public void Admin_authenticate_as_admin_with_password(String admin, String password) {
        currentUsername = admin;
        currentPassword = password;
    }

    @When("Admin tries to create a book with title {string} and author {string}")
    public void Admin_tries_to_create_a_book_with_title_and_author(String title, String author) {
        // Check if the book already exists
        boolean bookExists = checkIfBookExists(title);

        if (bookExists) {
            System.out.println("BUG DETECTED: The book with title '" + title + "' already exists in the system.");
            System.out.println("Skipping creation of this book to avoid duplication.");

            lastResponse = given()
                    .auth()
                    .basic(currentUsername, currentPassword)
                    .header("Content-Type", "application/json")
                    .when()
                    .get("/api/books"); // Mock response for debugging

            commonValidationSteps.setLastResponse(lastResponse);
            return;
        }

        // Handle missing title and author
        if (title == null || title.isEmpty()) {
            System.out.println("BUG DETECTED: Title is required.");
            lastResponse = given()
                    .auth()
                    .basic(currentUsername, currentPassword)
                    .header("Content-Type", "application/json")
                    .when()
                    .post("/api/books"); // Send request with missing title
            commonValidationSteps.setLastResponse(lastResponse);
            return;
        }

        if (author == null || author.isEmpty()) {
            System.out.println("BUG DETECTED: Author is required.");
            lastResponse = given()
                    .auth()
                    .basic(currentUsername, currentPassword)
                    .header("Content-Type", "application/json")
                    .when()
                    .post("/api/books"); // Send request with missing author
            commonValidationSteps.setLastResponse(lastResponse);
            return;
        }

        // Create the book if it doesn't exist
        String bookData = "{ \"title\": \"" + title + "\", \"author\": \"" + author + "\" }";
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .post("/api/books");

        System.out.println("Create Book Response: " + lastResponse.getBody().asString());
        commonValidationSteps.setLastResponse(lastResponse);
    }

    // Check if the book exists by title
    private boolean checkIfBookExists(String title) {
        Response response = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .when()
                .get("/api/books");

        if (response.getStatusCode() != 200) {
            System.err.println("Failed to fetch books. Status code: " + response.getStatusCode());
            return false;
        }

        // Assuming the response is a JSON array of books, and we check for the title in the response
        List<String> bookTitles = response.jsonPath().getList("title");
        return bookTitles.contains(title);
    }

    @Then("BUG DETECTED: Admin should see a log that the book already exists")
    public void bug_detected_admin_should_see_a_log_that_the_book_already_exists() {
        System.out.println("BUG DETECTED: Admin should see a log that the book already exists");
    }

    @When("Admin tries to create a book without a title and with author {string}")
    public void admin_tries_to_create_a_book_without_a_title_and_with_author(String author) {
        String title = "";
        System.out.println("Admin tries to create a book without a title and with author " + author);
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body("{\"title\": \"" + title + "\", \"author\": \"" + author + "\"}")
                .when()
                .post("/api/books");
        commonValidationSteps.setLastResponse(lastResponse);
        System.out.println("admin_tries_to_create_a_book_without_a_title_and_with_author Response: " + lastResponse.asString());

    }
    @When("Admin tries to create a book with title {string} and without an author")
    public void admin_tries_to_create_a_book_with_title_and_without_an_author(String title) {
        String author = ""; // Empty author field
        System.out.println("Admin tries to create a book with title '" + title + "' and without an author");

        // Ensure book creation request is sent with an empty author field
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body("{\"title\": \"" + title + "\", \"author\": \"" + author + "\"}")
                .when()
                .post("/api/books");

        commonValidationSteps.setLastResponse(lastResponse);
        System.out.println("Response: " + lastResponse.asString());
    }



    @When("Admin tries to create a book without a title and without an author")
    public void admin_tries_to_create_a_book_without_a_title_and_without_an_author() {
        String title = ""; // Empty title
        String author = ""; // Empty author
        System.out.println("Admin tries to create a book without a title and without an author");
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body("{\"title\": \"" + title + "\", \"author\": \"" + author + "\"}")
                .when()
                .post("/api/books");
        commonValidationSteps.setLastResponse(lastResponse);
    }

    @When("Admin tries to create a book without an ID but with title {string} and author {string}")
    public void admin_tries_to_create_a_book_without_an_id_but_with_title_and_author(String title, String author) {
        System.out.println("Admin tries to create a book without an ID but with title " + title + " and author " + author);
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body("{\"title\": \"" + title + "\", \"author\": \"" + author + "\"}")
                .when()
                .post("/api/books");
        commonValidationSteps.setLastResponse(lastResponse);
    }

//    @Then("Admin should receive a failed response with status code {int} and error message {string}")
//    public void admin_should_receive_a_failed_response_with_status_code_and_error_message(Integer statusCode, String errorMessage) {
//        System.out.println("Admin should receive a failed response with status code " + statusCode + " and error message " + errorMessage);
//
//        int actualStatusCode = lastResponse.getStatusCode();
//        String actualErrorMessage = lastResponse.jsonPath().getString("errorMessage");
//
//        // Check if status code matches
//        assert actualStatusCode == statusCode : "Expected status code " + statusCode + " but got " + actualStatusCode;
//
//        // Check if error message matches
//        assert actualErrorMessage != null && actualErrorMessage.contains(errorMessage) :
//                "Expected error message to contain '" + errorMessage + "' but got '" + actualErrorMessage + "'";
//    }

}
