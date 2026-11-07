package part2;

import org.junit.jupiter.api.Test;
import part1.ChatServer;
import part1.Message;
import part1.MessageMemento;
import part1.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatServerTest {

    part1.ChatServer server = new ChatServer();
    part1.User mazen = new part1.User("Mazen", server);
    part1.User ahmed = new User("Ahmed", server);
    part1.Message message = new Message(mazen, List.of(ahmed), "hi");

    @Test
    void registerUser() {
        server.registerUser(mazen);
        server.registerUser(ahmed);
        server.sendMessage(message);
        assertEquals(1, ahmed.getChatHistory().getMessages().size());
    }

    @Test
    void unregisterUser() {
        server.registerUser(mazen);
        server.registerUser(ahmed);
        server.unregisterUser(ahmed);
        server.sendMessage(message);
        assertTrue(ahmed.getChatHistory().getMessages().isEmpty());
    }

    @Test
    void blockUser() {
        server.registerUser(mazen);
        server.registerUser(ahmed);
        server.blockUser(ahmed, mazen);
        assertTrue(server.isBlocked(ahmed, mazen));
    }

    @Test
    void isBlocked() {
        server.registerUser(mazen);
        server.registerUser(ahmed);
        assertFalse(server.isBlocked(ahmed, mazen));
    }

    @Test
    void sendMessage() {
        server.registerUser(mazen);
        server.registerUser(ahmed);
        server.sendMessage(message);
        assertTrue(ahmed.getChatHistory().getMessages().contains(message));
    }

    @Test
    void sendMessageToBlockedUser() {
        server.registerUser(mazen);
        server.registerUser(ahmed);
        server.blockUser(ahmed, mazen);
        server.sendMessage(message);
        assertTrue(ahmed.getChatHistory().getMessages().isEmpty());
    }

    @Test
    void undoMessage() {
        server.registerUser(mazen);
        server.registerUser(ahmed);
        server.sendMessage(message);
        server.undoMessage(mazen, new MessageMemento("hi", message.getTimestamp()));
        assertTrue(ahmed.getChatHistory().getMessages().isEmpty());
    }
}