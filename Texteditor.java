import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SimpleTextEditor extends JFrame {
    private JTextArea textArea;

    public SimpleTextEditor() {
        setTitle("Flori-Soft-open Text Editor Pro");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setBackground(new Color (0, 0, 0));
        textArea.setForeground(new Color (255, 255, 255));
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        createMenuBar();

        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        JMenu aboutmenu = new JMenu ("About");
        JMenuItem About = new  JMenuItem("About Text Editor Pro");

        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        About.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("About FloriSoft");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 400);
                
                // Erstelle ein Panel für die GUI-Elemente
                JPanel panel = new JPanel();
                
                // Erstelle die JLabel-Objekte mit Text
                JLabel label = new JLabel("FloriSoft 2024 Open Pro open");
                JLabel label2 = new JLabel("Version 1.0.0.0");
                JLabel label3 = new JLabel("Made in Java");
                
                // Erstelle ein ImageIcon-Objekt und ein JLabel für das Bild
                ImageIcon imageIcon = new ImageIcon("icon.png"); // Ersetze den Pfad mit dem tatsächlichen Bildpfad
                JLabel imageLabel = new JLabel(imageIcon); // Hier das ImageIcon korrekt zuweisen
                
                // Füge die JLabel-Objekte und das Bild zum Panel hinzu
                panel.add(imageLabel);
                panel.add(label);
                panel.add(label2);
                panel.add(label3);
                
                // Füge das Panel zum Frame hinzu
                frame.add(panel);
                
                // Setze das Frame sichtbar
                frame.setVisible(true);
            }
        });

        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        aboutmenu.add(About);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        menuBar.add (aboutmenu);
        setJMenuBar(menuBar);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                textArea.setText("");
                String line;
                while ((line = br.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleTextEditor());
    }
}
