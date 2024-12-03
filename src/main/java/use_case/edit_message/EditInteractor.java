package use_case.edit_message;

import entity.Message;

public class EditInteractor implements EditInputBoundry {

    private final EditUserDataAccessInterface messageDataAccess;
    private final EditOutputBoundry presenter;

    public EditInteractor(EditUserDataAccessInterface messageDataAccess, EditOutputBoundry presenter) {
        this.messageDataAccess = messageDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(EditInputData editInputData) {
        // Get the latest message for the current user
        final Message latestMessage = messageDataAccess.getLatestMessageByUser(editInputData.getCurrentUser(),
                editInputData.getCurrentFriend());

        if (latestMessage != null) {
            // Update the latest message with the new content
            messageDataAccess.updateMessage(editInputData.getCurrentUser(), editInputData.getCurrentFriend(),
                    editInputData.getNewContent());

            // Prepare the output data for success (useCaseFailed should be false)
            final EditOutputData outputData = new EditOutputData("Message updated successfully.", false);
            presenter.prepareSuccessView(outputData);
        }
        else {
            // If no message found, prepare the output data for failure (useCaseFailed should be true)
            final EditOutputData outputData = new EditOutputData("No message found for the user.", true);
            presenter.prepareFailView(outputData.getMessage());
        }
    }
}
