import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("Setting up the WebDriver...");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        System.out.println("Navigating to the application...");
        driver.get("https://opensource-demo.orangehrmlive.com");
        System.out.println("Application loaded successfully.");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Tearing down the WebDriver...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("WebDriver closed.");
    }
}
