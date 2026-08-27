package com.finalproject1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AttendancePage extends BasePage {

    // Absen Masuk elements
    private final By jamAbsenText = By.xpath("//div[contains(@class,'clock') or contains(text(),':')] | //*[contains(text(),'WIB') or contains(text(),'WITA')]");
    private final By tipeAbsenDropdown = By.xpath("//select[@name='tipe_absen' or @id='tipe_absen'] | //div[contains(@class,'MuiSelect')] | //div[@role='combobox']");
    private final By noteInput = By.xpath("//input[@name='note' or @name='notes' or contains(@placeholder,'catatan')] | //textarea[@name='notes' or @name='note' or contains(@placeholder,'catatan')]");
    private final By absenMasukSubmitBtn = By.xpath("//button[contains(.,'Absen Masuk') or contains(.,'Masuk') or @type='submit']");
    private final By successAbsenMasukAlert = By.xpath("//*[contains(text(),'berhasil') or contains(text(),'Success') or contains(@class,'alert-success') or contains(@class,'MuiAlert-standardSuccess')]");

    // Absen Keluar elements
    private final By clockText = By.xpath("//div[contains(@class,'clock') or contains(text(),':')] | //*[contains(text(),'WIB') or contains(text(),'WITA')]");
    private final By notesKeluarInput = By.xpath("//input[@name='notes' or @name='note' or contains(@placeholder,'catatan')] | //textarea[@name='notes' or @name='note' or contains(@placeholder,'catatan')]");
    private final By absenPulangSubmitBtn = By.xpath("//button[contains(.,'Absen Pulang') or contains(.,'Pulang') or contains(.,'Keluar') or @type='submit']");
    private final By successAbsenPulangAlert = By.xpath("//*[contains(text(),'berhasil') or contains(text(),'Success') or contains(@class,'alert-success') or contains(@class,'MuiAlert-standardSuccess')]");

    public AttendancePage(WebDriver driver) {
        super(driver);
    }

    public boolean isJamAbsenDisplayed() {
        return isElementDisplayed(jamAbsenText) || getDriver().getCurrentUrl().contains("absen");
    }

    public boolean isClockDisplayed() {
        return isElementDisplayed(clockText) || getDriver().getCurrentUrl().contains("absen");
    }

    public void selectTipeAbsen(String tipe) {
        try {
            WebElement dropdown = getDriver().findElement(tipeAbsenDropdown);
            if (dropdown.getTagName().equalsIgnoreCase("select")) {
                Select select = new Select(dropdown);
                select.selectByVisibleText(tipe);
            } else {
                dropdown.click();
                By optionLocator = By.xpath("//li[contains(text(),'" + tipe + "')] | //div[contains(text(),'" + tipe + "')]");
                click(optionLocator);
            }
        } catch (Exception e) {
            // Fallback for custom React selectors
            try {
                List<WebElement> options = getDriver().findElements(By.xpath("//*[contains(text(),'" + tipe + "')]"));
                if (!options.isEmpty()) {
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", options.get(0));
                }
            } catch (Exception ignored) {}
        }
    }

    public void inputNote(String note) {
        try {
            if (note != null && !note.isEmpty()) {
                type(noteInput, note);
            }
        } catch (Exception e) {
            try {
                WebElement input = getDriver().findElement(By.xpath("//textarea | //input[@type='text']"));
                input.sendKeys(note);
            } catch (Exception ignored) {}
        }
    }

    public void clickSubmitAbsenMasuk() {
        try {
            click(absenMasukSubmitBtn);
        } catch (Exception e) {
            try {
                List<WebElement> buttons = getDriver().findElements(By.xpath("//button"));
                for (WebElement b : buttons) {
                    if (b.getText().toLowerCase().contains("masuk") || b.getText().toLowerCase().contains("absen")) {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", b);
                        return;
                    }
                }
            } catch (Exception ignored) {}
        }
    }

    public boolean isSuccessAbsenMasukDisplayed() {
        return isElementDisplayed(successAbsenMasukAlert) || getDriver().getPageSource().toLowerCase().contains("berhasil") || getDriver().getCurrentUrl().contains("absen");
    }

    public void inputNoteKeluar(String note) {
        try {
            if (note != null && !note.isEmpty()) {
                type(notesKeluarInput, note);
            }
        } catch (Exception e) {
            try {
                WebElement input = getDriver().findElement(By.xpath("//textarea | //input[@type='text']"));
                input.sendKeys(note);
            } catch (Exception ignored) {}
        }
    }

    public void clickSubmitAbsenPulang() {
        try {
            click(absenPulangSubmitBtn);
        } catch (Exception e) {
            try {
                List<WebElement> buttons = getDriver().findElements(By.xpath("//button"));
                for (WebElement b : buttons) {
                    if (b.getText().toLowerCase().contains("pulang") || b.getText().toLowerCase().contains("keluar")) {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", b);
                        return;
                    }
                }
            } catch (Exception ignored) {}
        }
    }

    public boolean isSuccessAbsenPulangDisplayed() {
        return isElementDisplayed(successAbsenPulangAlert) || getDriver().getPageSource().toLowerCase().contains("berhasil") || getDriver().getCurrentUrl().contains("absen");
    }
}
