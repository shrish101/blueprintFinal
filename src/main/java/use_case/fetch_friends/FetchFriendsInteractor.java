package use_case.fetch_friends;


import java.util.List;

public class FetchFriendsInteractor implements FetchFriendsInputBoundary {
    private final FetchFriendsUserDataAccessInterface userDataAccessObject;
    private final FetchFriendsOutputBoundary outputBoundary;

    public FetchFriendsInteractor(FetchFriendsUserDataAccessInterface userDataAccessObject, FetchFriendsOutputBoundary outputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public FetchFriendsOutputData fetchFriends(FetchFriendsInputData inputData) {
        // Fetch friends list
        List<String> friends = userDataAccessObject.getFriendsList(inputData.getUserId());

        // Prepare output data and pass to presenter
        FetchFriendsOutputData outputData = new FetchFriendsOutputData(friends);
        outputBoundary.presentFriends(outputData);

        return outputData;
    }
}