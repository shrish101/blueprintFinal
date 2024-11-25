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
     * @param user The username of the sender.
     * @return The latest message sent by the user, or null if no message is found.
     */
    Message getLatestMessageByUser(String user);

    /**
     * Updates the content of the specified message.
     *
     * @param latestMessage The ID of the message to update.
     * @param newContent The new content for the message.
     */
    void updateMessage(Message latestMessage, String newContent);
}
