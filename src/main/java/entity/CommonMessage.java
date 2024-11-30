package entity;

import app.LibreTranslateClient;

/**
 * Represents a message with sender, recipient, original content, and translated content.
 * This class implements the {@link Message} interface and provides methods to retrieve
 * the sender, recipient, original message, and translated message.
 */
public class CommonMessage implements Message {
    private final String sender;
    private final String recipient;
    private final String originalmessage;
    private final String translatedmessage;
    private final String recipientLanguage;
    private LibreTranslateClient client = new LibreTranslateClient("http://localhost:5000");

    public CommonMessage(String sender, String recipient, String originalmessage,
                         String translatedmessage, String recipientLanguage) {
        this.sender = sender;
        this.recipient = recipient;
        this.originalmessage = originalmessage;
        this.translatedmessage = translatedmessage;
        this.recipientLanguage = recipientLanguage;
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
    public String getTranslatedContent(String lang) {
        final String originalLanguage = client.detectLanguage(translatedmessage);
        return client.translate(translatedmessage, originalLanguage, lang);
    }
}
