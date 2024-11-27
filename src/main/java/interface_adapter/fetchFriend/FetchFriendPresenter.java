package interface_adapter.fetchFriend;

import use_case.fetch_friends.FetchFriendsOutputBoundary;
import use_case.fetch_friends.FetchFriendsOutputData;

/**
 * A presenter for the Fetch Friends use case.
 * Responsible for preparing the data to be displayed in the view model.
 * Implements the {@code FetchFriendsOutputBoundary} interface to handle success and failure scenarios.
 */
public class FetchFriendPresenter implements FetchFriendsOutputBoundary {

    private final FetchFriendViewModel fetchFriendsViewModel;

    public FetchFriendPresenter(FetchFriendViewModel fetchFriendsViewModel) {
        this.fetchFriendsViewModel = fetchFriendsViewModel;
    }

    @Override
    public void prepareSuccessView(FetchFriendsOutputData outputData) {
        final FetchFriendState fetchFriendState = fetchFriendsViewModel.getState();
        fetchFriendState.setFriendsList(outputData.getFriends());
        System.out.println(fetchFriendState.getFriendsList());
        fetchFriendState.setSuccessMessage("Friends list fetched successfully.");
        fetchFriendsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final FetchFriendState fetchFriendState = fetchFriendsViewModel.getState();
        fetchFriendState.setErrorMessage(error);
        fetchFriendsViewModel.firePropertyChanged();
    }
}
