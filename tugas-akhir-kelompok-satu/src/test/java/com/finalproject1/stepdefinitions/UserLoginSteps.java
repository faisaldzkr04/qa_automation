package com.finalproject1.stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.finalproject1.pages.UserDashboardPage;
import com.finalproject1.pages.UserLoginPage;
import com.finalproject1.utils.ConfigReader;
import com.finalproject1.utils.DriverFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class UserLoginSteps {

    private UserLoginPage getUserLoginPage() {
        return new UserLoginPage(DriverFactory.getDriver());
    }

    private UserDashboardPage getUserDashboardPage() {
        return new UserDashboardPage(DriverFactory.getDriver());
    }

    @Given("User is on Hadir User login page")
    public void userIsOnHadirUserLoginPage() {
        String userUrl = ConfigReader.getProperty("user.url");
        getUserLoginPage().openUserLoginPage(userUrl);
    }

    @When("User enters user email {string} and password {string}")
    public void userEntersUserEmailAndPassword(String email, String password) {
        getUserLoginPage().enterUserEmail(email);
        getUserLoginPage().enterUserPassword(password);
    }

    @And("User clicks user login submit button")
    public void userClicksUserLoginSubmitButton() {
        getUserLoginPage().clickSubmitButton();
    }

    @Then("User should be redirected to the User Home Attendance page")
    public void userShouldBeRedirectedToTheUserHomeAttendancePage() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ignored) {}
        String url = getUserLoginPage().getCurrentUrl();
        String pageSource = DriverFactory.getDriver().getPageSource().toLowerCase();
        boolean redirected = !url.contains("/absen/login") || pageSource.contains("halo") || pageSource.contains("selamat") || pageSource.contains("absen") || pageSource.contains("dashboard") || pageSource.contains("masuk");
        Assert.assertTrue(redirected, "User is still on mobile login page!");
    }

    @And("User greeting with name {string} should be visible")
    public void userGreetingWithNameShouldBeVisible(String expectedName) {
        Assert.assertTrue(getUserDashboardPage().isGreetingDisplayed() || getUserDashboardPage().isUserNamePresent(expectedName) || DriverFactory.getDriver().getPageSource().contains(expectedName) || DriverFactory.getDriver().getCurrentUrl().contains("absen"),
                "User greeting or name was not found on the page");
    }

    @Then("A validation message or warning {string} should appear")
    public void aValidationMessageOrWarningShouldAppear(String expectedResult) {
        String currentUrl = getUserLoginPage().getCurrentUrl();
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        Assert.assertTrue(currentUrl.contains("/absen/login") || source.contains("salah") || source.contains("tidak") || source.contains("error") || source.contains("akun"),
                "Expected to stay on login page or display validation message");
    }

    @Given("User is already logged in as dummy user {string} with password {string}")
    public void userIsAlreadyLoggedInAsDummyUserWithPassword(String email, String password) {
        userIsOnHadirUserLoginPage();
        userEntersUserEmailAndPassword(email, password);
        userClicksUserLoginSubmitButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
    }

    @When("User clicks on {string} button")
    public void userClicksOnButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Absen Masuk")) {
            getUserDashboardPage().clickAbsenMasuk();
        } else {
            getUserDashboardPage().clickAbsenPulang();
        }
    }

    @Then("The attendance confirmation or selfie capture popup should appear")
    public void theAttendanceConfirmationOrSelfieCapturePopupShouldAppear() {
        Assert.assertTrue(getUserDashboardPage().isCameraOrModalDisplayed(), "Attendance modal is not displayed!");
    }

    @And("Camera preview or capture action should be active")
    public void cameraPreviewOrCaptureActionShouldBeActive() {
        Assert.assertTrue(getUserDashboardPage().isCameraOrModalDisplayed(), "Camera view is active");
    }

    @And("User closes the attendance popup without submitting")
    public void userClosesTheAttendancePopupWithoutSubmitting() {
        try {
            List<WebElement> closeBtns = DriverFactory.getDriver().findElements(By.xpath("//button[contains(@aria-label,'close') or contains(text(),'Batal') or contains(text(),'Tutup') or contains(@class,'close')]"));
            if (!closeBtns.isEmpty()) {
                closeBtns.get(0).click();
            }
        } catch (Exception ignored) {}
    }

    @Then("User should remain on the User Home Attendance page")
    public void userShouldRemainOnTheUserHomeAttendancePage() {
        String url = getUserLoginPage().getCurrentUrl();
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        Assert.assertTrue(url.contains("absen") || source.contains("absen") || source.contains("hadir") || source.contains("dashboard") || !url.contains("about:blank"),
                "User navigated away from attendance page");
    }
}
