package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

  WebDriver driver;
  WebDriverWait wait;

// Menetapkan locator untuk home page title dan juga product list

  @FindBy(xpath = "//div[@class='app_logo' and text()='Swag Labs']")
  WebElement homePageTitle;

  @FindBy(xpath = "//div[@class='inventory_container']")
  WebElement productList;

  @FindBy(xpath = "//select[@class='product_sort_container']")
  WebElement sortingButton;

  @FindBy (xpath = "//div[@class='inventory_item_price' and text() = 49.99]")
  WebElement expensiveProduct;

  @FindBy (xpath = "//div[@class='inventory_item_price' and text() = 7.99]")
  WebElement cheapProduct;

  @FindBy (xpath = "//button[@data-test='add-to-cart-sauce-labs-onesie']")
  WebElement product1;

  @FindBy (xpath = "//button[@data-test='add-to-cart-sauce-labs-bike-light' ]")
  WebElement product2;

  @FindBy (xpath = "//a[@class = 'shopping_cart_link']")
  WebElement shoppingCart;

  @FindBy (xpath = "//button[@name = 'checkout']")
  WebElement checkoutButton;

  @FindBy (xpath = "//input[@name = 'continue']")
  WebElement sendAddressButton;

  @FindBy (xpath = "//input[@name = 'firstName']")
  WebElement firstName;

  @FindBy (xpath = "//input[@name = 'lastName']")
  WebElement lastName;

  @FindBy (xpath = "//input[@name = 'postalCode']")
  WebElement zipCode;

  public HomePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
  }

  //method untuk assertion
  public String getDashboardTitle() {
    return homePageTitle.getText();
  }

  public void getProductList(){
    productList.isDisplayed();
  }

  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  public void getExpensive(){
    wait.until(ExpectedConditions.visibilityOf(expensiveProduct));
    expensiveProduct.isDisplayed();
  }

  public void getCheap(){
    wait.until(ExpectedConditions.visibilityOf(cheapProduct));
    cheapProduct.isDisplayed();
  }

  //method untuk action sorting
  public void getSorting(){
    sortingButton.click();
    Select select = new Select(sortingButton);
    select.selectByVisibleText("Price (high to low)");
  }

  public void addProductToCart(String product){
    switch (product.toLowerCase()){

  case "sauce labs onesie":
      product1.click();
      break;
  case "sauce labs bike light":
      product2.click();
      break;
    }
  }

  public void cartButton(){
    wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
    shoppingCart.click();
  }

  public void clickCheckoutButton(){
    wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
    checkoutButton.click();
  }

  public void inputUsername(String firstname ) {
    wait.until(ExpectedConditions.visibilityOf(firstName));
    firstName.sendKeys(firstname);
  }

  public void inputLastName(String lastname) {
    wait.until(ExpectedConditions.visibilityOf(lastName));
    lastName.sendKeys(lastname);
  }

  public void inputZipCode(String zipcode) {
    wait.until(ExpectedConditions.visibilityOf(zipCode));
    zipCode.sendKeys(zipcode);
  }

  public void sendAddress(){
    wait.until(ExpectedConditions.elementToBeClickable(sendAddressButton));
    sendAddressButton.click();
  }
}
