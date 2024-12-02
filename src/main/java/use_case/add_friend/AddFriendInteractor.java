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
            final AddFriendOutputData outputData = new AddFriendOutputData(friendUsername + ": Account does not exist.",
                    true);
            presenter.prepareFailView(outputData);
        }
        // Add validation or logic for adding a friend here.
        else if (username.equals(friendUsername)) {
            final AddFriendOutputData outputData = new AddFriendOutputData("You cannot add yourself as a friend.",
                    true);
            presenter.prepareFailView(outputData);
        }
        else {
            final boolean check = userDataAccessObject.addFriend(username, friendUsername);
            if (!check) {
                final AddFriendOutputData outputData = new AddFriendOutputData("You have already added " + friendUsername + " as your friend.",
                        true);
                presenter.prepareFailView(outputData);
            }
            else {
                final AddFriendOutputData outputData = new AddFriendOutputData(friendUsername + " successfully added as your friend!",
                        false);
                presenter.prepareSuccessView(outputData);
            }
        }
    }
}
