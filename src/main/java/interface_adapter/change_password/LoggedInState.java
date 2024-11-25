package interface_adapter.change_password;

import java.util.ArrayList;
import java.util.List;

/**
 * The State information representing the logged-in user.
 */
public class LoggedInState {
    private String username = "";
    private String password = "";
    private String passwordError;
    private String language;
    private List<String> friends = new ArrayList<>();

    public LoggedInState(LoggedInState copy) {
        this.username = copy.username;
        this.password = copy.password;
        this.passwordError = copy.passwordError;
        this.language = copy.language;
        this.friends = new ArrayList<>(copy.friends);
    }

    // Default constructor
    public LoggedInState() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
