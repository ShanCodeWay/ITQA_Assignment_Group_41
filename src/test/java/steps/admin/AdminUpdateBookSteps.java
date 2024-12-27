package steps.admin;

import io.cucumber.java.en.When;
import io.restassured.response.Response;
import steps.CommonValidationSteps;

import static io.restassured.RestAssured.given;

public class AdminUpdateBookSteps {

    private Response lastResponse;
    private final CommonValidationSteps commonValidationSteps;

    public AdminUpdateBookSteps(CommonValidationSteps commonValidationSteps) {
        this.commonValidationSteps = commonValidationSteps;
    }

    @When("Admin tries to update a book with id {int}, title {string}, and author {string}")
    public void Admin_tries_to_update_a_book_with_id_title_and_author(int id, String title, String author) {
        String bookData = "{ \"title\": \"" + title + "\", \"author\": \"" + author + "\" }";
        lastResponse = given()
                .header("Content-Type", "application/json")
                .body(bookData)
                .put("/api/books/" + id);

        commonValidationSteps.setLastResponse(lastResponse);
    }
}
