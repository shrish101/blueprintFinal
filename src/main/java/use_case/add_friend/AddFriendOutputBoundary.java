package use_case.add_friend;

/**
 * The Output Boundary for the AddFriend use case.
 */
public interface AddFriendOutputBoundary {

    /**
     * Prepares the success view with the output data.
     * @param outputData the output data
     */
    void prepareSuccessView(AddFriendOutputData outputData);

    /**
     * Prepares the failure view with an error message.
     * @param outputData the error message
     */
    void prepareFailView(AddFriendOutputData outputData);
}
