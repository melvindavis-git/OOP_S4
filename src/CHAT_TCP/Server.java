package CHAT_TCP;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {

    Socket socket;
    private PrintWriter out;

    private static List<Server> clients = new ArrayList<>();

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            synchronized (clients) {
                clients.add(this);
            }

            String clientMsg;
            while ((clientMsg = in.readLine()) != null) {

                broadcast(clientMsg);
            }
        } catch (IOException ex) {
            System.out.println("Client disconnected");
        } finally {

            synchronized (clients) {
                clients.remove(this);
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void broadcast(String message) {
        synchronized (clients) {
            for (Server client : clients) {
                client.out.println(message);
            }
        }
    }
}