package interface_adapter.addFriend;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddFriendState {

    private String username = "";
    private String friendUsername = "";
    private String errorMessage = "";
    private String successMessage = "";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getUsername() {
        System.out.println("add friend state");
        return username;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        String oldValue = this.friendUsername;
        this.friendUsername = friendUsername;
        support.firePropertyChange("friendUsername", oldValue, friendUsername);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.successMessage = "";
        String oldValue = this.errorMessage;
        this.errorMessage = errorMessage;
        support.firePropertyChange("errorMessage", oldValue, errorMessage);
        System.out.println("set error message");
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.errorMessage = "";
        String oldValue = this.successMessage;
        this.successMessage = successMessage;
        support.firePropertyChange("successMessage", oldValue, successMessage);
        System.out.println("set success message workiing");
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}