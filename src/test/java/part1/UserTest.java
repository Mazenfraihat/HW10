package part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    ChatServer server = new ChatServer();
    User mazen = new User("Mazen", server);
    User ahmed = new User("Ahmed", server);

    @BeforeEach
    void setUp() {
        server.registerUser(mazen);
        server.registerUser(ahmed);
    }

    @Test
    void getName() {
        assertEquals("Mazen", mazen.getName());
    }

    @Test
    void getChatHistory() {
        assertNotNull(mazen.getChatHistory());
    }

    @Test
    void sendMessage() {
        mazen.sendMessage("hi", List.of(ahmed));
        assertEquals(1, mazen.getChatHistory().getMessages().size());
        assertEquals(1, ahmed.getChatHistory().getMessages().size());
    }

    @Test
    void receiveMessage() {
        Message message = new Message(mazen, List.of(ahmed), "hi");
        ahmed.receiveMessage(message);
        assertTrue(ahmed.getChatHistory().getMessages().contains(message));
    }

    @Test
    void undoLastMessage() {
        mazen.sendMessage("hi", List.of(ahmed));
        mazen.undoLastMessage();
        assertTrue(mazen.getChatHistory().getMessages().isEmpty());
        assertTrue(ahmed.getChatHistory().getMessages().isEmpty());
    }

    @Test
    void blockUser() {
        ahmed.blockUser(mazen);
        mazen.sendMessage("hi", List.of(ahmed));
        assertTrue(ahmed.getChatHistory().getMessages().isEmpty());
    }

    @Test
    void viewChatHistory() {
        mazen.sendMessage("hi", List.of(ahmed));
        assertDoesNotThrow(() -> mazen.viewChatHistory());
    }
}