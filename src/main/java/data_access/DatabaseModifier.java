package app.data_access;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

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