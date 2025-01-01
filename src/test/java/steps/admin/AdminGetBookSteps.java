package steps.admin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import steps.CommonValidationSteps;

import static io.restassured.RestAssured.given;

public class AdminGetBookSteps {

    private Response lastResponse;
    private String currentUsername;
    private String currentPassword;
    private final CommonValidationSteps commonValidationSteps;

    public AdminGetBookSteps(CommonValidationSteps commonValidationSteps) {
        this.commonValidationSteps = commonValidationSteps;
    }

    @Given("Admin has set the base URI to {string}")
    public void admin_has_set_the_base_URI(String baseUri) {
        RestAssured.baseURI = baseUri;
        System.out.println("Base URI set to: " + baseUri);
    }

    @Given("Admin is authenticated with username {string} and password {string}")
    public void admin_is_authenticated(String username, String password) {
        currentUsername = username;
        currentPassword = password;
    }

    @When("Admin tries to retrieve all books")
    public void admin_tries_to_retrieve_all_books() {
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .when()
                .get("/api/books");

        System.out.println("Retrieve All Books Response: " + lastResponse.getBody().asString());
        commonValidationSteps.setLastResponse(lastResponse);
    }

    @Then("Admin should receive a successful response with status code {int} and a list of books")
    public void admin_should_receive_a_successful_response_with_status_code_and_a_list_of_books(Integer statusCode) {
        int actualStatusCode = lastResponse.getStatusCode();
        assert actualStatusCode == statusCode : "Expected status code " + statusCode + " but got " + actualStatusCode;

        System.out.println("Books list: " + lastResponse.getBody().asString());
    }

    @When("Admin tries to retrieve the book with ID {int}")
    public void admin_tries_to_retrieve_the_book_with_id(int id) {
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .when()
                .get("/api/books/" + id);

        System.out.println("Retrieve Book Response: " + lastResponse.getBody().asString());
        commonValidationSteps.setLastResponse(lastResponse);
    }

    @Then("Admin should receive a successful response with status code {int} and book details")
    public void admin_should_receive_a_successful_response_with_status_code_and_book_details(Integer statusCode) {
        int actualStatusCode = lastResponse.getStatusCode();
        assert actualStatusCode == statusCode : "Expected status code " + statusCode + " but got " + actualStatusCode;

        System.out.println("Book details: " + lastResponse.getBody().asString());
    }

    @When("Admin tries to retrieve a non-existent book with ID {int}")
    public void admin_tries_to_retrieve_a_non_existent_book_with_id(int id) {
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .when()
                .get("/api/books/" + id);

        System.out.println("Retrieve Non-Existent Book Response: " + lastResponse.getBody().asString());
        commonValidationSteps.setLastResponse(lastResponse);
    }

//    @Then("Admin should receive a failed response with status code {int} and error message {string}")
//    public void admin_should_receive_a_failed_response_with_status_code_and_error_message(Integer statusCode, String errorMessage) {
//        int actualStatusCode = lastResponse.getStatusCode();
//        assert actualStatusCode == statusCode : "Expected status code " + statusCode + " but got " + actualStatusCode;
//
//        String actualErrorMessage = lastResponse.jsonPath().getString("errorMessage");
//        assert actualErrorMessage != null && actualErrorMessage.contains(errorMessage) :
//                "Expected error message to contain '" + errorMessage + "' but got '" + actualErrorMessage + "'";
//    }


    @When("Admin tries to retrieve a book with an empty ID")
    public void admin_tries_to_retrieve_a_book_with_an_empty_id() {
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .when()
                .get("/api/books/");

        System.out.println("Retrieve Book with Empty ID Response: " + lastResponse.getBody().asString());
        commonValidationSteps.setLastResponse(lastResponse);
    }

//    // New step definition for retrieving a book with invalid ID (non-numeric)
//    @When("Admin tries to retrieve a book with ID {String}")
//    public void admin_tries_to_retrieve_a_book_with_invalid_id(String id) {
//        lastResponse = given()
//                .auth()
//                .basic(currentUsername, currentPassword)
//                .header("Content-Type", "application/json")
//                .when()
//                .get("/api/books/" + id);
//
//        System.out.println("Retrieve Book with Invalid ID Response: " + lastResponse.getBody().asString());
//        commonValidationSteps.setLastResponse(lastResponse);
//    }
}