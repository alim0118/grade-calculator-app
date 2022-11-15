package ui;

import model.Category;
import model.StudentRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame implements ActionListener {
   private StudentRecord studentRecord;
   private StudentRecordPanel studentRecordPanel;
   private CoursePanel coursePanel;
   private CategoryPanel categoryPanel;
   private JPanel panel;
   private JLabel label;
   private JButton buttonNew;
   private JButton buttonOld;
   private JButton buttonAdd;


    public MainGUI() {
        super("Grade Calculator App");
        this.setSize(600, 600);
        //this.setResizable(false);

        studentRecord = new StudentRecord(1);
        studentRecordPanel = new StudentRecordPanel(studentRecord);
        coursePanel = new CoursePanel();
        categoryPanel = new CategoryPanel();

        homePage();

    }

    public void homePage() {
        label = new JLabel("Welcome to the Grade Calculator App. Please select:");

        buttonNew = new JButton("New");
        buttonNew.setActionCommand("New");
        buttonNew.addActionListener(this);

        buttonOld = new JButton("Returning");
        buttonOld.setActionCommand("Returning");
        buttonOld.addActionListener(this);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setBackground(Color.GRAY);
        panel.add(label);
        panel.add(buttonNew, BorderLayout.WEST);
        panel.add(buttonOld, BorderLayout.EAST);

        this.add(panel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();

        if (button.equals("New")) {
            // go to "add course" panel (course panel)
            // add somewhere -> to after new
            dispose();
            new MenuPanel();

        }
        if (button.equals("Returning")) {
            // go to student record panel
        } else {
            // have popup asking number of categories


        }
    }



    public static void main(String[] args) {
       new MainGUI();
    }
}


