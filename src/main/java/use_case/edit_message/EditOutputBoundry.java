package use_case.edit_message;

/**
 * The output boundary for the Edit Password Use Case.
 */
public interface EditOutputBoundry {
    /**
     * Prepares the success view for the Edit Message Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(EditOutputData outputData);

    /**
     * Prepares the failure view for the Edit Password Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
