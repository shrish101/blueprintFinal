package entity;

import java.util.List;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     * @param name the name of the new user
     * @param password the password of the new user
     * @param language the language of the new user
     * @param friends the list of friends
     * @return the new user
     */
    User create(String name, String password, String language, List<String> friends);

}
