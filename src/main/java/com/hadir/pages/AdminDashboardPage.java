package com.hadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminDashboardPage extends BasePage {

    // Profile & Logout
    private final By userProfileMenu = By.xpath("//div[contains(@class,'MuiAvatar') or contains(text(),'Hi, Admin') or contains(@class,'profile')]");
    private final By ubahPasswordBtn = By.xpath("//button[contains(text(),'Ubah Password') or contains(.,'Change Password')] | //li[contains(.,'Ubah Password')]");
    private final By logoutBtn = By.xpath("//button[contains(text(),'Logout') or contains(text(),'Keluar')] | //li[contains(.,'Logout')]");

    // Dashboard Summary Cards
    private final By totalSemuaKaryawan = By.xpath("//*[contains(text(),'Total semua karyawan')]");
    private final By totalAbsenHariIni = By.xpath("//*[contains(text(),'Total absen hari ini')]");
    private final By totalWfhHariIni = By.xpath("//*[contains(text(),'sedang WFH')]");
    private final By totalCutiHariIni = By.xpath("//*[contains(text(),'sedang cuti')]");

    // Dashboard Menu - Pending
    private final By pendingMenu = By.xpath("//a[contains(@href,'pending') or contains(.,'Pending')] | //*[contains(text(),'Pending')]");
    private final By startDateInput = By.xpath("//input[@placeholder='Start Date' or @name='startDate'] | //input[@type='date'][1]");
    private final By endDateInput = By.xpath("//input[@placeholder='End Date' or @name='endDate'] | //input[@type='date'][2]");
    private final By searchBtn = By.xpath("//button[contains(text(),'Search') or contains(.,'Cari')]");
    private final By resetBtn = By.xpath("//button[contains(text(),'Reset')]");

    public AdminDashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean areSummaryCardsVisible() {
        return isElementDisplayed(totalSemuaKaryawan) && isElementDisplayed(totalAbsenHariIni);
    }

    public boolean isWfhSummaryCardVisible() {
        return isElementDisplayed(totalWfhHariIni);
    }

    public boolean isCutiSummaryCardVisible() {
        return isElementDisplayed(totalCutiHariIni);
    }

    public void openPendingMenu() {
        click(pendingMenu);
    }

    public void filterPendingByDate(String startDate, String endDate) {
        type(startDateInput, startDate);
        type(endDateInput, endDate);
        click(searchBtn);
    }

    public void clickReset() {
        click(resetBtn);
    }

    public void clickProfile() {
        click(userProfileMenu);
    }

    public void clickUbahPassword() {
        clickProfile();
        click(ubahPasswordBtn);
    }

    public void clickLogout() {
        clickProfile();
        click(logoutBtn);
    }
}
