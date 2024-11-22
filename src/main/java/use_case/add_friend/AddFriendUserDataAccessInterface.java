package use_case.add_friend;

import entity.User;
import java.util.List;

/**
 * DAO for the Add Friend Use Case.
 */
public interface AddFriendUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Adds a friend to the user's friend list.
     * @param username the username of the user who is adding a friend
     * @param friendUsername the username of the friend to be added
     */
    void addFriend(String username, String friendUsername);

    /**
     * Retrieves the list of friends for a given user.
     * @param username the username to look up
     * @return the list of usernames of the user's friends
     */
    List<String> getFriends(String username);

    /**
     * Returns the username of the current user of the application.
     * @return the username of the current user; null indicates no one is logged in
     */
    String getCurrentUsername();

    /**
     * Sets the current username indicating who is logged in.
     * @param username the new current username; null indicates no one is logged in
     */
    void setCurrentUsername(String username);
}