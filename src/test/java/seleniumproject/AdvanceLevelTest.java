package seleniumproject;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AdvanceLevelTest {

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

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

@Test
public void addItemShoppingCart(){

    driver.get("https://www.saucedemo.com/");
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.id("password")).sendKeys("secret_sauce");
    driver.findElement(By.id("login-button")).click();
    driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
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
    driver.findElement(By.id("first-name")).sendKeys("Gabriela");
    driver.findElement(By.id("last-name")).sendKeys("Pineiro");
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

 @Test
    public void dynamicProperties() {
        driver.get("https://demoqa.com/dynamic-properties");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("enableAfter")).isEnabled();
        driver.findElement(By.id("enableAfter")).click();
    }

    @Test
    public void dropAndDrag (){
        driver.get("https://demoqa.com/droppable");
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        
        Actions actions = new Actions(driver);
        actions.clickAndHold(drag) 
            .moveToElement(drop)
            .release() 
            .perform();
        assertEquals("Dropped!", drop.getText());
    }
}
