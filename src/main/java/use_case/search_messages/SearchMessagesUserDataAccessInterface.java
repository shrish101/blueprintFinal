package use_case.search_messages;

import java.util.List;

import entity.Message;

/**
 * The SearchMessagesUserDataAccessInterface interface defines the contract for accessing
 * the data related to messages in the system. Implementations of this interface will
 * provide the mechanism to retrieve all messages.
 */
public interface SearchMessagesUserDataAccessInterface {

    /**
     * Retrieves all messages from the data source.
     *
     * @return A list of Message objects representing all the messages in the system.
     * @throws Exception If an error occurs while retrieving the messages.
     */
    List<Message> getAllMessages() throws Exception;
}
