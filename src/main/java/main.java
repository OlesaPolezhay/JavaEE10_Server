public class main {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        Thread tChatServer = new Thread(chatServer);
        tChatServer.start();
    }
}
