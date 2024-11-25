package use_case.add_friend;

/**
 * The interactor for adding a friend.
 */
public class AddFriendInteractor implements AddFriendInputBoundary {

    private final AddFriendOutputBoundary presenter;
    private final AddFriendUserDataAccessInterface userDataAccessObject;
    private final int magic = 500;

    public AddFriendInteractor(AddFriendOutputBoundary presenter,
                               AddFriendUserDataAccessInterface userDataAccessObject) {
        this.presenter = presenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(AddFriendInputData addFriendInputData) {
        final String username = addFriendInputData.getUsername();
        final String friendUsername = addFriendInputData.getFriendUsername();
        System.out.println(magic);
        if (!userDataAccessObject.existsByName(friendUsername)) {
            presenter.prepareFailView(friendUsername + ": Account does not exist.");
            System.out.println("this works");
        }
        // Add validation or logic for adding a friend here.
        if (username.equals(friendUsername)) {
            presenter.prepareFailView("You cannot add yourself as a friend.");
            System.out.println("same works");
        }
        else {
            userDataAccessObject.addFriend(username, friendUsername);
            final AddFriendOutputData outputData = new AddFriendOutputData(friendUsername, false);
            presenter.prepareSuccessView(friendUsername + " successfully added as your friend!");
            System.out.println("goes to correct case");
        }
    }
}
