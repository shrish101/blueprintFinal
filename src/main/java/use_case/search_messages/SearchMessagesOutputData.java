package use_case.search_messages;

import java.util.List;

public class SearchMessagesOutputData {
    private final List<String> results;
    private final boolean hasResults;

    public SearchMessagesOutputData(List<String> results, boolean hasResults) {
        this.results = results;
        this.hasResults = hasResults;
    }

    public List<String> getResults() {
        return results;
    }

    public boolean hasResults() {
        return hasResults;
    }
}





