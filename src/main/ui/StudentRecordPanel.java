package ui;

import model.Course;
import model.StudentRecord;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.List;


// shows course, credit, grade on screen
// for returning user -> import from persistence package
public class StudentRecordPanel extends JPanel implements ActionListener {
    private static int id = 1;
    private static final String JSON_STORE = "./data/studentRecord.json";

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private StudentRecord studentRecord;
    private List<Course> courses;
    private Course curCourse;

    private JPanel recordPanel;
    private JPanel headingPanel;
    private JLabel heading;
    private JLabel courseName;
    private JLabel credits;
    private JButton exit;


    public StudentRecordPanel(List<Course> courses) {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        studentRecord = new StudentRecord(id);


        this.courses = courses;
        //createStudentRecord();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(100, 100));

        // overall panel should be in card layout
        // title: student record - id:
        // each course should be in flow layout (course name - credits)
        // "back" button

        // want to create a student record here
        heading = new JLabel();
        // fix heading to be centered
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setText("Student Record for id: " + studentRecord.getId());
        heading.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        courseName = new JLabel();
        courseName.setText("Course");
        courseName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        credits = new JLabel();
        credits.setText("Credits");
        credits.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        headingPanel = new JPanel();
        headingPanel.setLayout(new GridLayout(1, 2));
        headingPanel.add(courseName);
        courseName.setPreferredSize(new Dimension(50, 1));
        courseName.setHorizontalAlignment(JLabel.CENTER);
        headingPanel.add(credits);
        credits.setSize(new Dimension(50, 1));
        credits.setHorizontalAlignment(JLabel.CENTER);

        exit = new JButton("Save and Exit");
        exit.setActionCommand("Exit");
        exit.addActionListener(this);

        for (Course c : this.courses) {
            printCourse(c);
        }


        add(heading);
        add(headingPanel);


    }


//    public void createStudentRecord(int id, List<Course> courseList) {
//        studentRecord = new StudentRecord(id);
//        for (Course c : courseList) {
//            studentRecord.addCourse(c);
//        }
//    }
//
//    public void viewAll() {
//        List<Course> courses = studentRecord.getCourseList();
//        for (Course c : courses) {
//            printCourse(c);
//
//        }
//        add(exit);
//        exit.setHorizontalAlignment(JButton.CENTER);
//    }
//
//    public void viewCompleted() {
//        List<Course> courses = studentRecord.getCourseList();
//        for (Course c : courses) {
//            c.checkIsCompleted();
//            if (c.getIsCompleted()) {
//                printCourse(c);
//            }
//        }
//        add(exit);
//        exit.setHorizontalAlignment(JButton.CENTER);
//    }
//
//    public void viewIncomplete() {
//        List<Course> courses = studentRecord.getCourseList();
//        for (Course c : courses) {
//            c.checkIsCompleted();
//            if (!c.getIsCompleted()) {
//                printCourse(c);
//            }
//        }
//        add(exit);
//        exit.setHorizontalAlignment(JButton.CENTER);
//    }

    public void printCourse(Course c) {
        recordPanel = new JPanel();
        recordPanel.setLayout(new GridLayout(1, 2));


        JLabel curName = new JLabel();
        curName.setText(c.getCourseName());

        JLabel curCredits = new JLabel();
        curCredits.setText(Integer.toString(c.getCredits()));

        recordPanel.add(curName);
        curName.setPreferredSize(new Dimension(50, 1));
        curName.setHorizontalAlignment(JLabel.CENTER);
        recordPanel.add(curCredits);
        curCredits.setPreferredSize(new Dimension(50, 1));
        curCredits.setHorizontalAlignment(JLabel.CENTER);

        add(recordPanel);
        add(new JSeparator());


    }


    // saves student record before exiting
//    public void saveBeforeExit() {
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                try {
//                    jsonWriter.open();
//                    jsonWriter.write(studentRecord);
//                    jsonWriter.close();
//                } catch (FileNotFoundException exception) {
//                    System.out.println("Unable to write to file: " + JSON_STORE);
//                }
//            }
//        });
//
//    }

    public void saveBeforeExit() {
        try {
            jsonWriter.open();
            jsonWriter.write(studentRecord);
            jsonWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();

        if (button.equals("Exit")) {
            saveBeforeExit();
            setVisible(false);
        }
    }
}
