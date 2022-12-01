package ui;

import model.Course;
import model.StudentRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MinimumGradePanel extends JPanel implements ActionListener {

    private int id;
    private StudentRecord studentRecord;
    private List<Course> courses;

    private String choiceCourse;
    private double choiceGrade;
    private double minimum;

    private JPanel recordPanel;
    private JPanel headingPanel;
    private JLabel heading;
    private JPanel buttons;
    private JLabel courseName;
    private JLabel courseMinimum;
    private JButton back;


    // EFFECTS: sets up and creates a student record panel with back and save and exit
    public MinimumGradePanel(List<Course> courses, StudentRecord record) {

        this.courses = courses;
        this.studentRecord = record;
        id = studentRecord.getId();

        setLayout(new GridLayout(3 + this.courses.size(), 1));
        setPreferredSize(new Dimension(100, 100));

        popUp();

        buttons = new JPanel();

        headingLabelHelper();

        headingPanelHelper();

        backHelper();

        add(heading);
        add(headingPanel);

        for (Course c : this.courses) {
            if (c.getCourseName().equals(choiceCourse)) {
                printCourse(c);
            }
        }

        add(buttons);
    }

    // MODIFIES: this
    // EFFECTS: creates pop up window, asks for course name and desired grade for calculating;
    //          conducts calculating min final score
    public void popUp() {
        choiceCourse = JOptionPane.showInputDialog(this,
                "What course would like to calculate what you need on the final exam?"
                        + "\n(Chosen course must be incomplete, or default mark calculated will be 0%)", null);

        String optionGrade = JOptionPane.showInputDialog(this,
                "What is your desired final grade for " + choiceCourse + "?", null);

        choiceGrade = Double.parseDouble(optionGrade);

        doCalculateMinFinalScore(choiceCourse, choiceGrade);

    }

    // REQUIRES: courseChoice to be non-zero length and included in allCourses, 100 <= desiredGrade <= 0
    // MODIFIES: this
    // EFFECTS: calculates minimum score needed on final to get desired grade for course
    private void doCalculateMinFinalScore(String courseChoice, double desiredGrade) {
        for (int i = 0; i <= courses.size() - 1; i++) {
            if (courses.get(i).getCourseName().equals(courseChoice)) {
                courses.get(i).checkIsCompleted();
                if (!courses.get(i).getIsCompleted()) {
                    courses.get(i).calculateGrade();
                    courses.get(i).findFinalWeight();
                    courses.get(i).setDesiredFinalGrade(desiredGrade);

                    courses.get(i).calculateMinFinalScore();
                    minimum = courses.get(i).getMinFinalScore();

                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up heading labels for heading panel
    public void headingLabelHelper() {
        heading = new JLabel();
        heading.setText("Calculated Minimum Final Score Needed on Final Exam");
        heading.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setOpaque(true);
        heading.setBackground(Color.white);

        courseName = new JLabel();
        courseName.setText("Course");
        courseName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        courseMinimum = new JLabel();
        courseMinimum.setText("Minimum Mark");
        courseMinimum.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    // MODIFIES: this
    // EFFECTS: sets up panel with headings for student record
    public void headingPanelHelper() {
        headingPanel = new JPanel();
        headingPanel.setLayout(new GridLayout(1, 2));
        headingPanel.add(courseName);
        courseName.setPreferredSize(new Dimension(50, 1));
        courseName.setHorizontalAlignment(JLabel.CENTER);
        headingPanel.add(courseMinimum);
        courseMinimum.setSize(new Dimension(50, 1));
        courseMinimum.setHorizontalAlignment(JLabel.CENTER);
        headingPanel.setBackground(new Color(196, 215, 246));
    }

    // MODIFIES: this
    // EFFECTS: creates and sets up info for minimum mark needed
    public void printCourse(Course c) {
        recordPanel = new JPanel();
        recordPanel.setLayout(new GridLayout(1, 2));

        JLabel curName = new JLabel();
        curName.setText(c.getCourseName());

        JLabel curMark = new JLabel();

        curMark.setText((Math.round(minimum * 100.0) / 100.0) * 100 + "%");

        recordPanel.add(curName);
        curName.setPreferredSize(new Dimension(50, 1));
        curName.setHorizontalAlignment(JLabel.CENTER);
        recordPanel.add(curMark);
        curMark.setPreferredSize(new Dimension(50, 1));
        curMark.setHorizontalAlignment(JLabel.CENTER);

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
    // EFFECTS: identifies action and goes back to add/view page
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();

        if (button.equals("Back")) {
            setVisible(false);

        }
    }
}
