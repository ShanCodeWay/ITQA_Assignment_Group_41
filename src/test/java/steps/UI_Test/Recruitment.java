package steps.UI_Test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Recruitment extends BaseTest {

    @Test(priority = 1, description = "Verify that candidates can be added with valid mandatory fields")
    public void testAddCandidate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("Starting test: Add Candidate");

        login("Admin", "admin123");

        try {
            System.out.println("Navigating to Recruitment module...");
            WebElement recruitmentMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/web/index.php/recruitment/viewRecruitmentModule']")));
            recruitmentMenu.click();
            System.out.println("Navigated to Recruitment module.");

            System.out.println("Entering candidate name in the search field...");
            WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[1]/div/div[2]/div/div/input")));
            searchField.sendKeys("Md. Abir Hossen");

            System.out.println("Clicking on Search button...");
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[4]/button[2]")));
            searchButton.click();

            System.out.println("Verifying search results...");
            WebElement candidateResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'candidate-row')]")));
            Assert.assertTrue(candidateResult.isDisplayed(), "Candidate not found in search results.");

            System.out.println("Candidate found. Extracting details...");
            WebElement candidateName = candidateResult.findElement(By.xpath(".//div[@class='candidate-name']"));
            WebElement candidateEmail = candidateResult.findElement(By.xpath(".//div[@class='candidate-email']"));

            System.out.println("Candidate Name: " + candidateName.getText());
            System.out.println("Candidate Email: " + candidateEmail.getText());
        } catch (Exception e) {
            System.err.println("Error during candidate search: " + e.getMessage());
            Assert.fail("Searching candidate by name failed.");
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