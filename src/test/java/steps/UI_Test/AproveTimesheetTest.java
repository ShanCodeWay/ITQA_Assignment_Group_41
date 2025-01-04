package steps.UI_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AproveTimesheetTest extends BaseTest {
    @Test
    public void testAproveTimesheet(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("Starting test: Add Time Entry");

        // Login to the application
        login("Admin", "admin123");

        // Navigate to Time module
        try {
            System.out.println("Navigating to Time module...");
            WebElement timeMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[4]/a")));
            timeMenu.click();
            System.out.println("Time module loaded...");
        } catch (Exception e) {
            System.err.println("Failed to navigate to Time module: " + e.getMessage());
            Assert.fail("Navigation to Time module failed...");
        }

        // Click Timesheet Pending Action "View" button
        try {
            System.out.println("Loading pending timesheet...");
            WebElement timeMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[1]/div/div[3]/div/button")));
            timeMenu.click();
            System.out.println("Pending timesheet loaded...");
        } catch (Exception e) {
            System.err.println("Failed to load pending timesheet: " + e.getMessage());
            Assert.fail("Loading pending timesheet failed...");
        }

        // Click Pending Timesheet "Approve" button
        try {
            System.out.println("Clicking Approve timesheet...");
            WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/form/div[2]/button[2]")));
            approveButton.click();
            System.out.println("Timesheet approved...");
        } catch (Exception e) {
            System.err.println("Failed to aprove timesheet: " + e.getMessage());
            Assert.fail("Approving timesheet failed...");
        }
    }

    private void login(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Enter username
        System.out.println("Entering username...");
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));
        usernameField.sendKeys(username);

        // Enter password
        System.out.println("Entering password...");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordField.sendKeys(password);

        // Click Login
        System.out.println("Clicking login button...");
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        System.out.println("Login successful.");
    }
}
