package model;

import java.util.ArrayList;
import java.util.HashMap;

// Represents a transcript having an id, and courses (list of courses enrolled)
public class Transcript {
    private int id;
    private ArrayList<Course> courseList;
    private HashMap<Integer, ArrayList<Course>> idToCourse;

    private double totalWeighted;
    private int totalCredits;
    private double average;
    private String letterGrade;

    public Transcript(int id) {

    }

    public void addCourses(Course course) {
        courseList.add(course);
    }

    public void addCoursesToId() {
        idToCourse.put(id, courseList);
    }

    public void letterGrade() {
        if (average >= 90 && average <= 100) {
            letterGrade = "A+";

        } else if (average >= 85 && average <= 89) {
            letterGrade = "A";

        } else if (average >= 80 && average <= 84) {
            letterGrade = "A-";

        } else if (average >= 76 && average <= 79) {
            letterGrade = "B+";

        } else if (average >= 72 && average <= 75) {
            letterGrade = "B";

        } else if (average >= 68 && average <= 71) {
            letterGrade = "B-";

        } else if (average >= 64 && average <= 67) {
            letterGrade = "C+";

        } else if (average >= 60 && average <= 63) {
            letterGrade = "C";

        } else if (average >= 55 && average <= 59) {
            letterGrade = "C-";

        } else if (average >= 50 && average <= 54) {
            letterGrade = "D";

        } else {
            letterGrade = "F";
        }

    }

    public void calculateTotalCredits() {

    }

    public void calculateTotalWeightedGrades() {

    }

    public void calculateAverage() {

    }

    public int getId() {
        return id;
    }

    public String getLetterGrade() {
        return letterGrade;
    }


}
