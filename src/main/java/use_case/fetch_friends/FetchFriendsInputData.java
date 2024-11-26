package use_case.fetch_friends;

public class FetchFriendsInputData {
    private final String userId;

    public FetchFriendsInputData(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}