package app;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;

import app.data_access.DatabaseModifier;
import app.data_access.DatabaseRetriever;

import java.util.Optional;

public class MongoDBApp {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("MONGODB_URI");

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("TranslateApp");
            MongoCollection<Document> collection = database.getCollection("users");

            DatabaseModifier dbModifier = new DatabaseModifier(collection);
            DatabaseRetriever dbRetriever = new DatabaseRetriever(collection);

            // Insert a document
            Document newDocument = new Document("username", "Shrish")
                    .append("director", "Christopher Nolan")
                    .append("year", 2010);
            dbModifier.insertDocument(newDocument);

            // Retrieve the inserted document
            Optional<Document> retrievedDocument = dbRetriever.findDocument("username", "Shrish");
            retrievedDocument.ifPresentOrElse(
                    doc -> System.out.println("Document found: " + doc.toJson()),
                    () -> System.out.println("Document not found")
            );

            // Update the document
            Document update = new Document("year", 2011);
            dbModifier.updateDocument(new Document("title", "Inception"), update);

            // Verify the update
            Optional<Document> updatedDocument = dbRetriever.findDocument("title", "Inception");
            updatedDocument.ifPresent(doc -> System.out.println("Updated Document: " + doc.toJson()));
        }
    }
}