package ui;

import model.Course;
import model.EventLog;
import model.Event;
import model.StudentRecord;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.List;

// represents a student record panel
public class StudentRecordPanel extends JPanel implements ActionListener {
    private static final String JSON_STORE = "./data/studentRecord.json";
    private JsonWriter jsonWriter;

    private int id;
    private StudentRecord studentRecord;
    private List<Course> courses;

    private JPanel recordPanel;
    private JPanel headingPanel;
    private JLabel heading;
    private JPanel buttons;
    private JLabel courseName;
    private JLabel credits;
    private JButton back;
    private JButton exit;


    // EFFECTS: sets up and creates a student record panel with back and save and exit
    public StudentRecordPanel(List<Course> courses, StudentRecord record) {
        jsonWriter = new JsonWriter(JSON_STORE);

        this.courses = courses;
        this.studentRecord = record;
        id = studentRecord.getId();

        setLayout(new GridLayout(3 + this.courses.size(), 1));
        setPreferredSize(new Dimension(100, 100));

        buttons = new JPanel();

        headingLabelHelper();

        headingPanelHelper();

        backHelper();

        exitHelper();

        add(heading);
        add(headingPanel);

        for (Course c : this.courses) {
            printCourse(c);
        }
        add(buttons);
    }

    // MODIFIES: this
    // EFFECTS: sets up heading labels for heading panel
    public void headingLabelHelper() {
        heading = new JLabel();
        heading.setText("Student Record for id: " + id);
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
    }

    // MODIFIES: this
    // EFFECTS: sets up panel with headings for student record
    public void headingPanelHelper() {
        headingPanel = new JPanel();
        headingPanel.setLayout(new GridLayout(1, 2));
        headingPanel.add(courseName);
        courseName.setPreferredSize(new Dimension(50, 1));
        courseName.setHorizontalAlignment(JLabel.CENTER);
        headingPanel.add(credits);
        credits.setSize(new Dimension(50, 1));
        credits.setHorizontalAlignment(JLabel.CENTER);
        headingPanel.setBackground(new Color(196, 215, 246));
    }

    // MODIFIES: this
    // EFFECTS: creates and sets up course info panel
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

    }

    // MODIFIES: this
    // EFFECTS: sets up back button and adds to button panel
    public void backHelper() {
        back = new JButton("Back");
        back.setActionCommand("Back");
        back.addActionListener(this);
        buttons.add(back);

    }

    // MODIFIES: this
    // EFFECTS: sets up exit and save button and adds to button panel
    public void exitHelper() {
        exit = new JButton("Save and Exit");
        exit.setActionCommand("Exit");
        exit.addActionListener(this);
        buttons.add(exit);
    }


    // inspiration taken from WorkRoomApp in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: saves the student record to file
    public void saveBeforeExit() {
        try {
            jsonWriter.open();
            jsonWriter.write(studentRecord);
            jsonWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: identifies action and processes corresponding event
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();

        if (button.equals("Back")) {
            setVisible(false);

        } else if (button.equals("Exit")) {
            saveBeforeExit();
            setVisible(false);
            printLog(EventLog.getInstance());
            System.exit(0);


        }
    }

    // EFFECTS: prints event log
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }
}
