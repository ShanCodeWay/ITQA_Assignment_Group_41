package steps.user;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import steps.CommonValidationSteps;

import static io.restassured.RestAssured.given;

public class UserDeleteBookSteps {

    private Response lastResponse;
    private final CommonValidationSteps commonValidationSteps;

    public UserDeleteBookSteps(CommonValidationSteps commonValidationSteps) {
        this.commonValidationSteps = commonValidationSteps;
    }

    @When("User tries to delete a book with id {int}")
    public void deleteBook(int id) {
        lastResponse = given()
                .delete("/api/books/" + id);

        System.out.println("Delete Book Response: " + lastResponse.getBody().asString());
        commonValidationSteps.setLastResponse(lastResponse);
    }

    @Then("User should receive a successful delete response with status code {int}")
    public void verifyDeleteResponse(int expectedStatusCode) {
        int actualStatusCode = lastResponse.getStatusCode();
        assert actualStatusCode == expectedStatusCode :
                String.format("Expected status code %d but got %d", expectedStatusCode, actualStatusCode);
        System.out.println("Book deleted successfully with status code: " + actualStatusCode);
    }
}
