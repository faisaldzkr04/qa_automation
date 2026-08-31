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
            Thread.sleep(800);
        } catch (Exception ignored) {}
    }

    public void openLaporanSubmenu(String submenuName) {
        try {
            openLaporanMenu();
            By targetSubmenu = By.xpath("//*[text()='" + submenuName + "'] | //a[contains(.,'" + submenuName + "')]");
            click(targetSubmenu);
            Thread.sleep(1200);
        } catch (Exception e) {
            getDriver().get("https://magang.dikahadir.com/laporan/" + submenuName.toLowerCase().replace(" ", "-"));
            try { Thread.sleep(1200); } catch (InterruptedException ignored) {}
        }
    }

    public void filterLaporan(String namaKaryawan, String startDate, String endDate) {
        try {
            if (namaKaryawan != null && !namaKaryawan.isEmpty()) {
                typeSlowly(searchNamaInput, namaKaryawan);
            }
            if (startDate != null && !startDate.isEmpty()) {
                selectDates();
            }
            clickSearch();
        } catch (Exception ignored) {}
    }

    public void clickReset() {
        try {
            List<WebElement> buttons = getDriver().findElements(resetBtn);
            if (!buttons.isEmpty()) {
                highlight(buttons.get(0));
                buttons.get(0).click();
                Thread.sleep(1000);
            } else {
                List<WebElement> allButtons = getDriver().findElements(By.xpath("//button"));
                for (WebElement b : allButtons) {
                    if (b.getText().toLowerCase().contains("reset")) {
                        highlight(b);
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", b);
                        Thread.sleep(1000);
                        return;
                    }
                }
            }
        } catch (Exception ignored) {}
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
            String text = getDriver().findElement(By.tagName("body")).getText().toLowerCase();
            System.out.println("DEBUG_BODY_TEXT: " + text.replace("\n", " | "));
            if (text.contains("tidak ada") || text.contains("tidak ditemukan") || text.contains("no data") || text.contains("0-0") || text.contains("0 of 0") || text.contains("kosong") || text.contains("data tidak")) {
                return true;
            }
            List<WebElement> rows = getDriver().findElements(By.xpath("//tbody/tr | //div[contains(@class,'MuiDataGrid-row')] | //div[@role='row' and not(contains(@class,'header'))]"));
            return rows.isEmpty() || rows.size() == 0;
        } catch (Exception e) {
            return true;
        }
    }

    public void highlight(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            js.executeScript("arguments[0].style.outline = '2px solid #2196F3'; arguments[0].style.backgroundColor = 'rgba(33, 150, 243, 0.1)';", element);
            Thread.sleep(500);
            js.executeScript("arguments[0].style.outline = ''; arguments[0].style.backgroundColor = '';", element);
        } catch (Exception ignored) {}
    }

    public void inspectAndHighlightFilterElements() {
        try {
            // Highlight Search Input
            List<WebElement> searchInputs = getDriver().findElements(searchNamaInput);
            if (!searchInputs.isEmpty()) {
                highlight(searchInputs.get(0));
            }
            
            // Highlight Filter by Unit Departemen
            List<WebElement> unitFilters = getDriver().findElements(By.xpath("//*[contains(text(),'Unit Departemen') or contains(text(),'Unit')] | //button[contains(.,'Filter')]"));
            if (!unitFilters.isEmpty()) {
                highlight(unitFilters.get(0));
                unitFilters.get(0).click();
                Thread.sleep(1000);
                List<WebElement> batalBtns = getDriver().findElements(batalBtn);
                if (!batalBtns.isEmpty()) {
                    batalBtns.get(0).click();
                } else {
                    getDriver().findElement(By.tagName("body")).click();
                }
                Thread.sleep(500);
            }

            // Highlight Reset Button
            List<WebElement> resetBtns = getDriver().findElements(resetBtn);
            if (!resetBtns.isEmpty()) {
                highlight(resetBtns.get(0));
            }
            Thread.sleep(500);
        } catch (Exception ignored) {}
    }

    public void typeSlowly(By locator, String text) {
        try {
            WebElement input = waitForVisibility(locator);
            input.click();
            Thread.sleep(300);
            input.clear();
            for (char c : text.toCharArray()) {
                input.sendKeys(String.valueOf(c));
                Thread.sleep(80);
            }
            Thread.sleep(600);
        } catch (Exception e) {
            try {
                WebElement input = getDriver().findElement(locator);
                input.sendKeys(text);
            } catch (Exception ignored) {}
        }
    }

    public void typeSearchName(String name) {
        try {
            typeSlowly(searchNamaInput, name);
            Thread.sleep(500);
            WebElement input = getDriver().findElement(searchNamaInput);
            input.sendKeys(org.openqa.selenium.Keys.ENTER);
            Thread.sleep(800);
        } catch (Exception e) {
            System.out.println("ERROR in typeSearchName: " + e.getMessage());
        }
    }

    public void clickSearch() {
        try {
            Thread.sleep(600);
            click(searchBtn);
            Thread.sleep(1200);
        } catch (Exception e) {
            try {
                WebElement btn = getDriver().findElement(searchBtn);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", btn);
                Thread.sleep(1200);
            } catch (Exception ignored) {}
        }
    }

    public void selectUnitDepartemen(String unit) {
        try {
            List<WebElement> unitFilters = getDriver().findElements(By.xpath("//*[contains(text(),'Unit Departemen') or contains(text(),'Unit')] | //button[contains(.,'Filter')] | //div[contains(@class,'filter')]"));
            if (!unitFilters.isEmpty()) {
                highlight(unitFilters.get(0));
                unitFilters.get(0).click();
                Thread.sleep(1000);
                
                By option = By.xpath("//li[contains(.,'"+unit+"')] | //div[contains(text(),'"+unit+"')] | //span[contains(text(),'"+unit+"')] | //ul/li[1]");
                List<WebElement> options = getDriver().findElements(option);
                if (!options.isEmpty()) {
                    highlight(options.get(0));
                    options.get(0).click();
                    Thread.sleep(800);
                }
            }
        } catch (Exception e) {}
    }

    public void clickTerapkan() {
        try { 
            List<WebElement> btns = getDriver().findElements(terapkanBtn);
            if (!btns.isEmpty()) {
                highlight(btns.get(0));
                btns.get(0).click();
            } else {
                click(terapkanBtn);
            }
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    public void clickBatalFilter() {
        try { 
            List<WebElement> btns = getDriver().findElements(batalBtn);
            if (!btns.isEmpty()) {
                highlight(btns.get(0));
                btns.get(0).click();
            } else {
                click(batalBtn);
            }
            Thread.sleep(800);
        } catch (Exception e) {}
    }

    public void selectDates() {
        try {
            By dateTrigger = By.xpath("//input[contains(@placeholder,'Start') or contains(@placeholder,'Tanggal') or contains(@name,'start') or contains(@type,'date')] | //button[contains(.,'Tanggal') or contains(.,'Date') or contains(.,'Pilih')] | //div[contains(@class,'date')] | //*[contains(text(),'Start Date') or contains(text(),'Tanggal')]");
            List<WebElement> dateElements = getDriver().findElements(dateTrigger);
            if (!dateElements.isEmpty()) {
                WebElement el = dateElements.get(0);
                highlight(el);
                el.click();
                Thread.sleep(1000);
                
                try {
                    if ("input".equalsIgnoreCase(el.getTagName())) {
                        el.click();
                        el.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
                        el.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
                        for (char c : "01/08/2026".toCharArray()) {
                            el.sendKeys(String.valueOf(c));
                            Thread.sleep(70);
                        }
                    }
                } catch (Exception ignored) {}
                
                List<WebElement> days = getDriver().findElements(By.xpath("//button[contains(@class,'MuiPickersDay-root') or contains(@class,'day') or @role='gridcell']"));
                if (days.size() >= 10) {
                    highlight(days.get(2));
                    days.get(2).click();
                    Thread.sleep(600);
                    
                    if (days.size() > 15) {
                        highlight(days.get(15));
                        days.get(15).click();
                        Thread.sleep(600);
                    }
                }
            }
            Thread.sleep(800);
        } catch (Exception ignored) {}
    }

    public void clickSaveDate() {
        try { 
            List<WebElement> btns = getDriver().findElements(By.xpath("//button[contains(.,'Save') or contains(.,'Simpan') or contains(.,'Terapkan') or contains(.,'OK') or contains(.,'Apply')]"));
            if (!btns.isEmpty()) {
                highlight(btns.get(0));
                btns.get(0).click();
                Thread.sleep(1000);
            } else {
                click(saveDateBtn);
                Thread.sleep(1000);
            }
        } catch (Exception e) {}
    }

    public void clickCancelDate() {
        try { 
            List<WebElement> btns = getDriver().findElements(By.xpath("//button[contains(.,'Cancel') or contains(.,'Batal')]"));
            if (!btns.isEmpty()) {
                highlight(btns.get(0));
                btns.get(0).click();
                Thread.sleep(800);
            } else {
                click(cancelDateBtn);
                Thread.sleep(800);
            }
        } catch (Exception e) {}
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
            By input = By.xpath("//*[contains(text(),'"+field+"')]/following-sibling::*//input | //input[contains(@placeholder,'"+field+"') or contains(@name,'"+field.toLowerCase()+"')] | //div[contains(.,'"+field+"')]/..//input");
            typeSlowly(input, data);
            Thread.sleep(600);
            By option = By.xpath("//li[contains(.,'"+data+"')] | //div[contains(text(),'"+data+"')] | //ul/li[1]");
            List<WebElement> options = getDriver().findElements(option);
            if (!options.isEmpty()) {
                options.get(0).click();
                Thread.sleep(600);
            }
        } catch (Exception e) {}
    }

    public void fillDownloadAbsenForm() {
        try {
            selectDates();
            typeDropdown("NIK", "12345");
            typeDropdown("Nama", "Budi");
            typeDropdown("Upliner", "Manager");
            typeDropdown("Divisi", "IT");
            typeDropdown("Unit", "Quality Assurance");
            typeDropdown("Tipe", "Excel");
            Thread.sleep(800);
        } catch (Exception ignored) {}
    }

    public void fillDownloadAbsenV2Form() {
        try {
            selectDates();
            typeDropdown("NIK", "12345");
            typeDropdown("Nama", "Budi");
            typeDropdown("Upliner", "Manager");
            typeDropdown("Unit", "Quality Assurance");
            Thread.sleep(800);
        } catch (Exception ignored) {}
    }

    public void fillSisaCutiForm() {
        try {
            typeDropdown("NIK", "12345");
            typeDropdown("Nama", "Budi");
            typeDropdown("Upliner", "Manager");
            Thread.sleep(800);
        } catch (Exception ignored) {}
    }

    public void clickDownload() {
        try { 
            Thread.sleep(600);
            click(downloadBtn); 
            Thread.sleep(1200);
        } catch (Exception e) {}
    }
}
