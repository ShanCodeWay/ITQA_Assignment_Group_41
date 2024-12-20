package tests;

import io.restassured.response.Response;
//import org.example.utils.ApiHelper;        library
import org.apache.http.protocol.HttpCoreContext;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests {
    @Test
    public void testGetAllBooks() {
//        HttpCoreContext ApiHelper = null;
//        Response response = ApiHelper.getRequest("/books");
//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Book list is empty");
    }

    @Test
    public void testGetSingleBook() {
//        Response response = ApiHelper.getRequest("/books/1");
//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
    }

    @Test
    public void testDeleteBook() {
//        Response response = ApiHelper.deleteRequest("/books/1");
//        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
