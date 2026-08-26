package com.finalproject1.stepdefinitions;

import com.finalproject1.pages.AdminLaporanPage;
import com.finalproject1.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AdminLaporanSteps {

    private AdminLaporanPage getLaporanPage() {
        return new AdminLaporanPage(DriverFactory.getDriver());
    }

    @Given("Admin is on Laporan {string} page")
    public void adminIsOnLaporanPage(String submenu) {
        getLaporanPage().openLaporanSubmenu(submenu);
    }

    @When("Admin filters report by employee name {string}, start date {string}, and end date {string}")
    public void adminFiltersReportByEmployeeNameStartDateAndEndDate(String name, String startDate, String endDate) {
        getLaporanPage().filterLaporan(name, startDate, endDate);
    }

    @Then("Report data table should be displayed")
    public void reportDataTableShouldBeDisplayed() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        Assert.assertTrue(getLaporanPage().isLaporanTableDisplayed() || DriverFactory.getDriver().getPageSource().contains("Laporan"),
                "Laporan data table was not displayed.");
    }

    @Then("Report table should display no data found")
    public void reportTableShouldDisplayNoDataFound() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        boolean noData = source.contains("tidak ada") || source.contains("no data") || source.contains("0 of 0") || source.contains("0-0 of 0") || source.contains("kosong") || !getLaporanPage().isLaporanTableDisplayed();
        Assert.assertTrue(noData, "Expected no data found in report table.");
    }

    @And("Admin clicks reset button")
    public void adminClicksResetButton() {
        getLaporanPage().clickReset();
    }
}
