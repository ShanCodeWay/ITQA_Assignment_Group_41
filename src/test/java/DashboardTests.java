import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DashboardTests extends BaseTest {

    @Test
    public void validateWidgetNavigation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("Starting test: validateWidgetNavigation");

        // Login to the system
        login("Admin", "admin123");

        // Wait for the dashboard to load
        try {
            System.out.println("Waiting for the dashboard to load...");
            wait.until(ExpectedConditions.urlContains("dashboard"));
            System.out.println("Dashboard loaded.");
        } catch (Exception e) {
            System.err.println("Dashboard did not load: " + e.getMessage());
            Assert.fail("Dashboard load failed.");
        }

        // Click "Apply Leave" widget using the full XPath
        try {
            System.out.println("Clicking on Apply Leave widget...");
            WebElement applyLeave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[4]/button")));
            applyLeave.click();
            System.out.println("Clicked on Apply Leave.");
        } catch (Exception e) {
            System.err.println("Apply Leave widget not clickable: " + e.getMessage());
            Assert.fail("Apply Leave widget interaction failed.");
        }

        // Wait for the Leave page to load and preview the screen
        try {
            System.out.println("Waiting for the Apply Leave page to load...");
            wait.until(ExpectedConditions.urlContains("leave/applyLeave"));
            System.out.println("Apply Leave page loaded.");

            // Adding sleep to preview the Apply Leave screen
            Thread.sleep(5000);  // Sleep for 5 seconds to preview the screen
            System.out.println("Previewing Apply Leave page...");
        } catch (Exception e) {
            System.err.println("Apply Leave page did not load: " + e.getMessage());
            Assert.fail("Apply Leave page navigation failed.");
        }

        // Close the browser
        System.out.println("Test completed, closing the browser.");
        driver.quit();
    }

    @Test
    public void validateWidgetVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("Starting test: validateWidgetVisibility");

        // Login to the system
        login("Admin", "admin123");

        // Wait for the dashboard to load
        try {
            System.out.println("Waiting for the dashboard to load...");
            wait.until(ExpectedConditions.urlContains("dashboard"));
            System.out.println("Dashboard loaded.");
        } catch (Exception e) {
            System.err.println("Dashboard did not load: " + e.getMessage());
            Assert.fail("Dashboard load failed.");
        }

        // Validate visibility of Quick Launch widget
        try {
            System.out.println("Validating visibility of Quick Launch widget...");
            WebElement quickLaunch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[3]/div")));
            Assert.assertTrue(quickLaunch.isDisplayed(), "Quick Launch widget is not visible.");
            System.out.println("Quick Launch widget is visible.");
        } catch (Exception e) {
            System.err.println("Quick Launch widget visibility failed: " + e.getMessage());
            Assert.fail("Quick Launch widget visibility failed.");
        }

        // Validate visibility of Pending Leave Requests widget
        try {
            System.out.println("Validating visibility of Assign Leave Requests widget...");
            WebElement pendingLeaveRequests = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[1]/button")));
            Assert.assertTrue(pendingLeaveRequests.isDisplayed(), "Assign Leave Requests widget is not visible.");
            System.out.println("Assign Leave Requests widget is visible.");
        } catch (Exception e) {
            System.err.println("Assign Leave Requests widget visibility failed: " + e.getMessage());
            Assert.fail("Assign Leave Requests widget visibility failed.");
        }
    }

    @Test
    public void verifyNavigationFromApplyLeaveWidget() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("Starting test: verifyNavigationFromApplyLeaveWidget");

        // Login to the system
        login("Admin", "admin123");

        // Wait for the dashboard to load
        try {
            System.out.println("Waiting for the dashboard to load...");
            wait.until(ExpectedConditions.urlContains("dashboard"));
            System.out.println("Dashboard loaded.");
        } catch (Exception e) {
            System.err.println("Dashboard did not load: " + e.getMessage());
            Assert.fail("Dashboard load failed.");
        }

        // Click on Apply Leave widget
        try {
            System.out.println("Clicking on Apply Leave widget...");
            WebElement applyLeave = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[4]/button")));
            applyLeave.click();
            System.out.println("Clicked on Apply Leave widget.");
        } catch (Exception e) {
            System.err.println("Apply Leave widget interaction failed: " + e.getMessage());
            Assert.fail("Apply Leave widget not found or not clickable.");
        }

        // Validate navigation to the Apply Leave page
        try {
            System.out.println("Validating navigation to the Apply Leave page...");
            wait.until(ExpectedConditions.urlContains("leave/applyLeave"));
            Assert.assertTrue(driver.getCurrentUrl().contains("leave/applyLeave"), "Navigation to Apply Leave page failed!");
            System.out.println("Navigation to Apply Leave page successful.");
        } catch (Exception e) {
            System.err.println("Navigation to Apply Leave page failed: " + e.getMessage());
            Assert.fail("Apply Leave page navigation failed.");
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
        System.out.println("Login button clicked.");
    }
}
