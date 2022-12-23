package ChatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class Server {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;

    Server() throws IOException {
        serverSocket = new ServerSocket(1234);
    }

    public void sendAll(String message) {
        for (Client client : clients) {
            client.receive(message);
        }
    }

    public void run() throws IOException {
        while (true) {
            System.out.println("Server is listening");

            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client is connected!");

                clients.add(new Client(socket, this));

                while (!serverSocket.isClosed()) {
                    String input = "";
                    System.out.println("The message was received: " + input);
                    System.out.println(input + " back");
                }
            } catch (IOException e) {
                System.out.println("Oops! Exception was thrown: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Server().run();
    }
}
