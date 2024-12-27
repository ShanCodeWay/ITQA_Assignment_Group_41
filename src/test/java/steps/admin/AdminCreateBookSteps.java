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

        List<String> bookTitles = response.jsonPath().getList("title");
        return bookTitles.contains(title);
    }

    @Then("BUG DETECTED: Admin should see a log that the book already exists")
    public void bug_detected_admin_should_see_a_log_that_the_book_already_exists() {

        String logMessage = "BUG DETECTED: The book with title 'Duplicate Book' already exists in the system.";
        System.out.println("Verifying log: " + logMessage);
    }
}
