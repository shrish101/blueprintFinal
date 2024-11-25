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
        else if (username.equals(friendUsername)) {
            presenter.prepareFailView("You cannot add yourself as a friend.");
            System.out.println("same works");
        }
        else {
            System.out.println("goes to correct case");
            final boolean check = userDataAccessObject.addFriend(username, friendUsername);
            if (!check) {
                presenter.prepareFailView("You have already added " + friendUsername + " as your friend.");
                System.out.println("added already works");
            }
            else {
                final AddFriendOutputData outputData = new AddFriendOutputData(friendUsername, false);
                presenter.prepareSuccessView(friendUsername + " successfully added as your friend!");
                System.out.println("goes to correct case");
            }
        }
    }
}
