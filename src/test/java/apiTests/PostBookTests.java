package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseAPITest;

import java.util.HashMap;
import java.util.Map;

public class PostBookTests extends BaseAPITest {
    @Test
    public void testAddBook() {
        Map<String, Object> book = new HashMap<>();
        book.put("title", "New Book");
        book.put("author", "John Doe");

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(book)
                .post("/books");
        Assert.assertEquals(response.getStatusCode(), 201);
    }
}