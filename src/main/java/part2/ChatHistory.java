package part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Iterator;

public class ChatHistory implements IterableByUser {
    private List<Message> messages;
    private Stack<MessageMemento> mementos;

    public ChatHistory() {
        messages = new ArrayList<>();
        mementos = new Stack<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void saveMemento(MessageMemento memento) {
        mementos.push(memento);
    }

    public MessageMemento getLastMessageSent() {
        if (mementos.isEmpty()) {
            return null;
        }
        return mementos.pop();
    }

    public void removeMessage(MessageMemento memento) {
        for (int i = 0; i < messages.size(); i++) {
            Message m = messages.get(i);
            if (m.getContent().equals(memento.getContent())
                    && m.getTimestamp().equals(memento.getTimestamp())) {
                messages.remove(i);
                return;
            }
        }
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Iterator<Message> iterator(User userToSearchWith) {
        return new searchMessagesByUser(messages, userToSearchWith);
    }
}




