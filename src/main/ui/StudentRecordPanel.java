package ui;

import model.Course;
import model.StudentRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// shows course, credit, grade on screen
// for returning user
public class StudentRecordPanel extends JPanel implements ActionListener {
    private StudentRecord studentRecord;


    public StudentRecordPanel() {



        setLayout(new GridLayout(10, 10));

        // overall panel should be in card layout
        // title: student record - id:
        // each course should be in flow layout (course name - credits)
        // "back" button

        // want to create a student record here



    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
