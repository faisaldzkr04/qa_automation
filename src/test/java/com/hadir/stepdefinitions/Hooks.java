package com.hadir.stepdefinitions;

import com.hadir.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before("@ui")
    public void setupDriver() {
        DriverFactory.initDriver(null);
    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
        if (DriverFactory.getDriver() != null) {
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed_Screenshot_" + scenario.getName());
            }
            DriverFactory.quitDriver();
        }
    }
}
