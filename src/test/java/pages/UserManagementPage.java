package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class AdminPage {
    WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToUserManagement() {
        driver.findElement(By.id("menu_admin_UserManagement")).click();
    }

    public void addNewUser(String username, String role, String password) {
        driver.findElement(By.id("btnAdd")).click();
        driver.findElement(By.id("systemUser_userName")).sendKeys(username);
        driver.findElement(By.id("systemUser_userType")).sendKeys(role);
        driver.findElement(By.id("systemUser_password")).sendKeys(password);
        driver.findElement(By.id("btnSave")).click();
    }
}
