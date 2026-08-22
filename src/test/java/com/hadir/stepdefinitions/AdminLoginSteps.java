package com.hadir.stepdefinitions;

import com.hadir.pages.AdminLoginPage;
import com.hadir.utils.ConfigReader;
import com.hadir.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AdminLoginSteps {

    private AdminLoginPage adminLoginPage;

    private void ensurePage() {
        if (adminLoginPage == null) {
            adminLoginPage = new AdminLoginPage(DriverFactory.getDriver());
        }
    }

    @Given("User is on Hadir Admin login page")
    public void userIsOnHadirAdminLoginPage() {
        ensurePage();
        String adminUrl = ConfigReader.getProperty("admin.url");
        adminLoginPage.openAdminLoginPage(adminUrl);
    }

    @When("User enters admin email {string} and password {string}")
    public void userEntersAdminEmailAndPassword(String email, String password) {
        ensurePage();
        adminLoginPage.enterEmail(email);
        adminLoginPage.enterPassword(password);
    }

    @And("User clicks the login button")
    public void userClicksTheLoginButton() {
        ensurePage();
        adminLoginPage.clickLoginButton();
    }

    @Then("User should be redirected to the Admin Dashboard")
    public void userShouldBeRedirectedToTheAdminDashboard() {
        ensurePage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        String currentUrl = adminLoginPage.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("/authentication/login"), "User is still on login page!");
    }

    @And("Admin dashboard header should be displayed")
    public void adminDashboardHeaderShouldBeDisplayed() {
        ensurePage();
        Assert.assertTrue(adminLoginPage.isAdminDashboardDisplayed(), "Admin dashboard is not displayed!");
    }

    @Then("An error alert or message {string} should be displayed")
    public void anErrorAlertOrMessageShouldBeDisplayed(String expectedMessage) {
        ensurePage();
        String actualMsg = adminLoginPage.getErrorMessage();
        Assert.assertTrue(adminLoginPage.getCurrentUrl().contains("login") || !actualMsg.isEmpty(),
                "Expected error or stayed on login page, but redirected elsewhere.");
    }

    @Given("User is already logged in as Admin with email {string} and password {string}")
    public void userIsAlreadyLoggedInAsAdminWithEmailAndPassword(String email, String password) {
        userIsOnHadirAdminLoginPage();
        userEntersAdminEmailAndPassword(email, password);
        userClicksTheLoginButton();
        userShouldBeRedirectedToTheAdminDashboard();
    }
}
