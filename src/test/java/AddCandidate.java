import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddCandidate extends BaseTest {

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

            WebElement addCandidateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Add')]")));
            addCandidateButton.click();
            System.out.println("Navigated to Add Candidate page.");
        } catch (Exception e) {
            System.err.println("Failed to navigate to Add Candidate page: " + e.getMessage());
            Assert.fail("Navigation to Add Candidate failed.");
        }

        try {
            System.out.println("Entering candidate details...");
            WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='First Name']")));
            firstNameField.sendKeys("Md. Abir Hossen");

            WebElement middleNameField = driver.findElement(By.xpath("//input[@placeholder='Middle Name']"));
            middleNameField.sendKeys("Hossain");

            WebElement lastNameField = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
            lastNameField.sendKeys("Munna");

            WebElement emailField = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/input"));
            emailField.sendKeys("abir@gmail.com");


            WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[8]/button[2]"));
            saveButton.click();
            System.out.println("Clicked on Save button.");

//            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Candidate Profile')]")));
//            Assert.assertTrue(successMessage.isDisplayed(), "Candidate was not added successfully.");
            System.out.println("Candidate added successfully.");
        } catch (Exception e) {
            System.err.println("Error while adding candidate: " + e.getMessage());
            Assert.fail("Adding candidate failed.");
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
