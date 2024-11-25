package main;

import java.util.Optional;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import data_access.DatabaseModifier;
import data_access.DatabaseRetriever;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * A demonstration application for connecting to a MongoDB database and performing basic CRUD operations.
 *
 * <p>This application uses the MongoDB Java Driver to connect to a MongoDB database, insert documents,
 * retrieve documents, and update existing documents. It demonstrates how to use external libraries,
 * such as dotenv, for managing sensitive configuration like the database URI.</p>
 */
public class MongoDBApp {

    /**
     * Entry point for the MongoDB application.
     * <p>Establishes a connection to a MongoDB database, performs database operations such as
     * inserting, retrieving, and updating documents, and prints the results to the console.</p>
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        final Dotenv dotenv = Dotenv.load();
        final String uri = dotenv.get("MONGODB_URI");

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            final MongoDatabase database = mongoClient.getDatabase("TranslateApp");
            final MongoCollection<Document> collection = database.getCollection("users");

            final DatabaseModifier dbModifier = new DatabaseModifier(collection);
            final DatabaseRetriever dbRetriever = new DatabaseRetriever(collection);

            // Insert a document
            final Document newDocument = new Document("username", "Shrish")
                    .append("director", "Christopher Nolan")
                    .append("year", 2010);
            dbModifier.insertDocument(newDocument);

            // Retrieve the inserted document
            final Optional<Document> retrievedDocument = dbRetriever.findDocument("username", "Shrish");
            retrievedDocument.ifPresentOrElse(
                    doc -> System.out.println("Document found: " + doc.toJson()),
                    () -> System.out.println("Document not found")
            );

            // Update the document
            final Document update = new Document("year", 2011);
            dbModifier.updateDocument(new Document("title", "Inception"), update);

            // Verify the update
            final Optional<Document> updatedDocument = dbRetriever.findDocument("title", "Inception");
            updatedDocument.ifPresent(doc -> System.out.println("Updated Document: " + doc.toJson()));
        }
    }
}
