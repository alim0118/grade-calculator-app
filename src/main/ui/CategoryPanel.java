package ui;

import model.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// represents a category panel
public class CategoryPanel extends JPanel implements ActionListener {
    private TextField name;
    private TextField weight;
    private TextField status;
    private TextField mark;

    private ArrayList<Category> categories;

    // EFFECTS: sets up and creates a category panel
    public CategoryPanel(ArrayList<Category> allCategories) {
        categories = allCategories;

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

        add(new Label("Category mark (0 if incomplete): "));
        mark = new TextField(10);
        mark.setEditable(true);
        add(mark);

        JButton next = new JButton("Next");
        next.setActionCommand("Next");
        next.addActionListener(this);
        add(next, BorderLayout.PAGE_END);

    }

    // EFFECTS: creates a category with a name, weight, mark, and status and adds to list of categories
    public void createCategory() {
        Category category = new Category(getName(), getWeight(), getMark(), getStatus());
        categories.add(category);

    }

    // getters

    public String getName() {
        return name.getText();
    }

    // EFFECTS: parses text into double and returns
    public double getWeight() {
        return Double.parseDouble(weight.getText());
    }

    // EFFECTS: parses text into boolean and returns
    public boolean getStatus() {
        return Boolean.parseBoolean(status.getText());
    }

    // EFFECTS: parses text into int and returns
    public double getMark() {
        return Double.parseDouble(mark.getText());
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    // MODIFIES: this
    // EFFECTS: identifies action and creates a category
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if (button.equals("Next")) {
            createCategory();
            setVisible(false);

        }
    }
}
