package entity;

import java.util.List;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, String language, List<String> friends) {
        return new CommonUser(name, password, language, friends);
    }
}
