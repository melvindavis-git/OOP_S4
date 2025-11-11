package OOP_4_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {

    static void main() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        InetAddress address = InetAddress.getLocalHost();
        int port = 4444;
        DatagramSocket socket = new DatagramSocket();
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