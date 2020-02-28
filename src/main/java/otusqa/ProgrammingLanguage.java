package otusqa;

public enum ProgrammingLanguage {
    C("C"),
    CPlus("C++"),
    CSharp("C#"),
    Java("Java"),
    Go("Go"),
    ObjectiveC("Objective C/iOS"),
    Android("Android"),
    Perl("Perl"),
    Ruby("Ruby"),
    Python("Python"),
    PHP("PHP"),
    Javascript("Javascript"),
    R("R");

    private String name;

    ProgrammingLanguage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ProgrammingLanguage fromString(String n) {
        for (ProgrammingLanguage b : ProgrammingLanguage.values()) {
            if (b.name.equalsIgnoreCase(n)) {
                return b;
            }
        }
        return null;
    }
}
