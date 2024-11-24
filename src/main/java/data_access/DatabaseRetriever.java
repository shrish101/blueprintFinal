package data_access;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseRetriever {

    private final MongoCollection<Document> collection;

    public DatabaseRetriever(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    /**
     * Retrieves a document by a specific key-value pair.
     *
     * @param key   the key to search by.
     * @param value the value to search for.
     * @return an Optional containing the found document or empty if not found.
     */
    public Optional<Document> findDocument(String key, String value) {
        final Document document = collection.find(eq(key, value)).first();
        return Optional.ofNullable(document);
    }
}
