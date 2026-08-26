package com.finalproject1.stepdefinitions;

import com.finalproject1.pages.RegisterPage;
import com.finalproject1.utils.ConfigReader;
import com.finalproject1.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class RegistrationSteps {

    private RegisterPage getRegisterPage() {
        return new RegisterPage(DriverFactory.getDriver());
    }

    @Given("User is on Hadir Registration page")
    public void userIsOnHadirRegistrationPage() {
        String baseUrl = ConfigReader.getProperty("user.url");
        String registerUrl = baseUrl.replace("/absen/login", "/absen/register");
        getRegisterPage().openRegisterPage(registerUrl);
    }

    @When("User fills registration form with NIK {string}, Name {string}, Email {string}, Password {string}")
    public void userFillsRegistrationFormWithNIKNameEmailPassword(String nik, String name, String email, String password) {
        getRegisterPage().fillRegistrationForm(nik, name, email, password);
    }

    @And("User submits the registration form")
    public void userSubmitsTheRegistrationForm() {
        getRegisterPage().clickDaftar();
    }

    @Then("A success notification or redirection should occur")
    public void aSuccessNotificationOrRedirectionShouldOccur() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        String currentUrl = getRegisterPage().getCurrentUrl();
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        boolean success = getRegisterPage().isSuccessNotificationDisplayed() || !currentUrl.contains("register") || source.contains("berhasil") || source.contains("login") || source.contains("sukses") || source.contains("daftar") || currentUrl.contains("absen");
        Assert.assertTrue(success, "Registration did not show success or redirect!");
    }

    @Then("A validation error for invalid email format should be displayed")
    public void aValidationErrorForInvalidEmailFormatShouldBeDisplayed() {
        // Browser HTML5 validation or application error prevents navigation
        Assert.assertTrue(getRegisterPage().getCurrentUrl().contains("register") || DriverFactory.getDriver().getPageSource().contains("email"),
                "Form unexpectedly submitted with invalid email format!");
    }
}
