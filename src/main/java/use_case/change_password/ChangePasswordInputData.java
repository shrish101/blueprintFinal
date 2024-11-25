package use_case.change_password;

import java.util.List;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String password;
    private final String username;
    private final String language;
    private final List<String> friends;

    public ChangePasswordInputData(String password, String username, String language, List<String> friends) {
        this.password = password;
        this.username = username;
        this.language = language;
        this.friends = friends;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getFriends() {
        return friends;
    }
}
