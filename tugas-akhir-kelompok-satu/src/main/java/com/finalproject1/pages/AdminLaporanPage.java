package com.finalproject1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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
    private final By searchNamaInput = By.xpath("//input[@placeholder='Cari berdasarkan nama' or contains(@placeholder,'nama') or @name='search']");
    private final By startDateInput = By.xpath("(//input[@type='date' or contains(@placeholder,'Start')])[1]");
    private final By endDateInput = By.xpath("(//input[@type='date' or contains(@placeholder,'End')])[1]");
    private final By searchBtn = By.xpath("//button[contains(.,'Search') or contains(.,'Cari')]");
    private final By exportBtn = By.xpath("//button[contains(.,'Export')]");
    private final By resetBtn = By.xpath("//button[contains(.,'Reset')]");

    public AdminLaporanPage(WebDriver driver) {
        super(driver);
    }

    public void openLaporanMenu() {
        try {
            click(laporanMenu);
        } catch (Exception ignored) {}
    }

    public void openLaporanSubmenu(String submenuName) {
        try {
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
        } catch (Exception e) {
            getDriver().get("https://magang.dikahadir.com/laporan/" + submenuName.toLowerCase().replace(" ", "-"));
        }
    }

    public void filterLaporan(String namaKaryawan, String startDate, String endDate) {
        try {
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
        } catch (Exception e) {
            try {
                WebElement input = getDriver().findElement(searchNamaInput);
                input.sendKeys(namaKaryawan);
                input.submit();
            } catch (Exception ignored) {}
        }
    }

    public void clickReset() {
        try {
            click(resetBtn);
        } catch (Exception e) {
            try {
                List<WebElement> buttons = getDriver().findElements(By.xpath("//button"));
                for (WebElement b : buttons) {
                    if (b.getText().toLowerCase().contains("reset")) {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", b);
                        return;
                    }
                }
            } catch (Exception ignored) {}
        }
    }

    public boolean isLaporanTableDisplayed() {
        By tableLocator = By.xpath("//table | //div[contains(@class,'table')] | //div[contains(@class,'MuiDataGrid')]");
        return isElementDisplayed(tableLocator);
    }
}
