package ui;

import model.Course;
import model.StudentRecord;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.List;

public class StudentRecordPanel extends JPanel implements ActionListener {
    private static final String JSON_STORE = "./data/studentRecord.json";

    private JsonWriter jsonWriter;

    private int id;
    private StudentRecord studentRecord;
    private List<Course> courses;

    private JPanel recordPanel;
    private JPanel headingPanel;
    private JLabel heading;
    private JLabel courseName;
    private JLabel credits;
    private JButton exit;


    public StudentRecordPanel(List<Course> courses, StudentRecord record) {
        jsonWriter = new JsonWriter(JSON_STORE);

        this.courses = courses;
        this.studentRecord = record;
        id = studentRecord.getId();

        setLayout(new GridLayout(3 + this.courses.size(), 1));
        setPreferredSize(new Dimension(100, 100));

        headingLabelHelper();

        headingPanelHelper();

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
