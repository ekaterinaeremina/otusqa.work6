package otusqa;

public enum Gender {
    None("Не указано"),
    Male("Мужской"),
    Female("Женский");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
