package use_case.add_friend;

/**
 * The Output Data for the AddFriend Use Case.
 */
public class AddFriendOutputData {

    private final boolean useCaseFailed;
    private final String friendUsername;

    public AddFriendOutputData(String friendUsername, boolean useCaseFailed) {
        this.friendUsername = friendUsername;
        this.useCaseFailed = useCaseFailed;
    }

    public String getFriendUsername() {
        return friendUsername;
    }
}