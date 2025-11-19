package OOP_4_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() {
        try (ServerSocket ss = new ServerSocket(55555)) {
            Socket socket = ss.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String temp;
            while ((temp = in.readLine()) != null) {
                System.out.println(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void main() {
        new Server();
    }

}