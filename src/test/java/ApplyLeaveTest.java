
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ApplyLeaveTest extends BaseTest {

    @Test
    public void testApplyLeave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        System.out.println("Starting test: Apply Leave");

        // Login to the application
        login("Admin", "admin123");

        // Navigate to Leave module
        try {
            System.out.println("Navigating to Leave module...");
            WebElement leaveMenu = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[3]/a")));
            leaveMenu.click();
            System.out.println("Leave module loaded.");
        } catch (Exception e) {
            System.err.println("Failed to navigate to Leave module: " + e.getMessage());
            Assert.fail("Navigation to Leave module failed.");
        }

        // Click Apply Leave tab
        try {
            System.out.println("Clicking Apply Leave tab...");
            WebElement applyLeaveTab = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/a")));
            applyLeaveTab.click();
            System.out.println("Apply Leave tab loaded.");
        } catch (Exception e) {
            System.err.println("Failed to navigate to Apply Leave tab: " + e.getMessage());
            Assert.fail("Navigation to Apply Leave tab failed.");
        }

        // Select leave type
        try {
            System.out.println("Selecting leave type...");
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div")));
            dropdown.click();

            WebElement leaveOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[text()='CAN - FMLA']")));
            leaveOption.click();
            System.out.println("Leave type selected.");
        } catch (Exception e) {
            System.err.println("Failed to select leave type: " + e.getMessage());
            Assert.fail("Leave type selection failed.");
        }

        // Enter leave dates
        try {
            System.out.println("Entering leave date...");
            WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@placeholder='yyyy-dd-mm']")));
            dateInput.clear();
            dateInput.sendKeys("2025-10-01");
            dateInput.sendKeys(Keys.TAB); // Close date picker
            System.out.println("Leave date entered.");
        } catch (Exception e) {
            System.err.println("Failed to enter leave date: " + e.getMessage());
            Assert.fail("Leave date entry failed.");
        }

        // Enter comments and submit
        try {
            System.out.println("Entering leave comments...");
            WebElement commentBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div/div/div[2]/textarea")));
            commentBox.sendKeys("Testing leave apply");

            System.out.println("Submitting leave application...");
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/button")));
            submitButton.click();
            System.out.println("Leave application submitted.");
        } catch (Exception e) {
            System.err.println("Failed to submit leave application: " + e.getMessage());
            Assert.fail("Leave application submission failed.");
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
