package CHAT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class GUI extends JFrame {


    private JPanel panel = new JPanel();
    private JTextArea textArea = new JTextArea(20, 40);
    private JScrollPane scrollPane = new JScrollPane(textArea);
    JTextField textField = new JTextField(40);

    private DatagramSocket dgs;
    private InetAddress address;
    private int port;

    public GUI(DatagramSocket dgs, InetAddress address, int port) throws IOException {
        this.dgs = dgs;
        this.address = address;
        this.port = port;

        this.add(panel);
        panel.add(scrollPane);
        textArea.setEditable(false);
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        panel.add(textField);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = textField.getText().trim();
                    textArea.append(text + "\n");
                    byte[] bytes = text.getBytes();
                    DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
                    try {
                        dgs.send(packet);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    textField.setText("");
                }
            }
        });

        setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        textField.requestFocus();
    }

}