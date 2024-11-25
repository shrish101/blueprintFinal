package use_case.search_messages;

/**
 * The SearchMessagesInputData class holds the input data for searching messages.
 * It encapsulates the query string that will be used for the message search.
 */
public class SearchMessagesInputData {
    private final String query;

    public SearchMessagesInputData(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
