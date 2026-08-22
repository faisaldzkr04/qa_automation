package com.hadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AttendancePage extends BasePage {

    // Absen Masuk elements
    private final By jamAbsenText = By.xpath("//div[contains(@class,'clock') or contains(text(),':')]");
    private final By tipeAbsenDropdown = By.xpath("//select[@name='tipe_absen' or @id='tipe_absen'] | //div[contains(@class,'MuiSelect')]");
    private final By noteInput = By.xpath("//input[@name='note' or @name='notes'] | //textarea[@name='notes' or @name='note']");
    private final By absenMasukSubmitBtn = By.xpath("//button[contains(text(),'Absen Masuk') or @type='submit']");
    private final By successAbsenMasukAlert = By.xpath("//*[contains(text(),'berhasil') or contains(text(),'Success') or contains(@class,'alert-success')]");

    // Absen Keluar elements
    private final By clockText = By.xpath("//div[contains(@class,'clock') or contains(text(),':')]");
    private final By notesKeluarInput = By.xpath("//input[@name='notes' or @name='note'] | //textarea[@name='notes' or @name='note']");
    private final By absenPulangSubmitBtn = By.xpath("//button[contains(text(),'Absen Pulang') or @type='submit']");
    private final By successAbsenPulangAlert = By.xpath("//*[contains(text(),'berhasil') or contains(text(),'Success') or contains(@class,'alert-success')]");

    public AttendancePage(WebDriver driver) {
        super(driver);
    }

    public boolean isJamAbsenDisplayed() {
        return isElementDisplayed(jamAbsenText);
    }

    public boolean isClockDisplayed() {
        return isElementDisplayed(clockText);
    }

    public void selectTipeAbsen(String tipe) {
        try {
            Select select = new Select(driver.findElement(tipeAbsenDropdown));
            select.selectByVisibleText(tipe);
        } catch (Exception e) {
            // If it's a custom React/Material-UI dropdown
            click(tipeAbsenDropdown);
            By optionLocator = By.xpath("//li[contains(text(),'" + tipe + "')] | //div[contains(text(),'" + tipe + "')]");
            click(optionLocator);
        }
    }

    public void inputNote(String note) {
        if (note != null && !note.isEmpty()) {
            type(noteInput, note);
        }
    }

    public void clickSubmitAbsenMasuk() {
        click(absenMasukSubmitBtn);
    }

    public boolean isSuccessAbsenMasukDisplayed() {
        return isElementDisplayed(successAbsenMasukAlert);
    }

    public void inputNoteKeluar(String note) {
        if (note != null && !note.isEmpty()) {
            type(notesKeluarInput, note);
        }
    }

    public void clickSubmitAbsenPulang() {
        click(absenPulangSubmitBtn);
    }

    public boolean isSuccessAbsenPulangDisplayed() {
        return isElementDisplayed(successAbsenPulangAlert);
    }
}
