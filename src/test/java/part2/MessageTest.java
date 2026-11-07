package part2;

import org.junit.jupiter.api.Test;
import part1.ChatServer;
import part1.Message;
import part1.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    part1.ChatServer server = new ChatServer();
    part1.User mazen = new part1.User("Mazen", server);
    part1.User ahmed = new User("Ahmed", server);
    part1.Message message = new Message(mazen, List.of(ahmed), "hi");

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