package mobileTest;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyDemoApp {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:platformVersion", "9.0");
        capabilities.setCapability("appium:deviceName", "Samsung Galaxy S9 FHD GoogleAPI Emulator");
        capabilities.setCapability("appium:deviceOrientation", "portrait");
        capabilities.setCapability("appium:app", "storage:filename=mda-2.0.0-21.apk");
        capabilities.setCapability("appium:appPackage", "com.saucelabs.mydemoapp.android");
        capabilities.setCapability("appium:appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity");
        capabilities.setCapability("appium:ensureWebviewsHavePages", true);
        capabilities.setCapability("appium:nativeWebScreenshot", true);
        MutableCapabilities sauceOptions = new MutableCapabilities(); // conf Saucelabs
        sauceOptions.setCapability("name", "FTS137 MyDemoApp");
        capabilities.setCapability("sauce:options", sauceOptions);
        capabilities.setCapability("appium:newCommandTimeout", 3600);
        capabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("https://oauth-maza.junior16-44c72:1bf4f94f-34c2-4bf3-9524-65dd3a197ec0@ondemand.us-west-1.saucelabs.com:443/wd/hub");

        driver = new AndroidDriver(remoteUrl, capabilities);
    }

    @Test
    public void testeSelecionarProduto() {
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Na tela inicial (Home), clicar no produto mochila
        WebElement produtoSelecionado1 = (WebElement) driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Sauce Labs Backpack\"]"));
        produtoSelecionado1.click();
        // Verificar o nome do produto
        WebElement nomeProduto1 = (WebElement) driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/productTV"));
        assertEquals ("Sauce Labs Backpack",nomeProduto1.getText());
        // Verificar o preço do produto
        WebElement precoProduto1 = (WebElement) driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/priceTV"));
        assertEquals("$ 29.99", precoProduto1.getText());

        // Arrasta para Cima
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 200, 500));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(new Pause(finger, Duration.ofMillis(600)));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), 200, 100));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(dragNDrop));

        // clica no botão adicionar no carrinho
        WebElement btnAdicionarNoCarrinho = (WebElement) driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Tap to add product to cart\"]"));
        btnAdicionarNoCarrinho.click();
        // clica no carrinho
        WebElement icoCarrinho = (WebElement) driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/cartIV"));
        icoCarrinho.click();

        // clica no botão voltar do celular 2x
        driver.navigate().back();
        driver.navigate().back();

        // Na tela inicial (Home), clicar no produto Camiseta
        WebElement produtoSelecionado2 = (WebElement) driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Sauce Labs Bolt T-Shirt\"]"));
        produtoSelecionado2.click();
        // Verificar o nome do produto
        WebElement nomeProduto2 = (WebElement) driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/productTV"));
        assertEquals ("Sauce Labs Bolt T-Shirt",nomeProduto2.getText());
        // Verificar o preço do produto
        WebElement precoProduto2 = (WebElement) driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/priceTV"));
        assertEquals("$ 15.99", precoProduto2.getText());

        // Arrasta para Cima
        PointerInput fingerr = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDropp = new Sequence(fingerr, 1);
        dragNDropp.addAction(fingerr.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 200, 500));
        dragNDropp.addAction(fingerr.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDropp.addAction(new Pause(finger, Duration.ofMillis(600)));
        dragNDropp.addAction(fingerr.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), 200, 100));
        dragNDropp.addAction(fingerr.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(dragNDropp));

        // clica no botão adicionar no carrinho
        WebElement btnAdicionarNoCarrinho1 = (WebElement) driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Tap to add product to cart\"]"));
        btnAdicionarNoCarrinho1.click();
        // clica no carrinho
        WebElement icoCarrinho1 = (WebElement) driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/cartIV"));
        icoCarrinho1.click();


        // clica no carrinho
        WebElement sacola2 = (WebElement) driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/cartTV"));
        sacola2.click();
        // Verifica quantidade de itens
        WebElement item = (WebElement) driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/itemsTV"));
        assertEquals("11 Items", item.getText());

        /*WebElement sacola1 = (WebElement) driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/cartIV"));
        sacola1.click();
        WebElement limparsacola = (WebElement) driver.findElement(By.id("Removes product from cart"));
        limparsacola.click();*/





    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}




