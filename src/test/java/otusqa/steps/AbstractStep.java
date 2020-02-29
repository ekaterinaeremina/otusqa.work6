package otusqa.steps;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import otusqa.cases.OtusSiteTest;

public class AbstractStep {
    protected WebDriver driver;
    protected static final Logger log = Logger.getLogger(OtusSiteTest.class);

    public AbstractStep(WebDriver d)
    {
        driver = d;
    }
}
