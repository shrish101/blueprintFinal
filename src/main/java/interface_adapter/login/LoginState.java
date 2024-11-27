package interface_adapter.login;

import java.util.List;

/**
 * The state for the Login View Model.
 */
public class LoginState {
    private String username = "";
    private String loginError;
    private String password = "";
    private List<String> friendsList;

    public String getUsername() {
        return username;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getFriendsList() {
        return friendsList;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFriendsList(List<String> friendsList) {
        this.friendsList = friendsList;
    }
}
