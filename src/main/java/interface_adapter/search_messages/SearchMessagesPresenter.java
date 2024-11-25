package interface_adapter.search_messages;

import use_case.search_messages.SearchMessagesOutputBoundary;
import use_case.search_messages.SearchMessagesOutputData;
import view.SearchView;

/**
 * The SearchMessagesPresenter class is responsible for presenting the search results or error messages
 * to the user. It implements the SearchMessagesOutputBoundary and interacts with the SearchView to display
 * the results or error messages based on the outcome of the search operation.
 */
public class SearchMessagesPresenter implements SearchMessagesOutputBoundary {

    private final SearchView view;

    public SearchMessagesPresenter(SearchView view) {
        this.view = view;
    }

    @Override
    public void presentSearchResults(SearchMessagesOutputData outputData) {
        if (outputData.hasResults()) {
            final StringBuilder resultString = new StringBuilder("Results:\n");
            for (String result : outputData.getResults()) {
                resultString.append(result).append("\n");
            }
            view.showResults(resultString.toString());
        }
        else {
            view.showMessage("No messages found.");
        }
    }

    @Override
    public void presentError(String error) {
        view.showMessage("Error: " + error);
    }
}
