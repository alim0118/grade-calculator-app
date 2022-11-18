//package ui;
//
//import model.Course;
//import model.StudentRecord;
//import persistence.JsonReader;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ReturnFrame extends JFrame implements ActionListener {
//    private static final String JSON_STORE = "./data/studentRecord.json";
//    private JsonReader jsonReader;
//    private StudentRecord studentRecord;
//
//    private JButton buttonAdd;
//    private JButton buttonSubset;
//    private JButton next;
//    private JButton create;
//
//    private JRadioButton buttonAll;
//    private JRadioButton buttonComplete;
//    private JRadioButton buttonIncomplete;
//
//    private ButtonGroup buttonGroup;
//
//    private JPanel panel;
//    private JPanel catPanel;
//    private JPanel coursePanel;
//    private JPanel recordPanel;
//    private JPanel addPanel;
//    private JPanel selectPanel;
//    private JPanel applyPanel;
//    private JPanel criteriaPanel;
//
//    private JLabel select;
//
//    private StudentRecordPanel studentRecordPanel;
//
//    //private StudentRecordPanel studentRecordPanel;
//
//    // asks user if they want to reload then goes to student record panel?
//    public ReturnFrame() {
//        // fix title later
//        super("Student Record: returning");
//        this.setSize(500, 500);
//
//        jsonReader = new JsonReader(JSON_STORE);
//
//        reload();
//
//        doAdd();
//
//        doButton();
//
//        doPanel();
//
//        add(panel);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//
//        //new StudentRecordPanel(studentRecord.getCourseList());
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//
//
//    }
//
//    public void reload() {
//        int result = JOptionPane.showConfirmDialog(this, "Would you like to reload your course list?", "Load File",
//                JOptionPane.YES_NO_OPTION);
//
//        if (result == JOptionPane.YES_OPTION) {
//            try {
//                studentRecord = jsonReader.read();
//                //new StudentRecordPanel(studentRecord.getCourseList());
//            } catch (IOException e) {
//                System.out.println("Unable to read from file: " + JSON_STORE);
//            }
//
//        } else {
//            dispose();
//            //new MenuFrame();
//        }
//    }
//
//    public void doAdd() {
//        buttonAdd = new JButton("Add Course");
//        buttonAdd.setActionCommand("Add");
//        buttonAdd.addActionListener(this);
//
//        addPanel = new JPanel();
//        addPanel.add(buttonAdd);
//    }
//
//    public void doButton() {
//        select = new JLabel("Sort courses by: ");
//        selectPanel = new JPanel();
//        selectPanel.add(select);
//
//        buttonGroup = new ButtonGroup();
//
//        buttonAll = new JRadioButton("All Courses");
//        buttonGroup.add(buttonAll);
//        buttonAll.setActionCommand("All");
//        buttonAll.addActionListener(this);
//
//        buttonComplete = new JRadioButton("Complete");
//        buttonGroup.add(buttonComplete);
//        buttonComplete.setActionCommand("Complete");
//        buttonComplete.addActionListener(this);
//
//        buttonIncomplete = new JRadioButton("Incomplete");
//        buttonGroup.add(buttonIncomplete);
//        buttonIncomplete.setActionCommand("Incomplete");
//        buttonIncomplete.addActionListener(this);
//
//        buttonSubset = new JButton("View");
//        buttonSubset.setActionCommand("View");
//        buttonSubset.addActionListener(this);
//        applyPanel = new JPanel();
//        applyPanel.add(buttonSubset);
//    }
//
//    public void doPanel() {
//        criteriaPanel = new JPanel();
//        criteriaPanel.setLayout(new FlowLayout());
//        criteriaPanel.add(buttonAll);
//        criteriaPanel.add(buttonComplete);
//        criteriaPanel.add(buttonIncomplete);
//
//        panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
//
//        panel.add(addPanel);
//        // move to student record panel
//        panel.add(selectPanel);
//        panel.add(criteriaPanel);
//        panel.add(applyPanel);
//    }
//
//    public void viewAll() {
//        java.util.List<Course> courses = studentRecord.getCourseList();
//        studentRecordPanel = new StudentRecordPanel(courses, studentRecord);
//        add(studentRecordPanel);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//
//    }
//
//    public void viewCompleted() {
//        java.util.List<Course> courses = studentRecord.getCourseList();
//        java.util.List<Course> tempCourses = new ArrayList<>();
//        for (Course c : courses) {
//            c.checkIsCompleted();
//            if (c.getIsCompleted()) {
//                tempCourses.add(c);
//            }
//        }
//        studentRecordPanel = new StudentRecordPanel(tempCourses, studentRecord);
//        add(studentRecordPanel);
//
//    }
//
//    public void viewIncomplete() {
//        java.util.List<Course> courses = studentRecord.getCourseList();
//        List<Course> tempCourses = new ArrayList<>();
//        for (Course c : courses) {
//            c.checkIsCompleted();
//            if (!c.getIsCompleted()) {
//                tempCourses.add(c);
//            }
//        }
//        studentRecordPanel = new StudentRecordPanel(tempCourses, studentRecord);
//        add(studentRecordPanel);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}
