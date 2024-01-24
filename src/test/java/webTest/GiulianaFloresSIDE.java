package webTest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class GiulianaFloresSIDE{
    private WebDriver driver;
    //private Map<String, Object> vars;
    //JavascriptExecutor js;
    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Aponta onde está o Chrome Driver
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/chromedriver.exe");

        driver = new ChromeDriver(options);
        //js = (JavascriptExecutor) driver;
        //vars = new HashMap<String, Object>();


    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void giulianaFlores() throws InterruptedException {
        driver.get("https://www.giulianaflores.com.br/");
        driver.manage().window().setSize(new Dimension(1536, 816));
        driver.findElement(By.cssSelector(".item-2 .link-menu-desktop")).click();
        Thread.sleep(2000); // Pausa de 2 segundos
        {
            WebElement element = driver.findElement(By.cssSelector(".item-2 .link-menu-desktop"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".close-button")).click();
        Thread.sleep(2000); // Pausa de 2 segundos
        driver.findElement(By.cssSelector(".owl-carousel:nth-child(1) .owl-item:nth-child(2) img")).click();
        Thread.sleep(2000); // Pausa de 2 segundos
        driver.findElement(By.cssSelector(".close-button")).click();
        Thread.sleep(2000); // Pausa de 2 segundos
        driver.findElement(By.cssSelector(".item:nth-child(3) img")).click();
        Thread.sleep(2000); // Pausa de 2 segundos
        assertThat(driver.findElement(By.id("ContentSite_lblProductDsName")).getText(), is("CESTA DOCES E CHOCOLATES COM BALÃO"));
        assertThat(driver.findElement(By.cssSelector(".preco_prod > .precoPor_prod")).getText(), is("R$ 156,90"));
    }
}

