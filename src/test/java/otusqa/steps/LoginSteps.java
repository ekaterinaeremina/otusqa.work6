package otusqa.steps;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import otusqa.pages.LoginPage;
import otusqa.pages.PersonalPage;

import java.util.Iterator;
import java.util.Set;

public class LoginSteps extends AbstractStep{

    private WebDriverWait wait = new WebDriverWait(driver,10);

    private static String login = System.getProperty("login");
    private static String password = System.getProperty("password");

    private LoginPage loginPage = new LoginPage(driver);

    public LoginSteps(WebDriver d) {super(d);}

    public void inputEmail()
    {
        LoginPage loginPage = new LoginPage(driver);
        WebElement emailInput = loginPage.getEmail();
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(login);
        log.info("Input email");
    }

    public void inputPassword()
    {
        WebElement passwordInput = loginPage.getPassword();
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
        log.info("Input password");
    }

    public void clickEnter()
    {
        WebElement EnterButton = loginPage.getEnterButton();
        wait.until(ExpectedConditions.visibilityOf(EnterButton));
        EnterButton.submit();
        log.info("Click to 'Войти'");
    }

    public PersonalPage signIn()
    {
        inputEmail();
        inputPassword();
        clickEnter();
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
