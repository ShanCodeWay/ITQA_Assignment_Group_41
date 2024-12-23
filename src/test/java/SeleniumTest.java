import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;

public class SeleniumTest {

    @Test
    void Setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins");

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);

        // Step 1: Open the website
        driver.get("https://opensource-demo.orangehrmlive.com");

        // Wait for the username field to be visible before interacting with it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time to 20 seconds

        // Wait for the username field inside the auth-login component
        try {
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));
            usernameField.sendKeys("Admin");
        } catch (Exception e) {
            System.out.println("Username field not found: " + e.getMessage());
        }

        // Step 2: Locate and enter the password
        try {
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Password']")));
            passwordField.sendKeys("admin123");
        } catch (Exception e) {
            System.out.println("Password field not found: " + e.getMessage());
        }

        // Step 3: Locate and click the login button
        try {
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            loginButton.click();
        } catch (Exception e) {
            System.out.println("Login button not found: " + e.getMessage());
        }

        // Step 4: Wait for the page to load (optional, can be done with explicit wait)
        wait.until(ExpectedConditions.urlContains("dashboard"));

        // Step 5: Now that the user is logged in, navigate to the apply leave page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/leave/applyLeave");


    }
}
