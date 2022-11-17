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
import java.util.ArrayList;
import java.util.List;

public class StudentRecordFrame extends JFrame implements ActionListener {
    private static int id = 1; // id number of user
    private static final String JSON_STORE = "./data/studentRecord.json";

    private int catNum;
    private ArrayList<Category> allCategories = new ArrayList<>();
    private List<Course> allCourses = new ArrayList<>(); // all courses
    private Course curCourse; // current course getting input on
    private StudentRecord studentRecord;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private TextField catName;
    private TextField catWeight;
    private TextField catMark;
    private TextField catStatus;

    private TextField courseName;
    private TextField courseCredits;
    private TextField courseDesired;

    private JButton buttonAdd;
    private JButton buttonSubset;
    private JButton next;
    private JButton create;

    private JRadioButton buttonAll;
    private JRadioButton buttonComplete;
    private JRadioButton buttonIncomplete;

    private ButtonGroup buttonGroup;

    private JPanel panel;
    private JPanel addPanel;
    private JPanel selectPanel;
    private JPanel applyPanel;
    private JPanel criteriaPanel;

    private JLabel select;


    public StudentRecordFrame() {
        super("Student Record");
        this.setSize(500, 500);

        studentRecord = new StudentRecord(1);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        // call on other methods here

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
        // move to student record panel
        panel.add(selectPanel);
        panel.add(criteriaPanel);
        panel.add(applyPanel);
    }

    public void popUp() {
        String num = JOptionPane.showInputDialog(this, "How many categories are in your course?", null);
        catNum = Integer.parseInt(num);
        remove(panel);
        doCreateCategory();
        catNum--;


//        while (catNum >= 1) {
//            remove(panel);
//            doCreateCategory();
//            catNum--;
//        }

    }

    // start with adding category class

    public void doCreateCategory() {
        JPanel catPanel = new JPanel();
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(100, 100));
        panel.add(catHelper(catPanel));
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        allCategories.add(createCategory(getCatName(), getCatWeight(), getCatMark(), getCatStatus()));

        add(catPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //doCreateCourse(allCategories);
        //createStudentRecord(id, allCourses);

    }

    private void doCreateCourse(ArrayList<Category> categories) {
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new FlowLayout());
        coursePanel.setPreferredSize(new Dimension(100, 100));
        courseHelper(coursePanel);

        Course temp = createCourse(getCourseName(), getCourseCredits(), getCourseDesired(), categories);
        for (Category cat : categories) {
            temp.addCategory(cat);
        }
        allCourses.add(temp);
        createStudentRecord(id, allCourses);
        studentRecord.addCourse(curCourse);

    }


    private Category createCategory(String name, double weight, double mark, boolean status) {
        Category newCategory = new Category(name, weight, mark, status);
        return newCategory;
    }

    private Course createCourse(String name, int credits, double desired, ArrayList<Category> categories) {
        curCourse = new Course(name, credits, desired, categories);
        return curCourse;
    }

    private void createStudentRecord(int id, List<Course> courses) {
        studentRecord = new StudentRecord(id);

        for (Course c : courses) {
            studentRecord.addCourse(c);
        }
    }


    public JPanel catHelper(JPanel catPanel) {
        catPanel.add(new Label("Category Name: "));
        catName = new TextField(10);
        catName.setEditable(true);
        catPanel.add(catName);

        catPanel.add(new Label("Category Weight: "));
        catWeight = new TextField(10);
        catWeight.setEditable(true);
        catPanel.add(catWeight);

        catPanel.add(new Label("Category Status (True/False): "));
        catStatus = new TextField(10);
        catStatus.setEditable(true);
        catPanel.add(catStatus);

        catPanel.add(new Label("Category mark (0 if incomplete): "));
        catMark = new TextField(10);
        catMark.setEditable(true);
        catPanel.add(catMark);

        next = new JButton("Next");
        next.setActionCommand("Next");
        next.addActionListener(this);
        catPanel.add(next, BorderLayout.PAGE_END);

        return catPanel;
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
        add(create, BorderLayout.PAGE_END);

        panel.add(coursePanel);
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

    public String getCatName() {
        return catName.getText();
    }

    public double getCatWeight() {
        return Double.parseDouble(catWeight.getText());
    }

    public boolean getCatStatus() {
        return Boolean.parseBoolean(catStatus.getText());
    }

    public double getCatMark() {
        return Double.parseDouble(catMark.getText());
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

        // triggers creating category
        if (button.equals("Add")) {
            popUp();
            //add(panel);

            // after category is inputted
            // doCreateCategory keeps adding when next is pressed -> FIX
        } else if (button.equals("Next")) {
            if (catNum >= 1) {
                doCreateCategory();
                catNum--;
            }

            // for panel
            //setVisible(false);

            // after course is inputted
        } else if (button.equals("Create")) {
            doCreateCourse(allCategories);
            //setVisible(false);
        }
    }
}
