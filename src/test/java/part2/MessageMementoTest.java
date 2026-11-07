package part2;

import org.junit.jupiter.api.Test;
import part1.MessageMemento;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageMementoTest {

    LocalDateTime time = LocalDateTime.now();
    part1.MessageMemento memento = new MessageMemento("hi", time);

    @Test
    void getContent() {
        assertEquals("hi", memento.getContent());
    }

    @Test
    void getTimestamp() {
        assertEquals(time, memento.getTimestamp());
    }
}