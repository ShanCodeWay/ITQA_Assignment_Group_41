package com.example.library.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetBooksTest {
    @Test
    public void testGetBooks() {
        RestAssured.baseURI = "http://localhost:8080/api/books";
        given()
            .when()
            .get()
            .then()
            .statusCode(200);
    }
}
