//package org.example.utils;
//
//import io.restassured.response.Response;
//import static io.restassured.RestAssured.given;
//
//public class ApiHelper {
//    public static Response getRequest(String endpoint) {
//        return given().get(endpoint);
//    }
//
//    public static Response postRequest(String endpoint, Object body) {
//        return given()
//                .header("Content-Type", "application/json")
//                .body(body)
//                .post(endpoint);
//    }
//
//    public static Response deleteRequest(String endpoint) {
//        return given().delete(endpoint);
//    }
//}
