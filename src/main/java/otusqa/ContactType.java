package otusqa;

public enum ContactType {
    Facebook("Facebook"),
    VK("VK"),
    OK("OK"),
    Skype("Skype"),
    Viber("Viber"),
    Telegram("Тelegram"),
    WhatsApp("WhatsApp");

    private String name;

    ContactType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
