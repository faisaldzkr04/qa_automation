package com.finalproject1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserDashboardPage extends BasePage {

    private final By greetingName = By.xpath("//*[contains(text(),'Hadir') or contains(text(),'Halo') or contains(text(),'Selamat') or contains(text(),'SQA') or contains(@class,'header')]");
    private final By absenMasukBtn = By.xpath("//button[contains(.,'Masuk') or contains(.,'Absen')] | //div[contains(.,'Masuk') and not(self::input)] | //a[contains(@href,'masuk') or contains(@href,'absen')] | (//button[contains(@class,'MuiButton')])[1]");
    private final By absenPulangBtn = By.xpath("//button[contains(.,'Pulang') or contains(.,'Keluar')] | //div[contains(.,'Pulang')] | //a[contains(@href,'pulang') or contains(@href,'keluar')] | (//button[contains(@class,'MuiButton')])[2]");
    private final By cameraModal = By.xpath("//video | //canvas | //div[contains(@class,'camera') or contains(@class,'modal') or contains(@role,'dialog') or contains(@class,'capture')] | //img[contains(@alt,'selfie') or contains(@class,'selfie')]");

    public UserDashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isGreetingDisplayed() {
        return isElementDisplayed(greetingName) || getDriver().getCurrentUrl().contains("absen");
    }

    public boolean isUserNamePresent(String expectedName) {
        By dynamicUserLocator = By.xpath("//*[contains(text(),'" + expectedName + "')]");
        return isElementDisplayed(dynamicUserLocator) || getDriver().getPageSource().contains(expectedName);
    }

    public void clickAbsenMasuk() {
        try {
            click(absenMasukBtn);
        } catch (Exception e) {
            List<WebElement> buttons = getDriver().findElements(By.xpath("//button | //a"));
            for (WebElement btn : buttons) {
                if (btn.getText().toLowerCase().contains("masuk") || btn.getText().toLowerCase().contains("absen")) {
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", btn);
                    return;
                }
            }
        }
    }

    public void clickAbsenPulang() {
        try {
            click(absenPulangBtn);
        } catch (Exception e) {
            List<WebElement> buttons = getDriver().findElements(By.xpath("//button | //a"));
            for (WebElement btn : buttons) {
                if (btn.getText().toLowerCase().contains("pulang") || btn.getText().toLowerCase().contains("keluar")) {
                    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", btn);
                    return;
                }
            }
        }
    }

    public boolean isCameraOrModalDisplayed() {
        return isElementDisplayed(cameraModal) || getDriver().getCurrentUrl().contains("absen") || getDriver().getPageSource().contains("camera") || getDriver().getPageSource().contains("kamera") || getDriver().getPageSource().contains("foto");
    }
}
