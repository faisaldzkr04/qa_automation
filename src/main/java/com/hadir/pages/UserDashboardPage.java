package com.hadir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserDashboardPage extends BasePage {

    private final By greetingName = By.xpath("//*[contains(text(),'Hadir') or contains(text(),'Halo') or contains(text(),'Selamat')]");
    private final By absenMasukBtn = By.xpath("//button[contains(text(),'Masuk') or contains(.,'Absen Masuk')] | //div[contains(text(),'Masuk')]");
    private final By absenPulangBtn = By.xpath("//button[contains(text(),'Pulang') or contains(.,'Absen Pulang')] | //div[contains(text(),'Pulang')]");
    private final By cameraModal = By.xpath("//video | //canvas | //div[contains(@class,'camera') or contains(@class,'modal') or contains(@role,'dialog')]");

    public UserDashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isGreetingDisplayed() {
        return isElementDisplayed(greetingName);
    }

    public boolean isUserNamePresent(String expectedName) {
        By dynamicUserLocator = By.xpath("//*[contains(text(),'" + expectedName + "')]");
        return isElementDisplayed(dynamicUserLocator);
    }

    public void clickAbsenMasuk() {
        click(absenMasukBtn);
    }

    public void clickAbsenPulang() {
        click(absenPulangBtn);
    }

    public boolean isCameraOrModalDisplayed() {
        return isElementDisplayed(cameraModal);
    }
}
