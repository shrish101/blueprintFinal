package use_case.search_messages;

import entity.Message;

import java.util.List;

public interface SearchMessagesUserDataAccessInterface {
    List<Message> getAllMessages() throws Exception;
}



