package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.*;

public class BookApiSteps {

    private String authToken;
    private Response lastResponse; // To store the last response

    // Initialize the base URI for the application
    static {
        RestAssured.baseURI = "http://localhost:7081";  // Ensure this is the correct URL for the API
    }

    // Step for setting the base URI
    @Given("I have the base URI set to {string}")
    public void i_have_the_base_uri_set_to(String baseUri) {
        RestAssured.baseURI = baseUri;
    }

    // Step for authenticating the user (either admin or user)
    @Given("I authenticate as {string} with password {string}")
    public void authenticateAsUser(String username, String password) {
        // Authenticating the user and storing the token if necessary (Assuming a simple token-based auth)
        lastResponse = given()
                .header("Content-Type", "application/json")
                .body("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}")
                .when()
                .post("/api/login");

        // Save the token if authentication is successful
        if (lastResponse.statusCode() == 200) {
            authToken = lastResponse.jsonPath().getString("token");
        } else {
            // If authentication fails, print the response for debugging
            System.out.println("Authentication failed: " + lastResponse.getBody().asString());
        }

        // Assert that the authentication was successful
        Assert.assertEquals(200, lastResponse.getStatusCode());
    }

    // Step for creating a book (POST)
    @When("I create a book with title {string} and author {string}")
    public void createBook(String title, String author) {
        String bookData = "{\n" +
                "    \"title\": \"" + title + "\",\n" +
                "    \"author\": \"" + author + "\"\n" +
                "}";

        // Post the book using the correct POST endpoint
        lastResponse = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken)  // Use the token for authentication
                .body(bookData)
                .when()
                .post("/api/books");

        // Assert that the status code is 201 (book created successfully)
        Assert.assertEquals(201, lastResponse.getStatusCode());
        System.out.println("Response Body: " + lastResponse.getBody().asString());
    }

    // Step for verifying successful response with status code
    @Then("I should receive a successful response with status code {int}")
    public void i_should_receive_a_successful_response_with_status_code(Integer statusCode) {
        // Assert that the response code is the expected one
        Assert.assertEquals((int) statusCode, lastResponse.getStatusCode());
    }
}
