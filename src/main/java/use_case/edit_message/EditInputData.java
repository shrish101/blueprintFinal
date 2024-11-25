package use_case.edit_message;

/**
 * The EditInputData class is used to store the input data for the Edit Password Use Case.
 * It contains information about the current user and the new content to be updated.
 */
public class EditInputData {

    private final String currentUser;
    private final String newContent;

    public EditInputData(String currentUser, String newContent) {
        this.currentUser = currentUser;
        this.newContent = newContent;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public String getNewContent() {
        return newContent;
    }
}
