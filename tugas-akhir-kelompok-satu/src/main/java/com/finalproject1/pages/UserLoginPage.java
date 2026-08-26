package com.finalproject1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserLoginPage extends BasePage {

    // Locators for Hadir User / Mobile Mirroring login page
    private final By emailOrNikInput = By.xpath("//input[@name='email' or @name='nik' or @id='email' or @type='email' or @type='text']");
    private final By passwordInput = By.xpath("//input[@name='password' or @id='password' or @type='password']");
    private final By submitButton = By.xpath("//button[@type='submit' or contains(text(),'Masuk') or contains(text(),'Login')]");
    private final By errorAlert = By.xpath("//div[contains(@class,'MuiAlert') or contains(@class,'alert') or contains(@role,'alert')]");

    public UserLoginPage(WebDriver driver) {
        super(driver);
    }

    public void openUserLoginPage(String url) {
        getDriver().get(url);
    }

    public void enterUserEmail(String email) {
        type(emailOrNikInput, email);
    }

    public void enterUserPassword(String password) {
        type(passwordInput, password);
    }

    public void clickSubmitButton() {
        click(submitButton);
    }

    public void loginAsUser(String email, String password) {
        enterUserEmail(email);
        enterUserPassword(password);
        clickSubmitButton();
    }

    public String getErrorMessage() {
        return isElementDisplayed(errorAlert) ? getText(errorAlert) : "";
    }
}
