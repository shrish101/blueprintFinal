package use_case.search_messages;

/**
 * The SearchMessagesInputBoundary interface defines a method for searching messages.
 * It is used by the application to initiate a search for messages based on input data.
 */
public interface SearchMessagesInputBoundary {

    /**
     * Initiates the search for messages based on the provided input data.
     *
     * @param inputData The data that contains the search query and parameters.
     */
    void searchMessages(SearchMessagesInputData inputData);
}
