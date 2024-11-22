package use_case.add_friend;

/**
 * Input data for adding a friend.
 */
public class AddFriendInputData {

    private final String currentUsername; // the username of the current user
    private final String friendUsername;  // the username of the friend to be added

    /**
     * Constructor to initialize the input data with both usernames.
     *
     * @param currentUsername the username of the current user
     * @param friendUsername  the username of the friend to be added
     */
    public AddFriendInputData(String currentUsername, String friendUsername) {
        this.currentUsername = currentUsername;
        this.friendUsername = friendUsername;
    }

    /**
     * Gets the username of the current user.
     *
     * @return the current user's username
     */
    public String getCurrentUsername() {
        return currentUsername;
    }

    /**
     * Gets the username of the friend to be added.
     *
     * @return the friend's username
     */
    public String getFriendUsername() {
        return friendUsername;
    }
}