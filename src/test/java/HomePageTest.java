import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;


public class HomePageTest {

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;

@BeforeClass
    public void setup() {

    driver = WebDriverManager.chromedriver().create();
    driver.manage().window().maximize();

    //membuka halaman saucedemo login
    driver.get("https://www.saucedemo.com/");

    //inisialisasi halaman login dan homepage
    loginPage = new LoginPage(driver);
    homePage = new HomePage(driver);

    //Melakukan login
    loginPage.inputUsername("standard_user");
    loginPage.inputPassword("secret_sauce");
    loginPage.clickLoginButton();
}

    @Test
    public void sorting(){
        homePage.getSorting();

        //Assertion 1 mengecek item yang muncul sudah sesuai (Terkecil ke yang terbesar)
        homePage.getExpensive(); //expensive product is displayed
        homePage.getCheap(); //cheap product is display

        // Assertion 2 get current url
        assertEquals(homePage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void checkout(){
        homePage.addProductToCart("sauce labs onesie");
        homePage.addProductToCart("sauce labs bike light");
        homePage.cartButton();
        //Assertion get current URL in Cart page
        assertEquals(homePage.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        homePage.clickCheckoutButton();

        //Assertion check if form is displayed
        homePage.checkoutinfo();

        homePage.inputUsername("Herlan");
        homePage.inputLastName("Nurachman");
        homePage.inputZipCode("12345");
        // Assertion get current URL in Overview product page
        assertEquals(homePage.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");

        homePage.sendAddress();
        homePage.finishClick();
        //Assertion Get Current Url 1
        assertEquals(homePage.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");

        //Assertion checkout info complete
        assertEquals(homePage.getCheckoutComplete(), "Checkout: Complete!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

