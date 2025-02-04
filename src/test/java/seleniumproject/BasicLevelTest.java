package seleniumproject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Cookie;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertEquals;


public class BasicLevelTest {


private WebDriver driver;

@Before
public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
}

@After
public void tearDown() {
        driver.quit();
}

@Test
public void basicLevelTest() {
    driver.get("https://www.google.com");
    driver.findElement(By.id("L2AGLb")).click();
    assertEquals("Google", driver.getTitle());
}

@Test
public void searchBoxTest(){
    driver.get("https://www.google.com");
    driver.findElement(By.id("L2AGLb")).click();
    driver.findElement(By.id("APjFqb")).click();
    driver.findElement(By.id("APjFqb")).sendKeys("hello world");
    driver.findElement(By.id("APjFqb")).submit();
}

@Test
public void selectDropdownTest() {
    driver.get("https://the-internet.herokuapp.com/dropdown");
    WebElement dropdown = driver.findElement(By.id("dropdown"));
    dropdown.click();
    dropdown.findElement(By.xpath("//option[. = 'Option 1']")).click();
}

}