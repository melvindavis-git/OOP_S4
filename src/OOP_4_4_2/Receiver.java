package OOP_4_4_2;

import java.io.IOException;
import java.net.*;

public class Receiver {

    static void main() throws IOException {

        int port = 4444;
        MulticastSocket socket = new MulticastSocket(port);
        byte[] data = new byte[256];
        InetAddress address = InetAddress.getByName("234.234.234.234");

        InetSocketAddress group = new InetSocketAddress(address, port);
        NetworkInterface netI = NetworkInterface.getByName("lan");

        socket.joinGroup(group, netI);

        while(true){
            DatagramPacket packet  = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String msg = new String(packet.getData(), 0, packet.getLength());
            System.out.println(msg);
        }
    }

}