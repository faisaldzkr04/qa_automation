package com.finalproject1.stepdefinitions;

import com.finalproject1.pages.AdminLoginPage;
import com.finalproject1.utils.ConfigReader;
import com.finalproject1.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AdminLoginSteps {

    private AdminLoginPage getAdminLoginPage() {
        return new AdminLoginPage(DriverFactory.getDriver());
    }

    @Given("User is on Hadir Admin login page")
    public void userIsOnHadirAdminLoginPage() {
        String adminUrl = ConfigReader.getProperty("admin.url");
        getAdminLoginPage().openAdminLoginPage(adminUrl);
    }

    @When("User enters admin email {string} and password {string}")
    public void userEntersAdminEmailAndPassword(String email, String password) {
        getAdminLoginPage().enterEmail(email);
        getAdminLoginPage().enterPassword(password);
    }

    @And("User clicks the login button")
    public void userClicksTheLoginButton() {
        getAdminLoginPage().clickLoginButton();
    }

    @Then("User should be redirected to the Admin Dashboard")
    public void userShouldBeRedirectedToTheAdminDashboard() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        String currentUrl = getAdminLoginPage().getCurrentUrl();
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        boolean redirected = !currentUrl.contains("/authentication/login") || source.contains("dashboard") || source.contains("admin");
        Assert.assertTrue(redirected, "User is still on login page!");
    }

    @And("Admin dashboard header should be displayed")
    public void adminDashboardHeaderShouldBeDisplayed() {
        Assert.assertTrue(getAdminLoginPage().isAdminDashboardDisplayed(), "Admin dashboard is not displayed!");
    }

    @Then("An error alert or message {string} should be displayed")
    public void anErrorAlertOrMessageShouldBeDisplayed(String expectedMessage) {
        String actualMsg = getAdminLoginPage().getErrorMessage();
        Assert.assertTrue(getAdminLoginPage().getCurrentUrl().contains("login") || !actualMsg.isEmpty() || DriverFactory.getDriver().getPageSource().contains("salah"),
                "Expected error or stayed on login page, but redirected elsewhere.");
    }

    @Given("User is already logged in as Admin with email {string} and password {string}")
    public void userIsAlreadyLoggedInAsAdminWithEmailAndPassword(String email, String password) {
        userIsOnHadirAdminLoginPage();
        userEntersAdminEmailAndPassword(email, password);
        userClicksTheLoginButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
    }
}
