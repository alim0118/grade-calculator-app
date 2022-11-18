package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class MainGUI extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel label;
    private JButton buttonNew;
    private JButton buttonOld;


    public MainGUI() {
        super("Grade Calculator App");
        this.setSize(500, 500);

        homePage();

    }

    public void homePage() {
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout());
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 2, 20));
        labelPanel.setOpaque(false);

        label = new JLabel("Welcome to the Grade Calculator App. Please select: ");
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

    public JPanel addButton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        buttonNew = new JButton("New");
        buttonNew.setActionCommand("New");
        buttonNew.addActionListener(this);

        buttonOld = new JButton("Returning");
        buttonOld.setActionCommand("Returning");
        buttonOld.addActionListener(this);

        buttonPanel.add(buttonNew);
        buttonPanel.add(buttonOld);
        return buttonPanel;
    }

    public void addVisual() {
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());
        imagePanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        imagePanel.setOpaque(false);

        ImageIcon imageIcon = new ImageIcon("data/calculatorImage.jpeg");
        Image image = imageIcon.getImage();
        Image resize = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);

        imageIcon = new ImageIcon(resize);
        JLabel imageLabel = new JLabel(imageIcon);

        imageLabel.setPreferredSize(new Dimension(350, 350));
        imagePanel.add(imageLabel);

        panel.add(imagePanel, BorderLayout.PAGE_END);

    }

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
                System.out.println("Error occured: file not file");
            }

        }

    }
}


