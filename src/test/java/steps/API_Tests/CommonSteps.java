package steps.API_Tests;

import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

public class CommonSteps {
    private String baseUri;
    private Response lastResponse;
    public void setLastResponse(Response response) {
        this.lastResponse = response;
    }

    @Given("The API server is running")
    public void theApiServerIsRunning() {
        RestAssured.baseURI = "http://localhost:7081";
    }

    @Then("Admin should receive a failed response with status code {int} and error message {string}")
    public void Admin_should_receive_a_failed_response_with_status_code_and_error_message(int statusCode, String errorMessage) {
        System.out.println("Admin should receive a failed response with status code " + statusCode + " and error message " + errorMessage);
        String responseBody = lastResponse.asString();
        System.out.println("Admin should receive a failed responseBody with  " + lastResponse);
        try {
            int actualStatusCode = lastResponse.getStatusCode();
            String actualErrorMessage = lastResponse.jsonPath().getString("errorMessage");

            // Check if status code matches
            assert actualStatusCode == statusCode : "Expected status code " + statusCode + " but got " + actualStatusCode;

            // Check if error message matches
            assert actualErrorMessage != null && actualErrorMessage.contains(errorMessage) :
                    "Expected error message to contain '" + errorMessage + "' but got '" + actualErrorMessage + "'";

        } catch (Exception e) {
            System.out.println("Response is not in JSON format: " + responseBody);
        }
    }

    @Then("User should receive a failed response with status code {int} and error message {string}")
    public void User_should_receive_a_failed_response_with_status_code_and_error_message(int statusCode, String errorMessage) {
        System.out.println("User should receive a failed response with status code " + statusCode + " and error message " + errorMessage);
        String responseBody = lastResponse.asString();
        System.out.println("User should receive a failed responseBody with  " + lastResponse);
        try {
            int actualStatusCode = lastResponse.getStatusCode();
            String actualErrorMessage = lastResponse.jsonPath().getString("errorMessage");

            // Check if status code matches
            assert actualStatusCode == statusCode : "Expected status code " + statusCode + " but got " + actualStatusCode;

            // Check if error message matches
            assert actualErrorMessage != null && actualErrorMessage.contains(errorMessage) :
                    "Expected error message to contain '" + errorMessage + "' but got '" + actualErrorMessage + "'";

        } catch (Exception e) {
            System.out.println("Response is not in JSON format: " + responseBody);
        }


    }

}