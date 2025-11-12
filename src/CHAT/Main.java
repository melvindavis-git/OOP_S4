package CHAT;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Main {

    static void main() throws IOException {

        MulticastSocket dgs = new MulticastSocket();
        InetAddress address = InetAddress.getByName("234.235.236.237");
        int port = 12540;

        new GUI(dgs, address, port);

    }
}