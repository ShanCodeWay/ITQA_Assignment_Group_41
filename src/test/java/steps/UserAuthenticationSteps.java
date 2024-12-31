package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class UserAuthenticationSteps {

    private Response lastResponse;
    private String currentUsername;
    private String currentPassword;

    @Given("User has the base URI set to {string}")
    public void User_have_the_base_uri_set_to(String baseUri) {
        RestAssured.baseURI = baseUri;
        System.out.println("Base URI is set to: " + baseUri);
    }

    @Given("User authenticate as {string} with password {string}")
    public void User_authenticate_as_user_with_password(String username, String password) {
        currentUsername = username;
        currentPassword = password;
    }

    @When("User try to create a book with title {string} and author {string}")
    public void User_try_to_create_a_book_with_title_and_author(String title, String author) {
        String bookData = "{\n" +
                "    \"id\": 5,\n" +
                "    \"title\": \"" + title + "\",\n" +
                "    \"author\": \"" + author + "\"\n" +
                "}";
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .post("/api/books");
        System.out.println("Create Book Response: " + lastResponse.getBody().asString());
    }

    @When("User try to update a book with id {int}, title {string} and author {string}")
    public void User_try_to_update_a_book_with_id_title_and_author(int id, String title, String author) {
        String bookData = "{\n" +
                "    \"id\": " + id + ",\n" +
                "    \"title\": \"" + title + "\",\n" +
                "    \"author\": \"" + author + "\"\n" +
                "}";
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .header("Content-Type", "application/json")
                .body(bookData)
                .when()
                .put("/api/books/" + id);
        System.out.println("Update Book Response: " + lastResponse.getBody().asString());
    }

    @When("User try to delete a book with id {int}")
    public void User_try_to_delete_a_book_with_id(int id) {
        lastResponse = given()
                .auth()
                .basic(currentUsername, currentPassword)
                .when()
                .delete("/api/books/" + id);
        System.out.println("Delete Book Response: " + lastResponse.getBody().asString());
    }

    @Then("User should receive a successful response with status code {int}")
    public void User_should_receive_a_successful_response_with_status_code(Integer statusCode) {
        Assert.assertEquals((int) statusCode, lastResponse.getStatusCode());
    }

    @Then("User should receive a response with status code 403")
    public void User_should_receive_a_response_with_status_code_403() {
        Assert.assertEquals(403, lastResponse.getStatusCode());
    }

    @Then("User should receive a response with status code 401")
    public void User_should_receive_a_response_with_status_code_401() {
        Assert.assertEquals(401, lastResponse.getStatusCode());
    }
}
