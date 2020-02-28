package otusqa.cases;


import javafx.util.Pair;
import org.apache.log4j.Logger;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.aeonbits.owner.ConfigFactory;
import org.testng.asserts.SoftAssert;
import otusqa.*;
import otusqa.helpers.*;
import otusqa.pages.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//@Listeners(ExecutionListener.class)
public class OtusSiteTest extends BaseTest {

    private static final Logger log = Logger.getLogger(OtusSiteTest.class);
    private static MyConfig config = ConfigFactory.create(MyConfig.class);

    @Test (description = "")
    public void verifyMainPageURLTest()
    {
        log.info("Run verifyMainPageURLTest");
        new StartPage(driver).open();
        log.info("Go to " + config.url());
        Assert.assertEquals(driver.getCurrentUrl(), config.url());
        log.info("Passed verifyMainPageURLTest!");
    }

    @Test
    public void fieldAboutMyself() throws IOException {
        String country = "Россия";
        String city = "Екатеринбург";
        Gender gender = Gender.valueOf(config.Gender());
        ArrayList<FormatWork> WorkFormates = new ArrayList<>();
        ArrayList<String> workFormatesStr = config.WorkFormates();
        for (String w :workFormatesStr){
            WorkFormates.add(FormatWork.valueOf(w));
        }
        ArrayList<String> contactsStr = config.Contacts();
        ArrayList<Pair<ContactType,String>> contacts = new ArrayList<>();
        for (String c :contactsStr){
            String[] a = c.split(":");
            contacts.add(new Pair<>(ContactType.valueOf(a[0]), a[1]));
        }

        ArrayList<String> experienceStr = config.Experience();
        ArrayList<Pair<ProgrammingLanguage,ExperienceLevel>> experience = new ArrayList<>();
        for (String e :experienceStr){
            String[] a = e.split(":");
            experience.add(new Pair<>(ProgrammingLanguage.valueOf(a[0]), ExperienceLevel.valueOf(a[1])));
        }

        WebDriverWait wait = new WebDriverWait(driver,10);
        StartPage startPage = new StartPage(driver);
        startPage.open();

        PersonalPage personalPage = startPage.goToLoginPage()
                .signIn()
                .open()
                .setFirstName(config.Name())
                .setSecondName(config.SecondName())
                .setNameInBlog(config.Name())
                .setBirthDay(config.BirthDay())
                .setCountry(country)
                .setCity(city)
                .setReadyToRelocate(false)
                .setFormatWork(WorkFormates);

        ArrayList<Pair<ContactType,String>> ExpectedContacts = personalPage.getContacts();
        for (Pair<ContactType,String> contact : contacts) {
            personalPage = personalPage.addNewContact(contact.getKey(), contact.getValue());
        }

        personalPage = personalPage.setGender(gender)
        .setCompany(config.Company())
        .setPosition(config.Position());

        ArrayList<Pair<ProgrammingLanguage,ExperienceLevel>> ExpectedExpeience = personalPage.getExperience();
        for (Pair<ProgrammingLanguage,ExperienceLevel> exp : experience) {
            personalPage = personalPage.addExperience(exp.getKey(), exp.getValue());
        }

        personalPage = personalPage.saveAndProceed()
        .signOut()
        .goToLoginPage()
        .signIn()
        .open();

        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(personalPage.getFirstName(), config.Name(),"FirstName is not correct");
        softAssert.assertEquals(personalPage.getSecondName(), config.SecondName(), "SecondName is not correct");
        softAssert.assertEquals(personalPage.getNameInBlog(), config.Name(), "Name in blog is not correct");
        softAssert.assertEquals(personalPage.getBirthDay(), config.BirthDay(), "Day of Birth is not correct");
        softAssert.assertEquals(personalPage.getCountry(), country, "Country is not correct");
        softAssert.assertEquals(personalPage.getCity(), city, "City is not correct");
        softAssert.assertEquals(personalPage.getReadyToRelocate(), false, "Ready to locate is not correct" );
        softAssert.assertEquals(personalPage.getFormatWork(), WorkFormates, "Work formate is not correct");

        ExpectedContacts.addAll(contacts);
        Set set = new HashSet(ExpectedContacts);
        ExpectedContacts = new ArrayList(set);
        Collections.sort(ExpectedContacts, new ContactTypeComparator());
        ArrayList<Pair<ContactType,String>> ActualContacts = personalPage.getContacts();
        Collections.sort(ActualContacts, new ContactTypeComparator());
        softAssert.assertEquals(ActualContacts, ExpectedContacts, "Contacts is not correct");

        softAssert.assertEquals(personalPage.getGender(), gender.getName(), "Gender is not correct");
        softAssert.assertEquals(personalPage.getCompany(), config.Company(), "Company is not correct");
        softAssert.assertEquals(personalPage.getPosition(), config.Position(), "Position is not correct");

        ExpectedExpeience.addAll(experience);
        set = new HashSet(ExpectedExpeience);
        ExpectedExpeience = new ArrayList(set);
        Collections.sort(ExpectedExpeience, new ProgrammingLanguageComparator());
        ArrayList<Pair<ProgrammingLanguage, ExperienceLevel>> ActualExperience = personalPage.getExperience();
        Collections.sort(ActualExperience, new ProgrammingLanguageComparator());
        softAssert.assertEquals(ActualExperience, ExpectedExpeience, "Experience is not correct");

        softAssert.assertAll();
    }
}