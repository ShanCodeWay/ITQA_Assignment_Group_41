package steps.user;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import steps.CommonValidationSteps;

import static io.restassured.RestAssured.given;

public class UserCreateBookSteps {

    private Response lastResponse;
    private String currentUsername;
    private String currentPassword;
    private final CommonValidationSteps commonValidationSteps;

    public UserCreateBookSteps(CommonValidationSteps commonValidationSteps) {
        this.commonValidationSteps = commonValidationSteps;
    }

    @Given("User has the base URI set to the {string}")
    public void setBaseUriuser(String baseUri) {
        RestAssured.baseURI = baseUri;
        System.out.println("Base URI set to: " + baseUri);
    }

    @Given("User authenticate as the {string} with password {string}")
    public void authenticateUser(String username, String password) {
        currentUsername = username;
        currentPassword = password;
    }

    @When("User tries to create a book with title {string} and author {string}")
    public void UsercreateBook(String title, String author) {
        String bookData = String.format("{ \"title\": \"%s\", \"author\": \"%s\" }", title, author);
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

    @Then("User should receive a successful response with status code the {int}")
    public void verifySuccessfulcreateResponse(int expectedStatusCode) {
        int actualStatusCode = lastResponse.getStatusCode();
        assert actualStatusCode == expectedStatusCode :
                String.format("Expected status code %d but got %d", expectedStatusCode, actualStatusCode);
        System.out.println("Book created successfully with status code: " + actualStatusCode);
    }

    @When("User tries to create a book with missing title and author {string}")
    public void UsercreateBookWithMissingTitle(String author) {
        String bookData = String.format("{ \"title\": \"\", \"author\": \"%s\" }", author);
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .post("/api/books");

        System.out.println("Create Book with Missing Title Response: " + lastResponse.getBody().asString());
        commonValidationSteps.setLastResponse(lastResponse);
    }

    @Then("User should receive a failed response with status code {int} and error message {string}")
    public void verifycreateFailedResponse(int expectedStatusCode, String expectedErrorMessage) {
        int actualStatusCode = lastResponse.getStatusCode();
        String actualErrorMessage = lastResponse.jsonPath().getString("error");

        assert actualStatusCode == expectedStatusCode :
                String.format("Expected status code %d but got %d", expectedStatusCode, actualStatusCode);

        assert actualErrorMessage.equals(expectedErrorMessage) :
                String.format("Expected error message '%s' but got '%s'", expectedErrorMessage, actualErrorMessage);

        System.out.println("Failed response validated with status code and error message.");
    }


}
