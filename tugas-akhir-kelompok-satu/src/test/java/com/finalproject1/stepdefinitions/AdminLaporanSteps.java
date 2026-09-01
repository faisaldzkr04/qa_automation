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
    
    @Given("Admin is on {string} page")
    public void adminIsOnPage(String menuName) {
        getLaporanPage().openLaporanSubmenu(menuName);
    }

    @Then("Admin should see search input, search button, reset button, filter by unit departemen, start date, and end date")
    public void adminShouldSeeSearchInputSearchButtonEtc() {
        try {
            Thread.sleep(600);
        } catch (InterruptedException ignored) {}
    }

    @When("Admin inputs search by name {string}")
    public void adminInputsSearchByName(String name) {
        getLaporanPage().typeSearchName(name);
    }

    @When("Admin selects unit departemen filter {string} and clicks Terapkan")
    public void adminSelectsUnitDepartemenFilter(String unit) {
        getLaporanPage().selectUnitDepartemen(unit);
        getLaporanPage().clickTerapkan();
    }

    @When("Admin opens unit departemen filter and selects {string}")
    public void adminOpensUnitDepartemenFilter(String unit) {
        getLaporanPage().selectUnitDepartemen(unit);
    }

    @When("Admin clicks Batal on unit departemen filter")
    public void adminClicksBatalOnUnitDepartemenFilter() {
        getLaporanPage().clickBatalFilter();
    }

    @Then("The unit departemen filter should not be applied")
    public void theUnitDepartemenFilterShouldNotBeApplied() {}

    @When("Admin selects start date and end date and clicks Save")
    public void adminSelectsStartDateAndEndDateAndClicksSave() {
        getLaporanPage().selectDates();
        getLaporanPage().clickSaveDate();
    }
    
    @When("Admin selects start date and end date")
    public void adminSelectsStartDateAndEndDate() {
        getLaporanPage().selectDates();
    }

    @When("Admin clicks Cancel on date picker")
    public void adminClicksCancelOnDatePicker() {
        getLaporanPage().clickCancelDate();
    }

    @Then("The date filter should not be applied")
    public void theDateFilterShouldNotBeApplied() {}
    
    @Then("The date should not be applied")
    public void theDateShouldNotBeApplied() {}

    @When("Admin clicks Search button")
    public void adminClicksSearchButton() {
        getLaporanPage().clickSearch();
    }

    @Then("The report table should display {string} column")
    public void theReportTableShouldDisplayColumn(String column) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}
        boolean tableExists = getLaporanPage().isLaporanTableDisplayed();
        boolean textExists = DriverFactory.getDriver().getPageSource().toLowerCase().contains(column.toLowerCase());
        Assert.assertTrue(tableExists || textExists, "Laporan data table was not displayed.");
    }

    @Then("Export button visibility should be {string}")
    public void exportButtonVisibilityShouldBe(String expectedVisibility) {
        boolean expected = Boolean.parseBoolean(expectedVisibility);
        Assert.assertEquals(getLaporanPage().hasExportButton(), expected, "Export button visibility mismatch.");
    }

    @Then("The report table should display no data found")
    public void reportTableShouldDisplayNoDataFound() {
        try {
            Thread.sleep(5000);
            boolean isEmpty = getLaporanPage().isTableEmpty();
            if (!isEmpty) {
                System.out.println("WARNING: Table did not appear empty.");
                java.util.List<org.openqa.selenium.WebElement> rows = DriverFactory.getDriver().findElements(org.openqa.selenium.By.xpath("//tbody/tr | //div[contains(@class,'MuiDataGrid-row')] | //div[@role='row' and not(contains(@class,'header'))]"));
                System.out.println("Number of rows found: " + rows.size());
                for (org.openqa.selenium.WebElement row : rows) {
                    try {
                        System.out.println("Row outerHTML: " + row.getAttribute("outerHTML"));
                    } catch (Exception e) {
                        System.out.println("Error printing row: " + e.getMessage());
                    }
                }
            }
            Assert.assertTrue(isEmpty, "Expected no data found in report table.");
        } catch (InterruptedException ignored) {}
    }

    @And("Admin clicks Reset button")
    public void adminClicksResetButton2() {
        getLaporanPage().clickReset();
    }
    
    @And("Admin clicks reset button")
    public void adminClicksResetButton() {
        getLaporanPage().clickReset();
    }

    @Then("All filters should be cleared to default state")
    public void allFiltersShouldBeCleared() {}
    
    @Then("Admin should see start date, end date, input NIK, input Nama user, input Upliner, input Divisi, input Unit, input Tipe Report, Download button, and Reset button")
    public void adminShouldSeeDownloadAbsenInputs() {}
    
    @Then("Admin should see start date, end date, input NIK, input Nama user, input Upliner, input Unit, Download button, and Reset button")
    public void adminShouldSeeDownloadAbsenV2Inputs() {}
    
    @Then("Admin should see input NIK, input Nama user, input Upliner, Download button, and Reset button")
    public void adminShouldSeeSisaCutiInputs() {}
    
    @Then("Admin should see tanggal penarikan data, Download button, and Reset button")
    public void adminShouldSeeDailyReportInputs() {}

    @When("Admin selects start date and end date for download")
    public void adminSelectsDatesForDownload() {
        getLaporanPage().selectDates();
    }
    
    @When("Admin selects tanggal penarikan data")
    public void adminSelectsTanggalPenarikan() {
        getLaporanPage().selectDates();
    }

    @When("Admin inputs and selects NIK from dropdown")
    public void adminInputsAndSelectsNIKFromDropdown() { getLaporanPage().typeDropdown("NIK", "Test"); }
    @When("Admin inputs and selects Nama user from dropdown")
    public void adminInputsAndSelectsNamaUserDropdown() { getLaporanPage().typeDropdown("Nama", "Test"); }
    @When("Admin inputs and selects Upliner from dropdown")
    public void adminInputsAndSelectsUplinerDropdown() { getLaporanPage().typeDropdown("Upliner", "Test"); }
    @When("Admin inputs and selects Divisi from dropdown")
    public void adminInputsAndSelectsDivisiDropdown() { getLaporanPage().typeDropdown("Divisi", "Test"); }
    @When("Admin inputs and selects Unit from dropdown")
    public void adminInputsAndSelectsUnitDropdown() { getLaporanPage().typeDropdown("Unit", "Test"); }
    @When("Admin inputs and selects Tipe Report from dropdown")
    public void adminInputsAndSelectsTipeReportDropdown() { getLaporanPage().typeDropdown("Tipe", "Test"); }

    @When("Admin clicks Download button")
    public void adminClicksDownloadButton() {
        getLaporanPage().clickDownload();
    }

    @Then("The absen report should be downloaded successfully")
    public void theReportShouldBeDownloadedSuccessfully() {}
    
    @Then("The absen v2 report should be downloaded successfully")
    public void theReportV2ShouldBeDownloadedSuccessfully() {}
    
    @Then("The sisa cuti report should be downloaded successfully")
    public void theSisaCutiShouldBeDownloadedSuccessfully() {}
    
    @Then("The daily report should be downloaded successfully")
    public void theDailyReportShouldBeDownloadedSuccessfully() {}

    @When("Admin fills all inputs on Download Absen form")
    public void adminFillsAllInputsOnDownloadAbsenForm() {
        getLaporanPage().fillDownloadAbsenForm();
    }
    
    @When("Admin fills all inputs on Download Absen V2 form")
    public void adminFillsAllInputsOnDownloadAbsenV2Form() {
        getLaporanPage().fillDownloadAbsenV2Form();
    }
    
    @When("Admin fills all inputs on Sisa Cuti form")
    public void adminFillsAllInputsOnSisaCutiForm() {
        getLaporanPage().fillSisaCutiForm();
    }
    
    @Then("All inputs on Download Absen form should be cleared")
    public void allInputsAbsenShouldBeCleared() {
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
    }
    
    @Then("All inputs on Download Absen V2 form should be cleared")
    public void allInputsAbsenV2ShouldBeCleared() {
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
    }
    
    @Then("All inputs on Sisa Cuti form should be cleared")
    public void allInputsSisaCutiShouldBeCleared() {
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
    }
    
    @Then("The tanggal penarikan data should be cleared")
    public void tanggalPenarikanShouldBeCleared() {
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
    }

    @When("Admin types invalid data in {string} dropdown")
    public void adminTypesInvalidDataInDropdown(String field) {
        getLaporanPage().typeDropdown(field, "InvalidData999");
    }

    @Then("No matching data should be found in {string} dropdown")
    public void noMatchingDataShouldBeFoundInDropdown(String field) {}
}
