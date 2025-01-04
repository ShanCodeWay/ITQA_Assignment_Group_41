package steps.UI_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ValidateLeaveStatusTest extends BaseTest {

    @Test
    public void testValidateLeaveStatus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        System.out.println("Starting test: Validate Leave Status");

        // Login to the application
        login("Admin", "admin123");

        // Navigate to Leave List module
        try {
            System.out.println("Navigating to Leave module...");
            WebElement leaveMenu = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[3]/a")));
            leaveMenu.click();

            System.out.println("Navigating to Leave List tab...");
            WebElement leaveListTab = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/a")));
            leaveListTab.click();
        } catch (Exception e) {
            System.err.println("Failed to navigate to Leave List: " + e.getMessage());
            Assert.fail("Navigation to Leave List failed.");
        }

        // Setting values to match
        String startDate = "2025-07-01";
        String endDate = "2025-07-01";
        String leaveType = "CAN - FMLA";
        String expectedStatus = "Pending Approval (1.00)";

        // Calculate the number of leave days dynamically
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate start = LocalDate.parse(startDate, formatter);
//        LocalDate end = LocalDate.parse(endDate, formatter);
//        long leaveDays = ChronoUnit.DAYS.between(start, end) + 1; // +1 to include the start day
//        String expectedStatus = "Pending Approval (" + leaveDays + ".00)";

        // Input leave filters and search
        try {
            System.out.println("Entering start date...");
            WebElement startDateInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@placeholder='yyyy-dd-mm']")));
            startDateInput.sendKeys(Keys.CONTROL + "a");
            startDateInput.sendKeys(Keys.DELETE);
            startDateInput.sendKeys(startDate);
            startDateInput.sendKeys(Keys.TAB);

            System.out.println("Entering end date...");
            WebElement endDateInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@placeholder='yyyy-dd-mm']")));
            endDateInput.sendKeys(Keys.CONTROL + "a");
            endDateInput.sendKeys(Keys.DELETE);
            endDateInput.sendKeys(startDate);
            endDateInput.sendKeys(Keys.TAB);

            System.out.println("Selecting leave type...");
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div")));
            dropdown.click();
            WebElement leaveOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[text()='" + leaveType + "']")));
            leaveOption.click();

            System.out.println("Submitting search...");
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")));
            searchButton.click();
        } catch (Exception e) {
            System.err.println("Failed to input leave filters: " + e.getMessage());
            Assert.fail("Leave filter input failed.");
        }

        // Validate search results
        try {
            System.out.println("Validating search results...");
            WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@class='oxd-table-body']")));

            List<WebElement> rows = tableBody.findElements(By.xpath(".//*[@class='oxd-table-card']"));
            String expectedDateRange = startDate.equals(endDate) ? startDate : startDate + " to " + endDate;

            boolean recordFound = false;
            for (WebElement row : rows) {
                String dateRange = row.findElement(By.xpath(".//div[2]/div")).getText();
                String leaveTypeText = row.findElement(By.xpath(".//div[4]/div")).getText();
                String status = row.findElement(By.xpath(".//div[7]/div")).getText();

                if (dateRange.equals(expectedDateRange) && leaveTypeText.equals(leaveType)) {
                    recordFound = true;
                    Assert.assertEquals(status, expectedStatus, "Status does not match expected.");
                    System.out.println("Record found with matching details: " + dateRange + ", " + leaveTypeText + ", " + status);
                    break;
                }
            }

            Assert.assertTrue(recordFound, "No matching record found.");
        } catch (Exception e) {
            System.err.println("Validation failed: " + e.getMessage());
            Assert.fail("Search results validation failed.");
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
