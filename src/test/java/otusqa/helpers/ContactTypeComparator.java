package otusqa.helpers;

import javafx.util.Pair;
import otusqa.ContactType;
import otusqa.ExperienceLevel;
import otusqa.ProgrammingLanguage;

import java.util.Comparator;

public class ContactTypeComparator implements Comparator<Pair<ContactType,String>> {

    @Override
    public int compare(Pair<ContactType, String> contactTypeStringPair, Pair<ContactType, String> t1) {
        return contactTypeStringPair.getKey().getName().compareTo(t1.getKey().getName());
    }
}
