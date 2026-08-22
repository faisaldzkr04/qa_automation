package com.hadir.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.hadir.stepdefinitions"},
        tags = "@api",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/api-cucumber-pretty.html",
                "json:target/cucumber-reports/ApiCucumberTestReport.json"
        },
        monochrome = true
)
public class ApiTestRunner extends AbstractTestNGCucumberTests {
}
