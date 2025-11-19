package OOP_4_5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client() {
        try (Socket socket = new Socket("localhost", 55555)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner userInput = new Scanner(System.in);

            Object fromServer;
            String fromUser;

            fromServer = in.readObject();
            System.out.println(fromServer);

            while ((fromUser = userInput.nextLine()) != null) {
                out.println(fromUser);
                fromServer = in.readObject();

                if (fromServer instanceof Person) {
                    Person p = (Person) fromServer;
                    System.out.println(p);
                }
                else if (fromServer instanceof String) {
                    System.out.println(fromServer);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static void main() {
        new Client();
    }

}