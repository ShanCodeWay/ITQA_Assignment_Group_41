package com.example.steps;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import io.cucumber.java.en.*;

import java.util.Map;

public class ApiSteps {
    Response response;

    @Given("the endpoint {string} is available")
    public void theEndpointIsAvailable(String endpoint) {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = endpoint;
    }

    @When("I send a GET request")
    public void iSendAGetRequest() {
        response = RestAssured.get();
    }

    @When("I send a POST request with body:")
    public void iSendAPostRequestWithBody(DataTable dataTable) {
        Map<String, String> body = dataTable.asMap(String.class, String.class);
        response = RestAssured.given().contentType("application/json").body(body).post();
    }

    @When("I send a DELETE request")
    public void iSendADeleteRequest() {
        response = RestAssured.delete();
    }

    @Then("I should receive a response with status code {int}")
    public void iShouldReceiveAResponseWithStatusCode(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }
}
