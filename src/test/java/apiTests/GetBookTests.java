package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetBookTests {
    @Test
    public void testGetAllBooks() {
        Response response = RestAssured.get("/books");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty(), "Book list is empty");
    }

    @Test
    public void testGetSingleBook() {
        Response response = RestAssured.get("/books/1");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().get("title"));
    }
}
