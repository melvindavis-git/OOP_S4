package CHAT_TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    public ServerListener() {

        try (ServerSocket ss = new ServerSocket(55555)) {

            String s = "Listening for servers";


            Thread loadingThread = new Thread(() -> {
                while (true) {
                    try {
                        for (int i = 1; i <= 3; i++) {
                            System.out.print("\r" + s + new String(new char[i]).replace("\0", "."));
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            loadingThread.start();

            while (true) {
                Socket socket = ss.accept();
                Server server = new Server(socket);
                server.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void main() throws IOException {
        new ServerListener();
    }

}