package use_case.search_messages;

import java.util.ArrayList;
import java.util.List;

import entity.Message;

/**
 * The SearchMessagesInteractor is responsible for handling the logic for searching messages.
 * It retrieves all messages from the data access layer, filters them based on the provided query,
 * and presents the results to the user through the presenter.
 */

public class SearchMessagesInteractor implements SearchMessagesInputBoundary {

    private final SearchMessagesUserDataAccessInterface dataAccess;
    private final SearchMessagesOutputBoundary presenter;

    public SearchMessagesInteractor(SearchMessagesUserDataAccessInterface dataAccess,
                                    SearchMessagesOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void searchMessages(SearchMessagesInputData inputData) {
        try {
            final String query = inputData.getQuery();
            final List<Message> allMessages = dataAccess.getAllMessages();
            final List<String> results = new ArrayList<>();

            for (Message message : allMessages) {
                if (message.getOriginalLanguage().toLowerCase().contains(query.toLowerCase())) {
                    final String originalMessage = message.getOriginalLanguage();

                    results.add(String.format("From: %s, To: %s, Message: %s, Translation: %s", originalMessage));
                }
            }

            final SearchMessagesOutputData outputData = new SearchMessagesOutputData(results, !results.isEmpty());
            presenter.presentSearchResults(outputData);

        }
        catch (Exception e) {
            presenter.presentError("Error connecting to database: " + e.getMessage());
        }
    }
}
