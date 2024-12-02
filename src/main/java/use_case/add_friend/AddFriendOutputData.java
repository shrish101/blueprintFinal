package use_case.add_friend;

/**
 * The Output Data for the AddFriend Use Case.
 */
public class AddFriendOutputData {

    private final boolean useCaseFailed;
    private final String message;

    public AddFriendOutputData(String message, boolean useCaseFailed) {
        this.message = message;
        this.useCaseFailed = useCaseFailed;
    }

    public String getFriendUsername() {
        return message;
    }
}

