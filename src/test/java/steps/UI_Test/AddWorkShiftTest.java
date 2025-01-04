package steps.UI_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddWorkShiftTest extends BaseTest {

    @Test
    public void testAddWorkShift() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("Starting test: testAddWorkShift");

        // Step 1: Login to the application
        login("Admin", "admin123");

        // Step 2: Navigate to Admin > Job > Work Shifts
        try {
            System.out.println("Navigating to Admin > Job > Work Shifts...");

            // Click on the Admin menu
            WebElement adminModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']")));
            adminModule.click();

            // Click on the Job menu to open the dropdown
            WebElement jobMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='app']/div[1]/div[1]/header/div[2]/nav/ul/li[2]")));
            jobMenu.click();

            //  dropdown arrow
            WebElement dropDownArrow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='app']/div[1]/div[1]/header/div[2]/nav/ul/li[2]/span/i")));
            dropDownArrow.click();

            // Click on Work Shifts link from the dropdown
            WebElement workShiftLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='app']/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[5]/a")));
            workShiftLink.click();

            System.out.println("Navigated to Work Shifts.");
        } catch (Exception e) {
            System.err.println("Failed to navigate to Work Shifts: " + e.getMessage());
            Assert.fail("Navigation to Work Shifts failed.");
        }


        // Step 3: Click on Add button
        try {
            System.out.println("Clicking on Add button...");
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Add')]")));
            addButton.click();
            System.out.println("Navigated to Add Work Shift page.");
        } catch (Exception e) {
            System.err.println("Failed to click Add button: " + e.getMessage());
            Assert.fail("Clicking Add button failed.");
        }

        // Step 5: Fill in work shift details
        try {
            System.out.println("Filling in work shift details...");

            // Enter Work Shift Name
            WebElement workShiftNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/input")));
            workShiftNameField.sendKeys("Morning Shift");
            System.out.println("Work Shift Name entered.");

            // Enter Work Shift Start Time (Input field)
            try {
                System.out.println("Entering 'Start Time' in the input field...");
                WebElement startTimeInputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/div/div/input")));
                startTimeInputField.clear();
                startTimeInputField.sendKeys("08:00 AM");

                System.out.println("Start Time entered successfully.");
            } catch (Exception e) {
                System.err.println("Failed to enter Start Time: " + e.getMessage());
                Assert.fail("Entering Start Time failed.");
            }

            // Enter Work Shift End Time (Input field)
            try {
                System.out.println("Entering 'End Time' in the input field...");
                WebElement endTimeInputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/div/div/input")));
                endTimeInputField.clear();
                endTimeInputField.sendKeys("04:00 PM");

                System.out.println("End Time entered successfully.");
            } catch (Exception e) {
                System.err.println("Failed to enter End Time: " + e.getMessage());
                Assert.fail("Entering End Time failed.");
            }

            // Enter Assigned Employees
            WebElement assignedEmployeesField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[1]/input")));
            assignedEmployeesField.sendKeys("John Doe");
            System.out.println("Assigned Employee 'John Doe' entered.");

            // Step 6: Save the Work Shift
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            saveButton.click();
            System.out.println("Work Shift details saved.");
        } catch (Exception e) {
            System.err.println("Error while filling in work shift details: " + e.getMessage());
            Assert.fail("Filling work shift details failed.");
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
