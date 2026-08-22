package com.hadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLaporanPage extends BasePage {

    // Main Laporan Menu
    private final By laporanMenu = By.xpath("//*[contains(text(),'Laporan')]");

    // Submenus
    private final By semuaSubmenu = By.xpath("//*[text()='Semua']");
    private final By kehadiranSubmenu = By.xpath("//*[text()='Kehadiran']");
    private final By izinSubmenu = By.xpath("//*[text()='Izin']");
    private final By cutiSubmenu = By.xpath("//*[text()='Cuti']");
    private final By sakitSubmenu = By.xpath("//*[text()='Sakit']");
    private final By lemburSubmenu = By.xpath("//*[text()='Lembur']");
    private final By validasiLemburSubmenu = By.xpath("//*[text()='Validasi Lembur']");
    private final By perjalananDinasSubmenu = By.xpath("//*[text()='Perjalanan Dinas']");
    private final By koreksiSubmenu = By.xpath("//*[text()='Koreksi']");

    // Filter Elements
    private final By searchNamaInput = By.xpath("//input[@placeholder='Cari berdasarkan nama' or contains(@placeholder,'nama')]");
    private final By startDateInput = By.xpath("(//input[@type='date' or contains(@placeholder,'Start')])[1]");
    private final By endDateInput = By.xpath("(//input[@type='date' or contains(@placeholder,'End')])[1]");
    private final By searchBtn = By.xpath("//button[contains(text(),'Search') or contains(.,'Cari')]");
    private final By exportBtn = By.xpath("//button[contains(text(),'Export')]");
    private final By resetBtn = By.xpath("//button[contains(text(),'Reset')]");

    // Approval Actions
    private final By approveBtn = By.xpath("//button[contains(text(),'Approve') or contains(text(),'Setujui')]");
    private final By rejectBtn = By.xpath("//button[contains(text(),'Reject') or contains(text(),'Tolak')]");

    public AdminLaporanPage(WebDriver driver) {
        super(driver);
    }

    public void openLaporanMenu() {
        click(laporanMenu);
    }

    public void openLaporanSubmenu(String submenuName) {
        openLaporanMenu();
        switch (submenuName.toLowerCase()) {
            case "semua" -> click(semuaSubmenu);
            case "kehadiran" -> click(kehadiranSubmenu);
            case "izin" -> click(izinSubmenu);
            case "cuti" -> click(cutiSubmenu);
            case "sakit" -> click(sakitSubmenu);
            case "lembur" -> click(lemburSubmenu);
            case "validasi lembur" -> click(validasiLemburSubmenu);
            case "perjalanan dinas" -> click(perjalananDinasSubmenu);
            case "koreksi" -> click(koreksiSubmenu);
            default -> {
                By targetSubmenu = By.xpath("//*[text()='" + submenuName + "'] | //a[contains(.,'" + submenuName + "')]");
                click(targetSubmenu);
            }
        }
    }

    public void filterLaporan(String namaKaryawan, String startDate, String endDate) {
        if (namaKaryawan != null && !namaKaryawan.isEmpty()) {
            type(searchNamaInput, namaKaryawan);
        }
        if (startDate != null && !startDate.isEmpty()) {
            type(startDateInput, startDate);
        }
        if (endDate != null && !endDate.isEmpty()) {
            type(endDateInput, endDate);
        }
        click(searchBtn);
    }

    public void clickExport() {
        click(exportBtn);
    }

    public void clickReset() {
        click(resetBtn);
    }

    public void clickApprove() {
        click(approveBtn);
    }

    public void clickReject() {
        click(rejectBtn);
    }

    public boolean isLaporanTableDisplayed() {
        By tableLocator = By.xpath("//table | //div[contains(@class,'table')]");
        return isElementDisplayed(tableLocator);
    }
}
