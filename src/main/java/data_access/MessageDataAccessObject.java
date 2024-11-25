package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoClients;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import entity.Message;
import entity.CommonMessage;
import org.bson.Document;
import io.github.cdimascio.dotenv.Dotenv;

import use_case.edit_message.EditUserDataAccessInterface;
import use_case.search_messages.SearchMessagesUserDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class MessageDataAccessObject implements EditUserDataAccessInterface, SearchMessagesUserDataAccessInterface {

    private final MongoCollection<Document> messageCollection;

    public MessageDataAccessObject() {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("MONGODB_URI");

        // Connect to MongoDB and get the "messages" collection
        this.messageCollection = MongoClients.create(uri)
                .getDatabase("TranslateApp")
                .getCollection("messages");
    }

    // Method to save a message to MongoDB
    public void saveMessage(Message message) {
        Document messageDoc = new Document("sender", message.getSender())
                .append("recipient", message.getRecipient())
                .append("originalMessage", message.getOriginalLanguage())
                .append("translatedMessage", message.getTranslatedContent());

        messageCollection.insertOne(messageDoc);
    }

    @Override
    public Message getLatestMessageByUser(String user) {
        // Find the most recent message from the user, based on the _id (most recent document)
        Document latestMessageDoc = messageCollection.find(Filters.eq("sender", user))
                .sort(new Document("_id", -1))
                .first();

        if (latestMessageDoc != null) {
            return new CommonMessage(
                    latestMessageDoc.getString("sender"),
                    latestMessageDoc.getString("recipient"),
                    latestMessageDoc.getString("originalMessage"),
                    latestMessageDoc.getString("translatedMessage")
            );
        }
        return null;
    }

    @Override
    public void updateMessage(Message latestMessage, String newOriginalMessage) {
        // Find the most recent message sent by the user
        Document latestMessageDoc = messageCollection.find(Filters.eq("sender", latestMessage.getSender()))
                .sort(new Document("_id", -1))
                .first();

        if (latestMessageDoc != null) {
            messageCollection.updateOne(
                    Filters.eq("_id", latestMessageDoc.getObjectId("_id")),
                    Updates.combine(
                            Updates.set("originalMessage", newOriginalMessage),
                            Updates.set("translatedMessage", newOriginalMessage) // translate using api
                    )
            );
        }
    }

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        try {
            // Retrieve all documents from the "messages" collection
            for (Document doc : messageCollection.find()) {
                // Convert each document into a Message object
                Message message = new CommonMessage(
                        doc.getString("sender"),
                        doc.getString("recipient"),
                        doc.getString("originalMessage"),
                        doc.getString("translatedMessage")
                );
                messages.add(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }

}
