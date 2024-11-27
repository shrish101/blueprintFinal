package interface_adapter.fetchFriend;

import interface_adapter.ViewModel;

/**
 * The View Model for the Fetch Friends View.
 */
public class FetchFriendViewModel extends ViewModel<FetchFriendState> {

    public FetchFriendViewModel() {
        super("fetch friend");
        setState(new FetchFriendState());
    }
}
