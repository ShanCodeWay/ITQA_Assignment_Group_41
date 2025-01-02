package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookTests {

    @BeforeClass
    public void setupAPI() {
        RestAssured.baseURI = "https://example.com/api"; //  base URL
    }

    @Test(priority = 1)
    public void deleteBook_ValidId_ShouldReturn200() {
        //  valid book ID to delete
        String bookId = "123";

        Response response = given()
                .pathParam("id", bookId)
                .when()
                .delete("/books/{id}")
                .then()
                .statusCode(200) // Verify HTTP status 200
                .extract()
                .response();


        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "Book deleted successfully", "Delete confirmation message mismatch.");
    }

    @Test(priority = 2)
    public void deleteBook_InvalidId_ShouldReturn404() {
        // Using an invalid or non-existent book ID
        String invalidBookId = "999999";

        Response response = given()
                .pathParam("id", invalidBookId)
                .when()
                .delete("/books/{id}")
                .then()
                .statusCode(404) // Verify HTTP status 404
                .extract()
                .response();


        String error = response.jsonPath().getString("error");
        Assert.assertEquals(error, "Book not found", "Error message mismatch for invalid ID.");
    }

    @Test(priority = 3)
    public void deleteBook_MissingId_ShouldReturn400() {

        Response response = given()
                .when()
                .delete("/books/")
                .then()
                .statusCode(400) // Verify HTTP status 400 (Bad Request)
                .extract()
                .response();


        String error = response.jsonPath().getString("error");
        Assert.assertEquals(error, "Book ID is required", "Error message mismatch for missing ID.");
    }
}