package interface_adapter.addFriend;


public class AddFriendState {

    private String currentUsername;
    private String friendUsername;
    private String message;

    // Constructor
    public AddFriendState(String currentUsername, String friendUsername) {
        this.currentUsername = currentUsername;
        this.friendUsername = friendUsername;
        this.message = "";
    }

    // Getters and Setters for currentUsername
    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    // Getters and Setters for friendUsername
    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    // Getter and Setter for message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getUsernameError() {
        return message;
    }
}