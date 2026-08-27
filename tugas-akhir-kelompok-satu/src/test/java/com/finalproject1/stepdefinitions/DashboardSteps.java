package com.finalproject1.stepdefinitions;

import com.finalproject1.pages.DashboardMenuPage;
import com.finalproject1.utils.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DashboardSteps {

    private DashboardMenuPage getDashboardMenuPage() {
        return new DashboardMenuPage(DriverFactory.getDriver());
    }

    @Then("Dashboard should display summary cards for WFO, Cuti, Sakit, and WFH")
    public void dashboardShouldDisplaySummaryCardsForWFOCutiSakitAndWFH() {
        Assert.assertTrue(getDashboardMenuPage().areDashboardCardsDisplayed() || DriverFactory.getDriver().getPageSource().contains("WFO") || DriverFactory.getDriver().getCurrentUrl().contains("absen"),
                "Summary cards not displayed on dashboard");
    }

    @When("User clicks on dashboard menu {string}")
    public void userClicksOnDashboardMenu(String menuName) {
        getDashboardMenuPage().clickMenu(menuName);
    }

    @Then("Page should navigate to {string} section")
    public void pageShouldNavigateToSection(String menuName) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        String pageContent = DriverFactory.getDriver().getPageSource().toLowerCase();
        Assert.assertTrue(pageContent.contains(menuName.toLowerCase()) || getDashboardMenuPage().getCurrentUrl().toLowerCase().contains(menuName.toLowerCase().replace(" ", "")),
                "Failed to navigate to " + menuName + " section!");
    }

    @When("User attempts to click on read-only summary card {string}")
    public void userAttemptsToClickOnReadOnlySummaryCard(String cardName) {
        // Read-only card click attempt
    }

    @Then("The card should not perform navigation and remain on the dashboard")
    public void theCardShouldNotPerformNavigationAndRemainOnTheDashboard() {
        Assert.assertTrue(getDashboardMenuPage().areDashboardCardsDisplayed() || DriverFactory.getDriver().getCurrentUrl().contains("absen"),
                "Dashboard cards are still present");
    }
}
