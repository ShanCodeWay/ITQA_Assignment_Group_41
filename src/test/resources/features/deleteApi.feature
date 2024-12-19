package com.example.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests {

static {
RestAssured.baseURI = "http://localhost:7081";
}

@Test
public void testCreateBook() {
Response response = RestAssured
.given()
.header("Content-Type", "application/json")
.body("{\"title\": \"Test Book\", \"author\": \"Author Name\"}")
.post("/api/books");

Assert.assertEquals(response.getStatusCode(), 201);
}

@Test
public void testGetBook() {
Response response = RestAssured
.given()
.get("/api/books/1");

Assert.assertEquals(response.getStatusCode(), 200);
Assert.assertNotNull(response.jsonPath().getString("title"));
}

// Add more test cases for PUT and DELETE APIs
}
