package com.finalproject1.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminLaporanPage extends BasePage {

    // Main Laporan Menu
    private final By laporanMenu = By.xpath("//*[contains(text(),'Laporan')]");

    // Filter Elements
    private final By searchNamaInput = By.xpath("//input[@placeholder='Cari berdasarkan nama' or contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'nama') or @name='search']");
    private final By searchBtn = By.xpath("//button[contains(.,'Search') or contains(.,'Cari') or contains(translate(@aria-label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'search') or contains(translate(@aria-label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'cari')] | //button//*[@data-testid='SearchIcon']/..");
    private final By exportBtn = By.xpath("//button[contains(.,'Export')]");
    private final By resetBtn = By.xpath("//button[contains(.,'Reset')]");
    private final By downloadBtn = By.xpath("//button[contains(.,'Download')]");
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
            if (submenuName.equalsIgnoreCase("Download Absen v2") || submenuName.equalsIgnoreCase("Download Absen V2")) {
                getDriver().get("https://magang.dikahadir.com/laporan/new-generate");
                Thread.sleep(1200);
                return;
            }
            openLaporanMenu();
            By targetSubmenu = By.xpath("//*[text()='" + submenuName + "'] | //a[contains(.,'" + submenuName + "')]");
            click(targetSubmenu);
            Thread.sleep(1200);
        } catch (Exception e) {
            String urlPath = submenuName.toLowerCase().replace(" ", "-");
            if (submenuName.equalsIgnoreCase("Download Absen v2") || submenuName.equalsIgnoreCase("Download Absen V2")) {
                urlPath = "new-generate";
            }
            getDriver().get("https://magang.dikahadir.com/laporan/" + urlPath);
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
            // Cari input kalender pertama (biasanya Start Date atau Tanggal Penarikan)
            By startTrigger = By.xpath("//label[contains(text(),'Start Date') or contains(text(),'Tanggal')]/..//input | //input[contains(@placeholder,'Start') or contains(@placeholder,'Tanggal') or contains(@name,'start')] | (//div[contains(@class,'date')]//input)[1]");
            List<WebElement> startElements = getDriver().findElements(startTrigger);
            
            if (!startElements.isEmpty()) {
                WebElement startInput = startElements.get(0);
                highlight(startInput);
                startInput.click();
                Thread.sleep(300);
                
                // Clear inputan lama
                startInput.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
                startInput.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
                Thread.sleep(300);
                
                // Ketik tanggal (format umum MUI: MMDDYYYY atau DDMMYYYY)
                startInput.sendKeys("25-08-2026");
                startInput.sendKeys(org.openqa.selenium.Keys.TAB);
                Thread.sleep(500);
                
                // Coba cari input End Date jika ada
                By endTrigger = By.xpath("//label[contains(text(),'End Date')]/..//input | //input[contains(@placeholder,'End') or contains(@name,'end')] | (//div[contains(@class,'date')]//input)[2]");
                List<WebElement> endElements = getDriver().findElements(endTrigger);
                if (!endElements.isEmpty()) {
                    WebElement endInput = endElements.get(0);
                    highlight(endInput);
                    endInput.click();
                    Thread.sleep(300);
                    endInput.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
                    endInput.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
                    endInput.sendKeys("03-09-2026");
                    endInput.sendKeys(org.openqa.selenium.Keys.TAB);
                    Thread.sleep(500);
                }
                
                // Klik sembarang area untuk menutup popup kalender
                getDriver().findElement(By.tagName("body")).click();
                Thread.sleep(500);
            }
        } catch (Exception ignored) {
            System.out.println("Gagal memilih tanggal pada kalender");
        }
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
            // Perbaiki locator agar tidak secara tidak sengaja match dengan parent div (yang bisa mengakibatkan input Date terpilih)
            By input = By.xpath("//label[contains(text(),'"+field+"')]/..//input | //*[contains(text(),'"+field+"') and not(*)]/ancestor::div[contains(@class,'input') or contains(@class,'form') or position()=1]//input | //input[contains(@placeholder,'"+field+"') or contains(@name,'"+field.toLowerCase()+"')]");
            
            // Coba ambil elemen, jika salah kita gunakan cara lebih strict
            List<WebElement> inputs = getDriver().findElements(input);
            WebElement targetInput = null;
            if (!inputs.isEmpty()) {
                targetInput = inputs.get(inputs.size() - 1); // Biasanya elemen terakhir yg valid atau yg ke-1
                if (inputs.size() > 1) {
                    for(WebElement el : inputs) {
                        if(el.isDisplayed() && !el.getAttribute("type").equals("hidden")) {
                            targetInput = el;
                            break;
                        }
                    }
                }
            }
            
            if(targetInput != null) {
                highlight(targetInput);
                targetInput.click();
                Thread.sleep(300);
                targetInput.clear();
                for (char c : data.toCharArray()) {
                    targetInput.sendKeys(String.valueOf(c));
                    Thread.sleep(80);
                }
                Thread.sleep(600);
            }
            
            By option = By.xpath("//li[contains(.,'"+data+"')] | //div[contains(text(),'"+data+"') and @role='option'] | //ul/li[1]");
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
            typeDropdown("NIK", "D12345");
            typeDropdown("Nama", "Test Karyawan");
            typeDropdown("Upliner", "Dummy Manager");
            typeDropdown("Divisi", "123456");
            typeDropdown("Unit", "Juara Coding");
            // Tipe Report dikosongkan sesuai instruksi
            Thread.sleep(800);
        } catch (Exception ignored) {}
    }

    public void fillDownloadAbsenV2Form() {
        try {
            selectDates();
            typeDropdown("NIK", "D12345");
            typeDropdown("Nama", "Test Karyawan");
            typeDropdown("Upliner", "Dummy Manager");
            typeDropdown("Unit", "Juara Coding");
            Thread.sleep(800);
        } catch (Exception ignored) {}
    }

    public void fillSisaCutiForm() {
        try {
            typeDropdown("NIK", "D12345");
            typeDropdown("Nama", "Test Karyawan");
            typeDropdown("Upliner", "Dummy Manager");
            typeDropdown("Unit", "Juara Coding");
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
