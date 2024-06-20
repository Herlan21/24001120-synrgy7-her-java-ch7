package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

//    By usernameField = By.name("username");
//    By passwordField = By.name("password");
//    By loginButton = By.xpath("//input[contains(@class, 'submit-button btn_action')]");

    @FindBy(id = "user-name")
    WebElement usernameForm;

    @FindBy(id = "password")
    WebElement passwordForm;

    @FindBy(xpath = "//input[contains(@class, 'submit-button btn_action')]")
    WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errorLoginMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

//    Create method untuk action
    public void inputUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameForm));
        usernameForm.sendKeys(username);
    }

    public void inputPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordForm));
        passwordForm.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorLoginMessage));
        return errorLoginMessage.getText();
    }

}
