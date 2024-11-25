package use_case.search_messages;

public interface SearchMessagesOutputBoundary {
    void presentSearchResults(SearchMessagesOutputData outputData);
    void presentError(String error);
}



