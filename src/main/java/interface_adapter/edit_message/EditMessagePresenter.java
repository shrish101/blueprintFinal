package interface_adapter.edit_message;

import use_case.edit_message.EditOutputBoundry;
import use_case.edit_message.EditOutputData;

/**
 * The Presenter for the Edit Message Use Case.
 */
public class EditMessagePresenter implements EditOutputBoundry {

    private String message;
    private boolean useCaseFailed;

    @Override
    public void prepareSuccessView(EditOutputData outputData) {
        this.message = outputData.getMessage();
        this.useCaseFailed = outputData.isUseCaseFailed();
    }

    @Override
    public void prepareFailView(String error) {
        this.message = error;
        this.useCaseFailed = true;
    }
}
