package steps.UI_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.nio.file.Paths;

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

            // Locate and interact with the checkbox
            WebElement checkboxWrapper = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div"));
            checkboxWrapper.click();

            try {
                WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(20));

                // Wait for the additional fields to become visible
                System.out.println("Waiting for additional fields to appear...");
                WebElement usernameField = waitt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/div[2]/input")));
                System.out.println("Additional fields are visible.");

                // Enter text in the Username textbox
                System.out.println("Entering username...");
                usernameField.sendKeys("test_user2");
                System.out.println("Username entered successfully.");

                // Select "Enabled" or "Disabled" radio button
                System.out.println("Selecting the 'Enabled' radio button...");
                WebElement enabledRadioButton = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/label/span"));
                enabledRadioButton.click();
                System.out.println("'Enabled' radio button selected successfully.");

                // Enter text in Password and Confirm Password textboxes
                System.out.println("Entering password...");
                WebElement passwordField = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input"));
                passwordField.sendKeys("SecurePassword1232");
                System.out.println("Password entered successfully.");

                System.out.println("Confirming password...");
                WebElement confirmPasswordField = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input"));
                confirmPasswordField.sendKeys("SecurePassword1232");
                System.out.println("Confirm password entered successfully.");

            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
                Assert.fail("Test failed due to an exception.");
            }


            try {
                // Locate the file input element (usually of type "file")
                WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));

                // Provide the absolute path to the image file
                String imagePath = Paths.get("src/test/java/steps/UI_Tests/utils/image.png").toAbsolutePath().toString();
                uploadElement.sendKeys(imagePath);

                System.out.println("Image uploaded successfully.");
            } catch (Exception e) {
                System.err.println("Failed to upload image: " + e.getMessage());
                Assert.fail("Image upload failed.");
            }


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
