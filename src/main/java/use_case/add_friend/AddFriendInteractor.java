package use_case.add_friend;

/**
 * The interactor for adding a friend.
 */
public class AddFriendInteractor implements AddFriendInputBoundary {

    private final AddFriendOutputBoundary presenter;
    private final AddFriendUserDataAccessInterface userDataAccessObject;

    public AddFriendInteractor(AddFriendOutputBoundary presenter,
                               AddFriendUserDataAccessInterface userDataAccessObject) {
        this.presenter = presenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(AddFriendInputData addFriendInputData) {
        final String username = addFriendInputData.getUsername();
        final String friendUsername = addFriendInputData.getFriendUsername();
        if (!userDataAccessObject.existsByName(friendUsername)) {
            presenter.prepareFailView(friendUsername + ": Account does not exist.");
        }
        // Add validation or logic for adding a friend here.
        else if (username.equals(friendUsername)) {
            presenter.prepareFailView("You cannot add yourself as a friend.");
        }
        else {
            final boolean check = userDataAccessObject.addFriend(username, friendUsername);
            if (!check) {
                presenter.prepareFailView("You have already added " + friendUsername + " as your friend.");
            }
            else {
                final AddFriendOutputData outputData = new AddFriendOutputData(friendUsername, false);
                presenter.prepareSuccessView(friendUsername + " successfully added as your friend!");
            }
        }
    }
}
