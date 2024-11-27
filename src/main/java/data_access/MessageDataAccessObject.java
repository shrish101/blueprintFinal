package data_access;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import entity.CommonMessage;
import entity.Message;
import io.github.cdimascio.dotenv.Dotenv;
import use_case.edit_message.EditUserDataAccessInterface;
import use_case.search_messages.SearchMessagesUserDataAccessInterface;

/**
 * This class provides data access functionality for message-related operations, including saving,
 * retrieving, and updating messages in a MongoDB collection.
 * The data access object interacts with the "messages" collection in the MongoDB database
 * associated with the "TranslateApp" database.
 */
public class MessageDataAccessObject implements EditUserDataAccessInterface, SearchMessagesUserDataAccessInterface {

    private final MongoCollection<Document> messageCollection;
    private final String send = "sender";
    private final String reciever = "recipient";
    private final String ogmessage = "originalMessage";
    private final String tmessage = "translatedMessage";
    private final String strid = "_id";

    public MessageDataAccessObject() {
        final Dotenv dotenv = Dotenv.load();
        final String uri = dotenv.get("MONGODB_URI");

        // Connect to MongoDB and get the "messages" collection
        this.messageCollection = MongoClients.create(uri)
                .getDatabase("TranslateApp")
                .getCollection("messages");
    }

    /**
     * Saves a new message to the "messages" collection in MongoDB.
     *
     * @param message the message to be saved to the database.
     * @null The message object cannot be null.
     */
    public void saveMessage(Message message, String recipientLangauge) {
        final Document messageDoc = new Document(send, message.getSender())
                .append(reciever, message.getRecipient())
                .append(ogmessage, message.getOriginalLanguage())
                .append(tmessage, message.getTranslatedContent(recipientLangauge))
                .append("recipientLanguage", recipientLangauge);

        messageCollection.insertOne(messageDoc);
    }

    @Override
    public Message getLatestMessageByUser(String user) {
        // Find the most recent message from the user, based on the _id (most recent document)
        final Document latestMessageDoc = messageCollection.find(Filters.eq(send, user))
                .sort(new Document(strid, -1))
                .first();

        if (latestMessageDoc != null) {
            return new CommonMessage(
                    latestMessageDoc.getString(send),
                    latestMessageDoc.getString(reciever),
                    latestMessageDoc.getString(ogmessage),
                    latestMessageDoc.getString(tmessage),
                    latestMessageDoc.getString("recipientLanguage")
            );
        }
        return null;
    }

    @Override
    public void updateMessage(Message latestMessage, String newOriginalMessage) {
        // Find the most recent message sent by the user
        final Document latestMessageDoc = messageCollection.find(Filters.eq(send, latestMessage.getSender()))
                .sort(new Document(strid, -1))
                .first();

        if (latestMessageDoc != null) {
            messageCollection.updateOne(
                    Filters.eq(strid, latestMessageDoc.getObjectId(strid)),
                    Updates.combine(
                            Updates.set(ogmessage, newOriginalMessage),
                            Updates.set(tmessage, newOriginalMessage)
                    )
            );
        }
    }

    /**
     * Retrieves all messages from the "messages" collection.
     *
     * @return a list of all {@link Message} objects, or an empty list if no messages exist.
     */
    public List<Message> getAllMessages() {
        final List<Message> messages = new ArrayList<>();
        try {
            // Retrieve all documents from the "messages" collection
            for (Document doc : messageCollection.find()) {
                // Convert each document into a Message object
                final Message message = new CommonMessage(
                        doc.getString(send),
                        doc.getString(reciever),
                        doc.getString(ogmessage),
                        doc.getString(tmessage),
                        doc.getString("recipientLanguage")
                );
                messages.add(message);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }


    /**
     * Retrieves all messages between 2 people
     * @param username1 Username of one of the people
     * @param username2 Username of the other person in the conversation
     * @return a list of all {@link Message} objects, or an empty list if no messages exist.
     */
    public List<Message> getMessageConversation(String username1, String username2) {
        final List<Message> messages = new ArrayList<>();
        try {
            for (Document doc : messageCollection.find()) {
                final Message message = new CommonMessage(
                        doc.getString(send),
                        doc.getString(reciever),
                        doc.getString(ogmessage),
                        doc.getString(tmessage),
                        doc.getString("recipientLanguage")
                );
                if ((message.getSender().equals(username1) || message.getSender().equals(username2)) && (message.getRecipient().equals(username1) || message.getRecipient().equals(username2))) {
                    messages.add(message);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }

}
