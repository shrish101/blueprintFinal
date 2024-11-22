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

        MongoCollection<Document> messageCollection = MongoClients.create(uri)
                .getDatabase("TranslateApp")
                .getCollection("messages");

        this.messageCollection = messageCollection;
    }

    public void saveMessage(Message message) {
    }

    public List<Message> getAllMessages() {
    }

}
