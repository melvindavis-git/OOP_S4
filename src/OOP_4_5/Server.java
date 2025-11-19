package OOP_4_5;

import java.io.*;
import java.net.Socket;

public class Server extends Thread {

    Protocol p = new Protocol();

    Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            out.writeObject(p.getOutPut(null));
            String clientReq;

            while ((clientReq = in.readLine()) != null) {
                String response = p.getOutPut(clientReq);
                out.writeObject(response);
            }
        } catch (
                IOException ex) {
            throw new RuntimeException(ex);
        }
    }


}