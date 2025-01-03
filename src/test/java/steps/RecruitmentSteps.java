package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class RecruitmentSteps {

    WebDriver driver;

    @Given("the user is on the OrangeHRM recruitment page")
    public void user_is_on_recruitment_page() {
        // Setup WebDriver (Chrome, Firefox, etc.)
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates");

        // Login if necessary
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Username"))).sendKeys("Admin");
        driver.findElement(By.id("Password")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
    }

    @When("the user searches for a candidate with name {string}")
    public void user_searches_for_candidate(String candidateName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Locate the search input and search button
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("candidateSearch_candidateName")));
        searchBox.sendKeys(candidateName);

        // Click on the search button
        driver.findElement(By.id("searchBtn")).click();
    }

    @Then("the candidate details should be displayed")
    public void candidate_details_displayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Verify the candidate is listed in the search results (or adjust the locator as needed)
        WebElement candidateNameInList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//td[contains(text(), 'John Doe')]")));

        // Assert candidate is found in the search results
        assertTrue(candidateNameInList.isDisplayed(), "Candidate not found in the list!");

        // Close the browser after test completion
        driver.quit();
    }
}
