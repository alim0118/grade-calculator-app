package ui;

import model.Course;
import model.StudentRecord;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// shows course, credit, grade on screen
// for returning user
public class StudentRecordPanel extends JPanel {
    private StudentRecord studentRecord;
    private List<Course> courses;

    public StudentRecordPanel(StudentRecord s) {
        studentRecord = s;
        courses = studentRecord.getCourseList();

        setLayout(new GridLayout(10, 10));


    }

}
