package interface_adapter.addFriend;

import use_case.add_friend.AddFriendInputBoundary;
import use_case.add_friend.AddFriendInputData;
import use_case.add_friend.AddFriendOutputBoundary;

/**
 * Controller for handling Add Friend functionality.
 */
public class AddFriendController {

    private final AddFriendInputBoundary addFriendInputBoundary;
    private final AddFriendOutputBoundary addFriendOutputBoundary;

    /**
     * Constructor for AddFriendController.
     *
     * @param addFriendInputBoundary the input boundary for the use case
     * @param addFriendOutputBoundary the output boundary for the use case
     */
    public AddFriendController(AddFriendInputBoundary addFriendInputBoundary,
                               AddFriendOutputBoundary addFriendOutputBoundary) {
        this.addFriendInputBoundary = addFriendInputBoundary;
        this.addFriendOutputBoundary = addFriendOutputBoundary;
    }

    /**
     * Adds a friend by executing the AddFriend use case.
     *
     * @param username the username of the current user
     * @param friendUsername the username of the friend to be added
     */
    public void addFriend(String username, String friendUsername) {
        // Create input data for the use case
        final AddFriendInputData inputData = new AddFriendInputData(username, friendUsername);

        // Execute the use case
        addFriendInputBoundary.execute(inputData);
    }
}