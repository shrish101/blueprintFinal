package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final String language;
    private final List<String> friends;

    public CommonUser(String name, String password, String language, List<String> friends) {
        this.name = name;
        this.password = password;
        this.language = language;
        this.friends = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public List<String> getFriends() {
        return friends;
    }

}
