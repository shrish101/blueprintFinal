package use_case.add_friend;

/**
 * Input Data for the AddFriend Use Case.
 */
public class AddFriendInputData {

    private final String username;
    private final String friendUsername;

    public AddFriendInputData(String username, String friendUsername) {
        this.username = username;
        this.friendUsername = friendUsername;
    }

    public String getUsername() {
        return username;
    }

    public String getFriendUsername() {
        return friendUsername;
    }
}