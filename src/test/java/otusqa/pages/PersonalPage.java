package otusqa.pages;

import javafx.util.Pair;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import otusqa.*;
import otusqa.steps.PersonalSteps;

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
    private  WebElement ContactsContainer;

    @FindBy(xpath = "(//button[@class='lk-cv-block__action lk-cv-block__action_md-no-spacing js-formset-add js-lk-cv-custom-select-add'])")
    private WebElement AddContactButton;

    @FindBy(xpath = "(//select[@name='gender'])")
    private WebElement GenderSelect;

    @FindBy(xpath = "(//input[@name='company'])")
    private WebElement Company;

    @FindBy(xpath = "(//input[@name='work'])")
    private WebElement Position;

    @FindBy(xpath = "(//div[@data-prefix='experience'])")
    private  WebElement ExperienceContainer;

    @FindBy(xpath = "(//a[@class='experience-add js-formset-add'])")
    private WebElement AddExperinceButton;

    @FindBy(xpath = "(//a[@title='Выход'])")
    private  WebElement ExitButton;

    //Локатор для выбора способа связи
    private By locType = By.xpath("(//span[@class='placeholder'])");
    //Локатор для получения строк с контактами
    private By locContact = By.xpath("(//div[@class='container__row js-formset-row'])");

    //Локатор для получения строк с опытом
    private By locExperience = By.xpath("(//div[@class='experience-row js-formset-row'])");

    private PersonalSteps personalSteps = new PersonalSteps(driver);

    public PersonalPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getInputNameInblog() {return  InputNameInblog;}

    public PersonalPage open()
    {
        driver.get("https://otus.ru/lk/biography/personal/");
        log.info("Go to LK");
        return this;
    }

    public PersonalPage setFirstName(String name)
    {
        personalSteps.setInput(InputName,name);
        log.info("Input First Name");
        return this;
    }

    public String getFirstName() { return personalSteps.getInputValue(InputName); }

    public PersonalPage setSecondName(String name)
    {
        personalSteps.setInput(InputSecondName,name);
        log.info("Input Second Name");
        return this;
    }

    public String getSecondName() { return personalSteps.getInputValue(InputSecondName); }

    public PersonalPage setNameInBlog(String name)
    {
        personalSteps.setInput(InputNameInblog,name);
        log.info("Input Name in Blog");
        return this;
    }

    public String getNameInBlog() { return personalSteps.getInputValue(InputNameInblog); }

    public PersonalPage setCountry(String name)
    {
        personalSteps.setSelectedValue(Country,name);
        log.info("Select Country");
        return this;
    }

    public String getCountry() {return personalSteps.getSelectedValue(Country);}

    public PersonalPage setCity(String name)
    {
        personalSteps.setSelectedValue(City,name);
        log.info("Select City");
        return this;
    }

    public String getCity() {return personalSteps.getSelectedValue(City);}

    public PersonalPage setBirthDay(String birthDay)
    {
        personalSteps.setInput(DateOfBirth,birthDay);
        log.info("Input date of birth");
        return this;
    }

    public String getBirthDay() { return personalSteps.getInputValue(DateOfBirth); }

    public PersonalPage setReadyToRelocate(boolean value)
    {
        personalSteps.setRadioButtonValue(ReadyToRelocate,value);
        return this;
    }

    public  boolean getReadyToRelocate() {return personalSteps.getRadioButtonValue(ReadyToRelocate);}

    public WebElement getFormatWorkContainer() {return FormatWorkContainer;}

    public PersonalPage setFormatWork(ArrayList<FormatWork> format)
    {
        personalSteps.setFormatWork(format);
        log.info("Select work formates");
        return this;
    }

    public ArrayList<FormatWork> getFormatWork() {return personalSteps.getFormatWork();}

    public WebElement getContactsContainer() {return ContactsContainer;}
    public WebElement getAddContactButton() {return AddContactButton;}
    public By getLocType() {return locType;}
    public By getLocContact() {return locContact;}

    public PersonalPage addNewContact(ContactType type, String value)
    {
        personalSteps.addNewContact(type,value);
        log.info("Add new Contacts: "+ type + " - "+ value);
        return this;
    }

    public PersonalPage addNewListContact(ArrayList<Pair<ContactType,String>> contacts)
    {
        personalSteps.addNewListContact(contacts);
        for (Pair<ContactType,String> c : contacts)
            log.info("Add new Contacts: "+ c.getKey() + " - "+ c.getValue());
        return this;
    }

    public ArrayList<Pair<ContactType,String>> getContacts() {return personalSteps.getContacts();}

    public WebElement getExperienceContainer() {return ExperienceContainer;}
    public WebElement getAddExperinceButton() {return AddExperinceButton;}
    public By getLocExperience() {return locExperience;}

    public PersonalPage addExperience(ProgrammingLanguage language, ExperienceLevel level)
    {
        personalSteps.addExperience(language,level);
        log.info("Add new Experience " + language.getName() + " - " +level.getName());
        return this;
    }

    public PersonalPage addListExperience(ArrayList<Pair<ProgrammingLanguage,ExperienceLevel>> experiences)
    {
        personalSteps.addListExperience(experiences);
        for (Pair<ProgrammingLanguage,ExperienceLevel> exp : experiences)
            log.info("Add new Experience: "+ exp.getKey() + " - "+ exp.getValue());
        return this;
    }

    public ArrayList<Pair<ProgrammingLanguage, ExperienceLevel>> getExperience() {return personalSteps.getExperience();}

    public  WebElement getGenderSelect() {return GenderSelect;}

    public PersonalPage setGender(Gender gender)
    {
        personalSteps.setGender(gender);
        log.info("Select gender " + gender.getName());
        return this;
    }

    public String getGender() {return personalSteps.getGender();}

    public PersonalPage setCompany(String company)
    {
        personalSteps.setInput(Company,company);
        log.info("Input company");
        return this;
    }

    public String getCompany() {return personalSteps.getInputValue(Company);}

    public PersonalPage setPosition(String position) {
        personalSteps.setInput(Position,position);
        log.info("Input position");
        return this;
    }

    public String getPosition() { return personalSteps.getInputValue(Position);}

    public PersonalPage saveAndProceed() throws IOException {
        personalSteps.save(SaveAndProceedButton);
        log.info("Click save and proceed");
        return this;
    }

    public PersonalPage save() throws IOException {
        personalSteps.save(SaveButton);
        log.info("Click save");
        return this;
    }

    public WebElement getMenu() {return Menu;}

    public WebElement getExitButton() {return ExitButton;}

    public StartPage signOut()
    {
        personalSteps.signOut();
        return new StartPage(driver);
    }
}
