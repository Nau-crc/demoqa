package seleniumproject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.github.bonigarcia.wdm.WebDriverManager;

public class IntermediateLevelTest {

    private WebDriver driver;
    private JavascriptExecutor js;
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
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void studentRegistrationForm() {
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.id("firstName")).sendKeys("Gabriela");
        driver.findElement(By.id("lastName")).sendKeys("Pineiro");
        driver.findElement(By.cssSelector(".custom-radio:nth-child(2) > .custom-control-label")).click();
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");
        js.executeScript("arguments[0].click();", driver.findElement(By.id("submit")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("closeLargeModal")));
    }

    @Test
    public void alerts() {
        driver.get("https://demoqa.com/alerts");
        driver.manage().window().setSize(new Dimension(1472, 842));
        driver.findElement(By.id("alertButton")).click();
        assertEquals("You clicked a button", driver.switchTo().alert().getText());
    }

    @Test
    public void loginTest() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
}
}