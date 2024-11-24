package use_case.add_friend;

import entity.User;
/**
 * The interactor for adding a friend.
 */
public class AddFriendInteractor implements AddFriendInputBoundary {

    private final AddFriendOutputBoundary presenter;
    private final AddFriendUserDataAccessInterface userDataAccessObject;

    public AddFriendInteractor(AddFriendOutputBoundary presenter, AddFriendUserDataAccessInterface userDataAccessObject) {
        this.presenter = presenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(AddFriendInputData addFriendInputData) {
        String username = addFriendInputData.getUsername();
        String friendUsername = addFriendInputData.getFriendUsername();
        System.out.println(500);
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
            AddFriendOutputData outputData = new AddFriendOutputData(friendUsername, false);
            presenter.prepareSuccessView(friendUsername + " successfully added as your friend!");
            System.out.println("goes to correct case");
        }
    }
}