package data_access;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

/**
 * A utility class for modifying documents in a MongoDB collection.
 *
 * @null This class does not accept null values for the collection field.
 */
public class DatabaseModifier {

    private final MongoCollection<Document> collection;

    public DatabaseModifier(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    /**
     * Inserts a document into the collection.
     *
     * @param document the document to insert.
     */
    public void insertDocument(Document document) {
        collection.insertOne(document);
    }

    /**
     * Updates a document in the collection.
     *
     * @param filter the filter criteria to find the document.
     * @param update the update operation to apply.
     */
    public void updateDocument(Document filter, Document update) {
        collection.updateOne(filter, new Document("$set", update));
    }
}
