package use_case.login;

import java.util.List;

import entity.User;

/**
 * DAO for the Login Use Case.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);

    /**
     * Returns the username of the curren user of the application.
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUsername();

    /**
     * Retrieves the list of friends for a given user.
     *
     * @param username the username of the user whose friends list is to be fetched. Must not be null or empty.
     * @return a list of friends associated with the specified username. The list will never be null but may be empty
     *         if the user has no friends.
     */
    List<String> getFriendsList(String username);

    /**
     * Retrieves the language preference for a given user.
     *
     * @param username the username of the user whose language preference is to be retrieved. Must not be null or empty.
     * @return the language preference of the specified user (e.g., language code such as "en" for English).
     */
    String getLanguage(String username);

    /**
     * Sets the username indicating who is the current user of the application.
     * @param username the new current username; null to indicate that no one is currently logged into the application.
     */
    void setCurrentUsername(String username);

}
