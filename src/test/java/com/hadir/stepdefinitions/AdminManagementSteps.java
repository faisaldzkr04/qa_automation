package com.hadir.stepdefinitions;

import com.hadir.pages.AdminManagementUserPage;
import com.hadir.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AdminManagementSteps {

    private AdminManagementUserPage managementUserPage;

    private void ensurePage() {
        if (managementUserPage == null) {
            managementUserPage = new AdminManagementUserPage(DriverFactory.getDriver());
        }
    }

    @Given("Admin is on User Management page")
    public void adminIsOnUserManagementPage() {
        ensurePage();
        managementUserPage.openUserManagement();
    }

    @When("Admin searches user with keyword {string}")
    public void adminSearchesUserWithKeyword(String keyword) {
        ensurePage();
        managementUserPage.searchUserByNameOrNik(keyword);
    }

    @Then("User table should display matching user records")
    public void userTableShouldDisplayMatchingUserRecords() {
        ensurePage();
        Assert.assertTrue(managementUserPage.isUserTableNotEmpty(), "User table is empty after search");
    }

    @Then("User table should display no records or empty state")
    public void userTableShouldDisplayNoRecordsOrEmptyState() {
        ensurePage();
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        Assert.assertTrue(source.contains("0 of 0") || source.contains("no data") || source.contains("tidak ada") || !managementUserPage.isUserTableNotEmpty(),
                "Expected empty table but found records");
    }

    @Given("Admin is on Pendaftaran User page")
    public void adminIsOnPendaftaranUserPage() {
        ensurePage();
        managementUserPage.openPendaftaranUser();
    }

    @When("Admin fills account info NIK {string}, Name {string}, Email {string}, Password {string}")
    public void adminFillsAccountInfoNIKNameEmailPassword(String nik, String nama, String email, String password) {
        ensurePage();
        managementUserPage.fillPendaftaranAccountInfo(nik, nama, email, password);
    }

    @And("Admin submits employee registration")
    public void adminSubmitsEmployeeRegistration() {
        ensurePage();
        managementUserPage.submitPendaftaran();
    }

    @Then("Registration should be successfully processed")
    public void registrationShouldBeSuccessfullyProcessed() {
        ensurePage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        Assert.assertTrue(DriverFactory.getDriver().getPageSource().contains("berhasil") || !managementUserPage.getCurrentUrl().contains("pendaftaran"),
                "Employee registration could not be completed.");
    }

    @Then("Form validation error should prevent submission")
    public void formValidationErrorShouldPreventSubmission() {
        ensurePage();
        Assert.assertTrue(managementUserPage.getCurrentUrl().contains("pendaftaran") || DriverFactory.getDriver().getPageSource().contains("wajib"),
                "Form submitted despite missing mandatory field!");
    }
}
