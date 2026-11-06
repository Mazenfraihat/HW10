package part1;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MessageMementoTest {

    LocalDateTime time = LocalDateTime.now();
    MessageMemento memento = new MessageMemento("hi", time);

    @Test
    void getContent() {
        assertEquals("hi", memento.getContent());
    }

    @Test
    void getTimestamp() {
        assertEquals(time, memento.getTimestamp());
    }
}