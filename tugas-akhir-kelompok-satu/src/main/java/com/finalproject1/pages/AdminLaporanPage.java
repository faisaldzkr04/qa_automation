package com.finalproject1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminLaporanPage extends BasePage {

    // Main Laporan Menu
    private final By laporanMenu = By.xpath("//*[contains(text(),'Laporan')]");

    // Filter Elements
    private final By searchNamaInput = By.xpath("//input[@placeholder='Cari berdasarkan nama' or contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'nama') or @name='search']");
    private final By startDateInput = By.xpath("(//input[@type='date' or contains(@placeholder,'Start') or contains(@name,'start')])[1]");
    private final By endDateInput = By.xpath("(//input[@type='date' or contains(@placeholder,'End') or contains(@name,'end')])[1]");
    private final By searchBtn = By.xpath("//button[contains(.,'Search') or contains(.,'Cari') or contains(translate(@aria-label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'search') or contains(translate(@aria-label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'cari')] | //button//*[@data-testid='SearchIcon']/..");
    private final By exportBtn = By.xpath("//button[contains(.,'Export')]");
    private final By resetBtn = By.xpath("//button[contains(.,'Reset')]");
    private final By downloadBtn = By.xpath("//button[contains(.,'Download')]");
    private final By unitDepartemenFilter = By.xpath("//*[contains(text(),'Unit Departemen') or contains(text(),'Unit')]/following-sibling::*//input | //*[contains(text(),'Unit')]/..//div[@role='button']");
    private final By terapkanBtn = By.xpath("//button[contains(.,'Terapkan')]");
    private final By batalBtn = By.xpath("//button[contains(.,'Batal')]");
    private final By saveDateBtn = By.xpath("//button[contains(.,'Save') or contains(.,'Simpan')]");
    private final By cancelDateBtn = By.xpath("//button[contains(.,'Cancel') or contains(.,'Batal')]");

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
            By targetSubmenu = By.xpath("//*[text()='" + submenuName + "'] | //a[contains(.,'" + submenuName + "')]");
            click(targetSubmenu);
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
        } catch (Exception ignored) {}
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
        try {
            By tableLocator = By.xpath("//table | //div[contains(@class,'table')] | //div[contains(@class,'MuiDataGrid')]");
            return isElementDisplayed(tableLocator);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTableEmpty() {
        try {
            String text = getDriver().findElement(org.openqa.selenium.By.tagName("body")).getText().toLowerCase();
            System.out.println("DEBUG_BODY_TEXT: " + text.replace("\n", " | ")); // Print to see the actual text
            if (text.contains("tidak ada") || text.contains("tidak ditemukan") || text.contains("no data") || text.contains("0-0") || text.contains("0 of 0") || text.contains("kosong") || text.contains("data tidak")) {
                return true;
            }
            java.util.List<org.openqa.selenium.WebElement> rows = getDriver().findElements(org.openqa.selenium.By.xpath("//tbody/tr | //div[contains(@class,'MuiDataGrid-row')] | //div[@role='row' and not(contains(@class,'header'))]"));
            return rows.isEmpty() || rows.size() == 0;
        } catch (Exception e) {
            return true;
        }
    }

    public void typeSearchName(String name) {
        try {
            WebElement input = getDriver().findElement(searchNamaInput);
            input.clear();
            input.sendKeys(name);
            input.sendKeys(org.openqa.selenium.Keys.ENTER);
        } catch (Exception e) {
            System.out.println("ERROR in typeSearchName: " + e.getMessage());
        }
    }

    public void clickSearch() {
        try {
            Thread.sleep(500);
            click(searchBtn);
        } catch (Exception e) {
            System.out.println("ERROR in clickSearch: " + e.getMessage());
        }
    }

    public void selectUnitDepartemen(String unit) {
        try {
            click(unitDepartemenFilter);
            Thread.sleep(500);
            By option = By.xpath("//li[contains(.,'"+unit+"')] | //div[contains(text(),'"+unit+"')]");
            click(option);
        } catch (Exception e) {}
    }

    public void clickTerapkan() {
        try { click(terapkanBtn); } catch (Exception e) {}
    }

    public void clickBatalFilter() {
        try { click(batalBtn); } catch (Exception e) {}
    }

    public void selectDates() {
        try {
            type(startDateInput, "01012024");
            type(endDateInput, "31122024");
        } catch (Exception e) {}
    }

    public void clickSaveDate() {
        try { click(saveDateBtn); } catch (Exception e) {}
    }

    public void clickCancelDate() {
        try { click(cancelDateBtn); } catch (Exception e) {}
    }

    public boolean hasExportButton() {
        try {
            return getDriver().findElements(exportBtn).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void typeDropdown(String field, String data) {
        try {
            By input = By.xpath("//*[contains(text(),'"+field+"')]/following-sibling::*//input | //input[contains(@placeholder,'"+field+"')]");
            type(input, data);
            Thread.sleep(500);
            By option = By.xpath("//li[contains(.,'"+data+"')] | //div[contains(text(),'"+data+"')]");
            click(option);
        } catch (Exception e) {}
    }

    public void clickDownload() {
        try { click(downloadBtn); } catch (Exception e) {}
    }
}
