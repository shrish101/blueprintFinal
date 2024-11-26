package use_case.fetch_friends;

import java.util.List;

/**
 * The Interactor for the Fetch Friends Use Case.
 */
public class FetchFriendsInteractor implements FetchFriendsInputBoundary {
    private final FetchFriendsUserDataAccessInterface userDataAccessObject;
    private final FetchFriendsOutputBoundary outputBoundary;

    public FetchFriendsInteractor(FetchFriendsUserDataAccessInterface userDataAccessObject, FetchFriendsOutputBoundary outputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(FetchFriendsInputData inputData) {
        try {
            // Fetch friends list
            List<String> friends = userDataAccessObject.getFriendsList(inputData.getUserId());
            System.out.println(friends);

            // Prepare output data and pass to presenter
            FetchFriendsOutputData outputData = new FetchFriendsOutputData(friends);
            System.out.println(outputData.getFriends());
            outputBoundary.prepareSuccessView(outputData);

        } catch (Exception e) {
            // Handle the case where fetching friends fails
            outputBoundary.prepareFailView(e.getMessage());
        }
    }
}
