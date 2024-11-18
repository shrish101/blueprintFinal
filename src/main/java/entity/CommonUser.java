package entity;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final String language;

    public CommonUser(String name, String password, String language) {
        this.name = name;
        this.password = password;
        this.language = language;
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
    public String getLanguage() { return language; }

}
