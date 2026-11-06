package part1;

import java.util.List;

public class Driver {
    public static void main(String[] args) {

        System.out.println("=== 1. Setup: server + 3 users ===");
        ChatServer server = new ChatServer();
        User mazen = new User("Mazen", server);
        User ahmed = new User("Ahmed", server);
        User sara = new User("Sara", server);
        server.registerUser(mazen);
        server.registerUser(ahmed);
        server.registerUser(sara);
        System.out.println("Mazen, Ahmed, and Sara registered.");

        System.out.println("\n=== 2. Send to one user ===");
        mazen.sendMessage("salam", List.of(ahmed));

        System.out.println("\n=== 3. Send to many users ===");
        ahmed.sendMessage("meeting at 5", List.of(mazen, sara));

        System.out.println("\n=== 4. View chat history ===");
        mazen.viewChatHistory();
        ahmed.viewChatHistory();
        sara.viewChatHistory();

        System.out.println("\n=== 5. Undo last message (Memento) ===");
        mazen.sendMessage("oops wrong msg", List.of(ahmed, sara));
        System.out.println("Before undo:");
        ahmed.viewChatHistory();
        mazen.undoLastMessage();
        System.out.println("After undo:");
        mazen.viewChatHistory();
        ahmed.viewChatHistory();
        sara.viewChatHistory();

        System.out.println("\n=== 6. Block a user (Mediator) ===");
        sara.blockUser(mazen);
        mazen.sendMessage("hey", List.of(ahmed, sara));
        ahmed.viewChatHistory();
        sara.viewChatHistory();

        System.out.println("\n=== 7. Undo with nothing sent ===");
        sara.undoLastMessage();

        System.out.println("\n=== 8. Unregister a user ===");
        server.unregisterUser(ahmed);
        mazen.sendMessage("are you there?", List.of(ahmed));
    }
}