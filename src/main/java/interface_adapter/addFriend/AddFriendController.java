package interface_adapter.addFriend;

import use_case.add_friend.AddFriendInputBoundary;
import use_case.add_friend.AddFriendInputData;

/**
 * Controller class responsible for handling the "add friend" action.
 * It communicates with the {@link AddFriendInputBoundary} to process the
 * input data for adding a friend.
 */
public class AddFriendController {

    private final AddFriendInputBoundary addFriendInputBoundary;

    public AddFriendController(AddFriendInputBoundary addFriendInputBoundary) {
        this.addFriendInputBoundary = addFriendInputBoundary;
    }

    /**
     * Executes the action of adding a friend by passing the user and friend usernames
     * to the {@link AddFriendInputBoundary}.
     *
     * @param username the username of the user who wants to add a friend.
     * @param friendUsername the username of the person to be added as a friend.
     */
    public void execute(String username, String friendUsername) {
        final AddFriendInputData inputData = new AddFriendInputData(username, friendUsername);
        addFriendInputBoundary.execute(inputData);
    }
}
