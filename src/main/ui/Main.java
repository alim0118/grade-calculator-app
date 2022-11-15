package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

// new grade calculator app
public class Main {
    public static void main(String[] args) {
        try {
            new GradeCalculatorApp();
        } catch (FileNotFoundException e) {
            System.out.println("Error occured: file not file");
        }

        //SwingUtilities.invokeLater(MainGUI::new);

        //new MainGUI();
    }

}
