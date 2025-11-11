package CHAT;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {

    static void main() throws IOException {

        DatagramSocket dgs = new DatagramSocket();
        InetAddress address = InetAddress.getByName("yupp");
        int port = 4444;

        new GUI(dgs, address, port);

    }
}