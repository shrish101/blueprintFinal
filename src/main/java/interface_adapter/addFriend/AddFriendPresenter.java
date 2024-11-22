package interface_adapter.addFriend;

import use_case.add_friend.AddFriendInputData;
import use_case.add_friend.AddFriendOutputBoundary;
import use_case.add_friend.AddFriendOutputData;
import view.AddFriendView;

/**
 * Presenter for AddFriend use case.
 */
public class AddFriendPresenter implements AddFriendOutputBoundary {

    private final AddFriendView addFriendView;

    /**
     * Constructor for AddFriendPresenter.
     *
     * @param addFriendView the view to update with the result
     */
    public AddFriendPresenter(AddFriendView addFriendView) {
        this.addFriendView = addFriendView;
    }

    /**
     * Prepares the success view with the output data.
     *
     * @param inputData the output data for a successful operation
     */
    public void prepareSuccessView(AddFriendInputData inputData) {
        addFriendView.displaySuccessMessage("Friend added successfully: " + inputData.getFriendUsername());
    }

    /**
     * Prepares the fail view with an error message.
     *
     * @param errorMessage the error message to display
     * @param inputData input data
     */
    public void prepareFailView(AddFriendInputData inputData, String errorMessage) {
        addFriendView.displayErrorMessage("Failed to add friend: " + inputData.getFriendUsername());
    }

    @Override
    public void friendAddedSuccess() {

    }

    @Override
    public void friendAddedFailure(String errorMessage) {

    }

    @Override
    public void prepareFailView(String s) {

    }

    @Override
    public void prepareSuccessView(AddFriendOutputData addFriendOutputData) {

    }
}