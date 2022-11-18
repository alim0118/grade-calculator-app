package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// represents the main GUI
public class MainGUI extends JFrame implements ActionListener {
    private JPanel panel;

    // EFFECTS: creates and sets up main menu of application
    public MainGUI() {
        super("Grade Calculator App");
        this.setSize(500, 500);

        homePage();

    }

    // MODIFIES: this
    // EFFECTS: creates and sets up main menu of application
    public void homePage() {
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout());
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 2, 20));
        labelPanel.setOpaque(false);

        JLabel label = new JLabel("Welcome to the Grade Calculator App. Please select: ");
        labelPanel.add(label);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 200));
        panel.setBackground(Color.WHITE);
        panel.add(labelPanel, BorderLayout.PAGE_START);

        panel.add(addButton(), BorderLayout.CENTER);

        addVisual();

        this.add(panel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: helper to set up new and returning button, adds to button panel and returns it
    public JPanel addButton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton buttonNew = new JButton("New");
        buttonNew.setActionCommand("New");
        buttonNew.addActionListener(this);

        JButton buttonOld = new JButton("Returning");
        buttonOld.setActionCommand("Returning");
        buttonOld.addActionListener(this);

        buttonPanel.add(buttonNew);
        buttonPanel.add(buttonOld);
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: loads image and creates and sets up image panel, adds to panel
    public void addVisual() {
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());
        imagePanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        imagePanel.setOpaque(false);

        // image taken from: https://www.vecteezy.com/vector-art/4557287-calculator-cartoon-vector-object
        ImageIcon imageIcon = new ImageIcon("data/calculatorImage.jpeg");

        // inspiration for image resize taken from tutorial video:
        // https://www.youtube.com/watch?v=eZrdU3BvI4E&t=222s&ab_channel=MaxO%27Didily
        Image image = imageIcon.getImage();
        Image resize = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);

        imageIcon = new ImageIcon(resize);
        JLabel imageLabel = new JLabel(imageIcon);

        imageLabel.setPreferredSize(new Dimension(350, 350));
        imagePanel.add(imageLabel);

        panel.add(imagePanel, BorderLayout.PAGE_END);

    }

    // MODIFIES: this
    // EFFECTS: identifies action and processes corresponding event for new or returning user
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();

        if (button.equals("New")) {
            dispose();
            new StudentRecordFrame();

        } else {
            dispose();
            try {
                new ReturnStudentRecordFrame();
            } catch (FileNotFoundException exc) {
                System.out.println("Error occurred: file not file");
            }

        }

    }
}


