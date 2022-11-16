package ui;

import model.Category;
import model.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CoursePanel extends JPanel implements ActionListener {
    private TextField name;
    private TextField credits;
    private TextField desiredGrade;
    private TextField status;
    private JButton next;

    private Course course;
    private ArrayList<Category> categories;

    public CoursePanel(ArrayList<Category> categories) {
        this.categories = categories;
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(100, 100));

        add(new Label("Course Name: "));
        name = new TextField(10);
        name.setEditable(true);
        add(name);

        add(new Label("Number of Credits: "));
        credits = new TextField(10);
        credits.setEditable(true);
        add(credits);

        add(new Label("Desired Grade: "));
        desiredGrade = new TextField(10);
        desiredGrade.setEditable(true);
        add(desiredGrade);

//        add(new Label("Course Completed?: "));
//        status = new TextField(10);
//        status.setEditable(true);
//        add(status);

        next = new JButton("Add");
        next.setActionCommand("Add");
        next.addActionListener(this);
        add(next, BorderLayout.PAGE_END);



    }

    // fix
    public void saveText() {
        String courseName = this.name.getText();
        int courseCredit = Integer.parseInt(credits.getText());
        double courseDesired = Double.parseDouble(desiredGrade.getText());

        course = new Course(courseName, courseCredit, courseDesired, categories);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if (button.equals("Add")) {
            saveText();
            setVisible(false);
        }
    }
}
