package interface_adapter.addFriend;

import interface_adapter.login.LoginState;
import use_case.add_friend.AddFriendOutputData;
import use_case.add_friend.AddFriendOutputBoundary;

/**
 * The Presenter for the Add Friend Use Case.
 */
public class AddFriendPresenter implements AddFriendOutputBoundary {

    private final AddFriendViewModel addFriendViewModel;

    public AddFriendPresenter(AddFriendViewModel addFriendViewModel) {
        this.addFriendViewModel = addFriendViewModel;
    }

    @Override
    public void prepareSuccessView(AddFriendOutputData response) {

    }

    @Override
    public void prepareFailView(String error) {
        final AddFriendState addFriendState = addFriendViewModel.getState();
        addFriendState.setErrorMessage(error);
        addFriendViewModel.firePropertyChanged();
    }
}