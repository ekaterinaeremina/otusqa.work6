package otusqa.cases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import otusqa.WebDriverFactory;
import otusqa.WebDriverName;

public class BaseTest {
    protected static WebDriver driver;
    private static final Logger log = Logger.getLogger(BaseTest.class);
    protected static String browser = System.getProperty("browser").toUpperCase();

    @BeforeClass
    public static void setUp()
    {
        driver = WebDriverFactory.create(WebDriverName.valueOf(browser));
        driver.manage().window().maximize();
        log.info("Setup driver");
    }


    @AfterClass
    public static void tearDown()
    {
        if (driver!=null) {
            driver.quit();
            driver = null;
        }
        log.info("Driver quit");
    }
}
