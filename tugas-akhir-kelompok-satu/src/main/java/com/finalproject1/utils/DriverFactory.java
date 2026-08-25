package com.finalproject1.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver initDriver(String browser) {
        if (browser == null) {
            browser = ConfigReader.getProperty("browser", "chrome");
        }

        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"));

        switch (browser.toLowerCase()) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) firefoxOptions.addArguments("-headless");
                tlDriver.set(new FirefoxDriver(firefoxOptions));
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless=new");
                tlDriver.set(new EdgeDriver(edgeOptions));
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--use-fake-ui-for-media-stream"); // enable fake camera for attendance
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                tlDriver.set(new ChromeDriver(chromeOptions));
            }
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getTimeout()));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
