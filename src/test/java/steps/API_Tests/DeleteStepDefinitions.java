package steps.API_Tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class DeleteStepDefinitions {

    private Response lastResponse;
    private String currentUsername;
    private String currentPassword;
    private final CommonSteps commonSteps;

    public DeleteStepDefinitions(  CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
    }

    @Given("Admin authenticate as {string} with password {string} for delete operations")
    public void admin_authenticate_as_with_password(String username, String password) {
        currentUsername = username;
        currentPassword = password;
    }

    @Given("User authenticate as {string} with password {string} for delete operations")
    public void user_authenticate_as_with_password(String username, String password) {
        currentUsername = username;
        currentPassword = password;
    }

    @When("User deletes a book with ID {int}")
    public void user_deletes_a_book_with_ID(int bookId) {
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .when()
                .delete("/api/books/" + bookId);

        System.out.println("Delete Book Response: " + lastResponse.getBody().asString());
        commonSteps.setLastResponse(lastResponse);
    }

    @When("User deletes a non-existent book with ID {int}")
    public void user_deletes_a_non_existent_book_with_ID(int bookId) {
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .when()
                .delete("/api/books/" + bookId);

        System.out.println("Delete Non-Existent Book Response: " + lastResponse.getBody().asString());
        commonSteps.setLastResponse(lastResponse);
    }

    @When("Admin deletes a book with ID {int} without proper authentication")
    public void admin_deletes_a_book_with_ID_without_proper_authentication(int bookId) {

        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .when()
                .delete("/api/books/" + bookId);

        System.out.println("Admin Delete Book Response (User Not Permitted): " + lastResponse.getBody().asString());
        commonSteps.setLastResponse(lastResponse);
    }

    @Then("User should receive a successful response with status code {int} for operation")
    public void User_should_receive_a_successful_response_with_status_code(int statusCode) {
        Assert.assertNotNull("No response received!", lastResponse);
        Assert.assertEquals(statusCode, lastResponse.getStatusCode());
    }


}