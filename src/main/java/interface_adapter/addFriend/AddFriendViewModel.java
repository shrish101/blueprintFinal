package interface_adapter.addFriend;

import interface_adapter.ViewModel;
import use_case.add_friend.AddFriendInputData;
import interface_adapter.addFriend.AddFriendPresenter;
import view.AddFriendView;

/**
 * The ViewModel for the Add Friend View.
 */
public class AddFriendViewModel extends ViewModel<AddFriendState> {

    public static final String TITLE_LABEL = "Add Friend";
    public static final String USERNAME_LABEL = "Enter friend's username";
    public static final String ADD_FRIEND_BUTTON_LABEL = "Add Friend";
    public static final String SUCCESS_MESSAGE = "Friend added successfully";
    public static final String ERROR_MESSAGE = "Error adding friend";


    /**
     * Constructor for AddFriendViewModel.
     * Initializes AddFriendPresenter internally.
     */
    public AddFriendViewModel() {
        super("add friend");
        setState(new AddFriendState("", ""));
    }

    /**
     * Updates the state and triggers the presenter to update the view.
     *
     * @param inputData the output data containing the success message
     */
    public void updateView(AddFriendInputData inputData) {
        final AddFriendState currentState = getState();
        currentState.setMessage("Friend added: " + inputData.getFriendUsername());
    }

    /**
     * Sets the error message in the state and triggers the fail view.
     *
     * @param errorMessage the error message to display
     * @param inputData input data given
     */
    public void showError(AddFriendInputData inputData, String errorMessage) {
        final AddFriendState currentState = getState();
        currentState.setMessage(errorMessage);
    }

    /**
     * Returns the current state of AddFriend.
     * @return AddFriendState
     */
    public AddFriendState getAddFriendState() {
        return getState();
    }
}