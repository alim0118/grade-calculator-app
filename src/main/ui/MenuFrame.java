package ui;

import model.Category;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuFrame extends JFrame implements ActionListener {
    private JPanel panel;
    private JPanel addPanel;
    private JPanel selectPanel;
    private JPanel criteriaPanel;
    private JPanel applyPanel;

    private JButton buttonAdd;
    private JButton buttonSubset;
    private JRadioButton buttonAll;
    private JRadioButton buttonComplete;
    private JRadioButton buttonIncomplete;
    private ButtonGroup buttonGroup;

    private JLabel select;

    private CategoryPanel catPanel;
    private CoursePanel coursePanel;

    private ArrayList<Category> categories;


    // for new user
    public MenuFrame() {
        super("Menu");
        this.setSize(500, 500);

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
            catPanel = new CategoryPanel();
            add(catPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            catNum--;
        }
        categories = catPanel.getCategories();
        coursePanel = new CoursePanel(categories);
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
        } else if (buttonAll.isSelected() && button.equals("View")) {
            // conducts new StudentRecordPanel
            // run do viewCourse for all courses

        } else if (buttonComplete.isSelected() && button.equals("View")) {
            // run do viewCourse of all completed courses

        } else {
            // run do viewCourse of all incomplete courses
        }
    }

}
