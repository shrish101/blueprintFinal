package use_case.add_friend;

/**
 * The Output Boundary for the AddFriend use case.
 */
public interface AddFriendOutputBoundary {

    /**
     * Prepares the success view with the output data.
     * @param success the output data
     */
    void prepareSuccessView(String success);

    /**
     * Prepares the failure view with an error message.
     * @param error the error message
     */
    void prepareFailView(String error);
}