package use_case.search_messages;

import java.util.List;

/**
 * The SearchMessagesOutputData class represents the data structure used to hold the results
 * of a search operation for messages. It contains the list of results and a flag indicating
 * whether there were any results found.
 */
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

    /**
     * Checks if the search operation found any results.
     *
     * @return A boolean indicating whether there were any results.
     */
    public boolean hasResults() {
        return hasResults;
    }
}
