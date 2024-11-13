package data_access;

import com.mongodb.client.MongoClient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MongoDBConnectionTest {

    @Test
    public void testConnection() {
        // Attempt to connect to the database
        MongoClient client = MongoDBConnection.connectToDatabase();

        // Check if the client is not null, which means the connection was successful
        assertNotNull(client, "MongoClient should not be null if connection is successful");

        // Close the client after the test
        client.close();
    }
}