
// ============================================================
//  Secure File Unpacker with Caesar Cipher Decryption
// ------------------------------------------------------------
// File Name   : GUIUnpacker.java
// Description : GUI tool to unpack encrypted archive files and
//               restore original content using Caesar Cipher decryption.
// Author      : Nikita Omase
// Date        : August 8, 2025
// Encryption  : Caesar Cipher (key = 3)
// ============================================================
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUIUnpacker extends JFrame implements ActionListener {

    // GUI components
    JTextField archiveField;
    JButton unpackButton;

    // Constructor to set up the GUI
    public GUIUnpacker() {
        setTitle("Unpacking Activity");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2, 10, 10));

        // Label and input field
        JLabel archiveLabel = new JLabel("Enter Archive File Name:");
        archiveField = new JTextField();
        unpackButton = new JButton("Unpack");

        // Register button click event
        unpackButton.addActionListener(this);

        // Add components to the frame
        add(archiveLabel); add(archiveField);
        add(new JLabel()); add(unpackButton);

        setVisible(true);
    }

    // Event handler for the Unpack button
    public void actionPerformed(ActionEvent e) {
        String archiveName = archiveField.getText().trim();

        // Validate input
        if (archiveName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the archive file name.");
            return;
        }

        File archive = new File(archiveName);
        if (!archive.exists()) {
            JOptionPane.showMessageDialog(this, "Archive file not found.");
            return;
        }

        // Begin unpacking process
        try (BufferedReader reader = new BufferedReader(new FileReader(archive))) {
            String line;
            BufferedWriter writer = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("FILE:")) {
                    // Start writing to a new file
                    String fileName = line.substring(5);
                    writer = new BufferedWriter(new FileWriter("Unpacked_" + fileName));
                } else if (line.equals("END")) {
                    // Close current file
                    if (writer != null) writer.close();
                } else {
                    // Decrypt and write content
                    String decrypted = decryptCaesar(line, 3); // Caesar Cipher with key 3
                    if (writer != null) writer.write(decrypted + "\n");
                }
            }

            JOptionPane.showMessageDialog(this, "Unpacking completed successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error during unpacking: " + ex.getMessage());
        }
    }

    // Caesar Cipher decryption method
    private String decryptCaesar(String input, int key) {
        StringBuilder result = new StringBuilder();
        for (char ch : input.toCharArray()) {
            result.append((char)(ch - key));
        }
        return result.toString();
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        new GUIUnpacker();
    }
}
