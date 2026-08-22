package com.hadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminManagementUserPage extends BasePage {

    // Menus
    private final By managementMenu = By.xpath("//*[contains(text(),'Management')]");
    private final By userSubmenu = By.xpath("//a[contains(@href,'user') or contains(.,'User')] | //*[text()='User']");
    private final By pendaftaranUserSubmenu = By.xpath("//*[contains(text(),'Pendaftaran User')]");
    private final By pendaftaranSendiriSubmenu = By.xpath("//*[contains(text(),'Pendaftaran Sendiri')]");

    // Search & Filter
    private final By searchDropdown = By.xpath("//select | //div[contains(@class,'MuiSelect')]");
    private final By searchInput = By.xpath("//input[@placeholder='Search' or contains(@placeholder,'cari') or @name='search']");
    private final By searchBtn = By.xpath("//button[contains(text(),'Search') or contains(.,'Cari')]");
    private final By resetBtn = By.xpath("//button[contains(text(),'Reset')]");
    private final By exportBtn = By.xpath("//button[contains(text(),'Export')]");

    // Pendaftaran User Form Fields
    private final By nikInput = By.xpath("//input[@name='nik' or @placeholder='NIK']");
    private final By namaKaryawanInput = By.xpath("//input[@name='name' or @name='nama' or @placeholder='Nama Karyawan']");
    private final By emailInput = By.xpath("//input[@name='email' or @type='email']");
    private final By passwordInput = By.xpath("//input[@name='password' or @type='password']");
    private final By submitBtn = By.xpath("//button[contains(text(),'Submit') or @type='submit']");

    // Actions in User Table
    private final By actionMenuDot = By.xpath("(//button[contains(@class,'MuiIconButton') or contains(@aria-label,'more')])[1] | (//div[contains(@class,'action')]//button)[1]");
    private final By editUserOption = By.xpath("//*[contains(text(),'Edit')]");
    private final By deleteUserOption = By.xpath("//*[contains(text(),'Delete') or contains(text(),'Hapus')]");
    private final By confirmDeleteBtn = By.xpath("//button[contains(text(),'Ya') or contains(text(),'Hapus')]");
    private final By lihatSisaCutiOption = By.xpath("//*[contains(text(),'Lihat Sisa Cuti')]");
    private final By activeToggle = By.xpath("(//input[@type='checkbox' or contains(@class,'switch')])[1]");

    public AdminManagementUserPage(WebDriver driver) {
        super(driver);
    }

    public void openUserManagement() {
        click(managementMenu);
        click(userSubmenu);
    }

    public void openPendaftaranUser() {
        click(managementMenu);
        click(pendaftaranUserSubmenu);
    }

    public void openPendaftaranSendiri() {
        click(managementMenu);
        click(pendaftaranSendiriSubmenu);
    }

    public void selectSearchCategory(String category) {
        click(searchDropdown);
        By option = By.xpath("//li[contains(text(),'" + category + "')] | //option[contains(text(),'" + category + "')]");
        click(option);
    }

    public void fillPendaftaranAccountInfo(String nik, String nama, String email, String password) {
        type(nikInput, nik);
        type(namaKaryawanInput, nama);
        type(emailInput, email);
        type(passwordInput, password);
    }

    public void submitPendaftaran() {
        click(submitBtn);
    }

    public void searchUserByNameOrNik(String query) {
        type(searchInput, query);
        click(searchBtn);
    }

    public void clickResetSearch() {
        click(resetBtn);
    }

    public void clickExportData() {
        click(exportBtn);
    }

    public void toggleActiveStatus() {
        click(activeToggle);
    }

    public void clickActionOnFirstUser(String actionName) {
        click(actionMenuDot);
        if (actionName.equalsIgnoreCase("edit")) {
            click(editUserOption);
        } else if (actionName.equalsIgnoreCase("delete")) {
            click(deleteUserOption);
        } else if (actionName.equalsIgnoreCase("sisa cuti")) {
            click(lihatSisaCutiOption);
        }
    }

    public void confirmDelete() {
        click(confirmDeleteBtn);
    }

    public boolean isUserTableNotEmpty() {
        By tableRow = By.xpath("//table//tbody//tr | //div[contains(@class,'table-row')]");
        return isElementDisplayed(tableRow);
    }
}
