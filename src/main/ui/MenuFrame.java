package ui;

import model.Category;
import model.Course;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuFrame extends JFrame implements ActionListener {
    private JPanel panel;
    private JPanel donePanel;
    private JPanel addPanel;
    private JPanel selectPanel;
    private JPanel criteriaPanel;
    private JPanel applyPanel;

    private JButton buttonAdd;
    private JButton buttonDone;
    private JButton buttonSubset;
    private JRadioButton buttonAll;
    private JRadioButton buttonComplete;
    private JRadioButton buttonIncomplete;
    private ButtonGroup buttonGroup;

    private JLabel select;

    private CategoryPanel catPanel;
    private CoursePanel coursePanel;
    private StudentRecordPanel recordPanel;

    private ArrayList<Category> categories;
    private List<Course> courses;

    private List<Course> temp;


    // for new user
    public MenuFrame() {
        super("Menu");
        this.setSize(500, 500);
        temp = new ArrayList<>();

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
        int catNum = Integer.parseInt(num);

        while (catNum > 0) {
            remove(panel);
            catPanel = new CategoryPanel();
            add(catPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            catNum--;
        }
        categories = catPanel.getCategories();
        coursePanel = new CoursePanel(categories);


        // this courses should carry into student record
        courses = coursePanel.getCourses();

//        // go through for loop and .add() to courses
//        for (Course c : coursePanel.getCourses()) {
//            courses.add(c);
//        }

        add(coursePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();

        if (button.equals("Add")) {
            popUp();
            add(panel);


        } else if (button.equals("View")) {
            // go through for loop and .add() to courses
            // should be added the moment course panel is called
//            for (Course c : coursePanel.getCourses()) {
//                courses.add(c);
//            }
            //courses = coursePanel.getCourses();
            remove(panel);
            recordPanel = new StudentRecordPanel(temp);
            add(recordPanel);
            if (buttonAll.isSelected()) {
                recordPanel.viewAll();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
            } else if (buttonComplete.isSelected()) {
                recordPanel.viewCompleted();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
            } else {
                recordPanel.viewIncomplete();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
            }
        }
        if (!temp.contains(courses.get(0))) {
            temp.add(courses.get(0));
        }

    }
}
