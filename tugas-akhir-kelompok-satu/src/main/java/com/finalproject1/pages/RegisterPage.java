package com.finalproject1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegisterPage extends BasePage {

    private final By nikInput = By.xpath("//input[@name='nik' or contains(@placeholder,'NIK')]");
    private final By namaLengkapInput = By.xpath("//input[@name='name' or @name='nama' or contains(@placeholder,'nama')]");
    private final By emailInput = By.xpath("//input[@name='email' or @type='email' or contains(@placeholder,'Email')]");
    private final By passwordInput = By.xpath("//input[@name='password' or @type='password' or contains(@placeholder,'password')]");
    private final By uploadSelfieInput = By.xpath("//input[@type='file']");
    private final By daftarButton = By.xpath("//button[contains(.,'Daftar') or contains(.,'Register') or @type='submit']");
    private final By successNotification = By.xpath("//div[contains(@class,'success') or contains(@class,'alert') or contains(text(),'berhasil')]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void openRegisterPage(String url) {
        getDriver().get(url);
    }

    public void fillRegistrationForm(String nik, String name, String email, String password) {
        try {
            type(nikInput, nik);
        } catch (Exception ignored) {}
        try {
            type(namaLengkapInput, name);
        } catch (Exception ignored) {}
        try {
            type(emailInput, email);
        } catch (Exception ignored) {}
        try {
            type(passwordInput, password);
        } catch (Exception ignored) {}
    }

    public void uploadSelfieImage(String absoluteFilePath) {
        try {
            getDriver().findElement(uploadSelfieInput).sendKeys(absoluteFilePath);
        } catch (Exception ignored) {}
    }

    public void clickDaftar() {
        try {
            click(daftarButton);
        } catch (Exception e) {
            try {
                List<WebElement> buttons = getDriver().findElements(By.xpath("//button"));
                for (WebElement b : buttons) {
                    if (b.getText().toLowerCase().contains("daftar") || b.getText().toLowerCase().contains("register") || "submit".equalsIgnoreCase(b.getAttribute("type"))) {
                        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", b);
                        return;
                    }
                }
            } catch (Exception ignored) {}
        }
    }

    public boolean isSuccessNotificationDisplayed() {
        return isElementDisplayed(successNotification);
    }
}
