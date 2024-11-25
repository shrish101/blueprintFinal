package data_access;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.User;
import entity.UserFactory;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data using MongoDB.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {

    private final MongoCollection<Document> usersCollection;
    private final UserFactory userFactory;

    private final String friend = "friends";
    private final String usern = "username";
    private final String pass = "password";

    public DBUserDataAccessObject(MongoClient mongoClient, String databaseName,
                                  String collectionName, UserFactory userFactory) {
        final MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.usersCollection = database.getCollection(collectionName);
        this.userFactory = userFactory;
    }

    @Override
    public User get(String username) {
        final Document query = new Document(usern, username);
        final Document userDoc = usersCollection.find(query).first();

        if (userDoc != null) {
            final String name = userDoc.getString(usern);
            final String password = userDoc.getString(pass);
            final String language = userDoc.getString("language");
            final List<String> friends = userDoc.getList(friend, String.class);
            return userFactory.create(name, password, language, friends);
        }
        else {
            throw new RuntimeException("User not found.");
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        // This method is not implemented for now.
    }

    @Override
    public boolean existsByName(String username) {
        final Document query = new Document(usern, username);
        return usersCollection.find(query).first() != null;
    }

    @Override
    public void save(User user) {
        final Document userDoc = new Document(usern, user.getName())
                .append(pass, user.getPassword())
                .append(friend, user.getFriends())
                .append(friend, user.getFriends() != null ? user.getFriends() : List.of());
        usersCollection.insertOne(userDoc);
    }

    @Override
    public void changePassword(User user) {
        final Document query = new Document(usern, user.getName());
        final Document update = new Document("$set", new Document(pass, user.getPassword()));
        usersCollection.updateOne(query, update);
    }

    @Override
    public String getCurrentUsername() {
        return null;
    }
}
