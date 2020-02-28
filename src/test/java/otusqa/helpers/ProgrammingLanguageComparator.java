package otusqa.helpers;

import javafx.util.Pair;
import otusqa.ContactType;
import otusqa.ExperienceLevel;
import otusqa.ProgrammingLanguage;

import java.util.Comparator;

public class ProgrammingLanguageComparator implements Comparator<Pair<ProgrammingLanguage,ExperienceLevel>> {

    @Override
    public int compare(Pair<ProgrammingLanguage, ExperienceLevel> programmingLanguageStringPair, Pair<ProgrammingLanguage, ExperienceLevel> t1) {
        return programmingLanguageStringPair.getKey().getName().compareTo(t1.getKey().getName());
    }
}
