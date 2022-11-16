package ui;

import model.StudentRecord;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.WindowListener;
import java.io.IOException;

public class ReturnFrame extends JFrame {
    private static final String JSON_STORE = "./data/studentRecord.json";
    private JsonReader jsonReader;
    private StudentRecord studentRecord;

    private StudentRecordPanel studentRecordPanel;

    // asks user if they want to reload then goes to student record panel?
    public ReturnFrame() {
        super("Menu");
        this.setSize(500, 500);

        jsonReader = new JsonReader(JSON_STORE);

        reload();

        new StudentRecordPanel(studentRecord.getCourseList());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    public void reload() {
        int result = JOptionPane.showConfirmDialog(this, "Would you like to reload your course list?", "Load File",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            try {
                studentRecord = jsonReader.read();
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }

        } else {
            dispose();
        }
    }
}
