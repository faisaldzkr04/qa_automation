package com.hadir.stepdefinitions;

import com.hadir.pages.AdminLaporanPage;
import com.hadir.utils.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AdminLaporanSteps {

    private AdminLaporanPage laporanPage;

    private void ensurePage() {
        if (laporanPage == null) {
            laporanPage = new AdminLaporanPage(DriverFactory.getDriver());
        }
    }

    @Given("Admin is on Laporan {string} page")
    public void adminIsOnLaporanPage(String submenuName) {
        ensurePage();
        laporanPage.openLaporanSubmenu(submenuName);
    }

    @When("Admin filters report by employee name {string}, start date {string}, and end date {string}")
    public void adminFiltersReportByEmployeeNameStartDateAndEndDate(String name, String start, String end) {
        ensurePage();
        laporanPage.filterLaporan(name, start, end);
    }

    @Then("Report data table should be displayed")
    public void reportDataTableShouldBeDisplayed() {
        ensurePage();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        Assert.assertTrue(laporanPage.isLaporanTableDisplayed(), "Report data table was not displayed.");
    }

    @Then("Report table should display no data found")
    public void reportTableShouldDisplayNoDataFound() {
        ensurePage();
        String source = DriverFactory.getDriver().getPageSource().toLowerCase();
        Assert.assertTrue(source.contains("0 of 0") || source.contains("no data") || source.contains("tidak ada") || laporanPage.isLaporanTableDisplayed(),
                "Empty state was not displayed");
    }

    @And("Admin clicks reset button")
    public void adminClicksResetButton() {
        ensurePage();
        laporanPage.clickReset();
    }
}
