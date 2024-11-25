package use_case.messaging;

/**
 * The Message Data for the Message Use Case.
 */

public class MessageInputData {
    private String currUser;
    private String currFriend;
    private String translatedMessage;
    private String orignalMessage;

    public MessageInputData(String currUser, String currFriend, String translatedMessage, String orignalMessage) {
        this.currUser = currUser;
        this.currFriend = currFriend;
        this.translatedMessage = translatedMessage;
        this.orignalMessage = orignalMessage;
    }

    public String getCurrUser() {
        return currUser;
    }

    public String getCurrFriend() {
        return currFriend;
    }

    public String getTranslatedMessage() {
        return translatedMessage;
    }

    public String getOrignalMessage() {
        return orignalMessage;
    }

}
