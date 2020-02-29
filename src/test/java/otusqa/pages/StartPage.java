package otusqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import otusqa.helpers.*;
import otusqa.steps.LoginSteps;

public class StartPage extends AbstractPage{

    private WebDriverWait wait = new WebDriverWait(driver,10);

    @FindBy(css = "button[data-modal-id]")
    private WebElement EnterOrRegistration;

    public StartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open()
    {
        String otusUrl = config.url();
        driver.get(otusUrl);
        log.info("Go to " + otusUrl);
    }

    public LoginSteps goToLoginPage()
    {
        wait.until(ExpectedConditions.visibilityOf(EnterOrRegistration));
        EnterOrRegistration.click();
        log.info("Click to 'Вход и регистрация'");
        return new LoginSteps(driver);
    }
}
