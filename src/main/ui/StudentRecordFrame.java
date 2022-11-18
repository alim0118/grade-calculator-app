package ui;

import model.Category;
import model.Course;
import model.StudentRecord;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentRecordFrame extends JFrame implements ActionListener {
    private static int id = 1; // id number of user

    private ArrayList<Category> allCategories = new ArrayList<>();
    private List<Course> allCourses = new ArrayList<>(); // all courses
    private Course curCourse; // current course getting input on
    private StudentRecord studentRecord;

    private TextField courseName;
    private TextField courseCredits;
    private TextField courseDesired;

    private JButton buttonAdd;
    private JButton buttonSubset;
    private JButton create;

    private JRadioButton buttonAll;
    private JRadioButton buttonComplete;
    private JRadioButton buttonIncomplete;

    private ButtonGroup buttonGroup;

    private JPanel panel;
    private JPanel coursePanel;
    private JPanel addPanel;
    private JPanel selectPanel;
    private JPanel applyPanel;
    private JPanel criteriaPanel;

    private JLabel select;

    private CategoryPanel categoryPanel;
    private StudentRecordPanel studentRecordPanel;


    public StudentRecordFrame() {
        super("Student Record");
        this.setSize(500, 500);

        studentRecord = new StudentRecord(id);

        doAdd();

        doButton();

        doPanel();

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void doAdd() {
        buttonAdd = new JButton("Add Course");
        buttonAdd.setActionCommand("Add");
        buttonAdd.addActionListener(this);

        addPanel = new JPanel();
        addPanel.add(buttonAdd);
    }

    public void doButton() {
        select = new JLabel("Sort courses by: ");
        selectPanel = new JPanel();
        selectPanel.add(select);

        buttonGroup = new ButtonGroup();

        buttonAll = new JRadioButton("All Courses");
        buttonGroup.add(buttonAll);
        buttonAll.setActionCommand("All");
        buttonAll.addActionListener(this);

        buttonComplete = new JRadioButton("Complete");
        buttonGroup.add(buttonComplete);
        buttonComplete.setActionCommand("Complete");
        buttonComplete.addActionListener(this);

        buttonIncomplete = new JRadioButton("Incomplete");
        buttonGroup.add(buttonIncomplete);
        buttonIncomplete.setActionCommand("Incomplete");
        buttonIncomplete.addActionListener(this);

        buttonSubset = new JButton("View");
        buttonSubset.setActionCommand("View");
        buttonSubset.addActionListener(this);
        applyPanel = new JPanel();
        applyPanel.add(buttonSubset);
    }

    public void doPanel() {
        criteriaPanel = new JPanel();
        criteriaPanel.setLayout(new FlowLayout());
        criteriaPanel.add(buttonAll);
        criteriaPanel.add(buttonComplete);
        criteriaPanel.add(buttonIncomplete);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));

        panel.add(addPanel);
        panel.add(selectPanel);
        panel.add(criteriaPanel);
        panel.add(applyPanel);
    }

    public void popUp() {
        String num = JOptionPane.showInputDialog(this, "How many categories are in your course?", null);
        int catNum = Integer.parseInt(num);

        while (catNum > 0) {
            remove(panel);
            categoryPanel = new CategoryPanel();
            add(categoryPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            catNum--;
        }
        allCategories = categoryPanel.getCategories();
        doCreateCourse();

        createStudentRecord(allCourses);

    }

    private void doCreateCourse() {
        coursePanel = new JPanel();
        coursePanel.setLayout(new FlowLayout());
        coursePanel.setPreferredSize(new Dimension(100, 100));
        courseHelper(coursePanel);
    }

    private void doTemp() {
        curCourse = createCourse(getCourseName(), getCourseCredits(), getCourseDesired(), allCategories);
        for (Category cat : allCategories) {
            curCourse.addCategory(cat);
        }
        allCourses.add(curCourse);

        studentRecord.addCourse(curCourse);
    }

    private Course createCourse(String name, int credits, double desired, ArrayList<Category> categories) {
        Course temp = new Course(name, credits, desired, categories);
        return temp;
    }

    private void createStudentRecord(List<Course> courses) {
        for (Course c : courses) {
            studentRecord.addCourse(c);
        }
    }

    public void courseHelper(JPanel coursePanel) {
        coursePanel.add(new Label("Course Name: "));
        courseName = new TextField(10);
        courseName.setEditable(true);
        coursePanel.add(courseName);

        coursePanel.add(new Label("Number of Credits: "));
        courseCredits = new TextField(10);
        courseCredits.setEditable(true);
        coursePanel.add(courseCredits);

        coursePanel.add(new Label("Desired Grade: "));
        courseDesired = new TextField(10);
        courseDesired.setEditable(true);
        coursePanel.add(courseDesired);

        create = new JButton("Create");
        create.setActionCommand("Create");
        create.addActionListener(this);

        coursePanel.add(create, BorderLayout.PAGE_END);

        add(coursePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void viewAll() {
        List<Course> courses = studentRecord.getCourseList();
        studentRecordPanel = new StudentRecordPanel(courses, studentRecord);
        add(studentRecordPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void viewCompleted() {
        List<Course> courses = studentRecord.getCourseList();
        List<Course> tempCourses = new ArrayList<>();
        for (Course c : courses) {
            c.checkIsCompleted();
            if (c.getIsCompleted()) {
                tempCourses.add(c);
            }
        }
        studentRecordPanel = new StudentRecordPanel(tempCourses, studentRecord);
        add(studentRecordPanel);

    }

    public void viewIncomplete() {
        List<Course> courses = studentRecord.getCourseList();
        List<Course> tempCourses = new ArrayList<>();
        for (Course c : courses) {
            c.checkIsCompleted();
            if (!c.getIsCompleted()) {
                tempCourses.add(c);
            }
        }
        studentRecordPanel = new StudentRecordPanel(tempCourses, studentRecord);
        add(studentRecordPanel);
    }

    public String getCourseName() {
        return courseName.getText();
    }

    public int getCourseCredits() {
        return Integer.parseInt(courseCredits.getText());
    }

    public double getCourseDesired() {
        return Double.parseDouble(courseDesired.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();

        if (button.equals("Add")) {
            popUp();

        } else if (button.equals("Create")) {
            doTemp();
            coursePanel.setVisible(false);
            add(panel);

        } else if (button.equals("View")) {
            remove(panel);

            if (buttonAll.isSelected()) {
                viewAll();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);

            } else if (buttonComplete.isSelected()) {
                viewCompleted();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);

            } else {
                viewIncomplete();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
            }

        }

    }
}
