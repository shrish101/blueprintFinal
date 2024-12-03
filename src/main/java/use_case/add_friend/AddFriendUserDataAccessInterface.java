package use_case.add_friend;

import entity.User;
import java.util.List;
import java.util.ArrayList;

/**
 * The AddFriendUserDataAccessInterface defines the contract for the data access layer
 * to handle operations related to adding a friend. It provides methods to check if
 * a user exists by their username and to add a friend by associating two usernames.
 */
public interface AddFriendUserDataAccessInterface {

    /**
     * Checks if a user with the given username exists.
     *
     * @param friendUsername the username of the potential friend to check
     * @return true if a user with the given username exists, otherwise false
     */
    boolean existsByName(String friendUsername);

    /**
     * Adds a friend by associating the given usernames.
     *
     * @param username the username of the user who is adding the friend
     * @param friendUsername the username of the person to be added as a friend
     * @return true if the friend was successfully added, otherwise false
     */
    boolean addFriend(String username, String friendUsername);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Saves the user.
     * @param friendUsername the friend usename
     */
    List<String> getFriendsList(String friendUsername);
}
