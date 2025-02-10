package seleniumproject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExpertLevelTest {
    private WebDriver driver;
    JavascriptExecutor js;
    boolean useChrome = true; // Cambia a 'true' si quieres usar Chrome
    

    @BeforeEach
    public void setUp() {
      
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "firefox"})
    void multiBrowser(String browser) {
        WebDriverManager.getInstance(browser.equals("chrome") ? ChromeDriver.class : FirefoxDriver.class).setup();
        driver = browser.equals("chrome") ? new ChromeDriver() : new FirefoxDriver();
        
        driver.get("https://www.selenium.dev/");
        driver.findElement(By.id("Layer_1")).isDisplayed();
    }

    @Test
    public void deleteUserFromDinamicTable() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Initialize the driver
        driver.get("https://demoqa.com/webtables");
        driver.findElement(By.id("delete-record-1")).click();
        boolean isUserDeleted = driver.findElements(By.xpath("//div[text()='Cierra']")).isEmpty();
        assertEquals(true, isUserDeleted);
    }
  

}