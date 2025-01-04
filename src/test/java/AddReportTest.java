import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddReportTest extends BaseTest {

    @Test
    public void testAddReportNavigation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("Starting test: testAddReportNavigation");

        // Step 1: Login to the application
        login("Admin", "admin123");

        // Step 2: Navigate to Employee List
        try {
            System.out.println("Navigating to Employee List...");
            WebElement pimModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")));
            pimModule.click();
            System.out.println("Navigated to Employee List.");
        } catch (Exception e) {
            System.err.println("Failed to navigate to Employee List: " + e.getMessage());
            Assert.fail("Navigation to Employee List failed.");
        }

        // Step 3: Click on Reports button
        try {
            System.out.println("Clicking on Reports button...");
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Reports')]")));
            addButton.click();
            System.out.println("Navigated to Add report page.");
            System.out.println("Reports page loaded.");
        } catch (Exception e) {
            System.err.println("Failed to navigate to Reports page: " + e.getMessage());
            Assert.fail("Navigation to Reports page failed.");
        }

        // Step 4: Click on Add (+) button
        try {
            System.out.println("Clicking on Add button...");
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Add')]")));
            addButton.click();
            System.out.println("Navigated to Add Report page.");
        } catch (Exception e) {
            System.err.println("Failed to click Add button: " + e.getMessage());
            Assert.fail("Clicking Add button failed.");
        }

        // Step 5: Validate navigation to Add Report page
        try {
            System.out.println("Validating Add Report page navigation...");
            wait.until(ExpectedConditions.urlContains("pim/definePredefinedReport"));
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("pim/definePredefinedReport"), "Navigation to Add Report page failed!");
            System.out.println("Successfully navigated to Add Report page.");
        } catch (Exception e) {
            System.err.println("Add Report page navigation validation failed: " + e.getMessage());
            Assert.fail("Validation for Add Report page failed.");
        }

        // Fill in the report details
        try {
            System.out.println("Filling in the report details...");

            // Enter Report Name
            // Find the input field for "Report Name" with the placeholder text "Type Here ..."
            WebElement reportNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type here ...' or @name='reportName']")));
            reportNameField.sendKeys("Monthly Attendance Report 1");

            // Step: Select "Current Employees Only" in the "Include" dropdown
            try {
                System.out.println("Selecting 'Employee Name' in Selection Criteria dropdown...");

                // Click the dropdown to open the options
                WebElement dropdownWrapper = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div[1]/div[2]/div/div/div[1]")
                ));
                dropdownWrapper.click(); // Click to open the dropdown
                System.out.println("Dropdown opened successfully.");

                // Select the option with tabindex="0"
                WebElement desiredOption = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[normalize-space()='Education']")
                ));
                desiredOption.click();

                System.out.println("Option selected successfully.");
            } catch (Exception e) {
                System.err.println("Failed to select option with tabindex=0: " + e.getMessage());
                Assert.fail("Selecting option with tabindex=0 failed.");
            }

            try {
                System.out.println("Interacting with the dropdown to select the required option...");

                // Step 1: Locate the outer div class containing the dropdown
                WebElement dropdownWrapper = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/div/div/div[1]")
                ));
                dropdownWrapper.click(); // Click to open the dropdown
                System.out.println("Dropdown opened successfully.");

                // Step 2: Select the desired option (e.g., tabindex=1 for example)
                WebElement desiredOption = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[normalize-space()='Personal']")
                ));
                desiredOption.click();
                System.out.println("Option selected successfully.");

                // Step 3: Click the button after selecting the option
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div[2]/div[2]/button/i")
                ));
                button.click();
                System.out.println("Button clicked successfully.");

                // Step 4: Locate and interact with the checkbox
                WebElement checkboxWrapper = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[5]/div/label/span")
                ));
                checkboxWrapper.click();
            } catch (Exception e) {
                System.err.println("Failed to interact with the dropdown, button, or checkbox: " + e.getMessage());
                Assert.fail("Interaction with the dropdown, button, or checkbox failed.");
            }

            // Click Save button
            WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
            saveButton.click();
            System.out.println("Report details saved.");
        } catch (Exception e) {
            System.err.println("Failed to fill in report details: " + e.getMessage());
            Assert.fail("Filling in report details failed.");
        }

        // Verify if the report was added successfully
        try {
            System.out.println("Verifying report addition...");
            String expectedUrlPrefix = "/pim/displayPredefinedReport/";
            WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Wait for the URL to contain the expected prefix
            boolean isRedirected = waitt.until(ExpectedConditions.urlContains(expectedUrlPrefix));
            if (isRedirected) {
                String currentUrl = driver.getCurrentUrl();
                System.out.println("Current URL: " + currentUrl);

                // Extract the part after '/pim/displayPredefinedReport/' to get the report number
                String[] urlParts = currentUrl.split("/pim/displayPredefinedReport/");
                if (urlParts.length > 1) {
                    String dynamicPart = urlParts[1].split("/")[0];  // Get the part after /pim/displayPredefinedReport/
                    System.out.println("Extracted Dynamic Part: " + dynamicPart);

                    // Check if it's a valid report number (assuming it's numeric)
                    Assert.assertTrue(dynamicPart.matches("\\d+"), "URL does not contain a valid report number.");
                    System.out.println("Report addition verified successfully. Report Number: " + dynamicPart);
                } else {
                    Assert.fail("The URL does not contain the expected report number.");
                }
            } else {
                Assert.fail("Report was not added successfully. URL does not contain the expected prefix.");
            }
        } catch (Exception e) {
            System.err.println("Report verification failed: " + e.getMessage());
            Assert.fail("Failed to verify report addition.");
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

