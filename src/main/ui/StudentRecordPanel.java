package ui;

import model.Category;
import model.Course;
import model.StudentRecord;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;


// shows course, credit, grade on screen
// for returning user -> import from persistence package
public class StudentRecordPanel extends JPanel implements ActionListener {
    private static int id = 1;
    private static final String JSON_STORE = "./data/studentRecord.json";

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private JList<Course> courseJList;
    private StudentRecord studentRecord;

    private List<Course> courses;

    private JPanel recordPanel;
    private JPanel headingPanel;
    private JLabel name;
    private JLabel credits;
    private JButton exit;


    public StudentRecordPanel(List<Course> courses) {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);


        this.courses = courses;
        createStudentRecord();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // overall panel should be in card layout
        // title: student record - id:
        // each course should be in flow layout (course name - credits)
        // "back" button

        // want to create a student record here

        name = new JLabel();
        name.setText("Course");
        name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        credits = new JLabel();
        credits.setText("Credits");
        credits.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.add(name, BorderLayout.WEST);
        headingPanel.add(credits, BorderLayout.EAST);

        exit = new JButton("Exit");
        exit.setActionCommand("Exit");
        exit.addActionListener(this);

        add(headingPanel);
        add(exit);




    }


    public void createStudentRecord() {
        studentRecord = new StudentRecord(id);

        for (Course c : courses) {
            studentRecord.addCourse(c);
        }
    }

    public void viewAll() {
        List<Course> courses = studentRecord.getCourseList();
        for (Course c : courses) {
            printCourse(c);

        }
    }

    public void printCourse(Course c) {
        recordPanel = new JPanel();
        recordPanel.setLayout(new BorderLayout());


        JLabel curName = new JLabel();
        curName.setText(c.getCourseName());

        JLabel curCredits = new JLabel();
        curCredits.setText(Integer.toString(c.getCredits()));

        recordPanel.add(curName, BorderLayout.WEST);
        recordPanel.add(curCredits, BorderLayout.EAST);

        add(recordPanel);
        add(new JSeparator());


    }

    public void viewCompleted() {
        List<Course> courses = studentRecord.getCourseList();
        for (Course c : courses) {
            c.checkIsCompleted();
            if (c.getIsCompleted()) {
                printCourse(c);
            }
        }
    }

    public void viewIncomplete() {
        List<Course> courses = studentRecord.getCourseList();
        for (Course c : courses) {
            c.checkIsCompleted();
            if (!c.getIsCompleted()) {
                printCourse(c);
            }
        }
    }

    // saves student record before exiting
    public void saveBeforeExit() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(studentRecord);
                    jsonWriter.close();
                } catch (FileNotFoundException exception) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
            }
        });

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
