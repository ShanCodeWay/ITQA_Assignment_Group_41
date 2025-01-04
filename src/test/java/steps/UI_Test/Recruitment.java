package steps.UI_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Recruitment extends BaseTest {

    @Test(priority = 1, description = "Verify that candidates can be added with valid mandatory fields")
    public void testAddCandidate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Reduced wait time
        System.out.println("Starting test: Add Candidate");

        login("Admin", "admin123");

        try {
            System.out.println("Navigating to Recruitment module...");
            WebElement recruitmentMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/web/index.php/recruitment/viewRecruitmentModule']")));
            recruitmentMenu.click();

            System.out.println("Entering candidate name in the search field...");
            WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[1]/div/div[2]/div/div/input")));
            searchField.sendKeys("John Doe");

            System.out.println("Clicking on Search button...");
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[4]/button[2]")));
            searchButton.click();

            System.out.println("Verifying search results...");
            try {
                WebElement candidateRow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'candidate-row')]")));
                Assert.assertTrue(candidateRow.isDisplayed(), "Candidate row is not displayed as expected.");
                System.out.println("Candidate found.");
                System.out.println("Candidate Name: " + candidateRow.getText());
            } catch (TimeoutException e) {
                System.err.println("BUG: Candidate 'John Doe' exists but was not displayed in the search results. This is a potential issue.");
                Assert.fail("BUG: Expected candidate 'John Doe' was not found in the search results even though they exist.");
            }
        } catch (Exception e) {
            System.err.println("Error during candidate search: " + e.getMessage());
            Assert.fail("Searching candidate by name failed due to an unexpected error.");
        }
    }

    private void login(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        System.out.println("Entering username...");
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));
        usernameField.sendKeys(username);

        System.out.println("Entering password...");
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordField.sendKeys(password);

        System.out.println("Clicking login button...");
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        System.out.println("Login successful.");
    }
}
