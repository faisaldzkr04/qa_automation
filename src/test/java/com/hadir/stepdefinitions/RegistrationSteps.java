package com.hadir.stepdefinitions;

import com.hadir.pages.RegisterPage;
import com.hadir.utils.ConfigReader;
import com.hadir.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class RegistrationSteps {

    private RegisterPage registerPage;

    private void ensurePage() {
        if (registerPage == null) {
            registerPage = new RegisterPage(DriverFactory.getDriver());
        }
    }

    @Given("User is on Hadir Registration page")
    public void userIsOnHadirRegistrationPage() {
        ensurePage();
        String baseUrl = ConfigReader.getProperty("user.url");
        String registerUrl = baseUrl.replace("/absen/login", "/absen/register");
        registerPage.openRegisterPage(registerUrl);
    }

    @When("User fills registration form with NIK {string}, Name {string}, Email {string}, Password {string}")
    public void userFillsRegistrationFormWithNIKNameEmailPassword(String nik, String name, String email, String password) {
        ensurePage();
        registerPage.fillRegistrationForm(nik, name, email, password);
    }

    @And("User submits the registration form")
    public void userSubmitsTheRegistrationForm() {
        ensurePage();
        registerPage.clickDaftar();
    }

    @Then("A success notification or redirection should occur")
    public void aSuccessNotificationOrRedirectionShouldOccur() {
        ensurePage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        Assert.assertTrue(registerPage.isSuccessNotificationDisplayed() || !registerPage.getCurrentUrl().contains("register"),
                "Registration did not show success or redirect!");
    }

    @Then("A validation error for invalid email format should be displayed")
    public void aValidationErrorForInvalidEmailFormatShouldBeDisplayed() {
        ensurePage();
        // Browser HTML5 validation or application error
        Assert.assertTrue(registerPage.getCurrentUrl().contains("register"), "Form unexpectedly submitted with invalid email format!");
    }
}
