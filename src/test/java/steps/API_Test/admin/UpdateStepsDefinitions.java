package steps.API_Test.admin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import steps.API_Test.commonSteps.CommonSteps;

import static io.restassured.RestAssured.given;

public class UpdateStepsDefinitions {

    private Response lastResponse;
    private String currentUsername;
    private String currentPassword;
    private final CommonSteps commonSteps;

    public UpdateStepsDefinitions(  CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
    }

    @Given("Admin authenticate as {string} with password {string} for update operations")
    public void admin_authenticate_as_with_password(String username, String password) {
        currentUsername = username;
        currentPassword = password;
    }

    @Given("User authenticate as {string} with password {string} for update operations")
    public void user_authenticate_as_with_password(String username, String password) {
        currentUsername = username;
        currentPassword = password;
    }

    @When("Admin updates a book with ID {int}, title {string}, and author {string}")
    public void admin_updates_a_book_with_id_title_and_author(Integer id, String title, String author) {
        String bookData = "{ \"id\": " + id + ", \"title\": \"" + title + "\", \"author\": \"" + author + "\" }";
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .put("/api/books/" + id);
        commonSteps.setLastResponse(lastResponse);
    }

    @When("Admin tries to update a book with mismatched ID {int}, title {string}, and author {string}")
    public void admin_tries_to_update_a_book_with_mismatched_id(Integer id, String title, String author) {
        String bookData = "{ \"id\": " + id + ", \"title\": \"" + title + "\", \"author\": \"" + author + "\" }";
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .put("/api/books/" + id);
        commonSteps.setLastResponse(lastResponse);
    }

    @When("Admin updates a book with ID {int}, missing title, and author {string}")
    public void admin_updates_a_book_with_id_missing_title_and_author(Integer id, String author) {
        String bookData = "{ \"id\": " + id + ", \"title\": \"\", \"author\": \"" + author + "\" }";
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .put("/api/books/" + id);
        commonSteps.setLastResponse(lastResponse);
    }

    @When("Admin updates a book with ID {int}, title {string}, and missing author")
    public void admin_updates_a_book_with_id_title_and_missing_author(Integer id, String title) {
        String bookData = "{ \"id\": " + id + ", \"title\": \"" + title + "\", \"author\": \"\" }";
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .put("/api/books/" + id);
        commonSteps.setLastResponse(lastResponse);
    }

    @When("Admin updates a book with ID {int}, duplicate title {string}, and author {string}")
    public void admin_updates_a_book_with_id_duplicate_title_and_author(Integer id, String title, String author) {
        String bookData = "{ \"id\": " + id + ", \"title\": \"" + title + "\", \"author\": \"" + author + "\" }";
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .put("/api/books/" + id);
        commonSteps.setLastResponse(lastResponse);
    }

    @When("User updates a book with ID {int}, title {string}, and author {string}")
    public void user_updates_a_book_with_id_title_and_author(Integer id, String title, String author) {
        String bookData = "{ \"id\": " + id + ", \"title\": \"" + title + "\", \"author\": \"" + author + "\" }";
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .put("/api/books/" + id);
        commonSteps.setLastResponse(lastResponse);
    }

    @When("Admin updates a book with ID {int}, missing title, and missing author")
    public void admin_updates_a_book_with_id_missing_title_and_missing_author(Integer id) {
        String bookData = "{ \"id\": " + id + ", \"title\": \"\", \"author\": \"\" }";
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .put("/api/books/" + id);
        commonSteps.setLastResponse(lastResponse);
    }

    @Then("Admin should receive a successful response with status code {int} for operation")
    public void Admin_should_receive_a_successful_response_with_status_code_for_operation(int statusCode) {
        Assert.assertNotNull("No response received!", lastResponse);
        Assert.assertEquals(statusCode, lastResponse.getStatusCode());
    }


}