package otusqa.pages;

import javafx.util.Pair;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import otusqa.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonalPage extends AbstractPage{

    private WebDriverWait wait = new WebDriverWait(driver,10);

    @FindBy(xpath = "(//input[@id='id_fname'])")
    private WebElement InputName;

    @FindBy(xpath = "(//input[@id='id_lname'])")
    private WebElement InputSecondName;

    @FindBy(xpath = "(//input[@id='id_blog_name'])")
    private WebElement InputNameInblog;

    @FindBy(xpath = "(//input[@name='date_of_birth'])")
    private WebElement DateOfBirth;

    @FindBy(xpath = "(//div[@class='container__row lk-cv-block__line'][1]/div[2]/div/label/div)")
    private  WebElement Country;

    @FindBy(xpath = "(//div[@class='container__row lk-cv-block__line'][2]/div[2]/div/label/div)")
    private  WebElement City;

    @FindBy(xpath = "(//button[@title='Сохранить и заполнить позже'])")
    private WebElement SaveButton;

    @FindBy(xpath = "(//button[contains(text(), 'Сохранить и продолжить')])")
    private  WebElement SaveAndProceedButton;

    @FindBy(xpath = "(//p[@class='header2-menu__item-text header2-menu__item-text__username'])")
    private WebElement Menu;

    @FindBy(xpath = "(//div[@class='container__row lk-cv-block__line lk-cv-block__line_double'])")
    private WebElement ReadyToRelocate;

    @FindBy(xpath = "(//div[@class='container__col container__col_9 container__col_md-8 container__col_middle'])")
    private WebElement FormatWorkContainer;

    @FindBy(xpath = "(//div[@class='lk-cv-block__line js-formset-items'])")
    private  WebElement Contacts;

    @FindBy(xpath = "(//button[@class='lk-cv-block__action lk-cv-block__action_md-no-spacing js-formset-add js-lk-cv-custom-select-add'])")
    private WebElement AddContactButton;

    @FindBy(xpath = "(//select[@name='gender'])")
    private WebElement GenderSelect;

    @FindBy(xpath = "(//input[@name='company'])")
    private WebElement Company;

    @FindBy(xpath = "(//input[@name='work'])")
    private WebElement Position;

    @FindBy(xpath = "(//div[@data-prefix='experience'])")
    private  WebElement Experience;

    @FindBy(xpath = "(//a[@class='experience-add js-formset-add'])")
    private WebElement AddExperinceButton;

    @FindBy(xpath = "(//a[@title='Выход'])")
    private  WebElement ExitButton;

    public PersonalPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public PersonalPage open()
    {
        driver.get("https://otus.ru/lk/biography/personal/");
        log.info("Go to LK");
        return this;
    }

    public PersonalPage setFirstName(String name)
    {
        wait.until(ExpectedConditions.visibilityOf(InputName));
        InputName.clear();
        InputName.sendKeys(name);
        log.info("Input Name");
        return this;
    }

    public String getFirstName()
    {
        wait.until(ExpectedConditions.visibilityOf(InputName));
        return InputName.getAttribute("value");
    }

    public PersonalPage setSecondName(String name)
    {
        wait.until(ExpectedConditions.visibilityOf(InputSecondName));
        InputSecondName.clear();
        InputSecondName.sendKeys(name);
        log.info("Input Second Name");
        return this;
    }

    public String getSecondName()
    {
        wait.until(ExpectedConditions.visibilityOf(InputSecondName));
        return InputSecondName.getAttribute("value");
    }

    public PersonalPage setNameInBlog(String name)
    {
        wait.until(ExpectedConditions.visibilityOf(InputNameInblog));
        InputNameInblog.clear();
        InputNameInblog.sendKeys(name);
        log.info("Input Name in Blog");
        return this;
    }

    public String getNameInBlog()
    {
        wait.until(ExpectedConditions.visibilityOf(InputNameInblog));
        return InputNameInblog.getAttribute("value");
    }

    public PersonalPage setCountry(String name)
    {
        InputNameInblog.click();
        wait.until(ExpectedConditions.visibilityOf(Country));
        Country.click();
        By locCountry = By.xpath("//button[@title='"+name+"']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locCountry));
        driver.findElement(locCountry).click();
        log.info("Select Country");
        return this;
    }

    public String getCountry()
    {
        wait.until(ExpectedConditions.visibilityOf(Country));
        //WebElement country = Country.findElement(By.tagName("div"));
        return Country.getText();
    }

    public PersonalPage setCity(String name)
    {
        wait.until(ExpectedConditions.visibilityOf(City));
        City.click();
        By locCity = By.xpath("//button[@title='"+name+"']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locCity));
        driver.findElement(locCity).click();
        log.info("Select City");
        return this;
    }

    public String getCity() {
        wait.until(ExpectedConditions.visibilityOf(City));
        return City.getText();
    }

    public PersonalPage setBirthDay(String birthDay)
    {
        wait.until(ExpectedConditions.visibilityOf(DateOfBirth));
        DateOfBirth.clear();
        DateOfBirth.sendKeys(birthDay);
        log.info("Input date of birth");
        return this;
    }

    public String getBirthDay()
    {
        wait.until(ExpectedConditions.visibilityOf(DateOfBirth));
        return DateOfBirth.getAttribute("value");
    }

    public PersonalPage setReadyToRelocate(boolean f)
    {
        wait.until(ExpectedConditions.visibilityOf(ReadyToRelocate));
        List<WebElement> l = ReadyToRelocate.findElements(By.tagName("span"));
        if (f) {  l.get(1).click(); }
        else { l.get(0).click(); }
        return this;
    }

    public boolean getReadyToRelocate()
    {
        wait.until(ExpectedConditions.visibilityOf(ReadyToRelocate));
        List<WebElement> list = ReadyToRelocate.findElements(By.tagName("input"));
        if (list.get(0).getAttribute("checked")==null)
            return true;
        else
            return false;
    }

    public PersonalPage setFormatWork(ArrayList<FormatWork> format)
    {
        wait.until(ExpectedConditions.visibilityOf(FormatWorkContainer));
        clearFormatWork();

        for(int i = 0; i < format.size(); i++) {
            By locFormat = By.xpath("(//input[@title='"+format.get(i).getName()+"'])");
            WebElement formatEl = FormatWorkContainer.findElement(locFormat);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('checked', 'checked')",formatEl);
        }
        return this;
    }

    public PersonalPage clearFormatWork()
    {
        FormatWork[] formates = FormatWork.values();
        wait.until(ExpectedConditions.visibilityOf(FormatWorkContainer));
        for (FormatWork f : formates)
        {
            By locFormat = By.xpath("(//input[@title='"+f.getName()+"'])");
            WebElement formatEl = FormatWorkContainer.findElement(locFormat);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String checked = formatEl.getAttribute("checked");
            if (checked != "")
                js.executeScript("arguments[0].removeAttribute('checked')",formatEl);
        }
        return this;
    }

    public ArrayList<FormatWork> getFormatWork()
    {
        FormatWork[] formates = FormatWork.values();
        ArrayList<FormatWork> result = new ArrayList<FormatWork>();
        wait.until(ExpectedConditions.visibilityOf(FormatWorkContainer));
        for (FormatWork f : formates)
        {
            By locFormat = By.xpath("(//input[@title='"+f.getName()+"'])");
            WebElement formatEl = FormatWorkContainer.findElement(locFormat);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String checked = formatEl.getAttribute("checked");
            if (checked != null) {
                result.add(f);
            }
        }
        return result;
    }

    private int getContactsSize()
    {
        //log.info("Start getContactsSize");
        By locContact = By.xpath("(//div[@class='container__row js-formset-row'])");
        wait.until(ExpectedConditions.visibilityOf(Contacts));
        List<WebElement> contacts = Contacts.findElements(locContact);
        //log.info("End getContactsSize, return " + contacts.size());
        return contacts.size();
    }

    private WebElement getContactByNum(int num)
    {
        //log.info("Start getContactByNum " + num);
        int size = getContactsSize();
        if (num>=size) {
            //log.info("End getContactByNum " + num + ", return null");
            return null;
        }
        else
        {
            WebElement contact = Contacts.findElement(By.xpath("//div[@data-num='"+num+"']"));
            /*JavascriptExecutor executor = (JavascriptExecutor) driver;
            Object aa=executor.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", contact);
            log.info("return "+ aa.toString());*/
            //log.info("End getContactByNum " + num);
            return contact;
        }
    }

    public PersonalPage addNewContact(ContactType type, String value) {
        //log.info("Start addNewContact");
        int size = getContactsSize();
        By locType = By.xpath("(//span[@class='placeholder'])"); // Локатор для поля со способом связи
        List<WebElement> check = driver.findElements(locType);
        //log.info("Строк для нового контакта "+check.size());
        if (size == 1 && check.size() !=0)
            size = size-1;
        if(check.size()==0) // Проверяем есть ли строка для нового контакта, если нет - жмем добавить
        {
            wait.until(ExpectedConditions.visibilityOf(AddContactButton));
            AddContactButton.click();
            log.info("Click add new contact");
        }
        check = driver.findElements(locType);
        //log.info("Строк для нового контакта "+check.size());
        WebElement newContact = getContactByNum(size); // находим строку для нового контакта
        // Вводим значение
        By locValue = By.xpath("(//input[@id='id_contact-"+size+"-value'])");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locValue));
        newContact.findElement(locValue).sendKeys(value);
        log.info("Input contact value");
        // Выбор способа связи
        WebElement emptyTypeElement = newContact.findElement(locType); // Элемент с невыбранным способом связи
        emptyTypeElement.click();
        //log.info("Click type");
        // Выбираем нужный способ в зависимости от переданного параметра
        By locTypeValue = By.xpath("(//button[@title='"+type.getName()+"'])");
        //WebElement el = newContact.findElement(locTypeValue);
        List<WebElement> typeValues = newContact.findElements(locTypeValue);
        if(typeValues.size()==1)
        //el.click();
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", typeValues.get(0));
        else
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", typeValues.get(typeValues.size()-1));

        log.info("Select type " + type.getName());
        check = driver.findElements(locType);
        //log.info("Строк для нового контакта "+check.size());
        size = getContactsSize();
        //log.info("End addNewContact");
        return this;
    }

    public ArrayList<Pair<ContactType,String>> getContacts()
    {
        int size = getContactsSize();
        //log.info("size = " + size);
        By locType = By.xpath("(//span[@class='placeholder'])"); // Локатор для поля со способом связи
        List<WebElement> check = driver.findElements(locType);
        //log.info("Строк для нового контакта "+check.size());
        if (size == 1 && check.size() !=0)
            return new ArrayList<>();
        else
        {
            By locContact = By.xpath("(//div[@class='container__row js-formset-row'])");
            wait.until(ExpectedConditions.visibilityOf(Contacts));
            List<WebElement> contacts = Contacts.findElements(locContact);
            ArrayList<Pair<ContactType,String>> result = new ArrayList<>();
            for(int i=0;i<contacts.size();i++)
            {
                By locTypeValue = By.xpath("(//input[@name='contact-"+i+"-service'])");
                By locValue = By.xpath("(//input[@id='id_contact-"+i+"-value'])");

                String v = Contacts.findElement(locTypeValue).getAttribute("value");
                if (v.length() == 2)
                    v = v.toUpperCase();
                else
                    v=v.substring(0, 1).toUpperCase() + v.substring(1);
                ContactType type = ContactType.valueOf(v);
                String value = Contacts.findElement(locValue).getAttribute("value");
                result.add(new Pair<>(type, value));
            }
            return result;
        }
    }

    private int getExperienceSize()
    {
        By locExperience = By.xpath("(//div[@class='experience-row js-formset-row'])");
        wait.until(ExpectedConditions.visibilityOf(Experience));
        List<WebElement> experienceElements = Experience.findElements(locExperience);
        return experienceElements.size();
    }

    private WebElement getExperienceByNum(int num)
    {
        int size = getExperienceSize();
        if (num>=size) {
            return null;
        }
        else
        {
            WebElement experienceElement = Experience.findElement(By.xpath("//div[@data-num='"+num+"']"));
            return experienceElement;
        }
    }

    public ArrayList<Pair<ProgrammingLanguage, ExperienceLevel>> getExperience()
    {
        ArrayList<Pair<ProgrammingLanguage, ExperienceLevel>> experience = new ArrayList<>();
        By locExperience = By.xpath("(//div[@class='experience-row js-formset-row'])");
        wait.until(ExpectedConditions.visibilityOf(Experience));
        List<WebElement> experienceElements = Experience.findElements(locExperience);
        for(int i=0;i<experienceElements.size();i++)
        {
            By locPL = By.xpath("(//select[@id='id_experience-"+i+"-experience'])");
            By locLevel = By.xpath("(//select[@id='id_experience-"+i+"-level'])");
            WebElement PL = Experience.findElement(locPL);
            WebElement Level = Experience.findElement(locLevel);
            List<WebElement> optionsPL = PL.findElements(By.tagName("option"));
            List<WebElement> optionsLevel = Level.findElements(By.tagName("option"));
            WebElement selectedPL=null;
            WebElement selectedLevel=null;
            for(WebElement pl : optionsPL)
            {
                if(pl.isSelected())
                    selectedPL = pl;
            }
            for(WebElement level : optionsLevel)
            {
                if(level.isSelected())
                    selectedLevel = level;
            }
            ProgrammingLanguage pl = ProgrammingLanguage.fromString(selectedPL.getText());
            ExperienceLevel level = ExperienceLevel.fromString(selectedLevel.getText());
            experience.add(new Pair<>(pl, level));
        }
        return experience;
    }



    public PersonalPage addExperience(ProgrammingLanguage language, ExperienceLevel level) {
        int size = getExperienceSize();
        wait.until(ExpectedConditions.visibilityOf(AddExperinceButton));
        AddExperinceButton.click();
        WebElement newExperience = getExperienceByNum(size);
        By locPL = By.xpath("(//select[@id='id_experience-"+size+"-experience'])");
        By locLevel = By.xpath("(//select[@id='id_experience-"+size+"-level'])");
        Select PL = new Select(newExperience.findElement(locPL));
        Select Level = new Select(newExperience.findElement(locLevel));
        PL.selectByVisibleText(language.getName());
        Level.selectByVisibleText(level.getName());
        log.info("add new experience");
        return this;
    }

    public PersonalPage setGender(Gender gender)
    {
        wait.until(ExpectedConditions.visibilityOf(GenderSelect));
        GenderSelect.click();
        log.info("Click gender select");
        By locGenderValue = By.xpath("(//option[contains(text(), '"+gender.getName()+"')])");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locGenderValue));
        GenderSelect.findElement(locGenderValue).click();
        return this;
    }

    public String getGender()
    {
        List<WebElement> values = GenderSelect.findElements(By.tagName("option"));
        for(WebElement v : values)
        {
            if (v.isSelected())
                return v.getText();
        }
        return "";
    }

    public PersonalPage setCompany(String company)
    {
        wait.until(ExpectedConditions.visibilityOf(Company));
        Company.clear();
        Company.sendKeys(company);
        log.info("Input company");
        return this;
    }

    public String getCompany()
    {
        wait.until(ExpectedConditions.visibilityOf(Company));
        return Company.getAttribute("value");
    }

    public PersonalPage setPosition(String position) {
        wait.until(ExpectedConditions.visibilityOf(Position));
        Position.clear();
        Position.sendKeys(position);
        log.info("Input position");
        return this;
    }

    public String getPosition()
    {
        wait.until(ExpectedConditions.visibilityOf(Position));
        return Position.getAttribute("value");
    }

    public PersonalPage saveAndProceed() throws IOException {
        SaveAndProceedButton.click();
        log.info("Save change");
        try {
            wait.until(ExpectedConditions.visibilityOf(SaveAndProceedButton));
        }
        catch (TimeoutException ex)
        {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./screenshotes/ErrorSaving.png"));
        }
        return this;
    }

    public PersonalPage save() throws IOException {
        SaveButton.click();
        log.info("Save change");
        try {
            wait.until(ExpectedConditions.visibilityOf(SaveButton));
        }
        catch (TimeoutException ex)
        {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./screenshotes/ErrorSaving.png"));
        }
        return this;
    }

    public StartPage signOut()
    {
        Actions action = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(Menu));
        action.moveToElement(Menu);
        action.perform();
        Menu.click();
        log.info("go to menu");

        wait.until(ExpectedConditions.visibilityOf(ExitButton));
        ExitButton.click();
        log.info("LogOut");
        return new StartPage(driver);
    }


}
