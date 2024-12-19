package com.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // Path to the feature files
        glue = "com.example.stepDefinitions", // Path to step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html"}, // Report generation
        monochrome = true // For readable console output
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
