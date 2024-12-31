package steps.admin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import steps.CommonValidationSteps;

import static io.restassured.RestAssured.given;

public class AdminPostDeleteGetBookSteps {

    private Response lastResponse;
    private String currentUsername;
    private String currentPassword;
    private int bookId;
    private final CommonValidationSteps commonValidationSteps;

    public AdminPostDeleteGetBookSteps(CommonValidationSteps commonValidationSteps) {
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
        String bookData = "{ \"title\": \"" + title + "\", \"author\": \"" + author + "\" }";

        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .post("/api/books");

        bookId = lastResponse.jsonPath().getInt("id"); // Get the ID of the newly created book
        System.out.println("Create Book Response: " + lastResponse.getBody().asString());
        commonValidationSteps.setLastResponse(lastResponse);
    }

    @Then("Admin should receive a successful response with status code {int}")
    public void Admin_should_receive_a_successful_response_with_status_code(Integer statusCode) {
        System.out.println("Expected status code: " + statusCode);
        int actualStatusCode = lastResponse.getStatusCode();
        assert actualStatusCode == statusCode : "Expected status code " + statusCode + " but got " + actualStatusCode;
    }

    @Then("Admin should retrieve the book details with title {string} and author {string}")
    public void Admin_should_retrieve_the_book_details_with_title_and_author(String title, String author) {
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .when()
                .get("/api/books/" + bookId);

        System.out.println("Retrieve Book Response: " + lastResponse.getBody().asString());

        String retrievedTitle = lastResponse.jsonPath().getString("title");
        String retrievedAuthor = lastResponse.jsonPath().getString("author");

        assert retrievedTitle.equals(title) : "Expected title " + title + " but got " + retrievedTitle;
        assert retrievedAuthor.equals(author) : "Expected author " + author + " but got " + retrievedAuthor;
    }

    @When("Admin tries to delete the book with id {int}")
    public void Admin_tries_to_delete_the_book_with_id(int id) {
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .when()
                .delete("/api/books/" + id);

        System.out.println("Delete Book Response: " + lastResponse.getBody().asString());
        commonValidationSteps.setLastResponse(lastResponse);
    }

    @Then("Admin should verify that the book is deleted and receive a 404 response when retrieving the book")
    public void Admin_should_verify_that_the_book_is_deleted_and_receive_a_404_response_when_retrieving_the_book() {
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .when()
                .get("/api/books/" + bookId);

        System.out.println("Retrieve Book After Deletion Response: " + lastResponse.getBody().asString());

        int actualStatusCode = lastResponse.getStatusCode();
        assert actualStatusCode == 404 : "Expected 404 but got " + actualStatusCode;
    }
}
