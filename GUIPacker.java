import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUIPacker extends JFrame implements ActionListener {

    // GUI components
    JTextField dirField, archiveField;
    JButton packButton;

    // Constructor to set up the GUI
    public GUIPacker() {
        setTitle("Packing Activity");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Labels and input fields
        JLabel dirLabel = new JLabel("Enter Directory Name:");
        JLabel archiveLabel = new JLabel("Enter Archive File Name:");

        dirField = new JTextField();
        archiveField = new JTextField();
        packButton = new JButton("Pack");

        // Register button click event
        packButton.addActionListener(this);

        // Add components to the frame
        add(dirLabel); add(dirField);
        add(archiveLabel); add(archiveField);
        add(new JLabel()); add(packButton);

        setVisible(true);
    }

    // Event handler for the Pack button
    public void actionPerformed(ActionEvent e) {
        String dirName = dirField.getText().trim();
        String archiveName = archiveField.getText().trim();

        // Validate input
        if (dirName.isEmpty() || archiveName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both directory and archive file names.");
            return;
        }

        File dir = new File(dirName);
        if (!dir.exists() || !dir.isDirectory()) {
            JOptionPane.showMessageDialog(this, "Invalid directory.");
            return;
        }

        // Begin packing process
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archiveName))) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    // Write metadata
                    writer.write("FILE:" + file.getName());
                    writer.newLine();

                    // Read and encrypt file content line by line
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String encrypted = encryptCaesar(line, 3); // Caesar Cipher with key 3
                        writer.write(encrypted);
                        writer.newLine();
                    }
                    reader.close();

                    // Mark end of file
                    writer.write("END");
                    writer.newLine();
                }
            }
            JOptionPane.showMessageDialog(this, "Packing completed successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error during packing: " + ex.getMessage());
        }
    }

    // Caesar Cipher encryption method
    private String encryptCaesar(String input, int key) {
        StringBuilder result = new StringBuilder();
        for (char ch : input.toCharArray()) {
            result.append((char)(ch + key));
        }
        return result.toString();
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        new GUIPacker();
    }
}