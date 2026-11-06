package part1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatHistoryTest {

    ChatServer server = new ChatServer();
    User mazen = new User("Mazen", server);
    User ahmed = new User("Ahmed", server);
    Message message = new Message(mazen, List.of(ahmed), "hi");
    MessageMemento memento = new MessageMemento("hi", message.getTimestamp());
    ChatHistory history = new ChatHistory();

    @Test
    void addMessage() {
        history.addMessage(message);
        assertEquals(1, history.getMessages().size());
    }

    @Test
    void saveMemento() {
        history.saveMemento(memento);
        assertEquals(memento, history.getLastMessageSent());
    }

    @Test
    void getLastMessageSent() {
        assertNull(history.getLastMessageSent());
    }

    @Test
    void removeMessage() {
        history.addMessage(message);
        history.removeMessage(memento);
        assertTrue(history.getMessages().isEmpty());
    }

    @Test
    void getMessages() {
        history.addMessage(message);
        assertTrue(history.getMessages().contains(message));
    }
}