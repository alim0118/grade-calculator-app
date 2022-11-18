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


    public StudentRecordPanel(List<Course> courses, StudentRecord record) {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        this.courses = courses;
        this.studentRecord = record;
        //createStudentRecord();
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // 3 is for heading title, course to credit heading, exit
        setLayout(new GridLayout(3 + this.courses.size(), 1));
        setPreferredSize(new Dimension(100, 100));

        // overall panel should be in card layout
        // title: student record - id:
        // each course should be in flow layout (course name - credits)
        // "back" button

        // want to create a student record here
        heading = new JLabel();
        // fix heading to be centered
        heading.setText("Student Record for id: " + studentRecord.getId());
        heading.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setOpaque(true);
        heading.setBackground(Color.white);

        courseName = new JLabel();
        courseName.setText("Course");
        courseName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        credits = new JLabel();
        credits.setText("Credits");
        credits.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        headingPanel = new JPanel();
        headingPanel.setLayout(new GridLayout(1, 2));
        headingPanel.add(courseName);
        courseName.setPreferredSize(new Dimension(50, 1));
        courseName.setHorizontalAlignment(JLabel.CENTER);
        headingPanel.add(credits);
        credits.setSize(new Dimension(50, 1));
        credits.setHorizontalAlignment(JLabel.CENTER);
        headingPanel.setBackground(new Color(196, 215, 246));


        exit = new JButton("Save and Exit");
        exit.setActionCommand("Exit");
        exit.addActionListener(this);

        add(heading);
        add(headingPanel);

        for (Course c : this.courses) {
            printCourse(c);
        }

        add(exit);




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
        //add(new JSeparator());


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
            System.exit(0);

        }
    }
}
