/*
package com.example.produktapi.service;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class SeleniumTest {


    //hämta in de webdrivers du vill använda
    private static WebDriver driver;


    @BeforeAll
    public static void init() {
        // hämta webdrivers och skapa en instans av ChromeDriver
        driver = new ChromeDriver();
        // navigera till sidan en gång för alla tester
        driver.navigate().to("https://java22.netlify.app/");
    }

    @AfterAll
    public static void tearDown() {
        //Stänger driver efter alla tester
        if (driver != null) {
            driver.quit();
        }
    }


    @Test //testar sidans titel
    public void testWebsiteTitle() {

        //test för att kolla title
        assertEquals("Webbutik", driver.getTitle(), "titlen hittades inte");

    }


    @Test //För tajta tshirten
    public void testProductPriceForMensCasualSlimFitT_shirt() {

        // Hitta elementet med hjälp av dess XPath
        WebElement priceElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Vilken härlig t-shirt, slim fit o casual i ett!')]")));

        // ta ut priset från elementet
        String priceText = priceElement.getText();
        String price = priceText.replaceAll("[^\\d.]", "");

        // kör själva testet
        assertEquals("22.3", price, "The product price does not match");

    }


    @Test //pris för jackan
    public void testProductPriceForMensCottonJacket() {

        // Hitta elementet med hjälp av dess XPath
        WebElement priceElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Bomullsjacka, vad finns det mer att säga?!')]")));

        // ta ut priset från elementet
        String priceText = priceElement.getText();
        String price = priceText.replaceAll("[^\\d.]", "");

        // kör själva testet
        assertEquals("55.99", price, "The product price does not match");

    }


    @Test //pris för armbandet
    public void testProductPriceForJohnHardyBracelet() {

        // Hitta elementet med hjälp av dess XPath
        WebElement priceElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Silver drakens återkomst. Ett måste om man vill ha den!')]")));

        // ta ut priset från elementet
        String priceText = priceElement.getText();
        String price = priceText.replaceAll("[^\\d]", "");

        // kör själva testet
        assertEquals("695", price, "The product price does not match");

    }


    @Test //pris för dator skärmen
    public void testProductPriceForAcerPcScreen() {

        // Hitta elementet med hjälp av dess XPath
        WebElement priceElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Full HD, fan va coolt. Den ska vi ha!')]")));

        // ta ut priset från elementet
        String priceText = priceElement.getText();
        String price = priceText.replaceAll("[^\\d]", "");

        // kör själva testet
        assertEquals("599", price, "The product price does not match");

    }


    @Test //pris för väskan
    public void testProductPriceForFjallRavenBackPack() {

        // Hitta elementet med hjälp av dess XPath
        WebElement priceElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Fin väska me plats för dator')]")));

        // ta ut priset från elementet
        String priceText = priceElement.getText();
        String price = priceText.replaceAll("[^\\d.]", "");

        // kör själva testet
        assertEquals("109.95", price, "The product price does not match");

    }


    @Test //pris för dam jackan

    public void testProductPriceForWomensSolShortSleeve() {

        driver.navigate().to("https://java22.netlify.app/");

        // Hitta elementet med hjälp av dess XPath
        WebElement priceElement = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Snygg och bara att sätta på sig om man har köpt rätt storlek')]")));

        // ta ut priset från texten
        String priceText = priceElement.getText();
        String price = priceText.replaceAll("[^\\d.]", ""); //Vissa elements behöver man ta bort punkten efter d i regexen

        // kör själva testet 
        assertEquals("9.85", price, "The product price does not match");

    }


    @Test //Test för bild src av väskan
    public void testImageShowingForItemFjallravenBagpack() {

        // Hitta bild-elementet med hjälp av dess XPath
        WebElement productImage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='card-img-top' and contains(@src, 'https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg')]")));

        //test för att se ifall bilden visas
        assertTrue(productImage.isDisplayed(), "bilden verkar inte läsas in");


    }


    @Test  //Test för bild src av slimfit plaggen
    public void testImageShowingForMensCasualSlimFit() {

        // Hitta bild-elementet med hjälp av dess XPath
        WebElement productImage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='card-img-top' and contains(@src, 'https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg')]")));

        //test för att se ifall bilden visas
        assertTrue(productImage.isDisplayed(), "bilden verkar inte läsas in");


    }


    @Test  //Test för bild src av cotten jackan
    public void testImageShowingForMensCottenJacket() {

        // Hitta bild-elementet med hjälp av dess XPath
        WebElement productImage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='card-img-top' and contains(@src, 'https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg')]")));

        //test för att se ifall bilden visas
        assertTrue(productImage.isDisplayed(), "bilden verkar inte läsas in");



    }


    @Test
    public void testImageIsDisplayingForFjallravenBag() {

        // Hitta bild-elementet med hjälp av dess XPath
        WebElement imageElement = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"productsContainer\"]/div/div[1]/div/img")));

        // Kontrollera att bild-elementet är synligt på sidan
        assertTrue(imageElement.isDisplayed(), "The image is not displayed on the page");

    }


    @Test
    public void testImageIsDisplayingForMensCasualSlimFit() {

        // Hitta bild-elementet med hjälp av dess XPath
        WebElement imageElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"productsContainer\"]/div/div[2]/div/img")));

        // Kontrollera att bild-elementet är synligt på sidan
        assertTrue(imageElement.isDisplayed(), "The image is not displayed on the page");

    }


    @Test
    public void testImageIsDisplayingForCottonJacket() {


        // Hitta bild-elementet med hjälp av dess XPath
        WebElement imageElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"productsContainer\"]/div/div[3]/div/img")));

        // Kontrollera att bild-elementet är synligt på sidan
        assertTrue(imageElement.isDisplayed(), "The image is not displayed on the page");

    }


    @Test
    public void testCategoryNameElectronics() {

        // Hämta kategorielementen med hjälp av klassnamnet
        WebElement electronics = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div > a.menuLink[href='/category/electronics']")));

        // Kontrollera att  kategori har rätt namn
        assertEquals("electronics", electronics.getText(), "Fel kategori ");

    }


    @Test
    public void testCategoryNameJewelery() {

        // Hämta kategorielementen med hjälp av klassnamnet
        WebElement jewelery = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div > a.menuLink[href='/category/jewelery']")));

        // Kontrollera att  kategori har rätt namn
        assertEquals("jewelery", jewelery.getText(), "Fel kategori");

    }


    @Test
    public void testCategoryNameMensClothing() {

        // Hämta kategorielementen med hjälp av klassnamnet
        WebElement mensClothing = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div > a.menuLink[href='/category/men\\'s clothing']")));

        // Kontrollera att kategori har rätt namn
        assertEquals("men's clothing", mensClothing.getText(), "Fel kategori");

    }


    @Test
    public void testCategoryNameWomensClothing() {
        //Hämta kategorielementen med hjälp av klassnamnet
        WebElement womensClothing = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div > a.menuLink[href='/category/women\\'s clothing']")));

        // Kontrollera att kategori har rätt namn
        assertEquals("women's clothing", womensClothing.getText(), "Fel kategori");


    }


    @Test
    void testcategoriElectronicsProductsDisplayedOnClick() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // hitta och klicka på electronic länken
        WebElement electronicsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("electronics")));
        electronicsLink.click();

        // kolla så det finns 6 produkter på den sidan
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("productItem")));
        assertEquals(6, products.size());



    }


    @Test
    void testcategoriJeweleryProductsDisplayedOnClick() {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // hutta och klicka på jewelry länken
        WebElement jewelery = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("jewelery")));
        jewelery.click();

        // kolla så det finns 6 produkter på sidan
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("productItem")));

        assertEquals(4, products.size());

    }

    @Test
    void testcategoriMensClothingProductsDisplayedOnClick() {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // hitta och klicka på men's clothing länken
        WebElement mensClothing = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("men's clothing")));
        mensClothing.click();

        // kolla så det finns 4 produkter på sidan
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("productItem")));
        assertEquals(4, products.size());


    }


    @Test
    void testcategoriWomensClothingProductsDisplayedOnClick() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // hitta och klicka på womensclothing
        WebElement womensClothing = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("women's clothing")));
        womensClothing.click();

        // kolla så det finns 6 produkter på sidan
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("productItem")));
        assertEquals(6, products.size());



    }


    @Test
    public void testSearchProductByName() {
        // Hårdkodad söksträng
        String productName = "White Gold Plated Princess";

        driver.navigate().to("https://java22.netlify.app/");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Hitta alla element som matchar sökkriteriet
        List<WebElement> productTitles = driver.findElements(By.className("card-title"));

        // Loopa igenom alla element och kontrollera om söksträngen finns
        boolean isFound = false;
        for (WebElement productTitle : productTitles) {
            String actualName = productTitle.getText();
            if (actualName.equals(productName)) {
                isFound = true;
                break;
            }
        }

        // Kontrollera om söksträngen hittades i listan
        assertTrue(isFound, "Produkten kunde inte hittas i listan");

    }


    @Test
    public void testTotalNumberOfProducts() {

        driver.navigate().to("https://java22.netlify.app/");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Hämta det totala antalet produkter och jämför med det förväntade antalet
        List<WebElement> products = driver.findElements(By.className("productItem"));

        //testar så det finns totalt 20 produkter
        assertEquals(20, products.size(), "Antalet produkter stämmer inte");

    }
}


*/