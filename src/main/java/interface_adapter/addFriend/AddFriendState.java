package interface_adapter.addFriend;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the state of the "add friend" feature, holding relevant data such as
 * the username, friend username, error message, and success message. This class
 * supports property change listeners to allow external components to react to
 * changes in the state.
 */
public class AddFriendState {

    private String username = "";
    private String friendUsername = "";
    private String errorMessage = "";
    private String successMessage = "";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Retrieves the username associated with the "add friend" state.
     *
     * @return the current username.
     */
    public String getUsername() {
        System.out.println("add friend state");
        return username;
    }

    /**
     * Retrieves the friend username associated with the "add friend" state.
     *
     * @return the current friend's username.
     */
    public String getFriendUsername() {
        return friendUsername;
    }

    /**
     * Sets the friend username and fires a property change event to notify
     * listeners of the change.
     *
     * @param friendUsername the new friend username.
     */
    public void setFriendUsername(String friendUsername) {
        final String oldValue = this.friendUsername;
        this.friendUsername = friendUsername;
        support.firePropertyChange("friendUsername", oldValue, friendUsername);
    }

    /**
     * Retrieves the error message associated with the "add friend" state.
     *
     * @return the current error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message and fires a property change event to notify
     * listeners of the change.
     *
     * @param errorMessage the new error message.
     */
    public void setErrorMessage(String errorMessage) {
        final String oldValue = this.errorMessage;
        this.successMessage = "";
        this.errorMessage = errorMessage;
        support.firePropertyChange("errorMessage", oldValue, errorMessage);
        System.out.println("set error message");
    }

    /**
     * Retrieves the success message associated with the "add friend" state.
     *
     * @return the current success message.
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Sets the success message and fires a property change event to notify
     * listeners of the change.
     *
     * @param successMessage the new success message.
     */
    public void setSuccessMessage(String successMessage) {
        final String oldValue = this.successMessage;
        this.errorMessage = "";
        this.successMessage = successMessage;
        support.firePropertyChange("successMessage", oldValue, successMessage);
        System.out.println("set success message workiing");
    }

    /**
     * Adds a property change listener to be notified of changes to properties.
     *
     * @param listener the listener to add.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener so it will no longer be notified of changes.
     *
     * @param listener the listener to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
