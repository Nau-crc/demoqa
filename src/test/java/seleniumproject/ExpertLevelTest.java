package seleniumproject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
// import org.junit.jupiter.api.ParameterizedTest;
// import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExpertLevelTest {
    private WebDriver driver;
    JavascriptExecutor js;
    private boolean useChrome = false; // Cambia a 'true' si quieres usar Chrome

    @BeforeEach
    public void setUp() {
        if (useChrome) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        js = (JavascriptExecutor) driver;
    }
    // @ParameterizedTest
    // @ValueSource(strings = {"chrome", "firefox"})
    // public void testInDifferentBrowsers(String browser) {
    //     if (browser.equals("chrome")) {
    //         WebDriverManager.chromedriver().setup();
    //         driver = new ChromeDriver();
    //     } else if (browser.equals("firefox")) {
    //         WebDriverManager.firefoxdriver().setup();
    //         driver = new FirefoxDriver();
    //     }

    //     tearDown();
    // }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void deleteUserFromDinamicTable() {
        driver.get("https://demoqa.com/webtables");
        driver.findElement(By.id("delete-record-1")).click();
        boolean isUserDeleted = driver.findElements(By.xpath("//div[text()='Cierra']")).isEmpty();
        assertEquals(true, isUserDeleted);
    }
}
