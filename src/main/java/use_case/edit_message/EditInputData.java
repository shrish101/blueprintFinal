package use_case.edit_message;

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
