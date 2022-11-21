import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class ChatServer implements Runnable{
    private Map<Integer, Socket>  mapClient = new TreeMap<Integer, Socket>();

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(8887);
            System.out.println("Сервер працює. Чекаємо підключення клієнтів");
            int numberClient = 1;
            Socket client = null;
            while (true) {
                client = server.accept();
                Thread clientThread = new Thread( new ClientThread(client, this, numberClient));
                clientThread.setDaemon(true);
                clientThread.start();
                mapClient.put(numberClient,client);
                numberClient++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendMessageForAllClient(int numberClient, String clientMessage) {
        for(int i = 1; i <= mapClient.size(); i++){
            if(numberClient != i){
                System.out.println("Sending massage to Client " + numberClient + "\n");
                BufferedWriter outputUser = null;

                try {
                    outputUser = new BufferedWriter(new OutputStreamWriter(mapClient.get(i).getOutputStream()));
                }
                catch (IOException e){
                    throw new RuntimeException(e);
                }
                try {
                    outputUser.write("Client " + numberClient + ": " + clientMessage + "\nInput message: ");
                    outputUser.flush();
                } catch  (IOException e) {
                    throw new RuntimeException(e);
                }
                }
                }
        }
    }

