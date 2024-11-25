package use_case.search_messages;

import entity.Message;

import java.util.ArrayList;
import java.util.List;

public class SearchMessagesInteractor implements SearchMessagesInputBoundary {

    private final SearchMessagesUserDataAccessInterface dataAccess;
    private final SearchMessagesOutputBoundary presenter;

    public SearchMessagesInteractor(SearchMessagesUserDataAccessInterface dataAccess, SearchMessagesOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void searchMessages(SearchMessagesInputData inputData) {
        try {
            String query = inputData.getQuery();
            List<Message> allMessages = dataAccess.getAllMessages();
            List<String> results = new ArrayList<>();

            for (Message message : allMessages) {
                if (message.getTranslatedContent().toLowerCase().contains(query.toLowerCase())) {
                    String sender = message.getSender();
                    String recipient = message.getRecipient();
                    String originalMessage = message.getOriginalLanguage();
                    String translatedMessage = message.getTranslatedContent();

                    results.add(String.format("From: %s, To: %s, Message: %s, Translation: %s", sender, recipient, originalMessage, translatedMessage));
                }
            }

            SearchMessagesOutputData outputData = new SearchMessagesOutputData(results, !results.isEmpty());
            presenter.presentSearchResults(outputData);

        } catch (Exception e) {
            presenter.presentError("Error connecting to database: " + e.getMessage());
        }
    }
}


