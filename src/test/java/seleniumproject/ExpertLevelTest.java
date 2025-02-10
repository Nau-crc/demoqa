package seleniumproject;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
// import org.junit.jupiter.api.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExpertLevelTest {
    private WebDriver driver;
    private JavascriptExecutor js;
    private boolean useChrome = false; // Cambia a 'true' si quieres usar Chrome

    @Before
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
    @ValueSource(strings = {"chrome", "firefox"})
    public void testInDifferentBrowsers(String browser) {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
    }

    @After
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
