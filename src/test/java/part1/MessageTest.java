package part1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    ChatServer server = new ChatServer();
    User mazen = new User("Mazen", server);
    User ahmed = new User("Ahmed", server);
    Message message = new Message(mazen, List.of(ahmed), "hi");

    @Test
    void getSender() {
        assertEquals(mazen, message.getSender());
    }

    @Test
    void getRecipients() {
        assertTrue(message.getRecipients().contains(ahmed));
    }

    @Test
    void getTimestamp() {
        assertNotNull(message.getTimestamp());
    }

    @Test
    void getContent() {
        assertEquals("hi", message.getContent());
    }

    @Test
    void testToString() {
        String text = message.toString();
        assertTrue(text.contains("Mazen"));
        assertTrue(text.contains("Ahmed"));
        assertTrue(text.contains("hi"));
    }
}