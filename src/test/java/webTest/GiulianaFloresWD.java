// 0 - Pacote
package webTest;
// 1 - Bibliotecas

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 2 - Classe
public class GiulianaFloresWD { // inicio da classe
    // 2.1 - Atributos
    private WebDriver driver; // declaração do objeto Selenium WebDriver

    // 2.2 - Funções e Métodos

    // Antes do Teste
    @BeforeEach
    public void setUp(){ // inicio do Before

        // Aponta onde está o Chrome Driver
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/chromedriver.exe");

        // Declara o gerenciador para baixar o chrome driver
        //WebDriverManager.chromedriver().setup();

        // configuração especifica a partir do Selenium WebDriver 4.8.1
        ChromeOptions options = new ChromeOptions(); // instancia o ChromeOptions

        // adicionou ao ChromeOptions a opção de permitir qualquer origem de
        // acesso remoto
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options); // instancia o Chrome Driver com Options
        driver.manage().window().maximize(); // maximiza a janela do navegador

    } // fim do Before

    // Depois do Teste
    @AfterEach
    public void tearDown(){ // inicio do After
        driver.quit(); // destroi a instancia do Selenium WebDriver

    } // fim do After

    // O teste
    @Test
    public void comprarCestasGFWD() throws InterruptedException { // inicio do comprar cestas
        driver.get("https://www.giulianaflores.com.br/"); // abre o endereço alvo
        // selecionar a lista/combo de cestas
        driver.findElement(By.cssSelector(".item-2 .link-menu-desktop")).click();

        driver.findElement(By.cssSelector("div.close-button")).click();
        // Encontrar o elemento da lista de carrossel
        driver.findElement(By.cssSelector(".carousel-brands.carousel-brads-desktop"));
        // Encontrar o elemento da imagem
        driver.findElement(By.cssSelector(".owl-carousel:nth-child(1) .owl-item:nth-child(1) img")).click();
        driver.findElement(By.cssSelector("div.close-button")).click();
        Thread.sleep(2000); // Pausa de 2 segundos

        driver.findElement(By.cssSelector("div.product-item a[href*='p0312'] h3.title-item")).click();
        Thread.sleep(2000); // Pausa de 2 segundos

        assertEquals("CESTA DE CAFÉ DA MANHÃ BOM DIA", driver.findElement(By.id("ContentSite_lblProductDsName")).getText());
        Thread.sleep(2000); // Pausa de 2 segundos
        assertEquals("R$ 738,57", driver.findElement(By.cssSelector("span.precoPor_prod")).getText());
        Thread.sleep(2000); // Pausa de 2 segundos




    } // fim do comprar cestas
} // fim da classe
