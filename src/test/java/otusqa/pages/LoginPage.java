package otusqa.pages;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import otusqa.steps.LoginSteps;

import java.util.Iterator;
import java.util.Set;

public class LoginPage extends AbstractPage{

    @FindBy(xpath = "(//input[@type=\"text\"])[1]")
    private WebElement Email;

    @FindBy(xpath = "(//input[@type=\"password\"])")
    private WebElement Password;

    @FindBy(xpath = "(//button[contains(text(), 'Войти')])[1]")
    private WebElement EnterButton;

    public LoginPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getEmail() {return Email;}

    public WebElement getPassword() {return Password;}

    public WebElement getEnterButton() {return EnterButton;}


}
