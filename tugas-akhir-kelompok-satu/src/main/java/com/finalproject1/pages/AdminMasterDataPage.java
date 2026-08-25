package com.finalproject1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminMasterDataPage extends BasePage {

    private final By managementMenu = By.xpath("//*[contains(text(),'Management')]");
    private final By tambahkanBtn = By.xpath("//button[contains(text(),'Tambahkan') or contains(text(),'Tambah')]");
    private final By searchInput = By.xpath("//input[@placeholder='cari berdasarkan nama' or @placeholder='Search']");
    private final By searchBtn = By.xpath("//button[contains(text(),'Search') or contains(.,'Cari')]");
    private final By resetBtn = By.xpath("//button[contains(text(),'Reset')]");

    // Modal & Action
    private final By namaInput = By.xpath("//input[@name='name' or @name='nama' or @placeholder='Nama Divisi' or @placeholder='Nama Unit' or @placeholder='Nama Posisi']");
    private final By submitModalBtn = By.xpath("//div[contains(@role,'dialog')]//button[contains(text(),'Tambah') or contains(text(),'Simpan')]");

    public AdminMasterDataPage(WebDriver driver) {
        super(driver);
    }

    public void openMasterMenu(String masterName) {
        click(managementMenu);
        By menuLocator = By.xpath("//*[text()='" + masterName + "'] | //a[contains(.,'" + masterName + "')]");
        click(menuLocator);
    }

    public void clickTambahkan() {
        click(tambahkanBtn);
    }

    public void inputMasterNameAndSave(String name) {
        type(namaInput, name);
        click(submitModalBtn);
    }

    public void filterByName(String name) {
        type(searchInput, name);
        click(searchBtn);
    }

    public void clickReset() {
        click(resetBtn);
    }

    public boolean isDataTablePresent() {
        By dataRows = By.xpath("//table//tbody//tr | //div[contains(@class,'MuiDataGrid-row')]");
        return isElementDisplayed(dataRows);
    }
}
