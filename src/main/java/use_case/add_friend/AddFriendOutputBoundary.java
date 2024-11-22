package use_case.add_friend;

public interface AddFriendOutputBoundary {

    /**
     * Prepares the success view for the Login Use Case.
     */
    void friendAddedSuccess();

    /**
     * Prepares the success view for the Login Use Case.
     * @param errorMessage the error message
     */
    void friendAddedFailure(String errorMessage);

    void prepareFailView(String s);

    void prepareSuccessView(AddFriendOutputData addFriendOutputData);
}