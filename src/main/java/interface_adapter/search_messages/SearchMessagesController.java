package interface_adapter.search_messages;

import use_case.search_messages.SearchMessagesInputBoundary;
import use_case.search_messages.SearchMessagesInputData;

import javax.swing.*;
import java.awt.*;

public class SearchMessagesController {

    private final SearchMessagesInputBoundary interactor;

    public SearchMessagesController(SearchMessagesInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleSearch(String query) {
        if (query.isEmpty()) {
            // Handle empty query error
            JOptionPane.showMessageDialog(null, "Search query cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SearchMessagesInputData inputData = new SearchMessagesInputData(query);
        interactor.searchMessages(inputData);
    }
}


