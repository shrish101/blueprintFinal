package interface_adapter.edit_message;

import use_case.edit_message.EditOutputBoundry;
import use_case.edit_message.EditOutputData;

/**
 * The Presenter for the Edit Message Use Case.
 */
public class EditMessagePresenter implements EditOutputBoundry {

    @Override
    public void prepareSuccessView(EditOutputData outputData) {
        // Temporary Testing
        System.out.println("Message updated successfully.");
    }

    @Override
    public void prepareFailView(String error) {
        // Testing
        System.out.println("Error: " + error);
    }
}
