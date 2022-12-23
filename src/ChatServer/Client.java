package ChatServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class Client implements Runnable {
    Socket clientSocket;
    Scanner in;
    PrintStream out;
    Server server;

    public Client(Socket socket, Server server) {

        this.clientSocket = socket;
        this.server = server;

        new Thread(this).start();
    }

    public void receive(String message) {
        out.println(message);
    }

    public void run() {
        try {
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();

            in = new Scanner(is);
            out = new PrintStream(os);
            out.println("Hi there!");

            String input = in.nextLine();
            while (!input.equals("bye")) {
                server.sendAll(input);
                input = in.nextLine();
            }
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println("Oops! Exception was thrown: " + ex.getMessage());
        }
    }
}