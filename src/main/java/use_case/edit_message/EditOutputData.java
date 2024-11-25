package use_case.edit_message;

/**
 * The EditOutputData class represents the output data for the Edit Password Use Case.
 * It contains the result message and a flag indicating whether the use case has failed.
 */
public class EditOutputData {
    private final String message;

    private final boolean useCaseFailed;

    public EditOutputData(String message, boolean useCaseFailed) {
        this.message = message;
        this.useCaseFailed = useCaseFailed;
    }

    public String getMessage() {
        return message;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
