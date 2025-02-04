package seleniumproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {
    public static void main(String[] args) {
        // Configurar WebDriverManager para manejar el driver de Chrome
        WebDriverManager.chromedriver().setup();

        // Crear una instancia del navegador (Chrome en este caso)
        WebDriver driver = new ChromeDriver();

        // Abrir una página web
        driver.get("https://www.google.com");

        // Imprimir el título de la página
        System.out.println("Título de la página: " + driver.getTitle());

        // Cerrar el navegador
        driver.quit();
    }
}
