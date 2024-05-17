import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openItem, saveItem, newItem;

    public TextEditor() {
        // Create TextArea
        textArea = new JTextArea();
        getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create Menu
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Create and add menu items
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        newItem = new JMenuItem("New");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(newItem);

        // Add listeners
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        newItem.addActionListener(this);

        // Set JFrame properties
        setJMenuBar(menuBar);
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openItem) {
            openFile();
        } else if (e.getSource() == saveItem) {
            saveFile();
        } else if (e.getSource() == newItem) {
            textArea.setText("");
        }
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                textArea.read(reader, null);
                reader.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "File Error: " + ex.getMessage());
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                textArea.write(writer);
                writer.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "File Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new TextEditor();
    }
}

