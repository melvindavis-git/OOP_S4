package OOP_4_4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Sender {

    static void main() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        InetAddress address = InetAddress.getByName("234.234.234.234");
        int port = 4444;
        MulticastSocket socket = new MulticastSocket();
        String msgCity;
        String msgTemp;
        while (true) {
            System.out.print("Vilken stad befinner du dig i?: ");
            while ((msgCity = in.readLine()) != null) {
                byte[] bytesCity = msgCity.getBytes();
                DatagramPacket packet = new DatagramPacket(bytesCity, bytesCity.length, address, port);
                socket.send(packet);
                break;
            }
            System.out.print("Ange temperatur: ");
            while ((msgTemp = in.readLine()) != null) {
                byte[] bytesTemp = msgTemp.getBytes();
                DatagramPacket packet = new DatagramPacket(bytesTemp, bytesTemp.length, address, port);
                socket.send(packet);
                break;
            }
        }
    }
}