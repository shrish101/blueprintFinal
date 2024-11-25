package interface_adapter.search_messages;

import javax.swing.JOptionPane;

import use_case.search_messages.SearchMessagesInputBoundary;
import use_case.search_messages.SearchMessagesInputData;

/**
 * The SearchMessagesController class handles the user input for searching messages.
 * It interacts with the SearchMessagesInputBoundary to execute the search functionality.
 * This class also manages validation of the search query and displays error messages if necessary.
 */
public class SearchMessagesController {

    private final SearchMessagesInputBoundary interactor;

    public SearchMessagesController(SearchMessagesInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles the search operation by validating the query and passing it to the interactor
     * to search messages. If the query is empty, an error message is displayed.
     *
     * @param query the search query to be used in the message search
     */
    public void handleSearch(String query) {
        if (query.isEmpty()) {
            // Handle empty query error
            JOptionPane.showMessageDialog(null, "Search query cannot be empty.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final SearchMessagesInputData inputData = new SearchMessagesInputData(query);
        interactor.searchMessages(inputData);
    }
}
