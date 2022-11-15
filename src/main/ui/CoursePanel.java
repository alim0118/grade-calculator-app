package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoursePanel extends JPanel implements ActionListener {
    private TextField courseName;
    private TextField credits;
    private TextField desiredGrade;
    private TextField status;
    private JButton next;

    public CoursePanel() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(100, 100));

        add(new Label("Course Name: "));
        courseName = new TextField(10);
        courseName.setEditable(true);
        add(courseName);

        add(new Label("Number of Credits: "));
        credits = new TextField(10);
        credits.setEditable(true);
        add(credits);

        add(new Label("Desired Grade: "));
        desiredGrade = new TextField(10);
        desiredGrade.setEditable(true);
        add(desiredGrade);

        add(new Label("Course Completed?: "));
        status = new TextField(10);
        status.setEditable(true);
        add(status);

        next = new JButton("Next");
        next.setActionCommand("Next");
        add(next, BorderLayout.PAGE_END);

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setResizable(false);

        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        //new CoursePanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if (e.equals("Next")) {
            // re run this page again
        }
    }
}
