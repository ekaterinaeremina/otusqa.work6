package otusqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import otusqa.helpers.*;

import java.util.Iterator;
import java.util.Set;

public class LoginPage extends AbstractPage{

    private WebDriverWait wait = new WebDriverWait(driver,10);

    @FindBy(xpath = "(//input[@type=\"text\"])[1]")
    private WebElement Email;

    @FindBy(xpath = "(//input[@type=\"password\"])")
    private WebElement Password;

    @FindBy(xpath = "(//button[contains(text(), 'Войти')])[1]")
    private WebElement Enter;

    private static String login = System.getProperty("login");
    private static String password = System.getProperty("password");

    public LoginPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public PersonalPage signIn()
    {
        wait.until(ExpectedConditions.visibilityOf(Email));
        Email.clear();
        Email.sendKeys(login);
        log.info("Input email");
        wait.until(ExpectedConditions.visibilityOf(Password));
        Password.clear();
        Password.sendKeys(password);
        log.info("Input password");
        wait.until(ExpectedConditions.visibilityOf(Enter));
        Enter.submit();
        log.info("Click to 'Войти'");
        wait.until(CookiesContainsAuthTokenExpires(driver));
        log.info("LogIn");
        return new PersonalPage(driver);
    }

    public static ExpectedCondition<Boolean> CookiesContainsAuthTokenExpires(final WebDriver driver) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver driver) {
                Set<Cookie> cookies = driver.manage().getCookies();
                for (Iterator<Cookie> it = cookies.iterator(); it.hasNext(); ) {
                    Cookie c = it.next();
                    if (c.getName().equals("auth_token_expires")) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
