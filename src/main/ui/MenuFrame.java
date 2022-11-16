package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel menuTitle;
    private JButton buttonAdd;
    private JButton buttonSubset;
    private JRadioButton buttonComplete;
    private JRadioButton buttonIncomplete;
    private JLabel select;

    private CategoryPanel catPanel;
    private CoursePanel coursePanel;


    // for new user
    public MenuFrame() {
        super("Menu");
        this.setSize(500, 500);

        buttonAdd = new JButton("Add Course");
        buttonAdd.setActionCommand("Add");
        buttonAdd.addActionListener(this);

        JPanel addPanel = new JPanel();
        addPanel.add(buttonAdd);

        select = new JLabel("Sort courses by: ");
        JPanel selectPanel = new JPanel();
        selectPanel.add(select);

        buttonComplete = new JRadioButton("Complete");
        buttonComplete.setActionCommand("Complete");
        buttonComplete.addActionListener(this);

        buttonIncomplete = new JRadioButton("Incomplete");
        buttonIncomplete.setActionCommand("Incomplete");
        buttonIncomplete.addActionListener(this);

        JPanel criteriaPanel = new JPanel();
        criteriaPanel.setLayout(new FlowLayout());
        criteriaPanel.add(buttonComplete);
        criteriaPanel.add(buttonIncomplete);

        buttonSubset = new JButton("Apply");
        buttonSubset.setActionCommand("Apply");
        buttonSubset.addActionListener(this);
        JPanel applyPanel = new JPanel();
        applyPanel.add(buttonSubset);


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
        panel.add(addPanel);
        panel.add(selectPanel);
        panel.add(criteriaPanel);
        panel.add(applyPanel);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

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
        coursePanel = new CoursePanel();
        add(coursePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // then go back to main menu panel
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();

        if (button.equals("Add")) {
            popUp();
        } else if (button.equals("Apply")) {
            // check if complete or incomplete and call on viewCourse() then sort
        }
    }

//    public static void main(String[] args) {
//        new MenuPanel();
//    }
}
