package CHAT_TCP;

import java.io.*;
import java.net.Socket;

public class Server extends Thread {


    Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String clientMsg;

            while ((clientMsg = in.readLine()) != null) {
                System.out.println(clientMsg);
                out.println(clientMsg);
            }
        } catch (
                IOException ex) {
            throw new RuntimeException(ex);
        }
    }


}