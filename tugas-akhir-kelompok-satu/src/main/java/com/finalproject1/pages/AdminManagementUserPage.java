package com.finalproject1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminManagementUserPage extends BasePage {

    // Menus
    private final By managementMenu = By.xpath("//*[contains(text(),'Management')]");
    private final By userSubmenu = By.xpath("//a[contains(@href,'user') or contains(.,'User')] | //*[text()='User']");
    private final By pendaftaranUserSubmenu = By.xpath("//*[contains(text(),'Pendaftaran User')] | //a[contains(@href,'pendaftaran')]");
    private final By pendaftaranSendiriSubmenu = By.xpath("//*[contains(text(),'Pendaftaran Sendiri')]");

    // Search & Filter
    private final By searchDropdown = By.xpath("//select | //div[contains(@class,'MuiSelect')]");
    private final By searchInput = By.xpath("//input[@placeholder='Search' or contains(@placeholder,'cari') or @name='search' or @type='search' or @type='text']");
    private final By searchBtn = By.xpath("//button[contains(.,'Search') or contains(.,'Cari')]");
    private final By resetBtn = By.xpath("//button[contains(.,'Reset')]");
    private final By exportBtn = By.xpath("//button[contains(.,'Export')]");

    // Pendaftaran User Form Fields
    private final By nikInput = By.xpath("//input[@name='nik' or contains(@placeholder,'NIK')]");
    private final By namaKaryawanInput = By.xpath("//input[@name='name' or @name='nama' or contains(@placeholder,'Nama')]");
    private final By emailInput = By.xpath("//input[@name='email' or @type='email']");
    private final By passwordInput = By.xpath("//input[@name='password' or @type='password']");
    private final By submitBtn = By.xpath("//button[contains(.,'Submit') or contains(.,'Simpan') or contains(.,'Daftar') or @type='submit']");

    public AdminManagementUserPage(WebDriver driver) {
        super(driver);
    }

    public void openUserManagement() {
        try {
            click(managementMenu);
            click(userSubmenu);
        } catch (Exception e) {
            getDriver().get("https://magang.dikahadir.com/management/user");
        }
    }

    public void openPendaftaranUser() {
        try {
            click(managementMenu);
            click(pendaftaranUserSubmenu);
        } catch (Exception e) {
            getDriver().get("https://magang.dikahadir.com/management/pendaftaran-user");
        }
    }

    public void openPendaftaranSendiri() {
        try {
            click(managementMenu);
            click(pendaftaranSendiriSubmenu);
        } catch (Exception e) {
            getDriver().get("https://magang.dikahadir.com/management/pendaftaran-sendiri");
        }
    }

    public void selectSearchCategory(String category) {
        try {
            click(searchDropdown);
            By option = By.xpath("//li[contains(text(),'" + category + "')] | //option[contains(text(),'" + category + "')]");
            click(option);
        } catch (Exception ignored) {}
    }

    public void fillPendaftaranAccountInfo(String nik, String nama, String email, String password) {
        try {
            type(nikInput, nik);
        } catch (Exception ignored) {}
        try {
            type(namaKaryawanInput, nama);
        } catch (Exception ignored) {}
        try {
            type(emailInput, email);
        } catch (Exception ignored) {}
        try {
            type(passwordInput, password);
        } catch (Exception ignored) {}
    }

    public void submitPendaftaran() {
        try {
            click(submitBtn);
        } catch (Exception e) {
            try {
                List<WebElement> buttons = getDriver().findElements(By.xpath("//button"));
                for (WebElement b : buttons) {
                    if (b.getText().toLowerCase().contains("submit") || b.getText().toLowerCase().contains("simpan")) {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", b);
                        return;
                    }
                }
            } catch (Exception ignored) {}
        }
    }

    public void searchUserByNameOrNik(String query) {
        try {
            type(searchInput, query);
            click(searchBtn);
        } catch (Exception e) {
            try {
                WebElement input = getDriver().findElement(searchInput);
                input.sendKeys(query);
                input.submit();
            } catch (Exception ignored) {}
        }
    }

    public void clickResetSearch() {
        try {
            click(resetBtn);
        } catch (Exception ignored) {}
    }

    public void clickExportData() {
        try {
            click(exportBtn);
        } catch (Exception ignored) {}
    }

    public boolean isUserTableNotEmpty() {
        By tableRow = By.xpath("//table//tbody//tr | //div[contains(@class,'table-row')] | //div[contains(@class,'MuiDataGrid-row')]");
        return isElementDisplayed(tableRow);
    }
}
