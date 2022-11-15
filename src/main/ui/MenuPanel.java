package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel menuTitle;
    private JButton buttonAdd;
    private JButton buttonSubset;
    private JRadioButton buttonComplete;
    private JRadioButton buttonIncomplete;
    private JLabel select;


    // for new user
    public MenuPanel() {
        super("Menu");
        this.setSize(600, 600);

        menuTitle = new JLabel();
        menuTitle.setText("Menu");
        menuTitle.setAlignmentX(CENTER_ALIGNMENT);

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
        buttonIncomplete.setActionCommand("Complete");
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
        panel.add(menuTitle);

        panel.add(addPanel);
        panel.add(selectPanel);
        panel.add(criteriaPanel);
        panel.add(applyPanel);

        add(panel);
//        add(addPanel, BorderLayout.PAGE_START);
//        add(selectPanel, BorderLayout.LINE_START);
//        add(criteriaPanel, BorderLayout.CENTER);
//        add(applyPanel, BorderLayout.PAGE_END);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //popUp();
    }

    public void popUp() {
        String num = JOptionPane.showInputDialog(this, "How many categories are in your course?", null);
//        int catNum = Integer.parseInt(num);
//
//        while (catNum > 0) {
//            // run category panel
//            catNum--;
//        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

//    public static void main(String[] args) {
//        new MenuPanel();
//    }
}
