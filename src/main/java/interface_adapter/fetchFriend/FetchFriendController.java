package interface_adapter.fetchFriend;

import use_case.fetch_friends.FetchFriendsInputBoundary;
import use_case.fetch_friends.FetchFriendsInputData;
import use_case.fetch_friends.FetchFriendsOutputBoundary;
import use_case.fetch_friends.FetchFriendsOutputData;

public class FetchFriendController {
    private final FetchFriendsInputBoundary fetchFriendsInputInteractor;
    private final FetchFriendsOutputBoundary fetchFriendsOutputBoundary;

    public FetchFriendController(FetchFriendsInputBoundary fetchFriendsInputInteractor,
                                 FetchFriendsOutputBoundary fetchFriendsOutputBoundary) {
        this.fetchFriendsInputInteractor = fetchFriendsInputInteractor;
        this.fetchFriendsOutputBoundary = fetchFriendsOutputBoundary;
    }

    /**
     * Executes the Fetch Friends Use Case.
     * @param userId the user whose friends list we want to fetch
     */
    public void execute(String userId) {
        // Create the input data object with the userId
        final FetchFriendsInputData fetchFriendsInputData = new FetchFriendsInputData(userId);

        // Execute the use case
        FetchFriendsOutputData outputData = fetchFriendsInputInteractor.execute(fetchFriendsInputData);

        // Use the output boundary to present the result (friends list)
        fetchFriendsOutputBoundary.presentFriends(outputData);
    }
}
