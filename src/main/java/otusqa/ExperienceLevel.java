package otusqa;

public enum ExperienceLevel {
    Junior("Только начал"),
    Year("1 год"),
    TwoYears("2 года"),
    ThreeFiveYears("3-5 лет"),
    FiveSevenYears("5-7 лет"),
    SevenTenYears("7-10 лет"),
    MoreThenTenYears("Более 10 лет");

    private String name;

    ExperienceLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ExperienceLevel fromString(String n) {
        for (ExperienceLevel b : ExperienceLevel.values()) {
            if (b.name.equalsIgnoreCase(n)) {
                return b;
            }
        }
        return null;
    }
}
