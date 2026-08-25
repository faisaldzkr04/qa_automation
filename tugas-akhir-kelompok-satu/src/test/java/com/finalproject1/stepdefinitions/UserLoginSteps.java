package com.finalproject1.stepdefinitions;

import org.testng.Assert;

import com.finalproject1.pages.UserDashboardPage;
import com.finalproject1.pages.UserLoginPage;
import com.finalproject1.utils.ConfigReader;
import com.finalproject1.utils.DriverFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserLoginSteps {

    private UserLoginPage userLoginPage;
    private UserDashboardPage userDashboardPage;

    private void ensurePagesInitialized() {
        if (userLoginPage == null) {
            userLoginPage = new UserLoginPage(DriverFactory.getDriver());
            userDashboardPage = new UserDashboardPage(DriverFactory.getDriver());
        }
    }

    @Given("User is on Hadir User login page")
    public void userIsOnHadirUserLoginPage() {
        ensurePagesInitialized();
        String userUrl = ConfigReader.getProperty("user.url");
        userLoginPage.openUserLoginPage(userUrl);
    }

    @When("User enters user email {string} and password {string}")
    public void userEntersUserEmailAndPassword(String email, String password) {
        ensurePagesInitialized();
        userLoginPage.enterUserEmail(email);
        userLoginPage.enterUserPassword(password);
    }

    @And("User clicks user login submit button")
    public void userClicksUserLoginSubmitButton() {
        ensurePagesInitialized();
        userLoginPage.clickSubmitButton();
    }

    @Then("User should be redirected to the User Home Attendance page")
    public void userShouldBeRedirectedToTheUserHomeAttendancePage() {
        ensurePagesInitialized();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ignored) {}
        String url = userLoginPage.getCurrentUrl();
        Assert.assertFalse(url.contains("/absen/login"), "User is still on mobile login page!");
    }

    @And("User greeting with name {string} should be visible")
    public void userGreetingWithNameShouldBeVisible(String expectedName) {
        ensurePagesInitialized();
        Assert.assertTrue(userDashboardPage.isGreetingDisplayed() || userDashboardPage.isUserNamePresent(expectedName),
                "User greeting or name was not found on the page");
    }

    @Then("A validation message or warning {string} should appear")
    public void aValidationMessageOrWarningShouldAppear(String expectedResult) {
        ensurePagesInitialized();
        String currentUrl = userLoginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/absen/login"), "Expected to stay on login page upon failure");
    }

    @Given("User is already logged in as dummy user {string} with password {string}")
    public void userIsAlreadyLoggedInAsDummyUserWithPassword(String email, String password) {
        userIsOnHadirUserLoginPage();
        userEntersUserEmailAndPassword(email, password);
        userClicksUserLoginSubmitButton();
        userShouldBeRedirectedToTheUserHomeAttendancePage();
    }

    @When("User clicks on {string} button")
    public void userClicksOnButton(String buttonName) {
        ensurePagesInitialized();
        if (buttonName.equalsIgnoreCase("Absen Masuk")) {
            userDashboardPage.clickAbsenMasuk();
        } else {
            userDashboardPage.clickAbsenPulang();
        }
    }

    @Then("The attendance confirmation or selfie capture popup should appear")
    public void theAttendanceConfirmationOrSelfieCapturePopupShouldAppear() {
        ensurePagesInitialized();
        Assert.assertTrue(userDashboardPage.isCameraOrModalDisplayed(), "Attendance modal is not displayed!");
    }

    @And("Camera preview or capture action should be active")
    public void cameraPreviewOrCaptureActionShouldBeActive() {
        ensurePagesInitialized();
        Assert.assertTrue(userDashboardPage.isCameraOrModalDisplayed(), "Camera view is active");
    }

    @And("User closes the attendance popup without submitting")
    public void userClosesTheAttendancePopupWithoutSubmitting() {
        ensurePagesInitialized();
        DriverFactory.getDriver().navigate().back();
    }

    @Then("User should remain on the User Home Attendance page")
    public void userShouldRemainOnTheUserHomeAttendancePage() {
        ensurePagesInitialized();
        Assert.assertTrue(userLoginPage.getCurrentUrl().contains("absen"), "User navigated away from attendance page");
    }
}
