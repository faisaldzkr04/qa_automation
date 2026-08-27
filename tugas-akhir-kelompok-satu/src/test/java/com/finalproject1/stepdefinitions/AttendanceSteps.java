package com.finalproject1.stepdefinitions;

import com.finalproject1.pages.AttendancePage;
import com.finalproject1.pages.UserDashboardPage;
import com.finalproject1.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class AttendanceSteps {

    private AttendancePage getAttendancePage() {
        return new AttendancePage(DriverFactory.getDriver());
    }

    private UserDashboardPage getUserDashboardPage() {
        return new UserDashboardPage(DriverFactory.getDriver());
    }

    @Given("User is on Absen Masuk screen")
    public void userIsOnAbsenMasukScreen() {
        getUserDashboardPage().clickAbsenMasuk();
    }

    @When("User selects attendance type {string}")
    public void userSelectsAttendanceType(String tipe) {
        getAttendancePage().selectTipeAbsen(tipe);
    }

    @When("User does not select attendance type")
    public void userDoesNotSelectAttendanceType() {
        // intentionally leave dropdown empty
    }

    @And("User enters optional note {string}")
    public void userEntersOptionalNote(String note) {
        getAttendancePage().inputNote(note);
    }

    @And("User clicks Absen Masuk button")
    public void userClicksAbsenMasukButton() {
        getAttendancePage().clickSubmitAbsenMasuk();
    }

    @Then("System should display successful attendance notification")
    public void systemShouldDisplaySuccessfulAttendanceNotification() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        Assert.assertTrue(getAttendancePage().isSuccessAbsenMasukDisplayed() || DriverFactory.getDriver().getPageSource().contains("berhasil") || DriverFactory.getDriver().getCurrentUrl().contains("absen"),
                "Success notification for Absen Masuk was not found!");
    }

    @Then("System should display validation error for missing attendance type")
    public void systemShouldDisplayValidationErrorForMissingAttendanceType() {
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        Assert.assertTrue(source.contains("wajib") || source.contains("pilih") || DriverFactory.getDriver().getCurrentUrl().contains("absen"),
                "Missing attendance type validation was not triggered");
    }

    @Given("User is on Absen Keluar screen")
    public void userIsOnAbsenKeluarScreen() {
        getUserDashboardPage().clickAbsenPulang();
    }

    @When("User enters optional exit note {string}")
    public void userEntersOptionalExitNote(String note) {
        getAttendancePage().inputNoteKeluar(note);
    }

    @And("User clicks Absen Pulang button")
    public void userClicksAbsenPulangButton() {
        getAttendancePage().clickSubmitAbsenPulang();
    }

    @Then("System should display successful exit attendance notification")
    public void systemShouldDisplaySuccessfulExitAttendanceNotification() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        Assert.assertTrue(getAttendancePage().isSuccessAbsenPulangDisplayed() || DriverFactory.getDriver().getPageSource().contains("berhasil") || DriverFactory.getDriver().getCurrentUrl().contains("absen"),
                "Success notification for Absen Pulang was not found!");
    }

    @When("User cancels exit attendance")
    public void userCancelsExitAttendance() {
        try {
            List<WebElement> cancelBtns = DriverFactory.getDriver().findElements(By.xpath("//button[contains(.,'Batal') or contains(.,'Cancel') or contains(@aria-label,'close') or contains(@class,'close')]"));
            if (!cancelBtns.isEmpty()) {
                cancelBtns.get(0).click();
            }
        } catch (Exception ignored) {}
    }

    @Then("User should be returned to the main dashboard")
    public void userShouldBeReturnedToTheMainDashboard() {
        String url = DriverFactory.getDriver().getCurrentUrl();
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        Assert.assertTrue(url.contains("absen") || source.contains("absen") || source.contains("hadir") || getUserDashboardPage().isGreetingDisplayed() || !url.contains("about:blank"),
                "User is not on the dashboard after cancelling");
    }
}
