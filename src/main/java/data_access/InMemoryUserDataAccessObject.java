package data_access;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.CommonUser;
import entity.User;
import io.github.cdimascio.dotenv.Dotenv;
import use_case.add_friend.AddFriendUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of user data access for various user-related operations such as
 * saving, retrieving, and updating user data, as well as adding friends.
 * This class implements multiple interfaces to provide functionality for signing up, logging in,
 * changing passwords, logging out, and adding friends.
 *
 * @null The methods may return null if no user or friend is found.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface, AddFriendUserDataAccessInterface {

    private final MongoCollection<Document> userCollection;
    private String currentUsername;

    private final String friend = "friends";
    private final String usern = "username";
    private final String pass = "password";
    private final String lan = "language";
    private final int magic = 5;

    public InMemoryUserDataAccessObject() {
        final Dotenv dotenv = Dotenv.load();
        final String uri = dotenv.get("MONGODB_URI");

        final MongoClient mongoClient = MongoClients.create(uri);
        final MongoDatabase database = mongoClient.getDatabase("TranslateApp");
        userCollection = database.getCollection("users");
    }

    @Override
    public boolean existsByName(String username) {
        final Document userDoc = userCollection.find(Filters.eq(usern, username)).first();
        return userDoc != null;
    }

    @Override
    public void save(User user) {
        final Document userDoc = new Document(usern, user.getName())
                .append(pass, user.getPassword())
                .append(lan, user.getLanguage())
                .append(friend, user.getFriends());
        userCollection.insertOne(userDoc);
    }

    @Override
    public User get(String username) {
        final Document userDoc = userCollection.find(Filters.eq(usern, username)).first();
        if (userDoc == null) {
            return null;
        }
        return new CommonUser(
                userDoc.getString(usern),
                userDoc.getString(pass),
                userDoc.getString(lan),
                userDoc.getList(friend, String.class)
        );
    }

    @Override
    public void changePassword(User user) {
        final Document updatedUserDoc = new Document(usern, user.getName())
                .append(pass, user.getPassword())
                .append(lan, user.getLanguage())
                .append(friend, user.getFriends());
        userCollection.replaceOne(Filters.eq(usern, user.getName()), updatedUserDoc);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public boolean addFriend(String username, String friendUsername) {
        final Document userDoc = userCollection.find(Filters.eq(usern, username)).first();
        final Document friendDoc = userCollection.find(Filters.eq(usern, friendUsername)).first();

        // Check if both users exist
        if (userDoc == null || friendDoc == null) {
            return false;
        }

        // Get the current friends list for both users
        final List<String> userFriends = userDoc.getList(friend, String.class);
        final List<String> friendFriends = friendDoc.getList(friend, String.class);

        // Check if they are already friends
        if (userFriends.contains(friendUsername) || friendFriends.contains(username)) {
            return false;
        }

        // Add each other to their respective friends lists
        userFriends.add(friendUsername);
        friendFriends.add(username);

        // Update the user documents in the database
        userCollection.updateOne(
                Filters.eq(usern, username),
                new Document("$set", new Document(friend, userFriends))
        );
        userCollection.updateOne(
                Filters.eq(usern, friendUsername),
                new Document("$set", new Document(friend, friendFriends))
        );
        System.out.println(magic);
        return true;
    }

    /**
     * Retrieve all friends of the given user.
     *
     * @param username Username of the current account
     * @return a list of all friends that this person has
     */
    public List<String> getFriendsList(String username) {
        final List<String> friends = new ArrayList<>();
        try {
            final Document userDoc = userCollection.find(Filters.eq(usern, username)).first();

            if (userDoc != null && userDoc.containsKey(friend)) {
                friends.addAll(userDoc.getList(friend, String.class));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return friends;
    }

    @Override
    public String getLanguage(String username) {
        final Document userDoc = userCollection.find(Filters.eq(usern, username)).first();
        // Once database fxed i beleive we can jus return userDoc.getString(lan);

        if (userDoc != null && userDoc.containsKey(lan)) {
            // Return the value of the language field
            return userDoc.getString(lan);
        }

        // figure out this case...
        return "";
    }
}
