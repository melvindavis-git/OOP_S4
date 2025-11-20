package CHAT_TCP;

import java.io.*;
import java.net.Socket;

public class Client {

    private String fromUser;
    private String fromServer;

    public Client() {


        try (Socket socket = new Socket("localhost", 55555)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            while ((fromServer = in.readLine()) != null) {
                out.println(fromUser);
                fromServer = in.readLine();
                System.out.println(fromServer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }
}