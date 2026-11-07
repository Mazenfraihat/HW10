package part2;

import java.util.Iterator;
import java.util.List;

public class User implements IterableByUser {
    private String name;
    private ChatServer server;
    private ChatHistory history;

    public User(String name, ChatServer server) {
        this.name = name;
        this.server = server;
        this.history = new ChatHistory();
    }

    public String getName() {
        return name;
    }

    public ChatHistory getChatHistory() {
        return history;
    }

    public void sendMessage(String content, List<User> recipients) {
        Message message = new Message(this, recipients, content);
        history.addMessage(message);
        history.saveMemento(new MessageMemento(content, message.getTimestamp()));
        server.sendMessage(message);
    }

    public void receiveMessage(Message message) {
        history.addMessage(message);
    }

    public void undoLastMessage() {
        MessageMemento memento = history.getLastMessageSent();
        if (memento == null) {
            System.out.println(name + " has nothing to undo.");
            return;
        }
        history.removeMessage(memento);
        server.undoMessage(this, memento);
        System.out.println(name + " undid: " + memento.getContent());
    }

    public void blockUser(User other) {
        server.blockUser(this, other);
        System.out.println(name + " blocked " + other.getName());
    }

    public void viewChatHistory() {
        System.out.println("--- " + name + "'s chat history ---");
        for (Message m : history.getMessages()) {
            System.out.println(m);
        }
    }

    public Iterator<Message> iterator(User userToSearchWith) {
        return history.iterator(userToSearchWith);
    }
}