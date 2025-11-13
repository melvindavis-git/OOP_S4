package CHAT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.*;

import static java.awt.Font.BOLD;

public class GUI extends JFrame {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JTextArea textArea = new JTextArea(20, 40);
    private JScrollPane scrollPane = new JScrollPane(textArea);
    private JTextField textField = new JTextField(40);
    private String username;
    private JTextArea textAreaList = new JTextArea(10, 20);
    private JScrollPane scrollPaneList = new JScrollPane(textAreaList);
    private BufferedImage noIcon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    private Font font = new Font("Bahnschrift", BOLD, 14);
    private JButton themeBtn = new JButton("Dark Mode");
    private boolean darkMode = false;
    private Color bg = Color.WHITE;
    private Color fg = Color.BLACK;
    private JPanel panelTop = new JPanel();

    private DatagramSocket dgs;
    private InetAddress address;
    private int port;

    public GUI(DatagramSocket dgs, InetAddress address, int port) throws IOException {
        this.dgs = dgs;
        this.address = address;
        this.port = port;

        frame.setIconImage(noIcon);
        frame.setEnabled(false);
        frame.add(panel);
        frame.add(panelTop, BorderLayout.NORTH);
        themeBtn.setFocusPainted(false);
        themeBtn.setFont(font);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.WEST);
        textArea.setEditable(false);
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        textArea.setFont(font);
        textAreaList.setEditable(false);
        textAreaList.setEnabled(false);
        textAreaList.setDisabledTextColor(Color.BLACK);
        textAreaList.setFont(font);
        panel.add(textField, BorderLayout.SOUTH);
        panel.add(scrollPaneList, BorderLayout.EAST);
        textField.setFont(font);
        panelTop.setBackground(bg);
        panelTop.add(themeBtn);

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

        themeBtn.addActionListener(e -> darkLightToggle());

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
            updateUserList(msg);
        }
    }

    private void nameWindow() {
        JFrame nameFrame = new JFrame();
        JPanel namePanel = new JPanel();
        JTextField nameTF = new JTextField(20);
        JLabel nameLabel = new JLabel("Enter your username:");

        nameFrame.add(namePanel);
        namePanel.add(nameLabel);
        nameLabel.setFont(font);
        namePanel.add(nameTF);
        nameTF.setFont(font);
        nameFrame.setUndecorated(true);

        nameTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    username = nameTF.getText();
                    if (username.length() > 12 || username.isBlank()) {
                        nameTF.setText("");
                        nameLabel.setText("Username must be 1-12 characters.");
                    } else {
                        nameFrame.dispose();
                        frame.toFront();
                        frame.setEnabled(true);
                        textAreaList.setText(username + "\n");
                    }
                }
            }
        });

        nameFrame.setSize(300, 60);
        nameFrame.setVisible(true);
        nameFrame.setLocationRelativeTo(this);
        nameFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void darkLightToggle() {
        darkMode = !darkMode;

        if (darkMode) {
            bg = Color.DARK_GRAY;
            fg = Color.WHITE;
            themeBtn.setText("Light Mode");
        } else if (!darkMode) {
            bg = Color.WHITE;
            fg = Color.BLACK;
            themeBtn.setText("Dark Mode");
        }

        textArea.setBackground(bg);
        textArea.setForeground(fg);
        textArea.setDisabledTextColor(fg);
        textAreaList.setBackground(bg);
        textAreaList.setForeground(fg);
        textAreaList.setDisabledTextColor(fg);
        textField.setBackground(bg);
        textField.setForeground(fg);
        panel.setBackground(bg);
        panelTop.setBackground(bg);
        themeBtn.setBackground(bg);
        themeBtn.setForeground(fg);
    }

    private void updateUserList(String msg) {
        if (!msg.contains(":")) return;
        String name = msg.split(":", 2)[0].trim();
        if (!textAreaList.getText().contains(name)) {
            textAreaList.append(name + "\n");
        }
    }

}