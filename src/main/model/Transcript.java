package model;

import java.util.ArrayList;

// Represents a transcript having an id, courses (list of courses enrolled), total weights, total credits, average,
// and letter grade
public class Transcript {
    private int id; // id of user
    private ArrayList<Course> courseList; // list of all courses enrolled
    private double totalWeighted; // total weighted grades from each course
    private int totalCredits; // total number of credits enrolled
    private double average; // average from all courses
    private String letterGrade; // average converted to letter grade

    /*
     * REQUIRES: id > 0
     * EFFECTS: id of transcript is set to userId; courseList is the list of courses in the transcript
     */
    public Transcript(int userId, ArrayList<Course> courses) {
        id = userId;
        courseList = courses;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds course to course list (with no duplicated)
     */
    public void addCourses(Course course) {
        if (!courseList.contains(course)) {
            courseList.add(course);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds each number of credits of courses to total credits
     */
    public int calculateTotalCredits() {
        for (int i = 0; i <= courseList.size() - 1; i++) {
            totalCredits += courseList.get(i).getCredits();
        }
        return totalCredits;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds each weighted grade of courses to total weighted
     */
    public double calculateTotalWeightedGrades() {
        for (int i = 0; i <= courseList.size() - 1; i++) {
            totalWeighted += courseList.get(i).getActualFinalGrade();
        }
        return totalWeighted;
    }

    /*
     * MODIFIES: this
     * EFFECTS: divides total weighted grades of all courses by total number of total credits
     */
    public double calculateAverage() {
        return average = totalWeighted / (double)totalCredits;
    }

    /*
     * MODIFIES: this
     * EFFECTS: determines letter grade given average
     */
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

    //getters
    public int getId() {
        return id;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public double getTotalWeighted() {
        return totalWeighted;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public double getAverage() {
        return average;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

}
