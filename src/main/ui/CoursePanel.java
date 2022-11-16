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

        next = new JButton("Add");
        next.setActionCommand("Add");
        add(next, BorderLayout.PAGE_END);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if (e.equals("Add")) {
            // re run this page again
        }
    }
}
