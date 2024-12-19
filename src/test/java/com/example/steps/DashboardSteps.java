package com.example.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.cucumber.java.en.*;

public class DashboardSteps {
    WebDriver driver;

    @Given("I am on the OrangeHRM dashboard page")
    public void iAmOnTheDashboardPage() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Documents\\chrome-win64\\chrome-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
    }

    @Then("I should see the {string} widget")
    public void iShouldSeeTheWidget(String widgetName) {
        WebElement widget = driver.findElement(By.xpath("//h1[text()='" + widgetName + "']"));
        Assert.assertTrue(widget.isDisplayed());
    }

    @When("I click on {string} in the {string} widget")
    public void iClickOnInWidget(String linkText, String widgetName) {
        WebElement link = driver.findElement(By.xpath("//a[text()='" + linkText + "']"));
        link.click();
    }

    @Then("I should navigate to the {string} page")
    public void iShouldNavigateToThePage(String pageName) {
        Assert.assertTrue(driver.getTitle().contains(pageName));
        driver.quit();
    }
}
