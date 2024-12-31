package steps;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class CommonValidationSteps {

    private Response lastResponse;

    public void setLastResponse(Response response) {
        this.lastResponse = response;
    }

    @Then("Admin should receive a successful response with status code {int}")
    public void Admin_should_receive_a_successful_response_with_status_code(int statusCode) {
        Assert.assertNotNull("No response received!", lastResponse);
        Assert.assertEquals(statusCode, lastResponse.getStatusCode());
    }
    @Then("Admin should receive a Book Already Exists response with status code {int}")
    public void Admin_should_receive_a_already_response_with_status_code(int statusCode) {
        Assert.assertNotNull("No response received!", lastResponse);
        Assert.assertEquals(statusCode, lastResponse.getStatusCode());
    }
    @Then("Admin should receive failed response response with status code {int}")
    public void Admin_should_receive_a_failed_response_with_status_code(int statusCode, String errorMessage) {
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
}
