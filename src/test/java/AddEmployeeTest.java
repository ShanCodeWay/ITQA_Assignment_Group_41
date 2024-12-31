import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddEmployeeTest extends BaseTest {

    @Test
    public void testAddEmployee() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("Starting test: Add Employee");

        // Login to the application
        login("Admin", "admin123");

        // Navigate to PIM module and click "Add" button
        try {
            System.out.println("Navigating to PIM module...");
            WebElement pimMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")));
            pimMenu.click();
            System.out.println("PIM module loaded.");

            System.out.println("Clicking the 'Add' button...");
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Add')]")));
            addButton.click();
            System.out.println("Navigated to Add Employee page.");
        } catch (Exception e) {
            System.err.println("Failed to navigate to Add Employee page: " + e.getMessage());
            Assert.fail("Navigation to Add Employee page failed.");
        }

        // Fill in employee details
        try {
            System.out.println("Filling employee details...");
            WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']")));
            firstNameField.sendKeys("John");

            WebElement middleNameField = driver.findElement(By.xpath("//input[@name='middleName']"));
            middleNameField.sendKeys("Michael");

            WebElement lastNameField = driver.findElement(By.xpath("//input[@name='lastName']"));
            lastNameField.sendKeys("Doe");


            WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
            saveButton.click();
            System.out.println("Employee details saved.");
        } catch (Exception e) {
            System.err.println("Filling employee details failed: " + e.getMessage());
            Assert.fail("Failed to fill employee details.");
        }

        // Verify if the employee was added successfully
        try {
            System.out.println("Verifying employee addition...");
            String expectedUrlPrefix = "/pim/viewPersonalDetails/";
            WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Wait for the URL to contain the expected prefix
            boolean isRedirected = waitt.until(ExpectedConditions.urlContains(expectedUrlPrefix));
            if (isRedirected) {
                String currentUrl = driver.getCurrentUrl();
                System.out.println("Current URL: " + currentUrl);

                // Extract the part after '/pim/viewPersonalDetails/empNumber/' to get the employee number
                String[] urlParts = currentUrl.split("/pim/viewPersonalDetails/empNumber/");
                if (urlParts.length > 1) {
                    String dynamicPart = urlParts[1].split("/")[0];  // Get the part after /pim/viewPersonalDetails/empNumber/
                    System.out.println("Extracted Dynamic Part (Employee Number): " + dynamicPart);

                    // Check if it's a valid employee number (assuming it's numeric)
                    Assert.assertTrue(dynamicPart.matches("\\d+"), "URL does not contain a valid employee number.");
                    System.out.println("Employee addition verified successfully. Employee Number: " + dynamicPart);
                } else {
                    Assert.fail("The URL does not contain the expected employee number.");
                }
            } else {
                Assert.fail("Employee was not added successfully. URL does not contain the expected prefix.");
            }
        } catch (Exception e) {
            System.err.println("Employee verification failed: " + e.getMessage());
            Assert.fail("Failed to verify employee addition.");
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

