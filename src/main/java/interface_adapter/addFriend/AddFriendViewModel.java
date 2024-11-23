package interface_adapter.addFriend;

import interface_adapter.ViewModel;

/**
 * The View Model for the Add Friend View.
 */
public class AddFriendViewModel extends ViewModel<AddFriendState> {

    public AddFriendViewModel() {
        super("add friend");
        setState(new AddFriendState());  // Initializes the state with an AddFriendState object
    }
}