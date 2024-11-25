package use_case.add_friend;

/**
 * Input Boundary for actions related to adding a friend.
 */
public interface AddFriendInputBoundary {

    /**
     * Executes the add friend use case.
     * @param addFriendInputData the input data
     */
    void execute(AddFriendInputData addFriendInputData);
}
