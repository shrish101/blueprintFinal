package interface_adapter.search_messages;

import use_case.search_messages.SearchMessagesOutputBoundary;
import use_case.search_messages.SearchMessagesOutputData;
import view.SearchView;

import javax.swing.*;
import java.awt.*;

public class SearchMessagesPresenter implements SearchMessagesOutputBoundary {

    private final SearchView view;

    public SearchMessagesPresenter(SearchView view) {
        this.view = view;
    }

    @Override
    public void presentSearchResults(SearchMessagesOutputData outputData) {
        if (outputData.hasResults()) {
            StringBuilder resultString = new StringBuilder("Results:\n");
            for (String result : outputData.getResults()) {
                resultString.append(result).append("\n");
            }
            view.showResults(resultString.toString());
        } else {
            view.showMessage("No messages found.");
        }
    }

    @Override
    public void presentError(String error) {
        view.showMessage("Error: " + error);
    }
}



