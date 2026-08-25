package com.finalproject1.stepdefinitions;

import com.finalproject1.pages.AdminMasterDataPage;
import com.finalproject1.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AdminMasterDataSteps {

    private AdminMasterDataPage masterDataPage;

    private void ensurePage() {
        if (masterDataPage == null) {
            masterDataPage = new AdminMasterDataPage(DriverFactory.getDriver());
        }
    }

    @Given("Admin is on Master Data {string} page")
    public void adminIsOnMasterDataPage(String masterMenu) {
        ensurePage();
        masterDataPage.openMasterMenu(masterMenu);
    }

    @When("Admin filters master data by name {string}")
    public void adminFiltersMasterDataByName(String searchQuery) {
        ensurePage();
        masterDataPage.filterByName(searchQuery);
    }

    @Then("Master data table should be displayed")
    public void masterDataTableShouldBeDisplayed() {
        ensurePage();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        Assert.assertTrue(masterDataPage.isDataTablePresent(), "Master data table was not displayed.");
    }

    @Then("Master data table should display no records or empty state")
    public void masterDataTableShouldDisplayNoRecordsOrEmptyState() {
        ensurePage();
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        Assert.assertTrue(source.contains("0 of 0") || source.contains("no data") || source.contains("tidak ada") || !masterDataPage.isDataTablePresent(),
                "Empty state was not displayed for master search");
    }

    @When("Admin clicks Tambahkan button")
    public void adminClicksTambahkanButton() {
        ensurePage();
        masterDataPage.clickTambahkan();
    }

    @And("Admin inputs master name {string} and submits")
    public void adminInputsMasterNameAndSubmits(String masterName) {
        ensurePage();
        masterDataPage.inputMasterNameAndSave(masterName);
    }

    @Then("Form validation error should prevent saving empty master data")
    public void formValidationErrorShouldPreventSavingEmptyMasterData() {
        ensurePage();
        Assert.assertTrue(DriverFactory.getDriver().getPageSource().contains("wajib") || DriverFactory.getDriver().getPageSource().contains("dialog"),
                "Form was submitted with empty master data name");
    }
}
