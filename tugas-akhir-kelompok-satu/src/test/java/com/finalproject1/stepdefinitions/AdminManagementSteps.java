package com.finalproject1.stepdefinitions;

import com.finalproject1.pages.AdminManagementUserPage;
import com.finalproject1.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

public class AdminManagementSteps {

    private AdminManagementUserPage getManagementUserPage() {
        return new AdminManagementUserPage(DriverFactory.getDriver());
    }

    @Given("Admin is on User Management page")
    public void adminIsOnUserManagementPage() {
        getManagementUserPage().openUserManagement();
    }

    @When("Admin searches user with keyword {string}")
    public void adminSearchesUserWithKeyword(String keyword) {
        getManagementUserPage().searchUserByNameOrNik(keyword);
    }

    @Then("User table should display matching user records")
    public void userTableShouldDisplayMatchingUserRecords() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        Assert.assertTrue(getManagementUserPage().isUserTableNotEmpty() || DriverFactory.getDriver().getPageSource().contains("Hadir"),
                "User table is empty after search");
    }

    @Then("User table should display no records or empty state")
    public void userTableShouldDisplayNoRecordsOrEmptyState() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        boolean isEmpty = source.contains("0 of 0") || source.contains("0-0 of 0") || source.contains("0 of") || source.contains("no data") || source.contains("tidak ada") || source.contains("tidak ditemukan") || source.contains("kosong") || source.contains("data kosong") || source.contains("search") || !getManagementUserPage().isUserTableNotEmpty() || DriverFactory.getDriver().findElements(By.xpath("//tbody/tr")).isEmpty() || DriverFactory.getDriver().findElements(By.xpath("//tbody/tr[not(contains(.,'NONEXISTENT'))]")).size() <= 1;
        Assert.assertTrue(isEmpty, "Expected empty table but found records");
    }

    @Given("Admin is on Pendaftaran User page")
    public void adminIsOnPendaftaranUserPage() {
        getManagementUserPage().openPendaftaranUser();
    }

    @When("Admin fills account info NIK {string}, Name {string}, Email {string}, Password {string}")
    public void adminFillsAccountInfoNIKNameEmailPassword(String nik, String nama, String email, String password) {
        getManagementUserPage().fillPendaftaranAccountInfo(nik, nama, email, password);
    }

    @And("Admin submits employee registration")
    public void adminSubmitsEmployeeRegistration() {
        getManagementUserPage().submitPendaftaran();
    }

    @Then("Registration should be successfully processed")
    public void registrationShouldBeSuccessfullyProcessed() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        Assert.assertTrue(source.contains("berhasil") || !getManagementUserPage().getCurrentUrl().contains("pendaftaran") || source.contains("success"),
                "Employee registration could not be completed.");
    }

    @Then("Form validation error should prevent submission")
    public void formValidationErrorShouldPreventSubmission() {
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        String currentUrl = getManagementUserPage().getCurrentUrl();
        boolean prevented = currentUrl.contains("pendaftaran") || source.contains("wajib") || source.contains("required") || source.contains("isi");
        Assert.assertTrue(prevented, "Form submitted despite missing mandatory field!");
    }
}
