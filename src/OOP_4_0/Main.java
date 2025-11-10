package OOP_4_0;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    static void main() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address);
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());
        System.out.println(address.isMulticastAddress());
        System.out.println(InetAddress.getLoopbackAddress());

        InetAddress address2;
        address2 = InetAddress.getByName("google.se");
        System.out.println(address2);

        InetAddress AA[] = InetAddress.getAllByName("hm.se");
        for (int i = 0; i < AA.length; i++) {
            System.out.println(AA[i]);
        }

    }
}