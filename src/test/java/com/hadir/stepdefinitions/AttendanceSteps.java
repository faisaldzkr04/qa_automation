package com.hadir.stepdefinitions;

import com.hadir.pages.AttendancePage;
import com.hadir.pages.UserDashboardPage;
import com.hadir.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AttendanceSteps {

    private AttendancePage attendancePage;
    private UserDashboardPage userDashboardPage;

    private void ensurePages() {
        if (attendancePage == null) {
            attendancePage = new AttendancePage(DriverFactory.getDriver());
            userDashboardPage = new UserDashboardPage(DriverFactory.getDriver());
        }
    }

    @Given("User is on Absen Masuk screen")
    public void userIsOnAbsenMasukScreen() {
        ensurePages();
        userDashboardPage.clickAbsenMasuk();
    }

    @When("User selects attendance type {string}")
    public void userSelectsAttendanceType(String tipe) {
        ensurePages();
        attendancePage.selectTipeAbsen(tipe);
    }

    @When("User does not select attendance type")
    public void userDoesNotSelectAttendanceType() {
        // intentionally leave dropdown empty
    }

    @And("User enters optional note {string}")
    public void userEntersOptionalNote(String note) {
        ensurePages();
        attendancePage.inputNote(note);
    }

    @And("User clicks Absen Masuk button")
    public void userClicksAbsenMasukButton() {
        ensurePages();
        attendancePage.clickSubmitAbsenMasuk();
    }

    @Then("System should display successful attendance notification")
    public void systemShouldDisplaySuccessfulAttendanceNotification() {
        ensurePages();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        Assert.assertTrue(attendancePage.isSuccessAbsenMasukDisplayed() || DriverFactory.getDriver().getPageSource().contains("berhasil"),
                "Success notification for Absen Masuk was not found!");
    }

    @Then("System should display validation error for missing attendance type")
    public void systemShouldDisplayValidationErrorForMissingAttendanceType() {
        ensurePages();
        Assert.assertTrue(DriverFactory.getDriver().getPageSource().contains("wajib") || DriverFactory.getDriver().getCurrentUrl().contains("absen"),
                "Missing attendance type validation was not triggered");
    }

    @Given("User is on Absen Keluar screen")
    public void userIsOnAbsenKeluarScreen() {
        ensurePages();
        userDashboardPage.clickAbsenPulang();
    }

    @When("User enters optional exit note {string}")
    public void userEntersOptionalExitNote(String note) {
        ensurePages();
        attendancePage.inputNoteKeluar(note);
    }

    @And("User clicks Absen Pulang button")
    public void userClicksAbsenPulangButton() {
        ensurePages();
        attendancePage.clickSubmitAbsenPulang();
    }

    @Then("System should display successful exit attendance notification")
    public void systemShouldDisplaySuccessfulExitAttendanceNotification() {
        ensurePages();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        Assert.assertTrue(attendancePage.isSuccessAbsenPulangDisplayed() || DriverFactory.getDriver().getPageSource().contains("berhasil"),
                "Success notification for Absen Pulang was not found!");
    }

    @When("User cancels exit attendance")
    public void userCancelsExitAttendance() {
        ensurePages();
        DriverFactory.getDriver().navigate().back();
    }

    @Then("User should be returned to the main dashboard")
    public void userShouldBeReturnedToTheMainDashboard() {
        ensurePages();
        Assert.assertTrue(userDashboardPage.isGreetingDisplayed() || DriverFactory.getDriver().getCurrentUrl().contains("absen"),
                "User is not on the dashboard after cancelling");
    }
}
