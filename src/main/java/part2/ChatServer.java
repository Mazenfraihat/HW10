package part2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer {
    private List<User> users;
    private Map<User, List<User>> blockedUsers;

    public ChatServer() {
        users = new ArrayList<>();
        blockedUsers = new HashMap<>();
    }

    public void registerUser(User user) {
        users.add(user);
        blockedUsers.put(user, new ArrayList<>());
    }

    public void unregisterUser(User user) {
        users.remove(user);
        blockedUsers.remove(user);
    }

    public void blockUser(User blocker, User blocked) {
        blockedUsers.get(blocker).add(blocked);
    }

    public boolean isBlocked(User receiver, User sender) {
        return blockedUsers.get(receiver).contains(sender);
    }

    public void sendMessage(Message message) {
        User sender = message.getSender();
        for (User recipient : message.getRecipients()) {
            if (!users.contains(recipient)) {
                System.out.println(recipient.getName() + " is not registered. Message not delivered.");
            } else if (isBlocked(recipient, sender)) {
                System.out.println(recipient.getName() + " has blocked " + sender.getName() + ". Message not delivered.");
            } else {
                recipient.receiveMessage(message);
            }
        }
    }

    public void undoMessage(User sender, MessageMemento memento) {
        for (User user : users) {
            if (user != sender) {
                user.getChatHistory().removeMessage(memento);
            }
        }
    }
}