package OOP_4_2;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {

    static void main() throws IOException, InterruptedException {

        String[] quotes = {
                "Dont be dumb.",
                "You only live once.",
                "You will never know, if you never tried.",
                "Reach for the stars.",
                "Stay hydrated."
        };

        InetAddress address = InetAddress.getLocalHost();
        int port = 55555;
        DatagramSocket socket = new DatagramSocket();
        int index = 0;

        while (true) {
            String msg = quotes[index];
            byte[] bytes = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
            socket.send(packet);
            index++;
            if (index > 4) {
                index = 0;
            }
            Thread.sleep(5000);
        }
    }

}