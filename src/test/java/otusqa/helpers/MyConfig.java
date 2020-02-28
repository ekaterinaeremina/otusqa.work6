package otusqa.helpers;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

import java.util.ArrayList;


@Sources({"classpath:Config.properties"})
public interface MyConfig extends Config {
    @DefaultValue("Test")
    @Key("browser.name")
    String browserName();

    @DefaultValue("https://otus.ru/")
    @Key("url")
    String url();

    @DefaultValue("Test")
    @Key("Name")
    String Name();

    @DefaultValue("Test")
    @Key("SecondName")
    String SecondName();

    @DefaultValue("01.01.1999")
    @Key("BirthDay")
    String BirthDay();

    @DefaultValue("Россия")
    @Key("Country")
    String Counrty();

    @DefaultValue("Москва")
    @Key("City")
    String City();

    @DefaultValue("Telegram")
    @Key("ContactType")
    String ContactType();

    @DefaultValue("TelegramValue")
    @Key("ContactValue")
    String ContactValue();

    @DefaultValue("Не указано")
    @Key("Gender")
    String Gender();

    @DefaultValue("Test")
    @Key("Company")
    String Company();

    @DefaultValue("Test")
    @Key("Position")
    String Position();

    @DefaultValue("FullDay")
    @Key("WorkFormates")
    ArrayList<String> WorkFormates();

    @DefaultValue("Telegram:TelegramValue")
    @Key("Contacts")
    ArrayList<String> Contacts();

    @DefaultValue("Javascript:Junior")
    @Key("Experience")
    ArrayList<String> Experience();
}
