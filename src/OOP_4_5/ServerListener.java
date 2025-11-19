package OOP_4_5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    public ServerListener() {

        try (ServerSocket ss = new ServerSocket(55555)) {

            while (true) {

                Socket socket = ss.accept();
                Server server = new Server(socket);
                server.start();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static void main() {
        ServerListener sl = new ServerListener();
    }

}