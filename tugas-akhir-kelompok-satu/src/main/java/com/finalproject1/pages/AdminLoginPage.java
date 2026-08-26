package com.finalproject1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginPage extends BasePage {

    // Locators based on standard Hadir Web Admin login interface
    private final By emailInput = By.xpath("//input[@name='email' or @id='email' or @type='email']");
    private final By passwordInput = By.xpath("//input[@name='password' or @id='password' or @type='password']");
    private final By loginButton = By.xpath("//button[@type='submit' or contains(text(),'Masuk') or contains(text(),'Login')]");
    private final By errorMessageAlert = By.xpath("//div[contains(@class,'alert') or contains(@class,'error') or contains(@role,'alert')]");
    private final By adminDashboardHeader = By.xpath("//h1 | //h2 | //header | //div[contains(@class,'sidebar')] | //*[contains(text(),'Dashboard')]");

    public AdminLoginPage(WebDriver driver) {
        super(driver);
    }

    public void openAdminLoginPage(String url) {
        getDriver().get(url);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void loginAsAdmin(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isAdminDashboardDisplayed() {
        return isElementDisplayed(adminDashboardHeader) || !getDriver().getCurrentUrl().contains("login");
    }

    public String getErrorMessage() {
        return isElementDisplayed(errorMessageAlert) ? getText(errorMessageAlert) : "";
    }
}
