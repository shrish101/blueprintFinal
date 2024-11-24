package interface_adapter.edit_message;

import use_case.edit_message.EditInputData;
import use_case.edit_message.EditInputBoundry;

public class EditMessageController {

    private final EditInputBoundry editMessageUseCaseInteractor;

    public EditMessageController(EditInputBoundry editMessageUseCaseInteractor) {
        this.editMessageUseCaseInteractor = editMessageUseCaseInteractor;
    }

    /**
     * Executes the Edit Message Use Case.
     * @param currentUser the user who is editing the message
     * @param newContent the new content of the message
     */
    public void execute(String currentUser, String newContent) {
        // Create the input data object with the current user and new content
        EditInputData editInputData = new EditInputData(currentUser, newContent);

        // Execute the use case
        editMessageUseCaseInteractor.execute(editInputData);
    }
}
