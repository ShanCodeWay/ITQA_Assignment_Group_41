package steps.admin;

import io.cucumber.java.en.When;
import io.restassured.response.Response;
import steps.CommonValidationSteps;

import static io.restassured.RestAssured.given;

public class AdminDeleteBookSteps {

    private Response lastResponse;
    private final CommonValidationSteps commonValidationSteps;

    public AdminDeleteBookSteps(CommonValidationSteps commonValidationSteps) {
        this.commonValidationSteps = commonValidationSteps;
    }

    @When("Admin tries to delete a book with id {int}")
    public void Admin_tries_to_delete_a_book_with_id(int id) {
        lastResponse = given()
                .delete("/api/books/" + id);

        commonValidationSteps.setLastResponse(lastResponse);
    }
}
