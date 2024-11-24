package use_case.edit_message;

public class EditOutputData {
    private final String message;

    private final boolean useCaseFailed;

    public EditOutputData(String message, boolean useCaseFailed) {
        this.message = message;
        this.useCaseFailed = useCaseFailed; // not sure...
    }

    public String getMessage() {
        return message;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
