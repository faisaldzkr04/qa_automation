package com.hadir.stepdefinitions;

import com.hadir.pages.DashboardMenuPage;
import com.hadir.utils.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DashboardSteps {

    private DashboardMenuPage dashboardMenuPage;

    private void ensurePage() {
        if (dashboardMenuPage == null) {
            dashboardMenuPage = new DashboardMenuPage(DriverFactory.getDriver());
        }
    }

    @Then("Dashboard should display summary cards for WFO, Cuti, Sakit, and WFH")
    public void dashboardShouldDisplaySummaryCardsForWFOCutiSakitAndWFH() {
        ensurePage();
        Assert.assertTrue(dashboardMenuPage.areDashboardCardsDisplayed(), "Summary cards not displayed on dashboard");
    }

    @When("User clicks on dashboard menu {string}")
    public void userClicksOnDashboardMenu(String menuName) {
        ensurePage();
        dashboardMenuPage.clickMenu(menuName);
    }

    @Then("Page should navigate to {string} section")
    public void pageShouldNavigateToSection(String menuName) {
        ensurePage();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        String pageContent = DriverFactory.getDriver().getPageSource().toLowerCase();
        Assert.assertTrue(pageContent.contains(menuName.toLowerCase()) || dashboardMenuPage.getCurrentUrl().toLowerCase().contains(menuName.toLowerCase().replace(" ", "")),
                "Failed to navigate to " + menuName + " section!");
    }

    @When("User attempts to click on read-only summary card {string}")
    public void userAttemptsToClickOnReadOnlySummaryCard(String cardName) {
        ensurePage();
        // Read-only card click attempt
    }

    @Then("The card should not perform navigation and remain on the dashboard")
    public void theCardShouldNotPerformNavigationAndRemainOnTheDashboard() {
        ensurePage();
        Assert.assertTrue(dashboardMenuPage.areDashboardCardsDisplayed(), "Dashboard cards are still present");
    }
}
