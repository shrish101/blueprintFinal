package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import entity.User;
import entity.UserFactory;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * The DAO for user data using MongoDB.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {

    private final MongoCollection<Document> usersCollection;
    private final UserFactory userFactory;

    public DBUserDataAccessObject(MongoClient mongoClient, String databaseName, String collectionName, UserFactory userFactory) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.usersCollection = database.getCollection(collectionName);
        this.userFactory = userFactory;
    }

    @Override
    public User get(String username) {
        Document query = new Document("username", username);
        Document userDoc = usersCollection.find(query).first();

        if (userDoc != null) {
            String name = userDoc.getString("username");
            String password = userDoc.getString("password");
            String language = userDoc.getString("language");
            List<String> friends = userDoc.getList("friends", String.class);
            return userFactory.create(name, password, language, friends);
        } else {
            throw new RuntimeException("User not found.");
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        // This method is not implemented for now.
    }

    @Override
    public boolean existsByName(String username) {
        Document query = new Document("username", username);
        return usersCollection.find(query).first() != null;
    }

    @Override
    public void save(User user) {
        Document userDoc = new Document("username", user.getName())
                .append("password", user.getPassword())
                .append("friends", user.getFriends())
                .append("friends", user.getFriends() != null ? user.getFriends() : List.of());
        usersCollection.insertOne(userDoc);
    }

    @Override
    public void changePassword(User user) {
        Document query = new Document("username", user.getName());
        Document update = new Document("$set", new Document("password", user.getPassword()));
        usersCollection.updateOne(query, update);
    }

    @Override
    public String getCurrentUsername() {
        return null; // Not implemented in this example.
    }
}