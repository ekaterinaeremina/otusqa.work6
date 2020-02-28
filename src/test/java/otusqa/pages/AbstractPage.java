package otusqa.pages;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import otusqa.cases.BaseTest;
import otusqa.cases.OtusSiteTest;
import otusqa.helpers.MyConfig;

public class AbstractPage {

    protected WebDriver driver;
    protected static final Logger log = Logger.getLogger(OtusSiteTest.class);
    protected static MyConfig config = ConfigFactory.create(MyConfig.class);
    //protected WebDriverWait wait = new WebDriverWait(driver,10);

    public AbstractPage(WebDriver d)
    {
        driver = d;
    }
}
