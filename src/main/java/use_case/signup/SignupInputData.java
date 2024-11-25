package use_case.signup;

import java.util.ArrayList;
import java.util.List;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;
    private final String language;

    public SignupInputData(String username, String password, String repeatPassword, String language) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.language = language;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    String getLanguage() {
        return language;
    }

    public List<String> getFriends() {
        return new ArrayList<>();
    }
}
