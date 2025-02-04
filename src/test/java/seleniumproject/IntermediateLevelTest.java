package seleniumproject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IntermediateLevelTest {

private ChromeDriver driver;
JavascriptExecutor js;

@Before
public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
}

@After
public void tearDown() {
        driver.quit();
}

@Test
public void studentRegistrationForm(){
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
}
