package ui;

import model.Course;
import model.StudentRecord;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentRecordFrame extends JFrame {
    private static int id = 1; // id number of user
    private static final String JSON_STORE = "./data/studentRecord.json";

    private List<Course> allCourses = new ArrayList<>(); // all courses
    private Course curCourse; // current course getting input on
    private StudentRecord studentRecord;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public StudentRecordFrame() {
        studentRecord = new StudentRecord(1);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // category can have its own panel class





    public void saveBeforeExit() {
        try {
            jsonWriter.open();
            jsonWriter.write(studentRecord);
            jsonWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }



}
