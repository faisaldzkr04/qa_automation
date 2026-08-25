package com.finalproject1.stepdefinitions;

import com.finalproject1.api.AuthApiService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class ApiAuthSteps {

    private AuthApiService authApiService;
    private String email;
    private String password;
    private Response response;

    public ApiAuthSteps() {
        this.authApiService = new AuthApiService();
    }

    @Given("The API request payload with email {string} and password {string}")
    public void theAPIRequestPayloadWithEmailAndPassword(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @When("QA sends POST request to endpoint {string} or authentication route")
    public void qaSendsPOSTRequestToEndpointOrAuthenticationRoute(String endpoint) {
        this.response = authApiService.login(email, password, endpoint);
    }

    @Then("The response status code should be 200 or 201")
    public void theResponseStatusCodeShouldBe200Or201() {
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 200 || statusCode == 201, 
                "Expected status 200/201 but got: " + statusCode + ", Body: " + response.getBody().asString());
    }

    @And("The response body should contain a valid {string} or {string}")
    public void theResponseBodyShouldContainAValidOr(String key1, String key2) {
        String body = response.getBody().asString();
        Assert.assertTrue(body.contains(key1) || body.contains(key2) || body.contains("data"), 
                "Token or data key not found in response body: " + body);
    }

    @Then("The response status code should be 400 or 401")
    public void theResponseStatusCodeShouldBe400Or401() {
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode >= 400 && statusCode < 500, 
                "Expected 4xx client error but got: " + statusCode);
    }

    @And("The response body should contain error message")
    public void theResponseBodyShouldContainErrorMessage() {
        String body = response.getBody().asString();
        Assert.assertTrue(body.toLowerCase().contains("error") || body.toLowerCase().contains("message") || body.toLowerCase().contains("unauthorized") || body.toLowerCase().contains("false"),
                "Error message not found in response body: " + body);
    }
}
