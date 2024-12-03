package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommonMessageTest {

    @Test
    void constructorTest() {
        CommonMessage message = new CommonMessage("Alice", "Bob", "Hello", "Bonjour", "fr");

        assertEquals("Alice", message.getSender());
        assertEquals("Bob", message.getRecipient());
        assertEquals("Hello", message.getOriginalLanguage());
        assertEquals("Bonjour", message.getTranslatedContent("fr")); // Assume no translation for same language
    }
}


