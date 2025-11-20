package CHAT_TCP;

import java.io.*;
import java.net.Socket;

public class Client {

    private PrintWriter out;
    private BufferedReader in;

    public Client(GUI gui) throws IOException {

        Socket socket = new Socket("172.20.207.203", 4444);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        new Thread(() -> {
            try {
                String fromServer;
                while ((fromServer = in.readLine()) != null) {
                    gui.setTextArea(fromServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessageToServer(String msg) {
        out.println(msg);
    }

}