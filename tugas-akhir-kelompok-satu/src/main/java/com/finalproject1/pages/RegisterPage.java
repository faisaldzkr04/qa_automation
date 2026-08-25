package com.finalproject1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private final By nikInput = By.xpath("//input[@name='nik' or @placeholder='Masukan NIK kamu disini']");
    private final By namaLengkapInput = By.xpath("//input[@name='name' or @name='nama' or @placeholder='Masukan nama lengkap kamu disini']");
    private final By emailInput = By.xpath("//input[@name='email' or @type='email' or @placeholder='Masukan Email kamu disini']");
    private final By passwordInput = By.xpath("//input[@name='password' or @type='password' or @placeholder='Masukan password kamu disini']");
    private final By uploadSelfieInput = By.xpath("//input[@type='file']");
    private final By daftarButton = By.xpath("//button[contains(text(),'Daftar') or @type='submit']");
    private final By successNotification = By.xpath("//div[contains(@class,'success') or contains(@class,'alert') or contains(text(),'berhasil')]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void openRegisterPage(String url) {
        driver.get(url);
    }

    public void fillRegistrationForm(String nik, String name, String email, String password) {
        type(nikInput, nik);
        type(namaLengkapInput, name);
        type(emailInput, email);
        type(passwordInput, password);
    }

    public void uploadSelfieImage(String absoluteFilePath) {
        driver.findElement(uploadSelfieInput).sendKeys(absoluteFilePath);
    }

    public void clickDaftar() {
        click(daftarButton);
    }

    public boolean isSuccessNotificationDisplayed() {
        return isElementDisplayed(successNotification);
    }
}
