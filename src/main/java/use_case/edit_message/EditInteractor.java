package use_case.edit_message;

import data_access.MessageDataAccessObject;
import entity.Message;

/**
 * The Edit Message Interactor.
 */
public class EditInteractor implements EditInputBoundry {

    private final EditUserDataAccessInterface messageDataAccess;
    private final EditOutputBoundry presenter;

    public EditInteractor(MessageDataAccessObject messageDataAccess, EditOutputBoundry presenter) {
        this.messageDataAccess = messageDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(EditInputData editInputData) {
        // Get the latest message for the current user
        final Message latestMessage = messageDataAccess.getLatestMessageByUser(editInputData.getCurrentUser());

        if (latestMessage != null) {
            // Update the latest message with the new content
            messageDataAccess.updateMessage(latestMessage, editInputData.getNewContent());

            // Prepare the output data for success
            final EditOutputData outputData = new EditOutputData("Message updated successfully.", true);
            presenter.prepareSuccessView(outputData);
        }
        else {
            // If no message found, prepare the output data for failure
            final EditOutputData outputData = new EditOutputData("No message found for the user.", false);
            presenter.prepareFailView(String.valueOf(outputData));
        }
    }
}
