package interface_adapter.fetchFriend;

import use_case.fetch_friends.FetchFriendsInputBoundary;
import use_case.fetch_friends.FetchFriendsInputData;

public class FetchFriendController {
    private final FetchFriendsInputBoundary fetchFriendsInputInteractor;

    public FetchFriendController(FetchFriendsInputBoundary fetchFriendsInputInteractor) {
        this.fetchFriendsInputInteractor = fetchFriendsInputInteractor;
    }

    /**
     * Executes the Fetch Friends Use Case.
     * @param userId the user whose friends list we want to fetch
     */
    public void execute(String userId) {
        // Create the input data object with the userId
        final FetchFriendsInputData fetchFriendsInputData = new FetchFriendsInputData(userId);

        fetchFriendsInputInteractor.execute(fetchFriendsInputData);
    }
}
