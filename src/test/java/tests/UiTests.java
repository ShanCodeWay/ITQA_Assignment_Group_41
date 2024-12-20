package tests;

//import org.example.pages.AdminPage;    library
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UiTests {
    WebDriver driver;
    AdminPage adminPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        adminPage = new AdminPage(driver);
    }

    @Test
    public void testAddNewUser() {
        adminPage.navigateToUserManagement();
        adminPage.addNewUser("testUser", "Admin", "password123");
        // Assertions can be added for validation
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
