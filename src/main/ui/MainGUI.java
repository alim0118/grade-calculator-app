package ui;

import model.StudentRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame implements ActionListener {
   private StudentRecord studentRecord;
   //private StudentRecordPanel studentRecordPanel;
   //private CoursePanel coursePanel;
   //private CategoryPanel categoryPanel;
   private JPanel panel;
   private JLabel label;
   private JButton buttonNew;
   private JButton buttonOld;
   private JButton buttonAdd;


    public MainGUI() {
        super("Grade Calculator App");
        this.setSize(500, 500);
        //this.setResizable(false);

        studentRecord = new StudentRecord(1);
        //studentRecordPanel = new StudentRecordPanel();
        //coursePanel = new CoursePanel();
        //categoryPanel = new CategoryPanel();

        homePage();

    }

    public void homePage() {
        label = new JLabel("Welcome to the Grade Calculator App. Please select: ");

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

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 200));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(label, BorderLayout.PAGE_START);
        panel.add(buttonPanel, BorderLayout.CENTER);

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
            //new MenuFrame();
            new StudentRecordFrame();

        }
        if (button.equals("Returning")) {
            // go to student record panel
            dispose();
            new ReturnStudentRecordFrame();
        } else {
            // have popup asking number of categories


        }
    }



    public static void main(String[] args) {
       new MainGUI();
    }
}


