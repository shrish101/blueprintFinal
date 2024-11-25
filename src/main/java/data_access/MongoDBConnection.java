package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * This class provides a utility method to establish a connection to a MongoDB database.
 * It uses the MongoDB URI loaded from environment variables to create a connection to the database.
 */
public class MongoDBConnection {
    /**
     * Establishes a connection to the MongoDB database.
     *
     * @return a {@link MongoClient} instance connected to the MongoDB database.
     * @throws IllegalArgumentException if the "MONGODB_URI" environment variable is not set
     *         or is invalid.
     */
    public static MongoClient connectToDatabase() {
        final Dotenv dotenv = Dotenv.load();
        final String uri = dotenv.get("MONGODB_URI");
        return MongoClients.create(uri);
    }
}
