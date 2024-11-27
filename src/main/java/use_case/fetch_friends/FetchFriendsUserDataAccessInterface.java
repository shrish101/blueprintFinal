package use_case.fetch_friends;

import java.util.List;

/**
 * The FetchFriendsUserDataAccessInterface defines the contract for the data access layer
 * to handle operations related to fetching a user's friends. It provides methods to retrieve
 * a list of friends associated with a given username.
 */
public interface FetchFriendsUserDataAccessInterface {

    /**
     * Retrieves a list of friends for the user with the given username.
     *
     * @param username the username of the user whose friends are to be fetched
     * @return a list of User objects representing the user's friends
     */
    List<String> getFriendsList(String username);
}
