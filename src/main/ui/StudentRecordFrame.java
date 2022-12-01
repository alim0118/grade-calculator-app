package ui;

import model.Category;
import model.Course;
import model.Event;
import model.EventLog;
import model.StudentRecord;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

// represents a student record frame for a new user
public class StudentRecordFrame extends JFrame implements ActionListener {
    private static int id = 1;

    private ArrayList<Category> allCategories;
    private List<Course> allCourses = new ArrayList<>();
    private Course curCourse;
    private StudentRecord studentRecord;

    private TextField courseName;
    private TextField courseCredits;
    private TextField courseDesired;

    private JButton create;
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


    // EFFECTS: sets up and creates a student record frame for new user
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

        windowListenerHelper();
    }

    // MODIFIES: this
    // EFFECTS: helper to set up add button, adds to add panel
    public void doAdd() {
        JButton buttonAdd = new JButton("Add Course");
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

        JButton buttonSubset = new JButton("View");
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
    //          conducts creating course and add to student record
    public void popUp() {
        allCategories = new ArrayList<>();
        String num = JOptionPane.showInputDialog(this, "How many categories are in your course?", null);
        int catNum = Integer.parseInt(num);

        while (catNum > 0) {
            remove(panel);
            categoryPanel = new CategoryPanel(allCategories);
            add(categoryPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            catNum--;

        }
        allCategories = categoryPanel.getCategories();
        doCreateCourse();

        addToStudentRecord(allCourses);

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
    private void addToStudentRecord(List<Course> courses) {
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

        windowListenerHelper();

    }

    // MODIFIES: this
    // EFFECTS: sets up student record panel with completed courses
    public void viewCompleted() {
        List<Course> tempCourses = studentRecord.getCompleteCourseList();
        studentRecordPanel = new StudentRecordPanel(tempCourses, studentRecord);
        add(studentRecordPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        windowListenerHelper();
    }

    // MODIFIES: this
    // EFFECTS: sets up student record panel with incomplete courses
    public void viewIncomplete() {
        List<Course> tempCourses = studentRecord.getIncompleteCourseList();
        studentRecordPanel = new StudentRecordPanel(tempCourses, studentRecord);
        add(studentRecordPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        windowListenerHelper();
    }

    // MODIFIES: this
    // EFFECTS: sets up minimum grade panel
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

    // inspiration taken from ScreenPrinter in:
    // https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

    // EFFECTS: prints event log
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    // inspiration taken from:
    // https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed

    // EFFECTS: helper to add window listener and process corresponding action
    public void windowListenerHelper() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
                System.exit(0);
            }
        });
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
