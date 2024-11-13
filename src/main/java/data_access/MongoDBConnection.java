package data_access;

import io.github.cdimascio.dotenv.Dotenv;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

public class MongoDBConnection {
    public static MongoClient connectToDatabase() {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("MONGODB_URI");
        return MongoClients.create(uri);
    }
}