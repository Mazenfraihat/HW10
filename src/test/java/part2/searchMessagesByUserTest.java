package part2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class searchMessagesByUserTest {

    ChatServer server = new ChatServer();
    User mazen = new User("Mazen", server);
    User ahmed = new User("Ahmed", server);
    User sara = new User("Sara", server);
    Message hi = new Message(mazen, List.of(ahmed), "hi");
    Message yo = new Message(sara, List.of(mazen), "yo");
    List<Message> messages = new ArrayList<>(List.of(hi, yo));

    @Test
    void hasNext() {
        searchMessagesByUser it = new searchMessagesByUser(messages, ahmed);
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    void next() {
        searchMessagesByUser it = new searchMessagesByUser(messages, ahmed);
        it.hasNext();
        assertEquals(hi, it.next());
    }

    @Test
    void noMatch() {
        User omar = new User("Omar", server);
        searchMessagesByUser it = new searchMessagesByUser(messages, omar);
        assertFalse(it.hasNext());
    }
}