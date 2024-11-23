package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import entity.Message;
import entity.CommonMessage;
import org.bson.Document;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.List;

public class MessageDataAccessObject {

    private final MongoCollection<Document> messageCollection;

    public MessageDataAccessObject() {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("MONGODB_URI");

        // Connect to MongoDB and get the "messages" collection
        MongoCollection<Document> messageCollection = MongoClients.create(uri)
                .getDatabase("TranslateApp")
                .getCollection("messages");

        this.messageCollection = messageCollection;
    }

    // Method to save a message to MongoDB
    public void saveMessage(Message message) {
        Document messageDoc = new Document("sender", message.getSender())
                .append("recipient", message.getRecipient())
                .append("originalMessage", message.getOriginalLanguage())
                .append("translatedMessage", message.getTranslatedContent());

        messageCollection.insertOne(messageDoc);
    }

    public List<Message> getAllMessages() {
        //need to be implemented for later
        return null;
    }

}
