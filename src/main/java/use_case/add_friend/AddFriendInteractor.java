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
        if (!userDataAccessObject.existsByName(username)) {
            presenter.prepareFailView(username + ": Account does not exist.");
        }
        // Add validation or logic for adding a friend here.
        else if (username.equals(friendUsername)) {
            presenter.prepareFailView("You cannot add yourself as a friend.");
        } else {
            AddFriendOutputData outputData = new AddFriendOutputData(friendUsername, false);
            presenter.prepareSuccessView(outputData);
        }
    }
}