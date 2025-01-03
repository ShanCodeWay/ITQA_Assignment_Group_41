package steps.user;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import steps.CommonValidationSteps;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserCreateBookSteps {

    private Response lastResponse;
    private String currentUsername;
    private String currentPassword;
    private final steps.API_Tests.CommonSteps commonSteps;

    public UserCreateBookSteps(steps.API_Tests.CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
    }

    @Given("User has the base URI set to the {string}")
    public void setBaseUriuser(String baseUri) {
        RestAssured.baseURI = baseUri;
        System.out.println("Base URI set to: " + baseUri);
    }

    @And("User authenticate as the {string} with password {string}")
    public void authenticateUser(String username, String password) {
        currentUsername = username;
        currentPassword = password;
    }

    @When("User tries to create a book with title {string} and author {string}")
    public void UsercreateBook(String title, String author) {

        String bookData = "{ \"title\": \"" + title + "\", \"author\": \"" + author + "\" }";
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .post("/api/books");

        System.out.println("Create Book Response: " + lastResponse.getBody().asString());
        commonSteps.setLastResponse(lastResponse);
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
        commonSteps.setLastResponse(lastResponse);
    }

    @When("User tries to create a book without a title and without an author")
    public void userTriesToCreateABookWithoutATitleAndWithoutAnAuthor() {
        System.out.println("User tries to create a book without a title and without an author");
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .when()
                .post("/api/books");
        commonSteps.setLastResponse(lastResponse);
        System.out.println("Response: " + lastResponse.asString());
        System.out.println("Status Code: " + lastResponse.getStatusCode());
    }

    @When("User tries to create a book without an ID but with title {string} and author {string}")
    public void userTriesToCreateABookWithoutAnIDButWithTitleAndAuthor(String title, String author) {
        System.out.println("User tries to create a book without an ID but with title " + title + " and author " + author);
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body("{\"title\": \"" + title + "\", \"author\": \"" + author + "\"}")
                .when()
                .post("/api/books");
        commonSteps.setLastResponse(lastResponse);
        System.out.println("Response: " + lastResponse.asString());
        System.out.println("Status Code: " + lastResponse.getStatusCode());
    }

    @When("User tries to create a book with title {string}, author {string}, and an invalid parameter {string} with value {string}")
    public void userTriesToCreateABookWithTitleAuthorAndAnInvalidParameterWithValue(String title, String author, String invalidParam, String invalidValue) {
        String bookData = String.format("{ \"title\": \"%s\", \"author\": \"%s\", \"%s\": \"%s\" }", title, author, invalidParam, invalidValue);
        System.out.println("User tries to create a book with invalid parameter: " + bookData);

        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .post("/api/books");

        commonSteps.setLastResponse(lastResponse);
        System.out.println("Response: " + lastResponse.asString());
        System.out.println("Status Code: " + lastResponse.getStatusCode());
    }

    @When("User tries to create a book with title {string}, and author {string} without credentials")
    public void userTriesToCreateABookWithTitleAndAuthorWithoutCredentials(String title, String author) {
        System.out.println("User tries to create a book with title '" + title + "' and author '" + author + "' without providing credentials");

        lastResponse = given()
                .header("Content-Type", "application/json")
                .body("{\"title\": \"" + title + "\", \"author\": \"" + author + "\"}")
                .when()
                .post("/api/books");

        commonSteps.setLastResponse(lastResponse);
        System.out.println("Response: " + lastResponse.asString());
        System.out.println("Status Code: " + lastResponse.getStatusCode());
    }

    @When("User tries to create a book without a title and with author {string}")
    public void userTriesToCreateABookWithoutATitleAndWithAuthor(String author) {
        System.out.println("User tries to create a book without a title and with author " + author);
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body("{\" \"author\": \"" + author + "\"}")
                .when()
                .post("/api/books");
        commonSteps.setLastResponse(lastResponse);
        System.out.println("Response: " + lastResponse.asString());
        System.out.println("Status Code: " + lastResponse.getStatusCode());
    }

    @When("User tries to create a book with title {string} and without an author")
    public void userTriesToCreateABookWithTitleAndWithoutAnAuthor(String title) {
        System.out.println("User tries to create a book with title '" + title + "' and without an author");

        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body("{\"title\": \"" + title +  "\"}")
                .when()
                .post("/api/books");

        commonSteps.setLastResponse(lastResponse);
        System.out.println("Response: " + lastResponse.asString());
        System.out.println("Status Code: " + lastResponse.getStatusCode());
    }

    @When("User tries to create a book with title {int} and author {string}")
    public void userTriesToCreateABookWithTitleAndAuthor(int title, String author) {

        String bookData = "{ \"title\": \"" + title + "\", \"author\": \"" + author + "\" }";
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .post("/api/books");

        commonSteps.setLastResponse(lastResponse);
        System.out.println("Response: " + lastResponse.asString());
        System.out.println("Status Code: " + lastResponse.getStatusCode());
    }
}
