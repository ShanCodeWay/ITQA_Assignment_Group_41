package steps.UI_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.time.Duration;

public class AddJobTitleTest extends BaseTest {

    @Test
    public void testAddJobTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("Starting test: testAddJobTitle");

        // Login to the application
        login("Admin", "admin123");

        // Navigate to Admin > Job > Job Titles
        try {
            System.out.println("Navigating to Admin > Job > Job Titles...");

            // Click on the Admin menu
            WebElement adminModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']")));
            adminModule.click();

            // Click on the Job menu to open the dropdown
            WebElement jobMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='app']/div[1]/div[1]/header/div[2]/nav/ul/li[2]")));
            jobMenu.click();

            // Dropdown arrow
            WebElement dropDownArrow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='app']/div[1]/div[1]/header/div[2]/nav/ul/li[2]/span/i")));
            dropDownArrow.click();

            // Click on Job Titles link from the dropdown
            WebElement jobTitleLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[1]/a")));
            jobTitleLink.click();
            System.out.println("Navigated to Job Titles.");
        } catch (Exception e) {
            System.err.println("Failed to navigate to Job Titles: " + e.getMessage());
            Assert.fail("Navigation to Job Titles failed.");
        }

        // Click on Add button
        try {
            System.out.println("Clicking on Add button...");
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Add')]")));
            addButton.click();
            System.out.println("Navigated to Add Job Title page.");
        } catch (Exception e) {
            System.err.println("Failed to click Add button: " + e.getMessage());
            Assert.fail("Clicking Add button failed.");
        }

        // Fill in job title details
        try {
            System.out.println("Filling in job title details...");

            // Enter Job Title Name
            WebElement jobTitleNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")));
            jobTitleNameField.sendKeys("Software Engineer");
            System.out.println("Job Title Name entered.");

            // Enter Job Description
            WebElement jobDescriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/textarea")));
            jobDescriptionField.sendKeys("Responsible for software development and maintenance.");
            System.out.println("Job Description entered.");

            //  Upload Job Specification (File upload)
            try {
                WebElement fileUploadField = driver.findElement(By.xpath("//input[@type='file']"));
                
                String imagePath = Paths.get("src/test/java/steps/UI_Test/utils/download.png").toAbsolutePath().toString();
                fileUploadField.sendKeys(imagePath);

                System.out.println("Job specification file uploaded successfully.");
            } catch (Exception e) {
                System.err.println("Failed to upload the job specification file: " + e.getMessage());
                Assert.fail("File upload failed.");
            }

            // Note
            WebElement noteField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div[2]/textarea")));
            noteField.sendKeys(" Software development and maintenance.");
            System.out.println("Note entered.");


            //  Save the Job Title
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            saveButton.click();
            System.out.println("Job Title details saved.");
        } catch (Exception e) {
            System.err.println("Error while filling in job title details: " + e.getMessage());
            Assert.fail("Filling job title details failed.");
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
