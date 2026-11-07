package part2;

import java.util.Iterator;
import java.util.List;

public class searchMessagesByUser implements Iterator<Message> {
    private List<Message> messages;
    private User user;
    private int index;

    public searchMessagesByUser(List<Message> messages, User user) {
        this.messages = messages;
        this.user = user;
        this.index = 0;
    }

    public boolean hasNext() {
        while (index < messages.size()) {
            Message m = messages.get(index);
            if (m.getSender() == user || m.getRecipients().contains(user)) {
                return true;
            }
            index++;
        }
        return false;
    }

    public Message next() {
        Message m = messages.get(index);
        index++;
        return m;
    }
}