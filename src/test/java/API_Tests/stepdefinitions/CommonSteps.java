package API_Tests.stepdefinitions;

import io.restassured.RestAssured;
import io.cucumber.java.en.Given;

public class CommonSteps {
    @Given("the API server is running")
    public void theApiServerIsRunning() {
        RestAssured.baseURI = "http://localhost:7081";
    }

}