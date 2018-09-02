package nnglebanov.auto.mantis.model;

public class MailMessage {

    public String to;
    public String text;

    @Override
    public String toString() {
        return "MailMessage{" +
                "to='" + to + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}