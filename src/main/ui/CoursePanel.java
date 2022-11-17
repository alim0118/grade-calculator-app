//package ui;
//
//import model.Category;
//import model.Course;
//import model.StudentRecord;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CoursePanel extends JPanel implements ActionListener {
//    private TextField name;
//    private TextField credits;
//    private TextField desiredGrade;
//    private TextField status;
//    private JButton next;
//
//    private Course course;
//    private ArrayList<Category> categories;
//    private List<Course> courses;
//
//    private StudentRecord studentRecord;
//
//    public CoursePanel(ArrayList<Category> categories) {
//        this.categories = categories;
//        // resets courses each time
//        courses = new ArrayList<>();
//        studentRecord = new StudentRecord(1);
//
//        setLayout(new FlowLayout());
//        setPreferredSize(new Dimension(100, 100));
//
//        add(new Label("Course Name: "));
//        name = new TextField(10);
//        name.setEditable(true);
//        add(name);
//
//        add(new Label("Number of Credits: "));
//        credits = new TextField(10);
//        credits.setEditable(true);
//        add(credits);
//
//        add(new Label("Desired Grade: "));
//        desiredGrade = new TextField(10);
//        desiredGrade.setEditable(true);
//        add(desiredGrade);
//
//        next = new JButton("Add");
//        next.setActionCommand("Add");
//        next.addActionListener(this);
//        add(next, BorderLayout.PAGE_END);
//
//
//
//    }
//
//    public void createCourse() {
//        course = new Course(getName(), getCredits(), getDesired(), categories);
//        for (Category cat : categories) {
//            course.addCategory(cat);
//        }
//        // why does courses reset each time?
//        courses.add(course);
//        studentRecord.addCourse(course);
//
//
//    }
//
//    public String getName() {
//        return name.getText();
//    }
//
//    public int getCredits() {
//        return Integer.parseInt(credits.getText());
//    }
//
//    public double getDesired() {
//        return Double.parseDouble(desiredGrade.getText());
//    }
//
//    public ArrayList<Category> getCategories() {
//        return categories;
//    }
//
//    // technically only returns one course
//    public List<Course> getCourses() {
//        return courses;
//    }
//
//    public Course getCurrentCourse() {
//        return course;
//    }
//
//    public StudentRecord getStudentRecord() {
//        return studentRecord;
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String button = e.getActionCommand();
//        if (button.equals("Add")) {
//            createCourse();
//            setVisible(false);
//        }
//    }
//}
