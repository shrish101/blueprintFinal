package use_case.edit_message;

/**
 * The EditInputData class is used to store the input data for the Edit Password Use Case.
 * It contains information about the current user and the new content to be updated.
 */
public class EditInputData {

    private final String currentUser;
    private final String newContent;
    private final String currentFriend;

    public EditInputData(String currentUser, String newContent, String currentFriend) {
        this.currentUser = currentUser;
        this.newContent = newContent;
        this.currentFriend = currentFriend;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public String getNewContent() {
        return newContent;
    }

    public String getCurrentFriend() {return currentFriend;}
}
