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
    private final CommonValidationSteps commonValidationSteps;

    public UserCreateBookSteps(CommonValidationSteps commonValidationSteps) {
        this.commonValidationSteps = commonValidationSteps;
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

        boolean bookExists = checkIfBookExists(title);

        if (bookExists) {
            System.out.println("BUG DETECTED: The book with title '" + title + "' already exists.");
            System.out.println("Skipping creation of this book to avoid duplication.");

            lastResponse = given()
                    .auth()
                    .basic(currentUsername, currentPassword)
                    .header("Content-Type", "application/json")
                    .when()
                    .get("/api/books");

            commonValidationSteps.setLastResponse(lastResponse);
            return;
        }

        if (title == null || title.isEmpty()) {
            System.out.println("BUG DETECTED: Title is required.");
            lastResponse = given()
                    .auth()
                    .basic(currentUsername, currentPassword)
                    .header("Content-Type", "application/json")
                    .when()
                    .post("/api/books");
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
                    .post("/api/books");
            commonValidationSteps.setLastResponse(lastResponse);
            return;
        }

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

        List<String> bookTitles = response.jsonPath().getList("title");
        return bookTitles.contains(title);
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
        Assert.assertNotNull("No response received! Ensure setLastResponse() is called.", lastResponse);

        int actualStatusCode = lastResponse.getStatusCode();
        String responseBody = lastResponse.getBody().asString();
        String actualErrorMessage = null;

        try {
            actualErrorMessage = lastResponse.jsonPath().getString("errorMessage");
        } catch (Exception e) {
            System.err.println("Failed to extract 'errorMessage' from response. Body might not be in JSON format.");
        }

        System.out.println("User should receive a failed response with status code " + expectedStatusCode + " and error message: " + expectedErrorMessage);
        System.out.println("Actual response body: " + responseBody);

        Assert.assertEquals("Unexpected status code received!", expectedStatusCode, actualStatusCode);

        if (actualErrorMessage != null) {
            Assert.assertTrue("Expected error message to contain '" + expectedErrorMessage + "' but got '" + actualErrorMessage + "'",
                    actualErrorMessage.contains(expectedErrorMessage));
        } else {
            System.err.println("Error message not found in the response body!");
        }
    }


    @When("User tries to create a book without a title and without an author")
    public void userTriesToCreateABookWithoutATitleAndWithoutAnAuthor() {
        String title = "";
        String author = "";
        System.out.println("User tries to create a book without a title and without an author");
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body("{\"title\": \"" + title + "\", \"author\": \"" + author + "\"}")
                .when()
                .post("/api/books");
        commonValidationSteps.setLastResponse(lastResponse);
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
        commonValidationSteps.setLastResponse(lastResponse);
    }

    @When("User tries to create a book with title {string}, author {string}, and an invalid parameter {string} with value {string}")
    public void userTriesToCreateABookWithTitleAuthorAndAnInvalidParameterWithValue(String title, String author, String invalidParam, String invalidValue) {
        String bookData = String.format("{ \"title\": \"%s\", \"author\": \"%s\", \"%s\": \"%s\" }", title, author, invalidParam, invalidValue);
        System.out.println("Payload with invalid parameter: " + bookData);

        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .post("/api/books");

        System.out.println("Response for request with invalid parameter: " + lastResponse.getBody().asString());
        commonValidationSteps.setLastResponse(lastResponse);
    }

    @When("User tries to create a book with title {string}, and author {string} without credentials")
    public void userTriesToCreateABookWithTitleAndAuthorWithoutCredentials(String title, String author) {
        System.out.println("User tries to create a book with title '" + title + "' and author '" + author + "' without providing credentials");

        lastResponse = given()
                .header("Content-Type", "application/json")
                .body("{\"title\": \"" + title + "\", \"author\": \"" + author + "\"}")
                .when()
                .post("/api/books");

        System.out.println("Response: " + lastResponse.asString());
        System.out.println("Status Code: " + lastResponse.getStatusCode());

        commonValidationSteps.setLastResponse(lastResponse);
    }

    @When("User tries to create a book without a title and with author {string}")
    public void userTriesToCreateABookWithoutATitleAndWithAuthor(String author) {
        String title = "";
        System.out.println("User tries to create a book without a title and with author " + author);
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

    @When("User tries to create a book with title {string} and without an author")
    public void userTriesToCreateABookWithTitleAndWithoutAnAuthor(String title) {
        String author = "";
        System.out.println("User tries to create a book with title '" + title + "' and without an author");

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
}
