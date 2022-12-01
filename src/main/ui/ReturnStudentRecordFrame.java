package ui;

import model.Category;
import model.Course;
import model.StudentRecord;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// represents a student record frame for a returning user
public class ReturnStudentRecordFrame extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/studentRecord.json";

    private int id;
    private ArrayList<Category> allCategories = new ArrayList<>();
    private List<Course> allCourses = new ArrayList<>();
    private Course curCourse;
    private StudentRecord studentRecord;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private TextField courseName;
    private TextField courseCredits;
    private TextField courseDesired;

    private JButton create;
    private JButton buttonAdd;
    private JButton buttonSubset;
    private JButton calculate;

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
    private MinimumGradePanel minimumGradePanel;

    // EFFECTS: sets up and creates a student record frame for returning user and student record is loaded from file
    public ReturnStudentRecordFrame() throws FileNotFoundException {
        super("Student Record: returning");
        this.setSize(500, 500);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        reload();
        id = studentRecord.getId();

        doAdd();

        doButton();

        doPanel();

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: helper to set up add button, adds to add panel
    public void doAdd() {
        buttonAdd = new JButton("Add Course");
        buttonAdd.setActionCommand("Add");
        buttonAdd.addActionListener(this);

        calculate = new JButton("Calculate Minimum Grade");
        calculate.setActionCommand("Calculate");
        calculate.addActionListener(this);

        addPanel = new JPanel();
        addPanel.add(buttonAdd);
        addPanel.add(calculate);
    }

    // MODIFIES: this
    // EFFECTS: helper to set up buttons for sorting and viewing courses, adds to apply panel
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

    // MODIFIES: this
    // EFFECTS: helper to create panels for add and sort/view buttons, adds to panel
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

    // MODIFIES: this
    // EFFECTS: creates pop up window and creates category panels catNum times;
    //          conducts creating course and adding to student record
    public void popUp() {
        String num = JOptionPane.showInputDialog(this, "How many categories are in your course?");
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

        addsToStudentRecord(allCourses);

    }

    // MODIFIES: this
    // EFFECTS: creates and sets up course panel
    private void doCreateCourse() {
        coursePanel = new JPanel();
        coursePanel.setLayout(new FlowLayout());
        coursePanel.setPreferredSize(new Dimension(100, 100));
        courseHelper(coursePanel);

    }

    // MODIFIES: this
    // EFFECTS: helper to create course and adds course to student record
    private void doTemp() {
        curCourse = createCourse(getCourseName(), getCourseCredits(), getCourseDesired(), allCategories);
        for (Category cat : allCategories) {
            curCourse.addCategory(cat);
        }
        allCourses.add(curCourse);

        studentRecord.addCourse(curCourse);
    }

    // REQUIRES: name to be non-zero length, credits >=1, 100 >= desired >= 0, categories to not be empty
    // MODIFIES: this
    // EFFECTS: creates a course with name, credits, desired grade, and corresponding categories and returns it
    private Course createCourse(String name, int credits, double desired, ArrayList<Category> categories) {
        Course temp = new Course(name, credits, desired, categories);
        return temp;
    }

    // REQUIRES: courses to be not empty
    // MODIFIES: this
    // EFFECTS: adds courses to student record
    private void addsToStudentRecord(List<Course> courses) {
        for (Course c : courses) {
            studentRecord.addCourse(c);
        }
    }

    // MODIFIES: this
    // EFFECTS: helper to set up course panel
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

    // MODIFIES: this
    // EFFECTS: sets up student record panel with all courses
    public void viewAll() {
        List<Course> courses = studentRecord.getCourseList();
        studentRecordPanel = new StudentRecordPanel(courses, studentRecord);
        add(studentRecordPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up student record panel with completed courses
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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up student record panel with incomplete courses
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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // helper method
    public void viewCalculated() {
        List<Course> courses = studentRecord.getCourseList();
        minimumGradePanel = new MinimumGradePanel(courses, studentRecord);
        add(minimumGradePanel);
        panel.setVisible(false);
        remove(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        add(panel);
        panel.setVisible(true);
    }

    // inspiration taken from WorkRoomApp in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: creates dialog window to load student record from file;
    //          if user chooses yes, then student record from file is loaded;
    //          else, creates a student record frame for new user
    public void reload() {
        // image taken from: https://www.veryicon.com/icons/miscellaneous/utility-development-icon/yy-xinxi.html
        Icon icon = new ImageIcon("data/categoryIcon.png");
        int result = JOptionPane.showConfirmDialog(this, "Would you like to reload your course list?", "Load File",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);

        if (result == JOptionPane.YES_OPTION) {
            try {
                studentRecord = jsonReader.read();
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }

        } else {
            dispose();
            new StudentRecordFrame();
        }
    }

    // getters

    public String getCourseName() {
        return courseName.getText();
    }

    // EFFECTS: parses text into int and returns
    public int getCourseCredits() {
        return Integer.parseInt(courseCredits.getText());
    }

    // EFFECTS: parses text into double and returns
    public double getCourseDesired() {
        return Double.parseDouble(courseDesired.getText());
    }

    // MODIFIES: this
    // EFFECTS: identifies action and processes corresponding event
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();

        if (button.equals("Add")) {
            popUp();

        } else if (button.equals("Create")) {
            doTemp();
            coursePanel.setVisible(false);
            add(panel);

        } else if (button.equals("Calculate")) {
            viewCalculated();

        } else if (button.equals("View")) {
            panel.setVisible(false);
            remove(panel);

            if (buttonAll.isSelected()) {
                viewAll();


            } else if (buttonComplete.isSelected()) {
                viewCompleted();


            } else {
                viewIncomplete();

            }

            add(panel);
            panel.setVisible(true);

        }

    }
}

