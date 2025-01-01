package steps;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class CommonValidationSteps {

    private Response lastResponse;

    // Method to set the lastResponse and log its details
    public void setLastResponse(Response response) {
        this.lastResponse = response;

        if (response != null) {
            System.out.println("Admin should setLastResponse asString****  " + lastResponse.getBody().asString());
            System.out.println("Admin should setLastResponse getStatusCode****  " + lastResponse.getStatusCode());
        } else {
            System.err.println("setLastResponse called with a null response!");
        }
    }

    // Verify a successful response with a specific status code
    @Then("Admin should receive a successful response with status code {int}")
    public void adminShouldReceiveASuccessfulResponseWithStatusCode(int expectedStatusCode) {
        Assert.assertNotNull("No response received! Ensure setLastResponse() is called.", lastResponse);
        Assert.assertEquals("Unexpected status code received!", expectedStatusCode, lastResponse.getStatusCode());
    }

    // Verify a "Book Already Exists" response with a specific status code
    @Then("Admin should receive a Book Already Exists response with status code {int}")
    public void adminShouldReceiveABookAlreadyExistsResponseWithStatusCode(int expectedStatusCode) {
        Assert.assertNotNull("No response received! Ensure setLastResponse() is called.", lastResponse);
        Assert.assertEquals("Unexpected status code received!", expectedStatusCode, lastResponse.getStatusCode());

        // Verify the response body contains the expected error message
        String responseBody = lastResponse.getBody().asString();
        Assert.assertTrue("Response body does not contain 'Book Already Exists'.",
                responseBody.contains("Book Already Exists"));
    }

    // Verify a failed response with a specific status code and error message
    @Then("Admin should receive a failed response with status code {int} and error message {string}")
    public void adminShouldReceiveAFailedResponseWithStatusCodeAndErrorMessage(int expectedStatusCode, String expectedErrorMessage) {
        Assert.assertNotNull("No response received! Ensure setLastResponse() is called.", lastResponse);

        int actualStatusCode = lastResponse.getStatusCode();
        String responseBody = lastResponse.getBody().asString();
        String actualErrorMessage = null;

        // Attempt to extract the error message from the response body
        try {
            actualErrorMessage = lastResponse.jsonPath().getString("errorMessage");
        } catch (Exception e) {
            System.err.println("Failed to extract 'errorMessage' from response. Body might not be in JSON format.");
        }

        // Log the response details
        System.out.println("Admin should receive a failed response with status code " + expectedStatusCode + " and error message: " + expectedErrorMessage);
        System.out.println("Actual response body: " + responseBody);

        // Assert status code
        Assert.assertEquals("Unexpected status code received!", expectedStatusCode, actualStatusCode);

        // Assert error message (if it's present and valid)
        if (actualErrorMessage != null) {
            Assert.assertTrue("Expected error message to contain '" + expectedErrorMessage + "' but got '" + actualErrorMessage + "'",
                    actualErrorMessage.contains(expectedErrorMessage));
        } else {
            System.err.println("Error message not found in the response body!");
        }
    }
}
