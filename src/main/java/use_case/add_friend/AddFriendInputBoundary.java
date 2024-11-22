package use_case.add_friend;

public interface AddFriendInputBoundary {

    /**
     * Executes the login use case.
     * @param friendInputData the input data
     */
    void addFriend(AddFriendInputData friendInputData);

    void execute(AddFriendInputData friendInputData);
}