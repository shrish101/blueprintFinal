package interface_adapter.fetchFriend;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class FetchFriendState {

    private String username = "";
    private List<String> friendsList = new ArrayList<>();
    private String errorMessage = "";
    private String successMessage = "";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        final String oldValue = this.username;
        this.username = username;
        support.firePropertyChange("username", oldValue, username);
    }

    public List<String> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<String> friendsList) {
        final List<String> oldValue = this.friendsList;
        this.friendsList = friendsList;
        support.firePropertyChange("friendsList", oldValue, friendsList);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        final String oldValue = this.errorMessage;
        this.successMessage = "";
        this.errorMessage = errorMessage;
        support.firePropertyChange("errorMessage", oldValue, errorMessage);
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        final String oldValue = this.successMessage;
        this.errorMessage = "";
        this.successMessage = successMessage;
        support.firePropertyChange("successMessage", oldValue, successMessage);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
