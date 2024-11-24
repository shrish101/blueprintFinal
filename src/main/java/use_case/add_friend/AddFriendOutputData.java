package use_case.add_friend;

/**
 * The Output Data for the AddFriend Use Case.
 */
public class AddFriendOutputData {

    private final String friendUsername;

    public AddFriendOutputData(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    public String getFriendUsername() {
        return friendUsername;
    }
}