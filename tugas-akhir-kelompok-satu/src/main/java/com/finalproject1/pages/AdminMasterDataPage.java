package com.finalproject1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminMasterDataPage extends BasePage {

    private final By managementMenu = By.xpath("//*[contains(text(),'Management')]");
    private final By tambahkanBtn = By.xpath("//button[contains(.,'Tambahkan') or contains(.,'Tambah')]");
    private final By searchInput = By.xpath("//input[@placeholder='cari berdasarkan nama' or @placeholder='Search' or contains(@placeholder,'cari') or @type='text']");
    private final By searchBtn = By.xpath("//button[contains(.,'Search') or contains(.,'Cari')]");
    private final By resetBtn = By.xpath("//button[contains(.,'Reset')]");

    // Modal & Action
    private final By namaInput = By.xpath("//input[@name='name' or @name='nama' or contains(@placeholder,'Nama')]");
    private final By submitModalBtn = By.xpath("//div[contains(@role,'dialog')]//button[contains(.,'Tambah') or contains(.,'Simpan') or @type='submit']");

    public AdminMasterDataPage(WebDriver driver) {
        super(driver);
    }

    public void openMasterMenu(String masterName) {
        try {
            click(managementMenu);
            By menuLocator = By.xpath("//*[text()='" + masterName + "'] | //a[contains(.,'" + masterName + "')]");
            click(menuLocator);
        } catch (Exception e) {
            getDriver().get("https://magang.dikahadir.com/management/" + masterName.toLowerCase().replace(" ", "-"));
        }
    }

    public void clickTambahkan() {
        try {
            click(tambahkanBtn);
        } catch (Exception e) {
            try {
                List<WebElement> buttons = getDriver().findElements(By.xpath("//button"));
                for (WebElement b : buttons) {
                    if (b.getText().toLowerCase().contains("tambah")) {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", b);
                        return;
                    }
                }
            } catch (Exception ignored) {}
        }
    }

    public void inputMasterNameAndSave(String name) {
        try {
            type(namaInput, name);
            click(submitModalBtn);
        } catch (Exception e) {
            try {
                List<WebElement> buttons = getDriver().findElements(By.xpath("//button"));
                for (WebElement b : buttons) {
                    if (b.getText().toLowerCase().contains("simpan") || b.getText().toLowerCase().contains("tambah")) {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", b);
                        return;
                    }
                }
            } catch (Exception ignored) {}
        }
    }

    public void filterByName(String name) {
        try {
            type(searchInput, name);
            click(searchBtn);
        } catch (Exception e) {
            try {
                WebElement input = getDriver().findElement(searchInput);
                input.sendKeys(name);
                input.submit();
            } catch (Exception ignored) {}
        }
    }

    public void clickReset() {
        try {
            click(resetBtn);
        } catch (Exception ignored) {}
    }

    public boolean isDataTablePresent() {
        By dataRows = By.xpath("//table//tbody//tr | //div[contains(@class,'MuiDataGrid-row')] | //table");
        return isElementDisplayed(dataRows);
    }
}
