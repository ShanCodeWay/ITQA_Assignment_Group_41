package com.example.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginStepDefinitions {

    @Given("the endpoint {string} is available")
    public void the_endpoint_is_available(String endpoint) {
        // Implement API request code here
    }

    @When("I send a GET request")
    public void i_send_a_get_request() {
        // Implement GET request code here
    }

    @Then("I should receive a response with status code {int}")
    public void i_should_receive_a_response_with_status_code(int statusCode) {
        // Implement status code validation here
    }
}
