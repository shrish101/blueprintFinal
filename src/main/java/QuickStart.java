import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * A simple MongoDB quick-start example to demonstrate basic database interaction.
 *
 * <p>This class connects to a MongoDB database, retrieves a specific document from a collection,
 * and prints the result to the console. It uses the MongoDB Java Driver and dotenv for managing
 * sensitive configuration details like the database connection string.</p>
 */
public class QuickStart {

    /**
     * Entry point for the QuickStart application.
     * <p>Connects to the MongoDB database specified by the connection string in the ..env file,
     * retrieves a document by a specific key, and prints the document if found.</p>
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {

        // Replace the placeholder with your MongoDB deployment's connection string
        final Dotenv dotenv = Dotenv.load();
        final String uri = dotenv.get("MONGODB_URI");

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            final MongoDatabase database = mongoClient.getDatabase("TranslateApp");
            final MongoCollection<Document> collection = database.getCollection("users");

            final Document doc = collection.find(eq("username", "Shrish")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            }
            else {
                System.out.println("No matching documents found.");
            }
        }
    }
}
