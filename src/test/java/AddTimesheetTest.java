import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddTimesheetTest extends BaseTest{
    @Test
    public void testAddTimesheet(){
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

        // Fill employee name and click "View" button
        try {
            System.out.println("Filling employee name...");
            WebElement employeeNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/form/div[1]/div/div/div/div[2]/div/div/input")));
            employeeNameField.sendKeys("John");
            System.out.println("Employee name filled...");

        } catch (Exception e) {
            System.err.println("Failed to fill the name: " + e.getMessage());
            Assert.fail("Filling the name failed...");
        }

        // Select the first option of employee drop down list
        try {
            System.out.println("Selecting the first option...");
            WebElement employeeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='John  Kaden']")));
            employeeOption.click();
            System.out.println("Employee option selected...");

        } catch (Exception e) {
            System.out.println("Failed to select the first option: " + e.getMessage());
            Assert.fail("Selecting first option failed...");
        }

        // Click "View" button
        try {
            System.out.println("Clicking the 'View' Button...");
            WebElement viewButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/form/div[2]/button")));
            viewButton.click();
            System.out.println("Timesheets loaded...");

        } catch (Exception e) {
            System.err.println("Failed to load the Timesheet: " + e.getMessage());
            Assert.fail("Loading Timesheet failed...");
        }

        // Click 'Create Timesheet' button
        try {
            System.out.println("Clicking the 'Create Timesheet' Button...");
            WebElement createTimesheetButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/button")));
            createTimesheetButton.click();
            System.out.println("Create Timesheet loaded...");
        } catch (Exception e) {
            System.out.println("Failed to load Create Timesheet: " + e.getMessage());
            Assert.fail("Loading Create Timesheet failed...");
        }

        // Click 'Submit' button
        try {
            System.out.println("Clicking the 'Submit' Button...");
            WebElement createTimesheetButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/button[2]")));
            createTimesheetButton.click();
            System.out.println("Timesheet submitted...");
        } catch (Exception e) {
            System.out.println("Failed to submit Timesheet: " + e.getMessage());
            Assert.fail("Submitting Timesheet failed...");
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
