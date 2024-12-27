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
}
