package interface_adapter.add_friend;

import interface_adapter.ViewModel;

/**
 * The View Model for the Add Friend View.
 */
public class AddFriendViewModel extends ViewModel<AddFriendState> {

    public AddFriendViewModel() {
        super("add friend");
        setState(new AddFriendState());
    }
}
