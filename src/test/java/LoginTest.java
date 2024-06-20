import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import static org.testng.Assert.assertEquals;


public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeClass
    public void setup() {
        //membuka chrome dan diatur ukurannya ke maximaze
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();

        //membuka halaman saucedemo.com
        driver.get("https://www.saucedemo.com");

        //Inisialisasi Halaman
        loginPage = new LoginPage(driver);
        homePage  = new HomePage(driver);
    }

// Login using correct username and password
    @Test
    public void loginSuccess() {

        //Membuka halaman / pages
//        LoginPage loginPage = new LoginPage(driver);
//        HomePage homePage  = new HomePage(driver);

        //Sesudah membuka halaman / page, action apa yang dilakukan
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLoginButton();

        //Assertion 1 - get url
        assertEquals(homePage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        //Assertion 2 - validate dashboard text
        assertEquals(homePage.getDashboardTitle(), "Swag Labs");

        //Assertion 3 - Validate the product is shown
        homePage.getProductList();

    }

// Login Fail using wrong username/password
    @Test
    public void loginFail() {
        //Membuka halaman / pages
//        LoginPage loginPage = new LoginPage(driver);
//        HomePage homePage  = new HomePage(driver);

        //Sesudah membuka halaman / page, action apa yang dilakukan
        loginPage.inputUsername("user_standard");
        loginPage.inputPassword("secret");
        loginPage.clickLoginButton();

        //Assertion 1 - get url
        Assert.assertEquals(homePage.getCurrentUrl(), "https://www.saucedemo.com/");

        //Assertion 2 - validate error message
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

// Login fail with blank username and password
    @Test
    public void loginFailBlank(){
        loginPage.inputUsername("");
        loginPage.inputPassword("");
        loginPage.clickLoginButton();

        //Assertion 1 - get url
        Assert.assertEquals(homePage.getCurrentUrl(), "https://www.saucedemo.com/");

        //Assertion 2 - validate error message
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

