package interface_adapter.addFriend;

import java.util.List;

import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.add_friend.AddFriendOutputBoundary;
import use_case.add_friend.AddFriendOutputData;

/**
 * The Presenter for the Add Friend Use Case.
 */
public class AddFriendPresenter implements AddFriendOutputBoundary {

    private final AddFriendViewModel addFriendViewModel;
    private final LoggedInViewModel loggedInViewModel;

    public AddFriendPresenter(AddFriendViewModel addFriendViewModel, LoggedInViewModel loggedInViewModel) {
        this.addFriendViewModel = addFriendViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(AddFriendOutputData data) {

        final LoggedInState loggedInState = loggedInViewModel.getState();
        final List<String> friends = loggedInState.getFriends();
        final AddFriendState addFriendState = addFriendViewModel.getState();
        addFriendState.setSuccessMessage(data.getFriendUsername());
        addFriendViewModel.firePropertyChanged();
        friends.add(addFriendState.getFriendUsername());
        loggedInState.setFriends(friends);
        loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(AddFriendOutputData data) {
        final AddFriendState addFriendState = addFriendViewModel.getState();
        addFriendState.setErrorMessage(data.getFriendUsername());
        addFriendViewModel.firePropertyChanged();
    }
}

