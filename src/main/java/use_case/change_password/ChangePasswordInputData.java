package use_case.change_password;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String password;
    private final String username;
    private final String language;

    public ChangePasswordInputData(String password, String username, String language) {
        this.password = password;
        this.username = username;
        this.language = language;
    }

    String getPassword() {
        return password;
    }

    String getUsername() {
        return username;
    }

    String getLanguage() { return language; }

}
