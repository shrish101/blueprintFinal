package use_case.add_friend;

import entity.User;
import java.util.List;

/**
 * The Add Friend Interactor.
 */
public class AddFriendInteractor implements AddFriendInputBoundary {
    private final AddFriendUserDataAccessInterface userDataAccessObject;
    private final AddFriendOutputBoundary addFriendPresenter;

    public AddFriendInteractor(AddFriendUserDataAccessInterface userDataAccessInterface,
                               AddFriendOutputBoundary addFriendOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.addFriendPresenter = addFriendOutputBoundary;
    }

    @Override
    public void execute(AddFriendInputData addFriendInputData) {
        final String username = addFriendInputData.getCurrentUsername();
        final String friendUsername = addFriendInputData.getFriendUsername();

        // Check if the current user exists
        if (!userDataAccessObject.existsByName(username)) {
            addFriendPresenter.prepareFailView(username + ": Account does not exist.");
        }
        // Check if the friend exists
        else if (!userDataAccessObject.existsByName(friendUsername)) {
            addFriendPresenter.prepareFailView(friendUsername + ": Friend account does not exist.");
        }
        // Check if the user is trying to add themselves
        else if (username.equals(friendUsername)) {
            addFriendPresenter.prepareFailView("You cannot add yourself as a friend.");
        }
        else {
            // Add the friend to the user's friend list
            userDataAccessObject.addFriend(username, friendUsername);

            // Get the updated list of friends
            final List<String> updatedFriends = userDataAccessObject.getFriends(username);

            // Prepare the success view with the updated friends list
            // final AddFriendOutputData addFriendOutputData = new AddFriendOutputData(updatedFriends);
            // addFriendPresenter.prepareSuccessView(addFriendOutputData);
        }
    }

    @Override
    public void addFriend(AddFriendInputData friendInputData) {

    }
}