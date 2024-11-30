package use_case.fetch_friends;

/**
 * Represents the input data required for the Fetch Friends use case.
 * Encapsulates the user ID of the user whose friends are to be fetched.
 */
public class FetchFriendsInputData {
    private final String userId;

    public FetchFriendsInputData(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
