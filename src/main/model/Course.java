package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Represents a course having a list of categories, name, final weight (in percent),
// current grade (in percent), actual final grade (in percent), desired final grade of course (in percent),
// minimum final score needed to get desired grade (in percent), and status of being completed or not
public class Course {
    private ArrayList<Category> categoryList; // list of categories that make up the course
    private String name; // name of the course
    private double finalWeight; // weight of final of the course
    private double currentGrade; // current grade in the course, without completion of final exam
    private double actualFinalGrade; // actual final grade of the course, once completed
    private double desiredFinalGrade; // desired final grade of the course
    private double minFinalScore; // minimum score needed on final exam to get desired final grade
    private boolean isCompleted; // status of course being completed or not

    /*
     * REQUIRES: courseName is a non-zero length, desiredGrade >= 0
     * EFFECTS: name of course is set to courseName; desiredFinalGrade of course is set to desiredGrade;
     *          categoryList is the list of categories in the course;
     *          numCategory is a positive integer set to the size of categories
     */
    public Course(String courseName, double desiredGrade, ArrayList<Category> categories) {
        name = courseName;
        desiredFinalGrade = desiredGrade;
        categoryList = categories;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds category to category list (with no duplicates)
     */
    public void addCategory(Category category) {
        if (!categoryList.contains(category)) {
            categoryList.add(category);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: if even one category status is false then course status is set to false
     */
    public void isCompleted() {
        for (int i = 0; i <= categoryList.size() - 1; i++) {
            if (categoryList.get(i).getCategoryStatus() == false) {
                isCompleted = false;

            } else {
                isCompleted = true;
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: if category status is false (it is the final exam category), set final weight to that category's weight
     */
    public void findFinalWeight() {
        if (isCompleted == false) {
            for (int i = 0; i <= categoryList.size() - 1; i++) {
                if (categoryList.get(i).getCategoryStatus() == false) {
                    finalWeight = categoryList.get(i).getWeight();
                }
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: if the course is incomplete, then the weighted mark of each category is added to current grade
     *          and is returned
     */
    public double calculateCurrentGrade() {
        if (isCompleted == false) {
            for (int i = 0; i <= categoryList.size() - 1; i++) {
                if (categoryList.get(i).getCategoryStatus() == true) {
                    currentGrade += categoryList.get(i).getWeightedMark();
                }
            }
        }
        return currentGrade;
    }

    /*
     * MODIFIES: this
     * EFFECTS: if the course is complete, then the weighted mark of each category is added to actual final grade
     *          and is returned
     */
    public double calculateActualGrade() {
        if (isCompleted == true) {
            for (int i = 0; i <= categoryList.size() - 1; i++) {
                actualFinalGrade += categoryList.get(i).getWeightedMark();
            }
        }
        return actualFinalGrade;
    }

    /*
     * MODIFIES: this
     * EFFECTS: minimum score needed to final exam is calculated with formula:
     *          Required = (Goal − Current × (100% − Final Weight)) / Final Weight
     */
    public void calculateMinFinalScore() {
        minFinalScore = (desiredFinalGrade - currentGrade * (100 - finalWeight)) / finalWeight;
    }

    public String getCourseName() {
        return name;
    }

    public double getFinalWeight() {
        return finalWeight;
    }

    public double getCurrentGrade() {
        return currentGrade;
    }

    public double getActualFinalGrade() {
        return actualFinalGrade;
    }

    public double getDesiredFinalGrade() {
        return desiredFinalGrade;
    }

    public double getMinFinalScore() {
        return minFinalScore;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }
}
