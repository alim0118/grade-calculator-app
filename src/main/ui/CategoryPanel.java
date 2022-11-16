package ui;

import model.StudentRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryPanel extends JPanel implements ActionListener {
    private TextField name;
    private TextField weight;
    private TextField status;
    private TextField mark;
    private JButton next;


    public CategoryPanel() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(100, 100));

        add(new Label("Category Name: "));
        name = new TextField(10);
        name.setEditable(true);
        add(name);

        add(new Label("Category Weight: "));
        weight = new TextField(10);
        weight.setEditable(true);
        add(weight);

        add(new Label("Category Status: "));
        status = new TextField(10);
        status.setEditable(true);
        add(status);

        add(new Label("Category mark: "));
        mark = new TextField(10);
        mark.setEditable(true);
        add(mark);

        next = new JButton("Next");
        next.setActionCommand("Next");
        next.addActionListener(this);
        add(next, BorderLayout.PAGE_END);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if (button.equals("Next")) {
            setVisible(false);




        }
    }
}
