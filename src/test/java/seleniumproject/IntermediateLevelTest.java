package seleniumproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class IntermediateLevelTest {

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

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void studentRegistrationForm() {
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.id("firstName")).click();
        driver.findElement(By.id("firstName")).sendKeys("Gabriela");
        driver.findElement(By.id("lastName")).click();
        driver.findElement(By.id("lastName")).sendKeys("Pineiro");
        driver.findElement(By.cssSelector(".custom-radio:nth-child(2) > .custom-control-label")).click();
        driver.findElement(By.id("userNumber")).click();
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
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
  }

  @Test
  public void addItemShoppingCart(){
    // Open the browser
    driver.get("https://www.saucedemo.com/");
    // login 
    driver.findElement(By.id("user-name")).click();
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("secret_sauce");
    driver.findElement(By.id("login-button")).click();
    // Add 2 items to the cart
    driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
    // Go to the cart and check that the items are there
    driver.findElement(By.id("shopping_cart_container")).click();
    boolean producto1 = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).isDisplayed(); 
    boolean producto2 = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']")).isDisplayed();
    assertEquals(producto1, true);
    assertEquals(producto2, true);
}

@Test
public void removeItemFromShoppingCart () {
    addItemShoppingCart();
    driver.findElement(By.id("remove-sauce-labs-bike-light")).click();
    boolean itemRemoved = driver.findElements(By.xpath("//div[text()='Sauce Labs Bike Light']")).isEmpty();
    assertEquals(true, itemRemoved);
}

@Test
public void checkOut() {
    addItemShoppingCart();
    driver.findElement(By.id("checkout")).click();
    driver.findElement(By.id("first-name")).click();
    driver.findElement(By.id("first-name")).sendKeys("Gabriela");
    driver.findElement(By.id("last-name")).click();
    driver.findElement(By.id("last-name")).sendKeys("Pineiro");
    driver.findElement(By.id("postal-code")).click();
    driver.findElement(By.id("postal-code")).sendKeys("12345");
    driver.findElement(By.id("continue")).click();
    boolean item1 = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).isDisplayed();
    boolean item2 = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']")).isDisplayed();
    assertEquals(true, item1);
    assertEquals(true, item2);
    driver.findElement(By.id("finish")).click();
    boolean complete = driver.findElement(By.xpath("//h2[text()='Thank you for your order!']")).isDisplayed();
    assertEquals(true, complete);
}
}