//package ui;
//
//import model.Category;
//import model.Course;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//public class CategoryPanel extends JPanel implements ActionListener {
//    private static final String JSON_STORE = "./data/studentRecord.json";
//
//    private TextField name;
//    private TextField weight;
//    private TextField status;
//    private TextField mark;
//    private JButton next;
//
//    private Category category;
//    private ArrayList<Category> categories;
//    private Course course;
//
//    public CategoryPanel() {
//        categories = new ArrayList<>();
//
//        setLayout(new FlowLayout());
//        setPreferredSize(new Dimension(100, 100));
//
//        add(new Label("Category Name: "));
//        name = new TextField(10);
//        name.setEditable(true);
//        add(name);
//
//        add(new Label("Category Weight: "));
//        weight = new TextField(10);
//        weight.setEditable(true);
//        add(weight);
//
//        add(new Label("Category Status (True/False): "));
//        status = new TextField(10);
//        status.setEditable(true);
//        add(status);
//
//        add(new Label("Category mark (0 if incomplete): "));
//        mark = new TextField(10);
//        mark.setEditable(true);
//        add(mark);
//
//        next = new JButton("Next");
//        next.setActionCommand("Next");
//        next.addActionListener(this);
//        add(next, BorderLayout.PAGE_END);
//
//    }
//
//    public void createCategory() {
//        category = new Category(getName(), getWeight(), getMark(), getStatus());
//        categories.add(category);
//
//
//
//
//    }
//
//    public String getName() {
//        return name.getText();
//    }
//
//    public double getWeight() {
//        return Double.parseDouble(weight.getText());
//    }
//
//    public boolean getStatus() {
//        return Boolean.parseBoolean(status.getText());
//    }
//
//    public double getMark() {
//        return Double.parseDouble(mark.getText());
//    }
//
//    public ArrayList<Category> getCategories() {
//        return categories;
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String button = e.getActionCommand();
//        if (button.equals("Next")) {
//            createCategory();
//            setVisible(false);
//
//
//
//
//        }
//    }
//}
