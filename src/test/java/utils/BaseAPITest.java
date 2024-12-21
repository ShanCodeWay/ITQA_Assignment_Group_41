package utils;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseAPITest {
    @BeforeClass
    public void setupAPI() {
        RestAssured.baseURI = "https://example.com/api";
    }
}
