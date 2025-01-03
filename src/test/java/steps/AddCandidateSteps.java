package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddCandidateSteps {

    WebDriver driver = new ChromeDriver();

    @Given("I am logged into the OrangeHRM application")
    public void i_am_logged_into_the_orangehrm_application() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
    }

    @When("I navigate to the Recruitment module")
    public void i_navigate_to_the_recruitment_module() {
        WebElement recruitmentModule = driver.findElement(By.xpath("//b[text()='Recruitment']"));
        recruitmentModule.click();
    }

    @And("I click on the {string} button")
    public void i_click_on_the_button(String buttonName) {
        WebElement addCandidateButton = driver.findElement(By.xpath("//input[@value='Add']"));
        addCandidateButton.click();
    }

    @And("I fill in the candidate details")
    public void i_fill_in_the_candidate_details() {
        driver.findElement(By.id("addCandidate_firstName")).sendKeys("John");
        driver.findElement(By.id("addCandidate_lastName")).sendKeys("Doe");
        driver.findElement(By.id("addCandidate_email")).sendKeys("john.doe@example.com");
        driver.findElement(By.id("addCandidate_contactNo")).sendKeys("1234567890");
    }

    @And("I click on the {string} button")
    public void i_click_on_the_save_button(String buttonName) {
        WebElement saveButton = driver.findElement(By.id("btnSave"));
        saveButton.click();
    }

    @Then("The candidate should be added successfully")
    public void the_candidate_should_be_added_successfully() {
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='message success fadable']"));
        assert successMessage.isDisplayed();
        driver.quit();
    }
}
