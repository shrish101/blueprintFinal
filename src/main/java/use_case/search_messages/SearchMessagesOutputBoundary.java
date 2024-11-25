package use_case.search_messages;

/**
 * The SearchMessagesOutputBoundary interface defines the methods for presenting the search results
 * or an error related to searching messages.
 * Implementing classes are responsible for displaying the results or error messages to the user.
 */
public interface SearchMessagesOutputBoundary {

    /**
     * Presents the search results to the user.
     *
     * @param outputData The data containing the search results to be displayed.
     */
    void presentSearchResults(SearchMessagesOutputData outputData);

    /**
     * Presents an error message to the user.
     *
     * @param error The error message to be displayed.
     */
    void presentError(String error);
}
