package use_case.login;

import java.util.List;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String username;
    private final String langgy;
    private final List<String> friendsList;
    private final boolean useCaseFailed;

    public LoginOutputData(String username, List<String> friendsList, String langgy, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.friendsList = friendsList;
        this.langgy = langgy;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getFriendsList() {
        return friendsList;
    }

    public String getLanguage() {
        return langgy;
    }

}
