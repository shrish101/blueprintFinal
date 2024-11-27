package entity;

/**
 * The representation of a message in our program.
 */
public interface Message {

    /**
     * Returns the sender of the message.
     * @return the username of the sender.
     */
    String getSender();

    /**
     * Returns the recipient of the message.
     * @return the username of the recipient.
     */
    String getRecipient();

    /**
     * Returns the original content of the message.
     * @param language the current language of user.
     * @return the content of the message in the original language.
     */
    String getTranslatedContent(String language);

    /**
     * Returns the original language of the message.
     * @return the language in which the message was sent.
     */
    String getOriginalLanguage();

}
