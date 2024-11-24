package use_case.edit_message;

import entity.Message;

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
