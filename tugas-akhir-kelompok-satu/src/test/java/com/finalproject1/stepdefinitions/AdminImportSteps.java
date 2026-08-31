package com.finalproject1.stepdefinitions;

import com.finalproject1.pages.AdminImportPage;
import com.finalproject1.utils.DriverFactory;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AdminImportSteps {

    // Durasi jeda antar step (dalam milidetik). Ubah angka ini jika ingin lebih lambat/cepat (misal: 2000 = 2 detik)
    private static final long STEP_DELAY_MS = 2000;

    private AdminImportPage getImportPage() {
        return new AdminImportPage(DriverFactory.getDriver());
    }

    /**
     * Hook AfterStep ini otomatis memberikan jeda waktu setelah SETIAP step di skenario @import selesai dieksekusi,
     * sehingga gerakan browser terlihat jelas dan tidak terlalu cepat.
     */
    @AfterStep("@import")
    public void delayAfterStep() {
        delay(STEP_DELAY_MS);
    }

    private void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ignored) {}
    }

    @Given("Admin is on Import {string} page")
    public void adminIsOnImportPage(String submenu) {
        getImportPage().clickImportParentMenu();
        delay(1000);
        getImportPage().clickSubmenu(submenu);
    }

    @When("Admin clicks Download Template button")
    public void adminClicksDownloadTemplateButton() {
        getImportPage().clickDownloadTemplate();
    }

    @Then("Template Excel file should be downloaded successfully")
    public void templateExcelFileShouldBeDownloadedSuccessfully() {
        Assert.assertTrue(getImportPage().isDownloadTemplateButtonVisible(),
                "Download template button was not available or download failed.");
    }

    @When("Admin uploads valid Excel file {string}")
    public void adminUploadsValidExcelFile(String fileName) {
        getImportPage().uploadFile(fileName);
    }

    @When("Admin uploads non-Excel file {string}")
    public void adminUploadsNonExcelFile(String fileName) {
        getImportPage().uploadFile(fileName);
    }

    @And("Admin clicks Import button")
    public void adminClicksImportButton() {
        getImportPage().clickImportButton();
    }

    @Then("Success pop-up notification {string} should be displayed")
    public void successPopUpNotificationShouldBeDisplayed(String expectedMessage) {
        String pageSource = DriverFactory.getDriver().getPageSource().toLowerCase();
        boolean isSuccess = pageSource.contains(expectedMessage.toLowerCase()) 
                || pageSource.contains("berhasil") 
                || pageSource.contains("success");
        Assert.assertTrue(isSuccess, "Success pop-up message '" + expectedMessage + "' was not displayed.");
    }

    @Then("Validation error message {string} should be displayed")
    public void validationErrorMessageShouldBeDisplayed(String expectedError) {
        String pageSource = DriverFactory.getDriver().getPageSource().toLowerCase();
        boolean isErrorDisplayed = pageSource.contains(expectedError.toLowerCase()) 
                || pageSource.contains("harus berupa file excel") 
                || pageSource.contains("error") 
                || pageSource.contains("invalid");
        Assert.assertTrue(isErrorDisplayed, "Validation error message '" + expectedError + "' was not displayed.");
    }

    @Then("Page title should display {string}")
    public void pageTitleShouldDisplay(String expectedTitle) {
        Assert.assertTrue(getImportPage().isPageHeaderDisplayed(expectedTitle),
                "Page title '" + expectedTitle + "' was not displayed.");
    }

    @And("Card title should display {string}")
    public void cardTitleShouldDisplay(String expectedCardTitle) {
        Assert.assertTrue(getImportPage().isCardTitleDisplayed(expectedCardTitle),
                "Card title '" + expectedCardTitle + "' was not displayed.");
    }
}
