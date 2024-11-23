package entity;

public class CommonMessage implements Message {
    private final String sender;
    private final String recipient;
    private final String originalmessage;
    private final String translatedmessage;

    public CommonMessage(String sender, String recipient, String originalmessage,
                         String translatedmessage) {
        this.sender = sender;
        this.recipient = recipient;
        this.originalmessage = originalmessage;
        this.translatedmessage = translatedmessage;
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public String getRecipient() {
        return recipient;
    }

    @Override
    public String getOriginalLanguage() {
        return originalmessage;
    }

    @Override
    public String getTranslatedContent() {
        return translatedmessage;
    }

}
