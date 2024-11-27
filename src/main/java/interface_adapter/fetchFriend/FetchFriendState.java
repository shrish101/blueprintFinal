package interface_adapter.fetchFriend;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the state of the Fetch Friends use case.
 * Manages the username, friends list, error message, and success message,
 * while supporting property change listeners for state updates.
 */
public class FetchFriendState {

    private String username = "";
    private List<String> friendsList = new ArrayList<>();
    private String errorMessage = "";
    private String successMessage = "";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getUsername() {
        return username;
    }

    /**
     * Sets the username and notifies listeners of the change.
     *
     * @param username the new username. Must not be null.
     */
    public void setUsername(String username) {
        final String oldValue = this.username;
        this.username = username;
        support.firePropertyChange("username", oldValue, username);
    }

    public List<String> getFriendsList() {
        return friendsList;
    }

    /**
     * Sets the list of friends and notifies listeners of the change.
     *
     * @param friendsList the new list of friends. Must not be null.
     */
    public void setFriendsList(List<String> friendsList) {
        final List<String> oldValue = this.friendsList;
        this.friendsList = friendsList;
        support.firePropertyChange("friendsList", oldValue, friendsList);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message, clears the success message, and notifies listeners of the change.
     *
     * @param errorMessage the new error message. Must not be null.
     */
    public void setErrorMessage(String errorMessage) {
        final String oldValue = this.errorMessage;
        this.successMessage = "";
        this.errorMessage = errorMessage;
        support.firePropertyChange("errorMessage", oldValue, errorMessage);
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Sets the success message, clears the error message, and notifies listeners of the change.
     *
     * @param successMessage the new success message. Must not be null.
     */
    public void setSuccessMessage(String successMessage) {
        final String oldValue = this.successMessage;
        this.errorMessage = "";
        this.successMessage = successMessage;
        support.firePropertyChange("successMessage", oldValue, successMessage);
    }

    /**
     * Adds a {@link PropertyChangeListener} to observe state changes.
     *
     * @param listener the listener to add. Must not be null.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a {@link PropertyChangeListener}.
     *
     * @param listener the listener to remove. Must not be null.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
