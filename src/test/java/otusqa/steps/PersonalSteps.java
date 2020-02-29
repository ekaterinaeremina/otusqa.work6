package otusqa.steps;

import javafx.util.Pair;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import otusqa.*;
import otusqa.pages.PersonalPage;
import otusqa.pages.StartPage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonalSteps extends AbstractStep{

    private WebDriverWait wait = new WebDriverWait(driver,10);

    public PersonalSteps(WebDriver d) {super(d);}

    public void setInput(WebElement input, String value)
    {
        wait.until(ExpectedConditions.visibilityOf(input));
        input.clear();
        input.sendKeys(value);
    }

    public String getInputValue(WebElement input)
    {
        wait.until(ExpectedConditions.visibilityOf(input));
        return input.getAttribute("value");
    }

    public void setSelectedValue(WebElement select, String name)
    {
        wait.until(ExpectedConditions.visibilityOf(select));
        select.click();
        By locSelectionValue = By.xpath("//button[@title='"+name+"']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locSelectionValue));
        driver.findElement(locSelectionValue).click();
    }

    public String getSelectedValue(WebElement select)
    {
        wait.until(ExpectedConditions.visibilityOf(select));
        return select.getText();
    }

    public void setRadioButtonValue(WebElement readyToLocate, boolean value)
    {
        wait.until(ExpectedConditions.visibilityOf(readyToLocate));
        List<WebElement> l = readyToLocate.findElements(By.tagName("span"));
        if (value) {  l.get(1).click(); }
        else { l.get(0).click(); }
    }

    public boolean getRadioButtonValue(WebElement readyToLocate)
    {
        wait.until(ExpectedConditions.visibilityOf(readyToLocate));
        List<WebElement> list = readyToLocate.findElements(By.tagName("input"));
        if (list.get(0).getAttribute("checked")==null)
            return true;
        else
            return false;
    }

    public void clearFormatWork()
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement FormatWorkContainer = personalPage.getFormatWorkContainer();
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
    }

    public void setFormatWork(ArrayList<FormatWork> format)
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement FormatWorkContainer = personalPage.getFormatWorkContainer();
        wait.until(ExpectedConditions.visibilityOf(FormatWorkContainer));
        clearFormatWork();

        for(int i = 0; i < format.size(); i++) {
            By locFormat = By.xpath("(//input[@title='"+format.get(i).getName()+"'])");
            WebElement formatEl = FormatWorkContainer.findElement(locFormat);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('checked', 'checked')",formatEl);
        }
    }

    public ArrayList<FormatWork> getFormatWork()
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement FormatWorkContainer = personalPage.getFormatWorkContainer();
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
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement Contacts = personalPage.getContactsContainer();
        By locContact = By.xpath("(//div[@class='container__row js-formset-row'])");
        wait.until(ExpectedConditions.visibilityOf(Contacts));
        List<WebElement> contacts = Contacts.findElements(locContact);
        return contacts.size();
    }

    private WebElement getContactByNum(int num)
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement Contacts = personalPage.getContactsContainer();
        int size = getContactsSize();
        if (num>=size) {
            return null;
        }
        else
        {
            WebElement contact = Contacts.findElement(By.xpath("//div[@data-num='"+num+"']"));
            return contact;
        }
    }

    private boolean isElementExsist(By loc)
    {
        List<WebElement> check = driver.findElements(loc);
        if (check.size()>0)
            return true;
        else
            return false;
    }

    private void inputContactValue(WebElement newContact, int contactId, String value)
    {
        By locValue = By.xpath("(//input[@id='id_contact-"+contactId+"-value'])");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locValue));
        newContact.findElement(locValue).sendKeys(value);
        log.info("Input contact value");
    }

    private void selectContactType(WebElement newContact, ContactType type)
    {
        PersonalPage personalPage = new PersonalPage(driver);
        By locType = personalPage.getLocType();
        WebElement emptyTypeElement = newContact.findElement(locType); // Элемент с невыбранным способом связи
        emptyTypeElement.click();
        // Выбираем нужный способ в зависимости от переданного параметра
        By locTypeValue = By.xpath("(//button[@title='"+type.getName()+"'])");
        List<WebElement> typeValues = newContact.findElements(locTypeValue);
        if(typeValues.size()==1)
            typeValues.get(0).click();
        else
            typeValues.get(typeValues.size()-1).click();

        log.info("Select type " + type.getName());
    }

    private void addFieldsForNewContact()
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement AddContactButton = personalPage.getAddContactButton();
        if(!isElementExsist(personalPage.getLocType())) // Проверяем есть ли строка для нового контакта, если нет - жмем добавить
        {
            wait.until(ExpectedConditions.visibilityOf(AddContactButton));
            AddContactButton.click();
            log.info("Click add new contact");
        }
    }

    public void addNewContact(ContactType type, String value)
    {
        PersonalPage personalPage = new PersonalPage(driver);
        int size = getContactsSize();
        if (size == 1 && isElementExsist(personalPage.getLocType()))
            size = size-1;
        addFieldsForNewContact();
        WebElement newContact = getContactByNum(size); // находим строку для нового контакта
        inputContactValue(newContact, size, value);
        selectContactType(newContact,type);
    }

    public void addNewListContact(ArrayList<Pair<ContactType,String>> contacts)
    {
        for (Pair<ContactType,String> contact : contacts) {
            addNewContact(contact.getKey(), contact.getValue());
        }
    }

    private List<WebElement> getContactsRow()
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement Contacts = personalPage.getContactsContainer();
        wait.until(ExpectedConditions.visibilityOf(Contacts));
        return Contacts.findElements(personalPage.getLocContact());
    }

    private ContactType getContactTypeValueById(int i)
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement Contacts = personalPage.getContactsContainer();
        By locTypeValue = By.xpath("(//input[@name='contact-"+i+"-service'])");
        String v = Contacts.findElement(locTypeValue).getAttribute("value");
        if (v.length() == 2)
            v = v.toUpperCase();
        else
            v=v.substring(0, 1).toUpperCase() + v.substring(1);
        return ContactType.valueOf(v);
    }

    private String getContactValueById(int i)
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement Contacts = personalPage.getContactsContainer();
        By locValue = By.xpath("(//input[@id='id_contact-"+i+"-value'])");
        return Contacts.findElement(locValue).getAttribute("value");
    }

    public ArrayList<Pair<ContactType,String>> getContacts()
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement Contacts = personalPage.getContactsContainer();
        int size = getContactsSize();
        if (size == 1 && isElementExsist(personalPage.getLocType()))
            return new ArrayList<>();
        else
        {
            List<WebElement> contactRows = getContactsRow();
            ArrayList<Pair<ContactType,String>> result = new ArrayList<>();
            for(int i=0;i<contactRows.size();i++)
            {
                ContactType type = getContactTypeValueById(i);
                String value = getContactValueById(i);
                result.add(new Pair<>(type, value));
            }
            return result;
        }
    }

    private int getExperienceSize()
    {
        return getExperienceRows().size();
    }

    private WebElement getExperienceByNum(int num)
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement Experience = personalPage.getExperienceContainer();
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

    private List<WebElement> getExperienceRows()
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement Experience = personalPage.getExperienceContainer();
        wait.until(ExpectedConditions.visibilityOf(Experience));
        return Experience.findElements(personalPage.getLocExperience());
    }

    private ProgrammingLanguage getProgrammingLanguageById(int i)
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement Experience = personalPage.getExperienceContainer();
        By locPL = By.xpath("(//select[@id='id_experience-"+i+"-experience'])");
        WebElement PL = Experience.findElement(locPL);
        List<WebElement> optionsPL = PL.findElements(By.tagName("option"));
        WebElement selectedPL=null;
        for(WebElement pl : optionsPL)
        {
            if(pl.isSelected())
                selectedPL = pl;
        }
        return ProgrammingLanguage.fromString(selectedPL.getText());
    }

    private ExperienceLevel getLevelById(int i)
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement Experience = personalPage.getExperienceContainer();
        By locLevel = By.xpath("(//select[@id='id_experience-"+i+"-level'])");
        WebElement Level = Experience.findElement(locLevel);
        List<WebElement> optionsLevel = Level.findElements(By.tagName("option"));
        WebElement selectedLevel=null;
        for(WebElement level : optionsLevel)
        {
            if(level.isSelected())
                selectedLevel = level;
        }
        return ExperienceLevel.fromString(selectedLevel.getText());
    }

    public ArrayList<Pair<ProgrammingLanguage, ExperienceLevel>> getExperience()
    {
        ArrayList<Pair<ProgrammingLanguage, ExperienceLevel>> experience = new ArrayList<>();
        List<WebElement> experienceRows = getExperienceRows();
        for(int i=0;i<experienceRows.size();i++)
        {
            ProgrammingLanguage pl = getProgrammingLanguageById(i);
            ExperienceLevel level = getLevelById(i);
            experience.add(new Pair<>(pl, level));
        }
        return experience;
    }

    private void addRowExperience()
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement AddExperinceButton = personalPage.getAddExperinceButton();
        wait.until(ExpectedConditions.visibilityOf(AddExperinceButton));
        AddExperinceButton.click();
    }

    private void selectProgrammingLanguageById(int i, ProgrammingLanguage language)
    {
        WebElement newExperience = getExperienceByNum(i);
        By locPL = By.xpath("(//select[@id='id_experience-"+i+"-experience'])");
        Select PL = new Select(newExperience.findElement(locPL));
        PL.selectByVisibleText(language.getName());
        log.info("Select Programming Language");
    }

    private void selectLevelById(int i, ExperienceLevel level)
    {
        WebElement newExperience = getExperienceByNum(i);
        By locLevel = By.xpath("(//select[@id='id_experience-"+i+"-level'])");
        Select Level = new Select(newExperience.findElement(locLevel));
        Level.selectByVisibleText(level.getName());
        log.info("Select Experience Level");
    }

    public void addExperience(ProgrammingLanguage language, ExperienceLevel level)
    {
        int size = getExperienceSize();
        addRowExperience();
        selectProgrammingLanguageById(size, language);
        selectLevelById(size,level);
    }

    public void addListExperience(ArrayList<Pair<ProgrammingLanguage,ExperienceLevel>> experience)
    {
        for (Pair<ProgrammingLanguage,ExperienceLevel> exp : experience) {
            addExperience(exp.getKey(), exp.getValue());
        }
    }

    public void setGender(Gender gender)
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement GenderSelect = personalPage.getGenderSelect();
        wait.until(ExpectedConditions.visibilityOf(GenderSelect));
        GenderSelect.click();
        By locGenderValue = By.xpath("(//option[contains(text(), '"+gender.getName()+"')])");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locGenderValue));
        GenderSelect.findElement(locGenderValue).click();
    }

    public String getGender()
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement GenderSelect = personalPage.getGenderSelect();
        List<WebElement> values = GenderSelect.findElements(By.tagName("option"));
        for(WebElement v : values)
        {
            if (v.isSelected())
                return v.getText();
        }
        return "";
    }

    public void save(WebElement saveButton) throws IOException {
        saveButton.click();
        try {
            wait.until(ExpectedConditions.visibilityOf(saveButton));
        }
        catch (TimeoutException ex)
        {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./screenshotes/ErrorSaving.png"));
        }
    }

    private void goToMenu()
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement Menu = personalPage.getMenu();
        Actions action = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(Menu));
        action.moveToElement(Menu);
        action.perform();
        Menu.click();
        log.info("go to menu");
    }

    private void clickExit()
    {
        PersonalPage personalPage = new PersonalPage(driver);
        WebElement ExitButton = personalPage.getExitButton();
        wait.until(ExpectedConditions.visibilityOf(ExitButton));
        ExitButton.click();
    }

    public void signOut()
    {
        goToMenu();
        clickExit();
    }
}
