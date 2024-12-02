package use_case.edit_message;

import entity.Message;

/**
 * The EditUserDataAccessInterface interface defines methods for retrieving and updating user messages.
 * It provides functionality to get the latest message sent by a user and update the content of a message.
 */
public interface EditUserDataAccessInterface {

    /**
     * Retrieves the latest message sent by the specified user.
     *
     * @param user1 The username of the sender.
     * @param user2 The username of reciever
     * @return The latest message sent by the user, or null if no message is found.
     */
    Message getLatestMessageByUser(String user1, String user2);

    /**
     * Updates the content of the specified message.
     *
     * @param newContent The new content for the message.
     * @param user1 The username of sender
     * @param user2 The username of reciever
     */
    void updateMessage(String user1, String user2, String newContent);
}
