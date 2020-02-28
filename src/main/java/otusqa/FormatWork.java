package otusqa;

public enum FormatWork {
    FullDay("Полный день"),
    Remotely("Удаленно"),
    Flexible("Гибкий график");

    private String name;

    FormatWork(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
