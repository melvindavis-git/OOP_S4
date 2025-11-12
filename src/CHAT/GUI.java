package CHAT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.*;

public class GUI extends JFrame {


    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JTextArea textArea = new JTextArea(20, 40);
    private JScrollPane scrollPane = new JScrollPane(textArea);
    JTextField textField = new JTextField(40);
    private String username;
    private JTextArea textAreaList = new JTextArea(10, 20);
    private JScrollPane scrollPaneList = new JScrollPane(textAreaList);

    private DatagramSocket dgs;
    private InetAddress address;
    private int port;

    public GUI(DatagramSocket dgs, InetAddress address, int port) throws IOException {
        this.dgs = dgs;
        this.address = address;
        this.port = port;

        frame.setEnabled(false);
        frame.add(panel);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.WEST);
        textArea.setEditable(false);
        textArea.setEnabled(false);
        textAreaList.setEditable(false);
        textAreaList.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        textAreaList.setDisabledTextColor(Color.BLACK);
        panel.add(textField, BorderLayout.SOUTH);
        panel.add(scrollPaneList, BorderLayout.EAST);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = username + ": " + textField.getText().trim() + "\n";
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

        MulticastSocket socket = new MulticastSocket(port);
        InetSocketAddress group = new InetSocketAddress(address, port);
        NetworkInterface netI = NetworkInterface.getByName("lan");
        socket.joinGroup(group, netI);
        byte[] data = new byte[256];


        frame.setVisible(true);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        textField.requestFocus();


        nameWindow();


        while (true) {
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            String msg = new String(packet.getData(), 0, packet.getLength());

            textArea.append(msg);
        }
    }

    private void nameWindow() {
        JFrame nameFrame = new JFrame();
        JPanel namePanel = new JPanel();
        JTextField nameTF = new JTextField(20);

        nameFrame.add(namePanel);
        namePanel.add(nameTF);
        nameFrame.setUndecorated(true);

        nameTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    username = nameTF.getText();
                    nameFrame.dispose();
                    frame.toFront();
                    frame.setEnabled(true);
                    textAreaList.setText(username);
                }
            }
        });

        nameFrame.setVisible(true);
        nameFrame.pack();
        nameFrame.setLocationRelativeTo(this);
        nameFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}