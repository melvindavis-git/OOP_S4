package OOP_4_4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver {
    static void main() throws IOException {
        int port = 4444;
        DatagramSocket socket = new DatagramSocket(port);
        byte[] data = new byte[256];
        while(true){
            DatagramPacket packet  = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String msg = new String(packet.getData(), 0, packet.getLength());
            System.out.println(msg);
        }
    }
}