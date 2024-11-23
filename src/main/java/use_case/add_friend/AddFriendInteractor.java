package use_case.add_friend;

/**
 * The interactor for adding a friend.
 */
public class AddFriendInteractor implements AddFriendInputBoundary {

    private final AddFriendOutputBoundary presenter;

    public AddFriendInteractor(AddFriendOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(AddFriendInputData addFriendInputData) {
        String username = addFriendInputData.getUsername();
        String friendUsername = addFriendInputData.getFriendUsername();

        // Add validation or logic for adding a friend here.
        if (username.equals(friendUsername)) {
            presenter.prepareFailView("You cannot add yourself as a friend.");
        } else {
            AddFriendOutputData outputData = new AddFriendOutputData(friendUsername);
            presenter.prepareSuccessView(outputData);
        }
    }
}