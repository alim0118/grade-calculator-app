package ui;

import model.Category;
import model.Course;
import model.StudentRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CategoryPanel extends JPanel implements ActionListener {
    private TextField name;
    private TextField weight;
    private TextField status;
    private TextField mark;
    private JButton next;

    private Category category;
    private ArrayList<Category> categories;
    private Course course;


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

        add(new Label("Category Status (True/False): "));
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

    // fix
    public void saveText() {

        String catName = name.getText();
        double catWeight = Double.parseDouble(weight.getText());
        boolean catStatus = Boolean.parseBoolean(status.getText());
        double catMark = Double.parseDouble(mark.getText());

        category = new Category(catName, catWeight, catMark, catStatus);
        categories.add(category);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if (button.equals("Next")) {
            saveText();
            setVisible(false);




        }
    }
}
