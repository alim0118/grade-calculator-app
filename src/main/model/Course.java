package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a course having a list of categories, name, credits, final weight (in percent),
// current grade (in percent), actual final grade (in percent), desired final grade of course (in percent),
// minimum final score needed to get desired grade (in percent), and status of being completed or not
public class Course implements Writable {
    private ArrayList<Category> categoryList; // list of categories that make up the course
    private String name; // name of the course
    private int credits; // number of credits of the course
    private double finalWeight; // weight of final of the course
    private double currentGrade; // current grade in the course, without completion of final exam
    private double actualFinalGrade; // actual final grade of the course, once completed
    private double desiredFinalGrade; // desired final grade of the course
    private double minFinalScore; // minimum score needed on final exam to get desired final grade
    private boolean isCompleted; // status of course being completed or not

    /*
     * REQUIRES: courseName is a non-zero length, courseCredits >= 1, desiredGrade >= 0, categories to be empty
     * EFFECTS: name of course is set to courseName; desiredFinalGrade of course is set to 0;
     *          categoryList is the list of categories in the course;
     */
    public Course(String courseName, int courseCredits, double desiredGrade, ArrayList<Category> categories) {
        name = courseName;
        credits = courseCredits;
        desiredFinalGrade = desiredGrade;
        categoryList = categories;
    }

    public Course(String courseName, int courseCredits, double desiredGrade) {
        name = courseName;
        credits = courseCredits;
        desiredFinalGrade = desiredGrade;
        categoryList = new ArrayList<>();
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
    public void checkIsCompleted() {
        for (int i = 0; i <= categoryList.size() - 1; i++) {
            if (!categoryList.get(i).getCategoryStatus()) {
                isCompleted = false;

            } else {
                isCompleted = true;
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: if category status is false (it is the final exam category), set final weight to that category's weight,
     *          otherwise category with the greatest weight is set to final weight
     */
    public void findFinalWeight() {
        double prev;
        double cur;
        if (!isCompleted) {
            for (int i = 0; i <= categoryList.size() - 1; i++) {
                if (!categoryList.get(i).getCategoryStatus()) {
                    finalWeight = categoryList.get(i).getWeight();
                }
            }
        } else {
            for (int i = 0; i <= categoryList.size() - 2; i++) {
                prev = categoryList.get(i).getWeight();
                cur = categoryList.get(i + 1).getWeight();
                finalWeight = Math.max(prev, cur);
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: if the course is complete, then the weighted mark of each category is added to actual final grade
     *          otherwise, the weighted mark of each category is added to current grade
     */
    public void calculateGrade() {
        if (isCompleted) {
            for (int i = 0; i <= categoryList.size() - 1; i++) {
                actualFinalGrade += categoryList.get(i).getWeightedMark();
            }
        } else {
            for (int i = 0; i <= categoryList.size() - 1; i++) {
                if (categoryList.get(i).getCategoryStatus()) {
                    currentGrade += categoryList.get(i).getWeightedMark();
                }
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: if incomplete, then minFinalScore will be actualFinalGrade
     *          otherwise, minimum score needed to final exam is calculated with formula:
     *          Required = (desired - ((1 - (finalWeight / 100)) * current)) / (finalWeight / 100)
     */
    public void calculateMinFinalScore() {
        if (isCompleted) {
            minFinalScore = actualFinalGrade;
        } else {
            findFinalWeight();
            minFinalScore = (desiredFinalGrade - ((1 - (finalWeight / 100)) * currentGrade)) / (finalWeight / 100);
        }
    }

    /*
     * REQUIRES: 0 <= newDesiredGrade <= 100
     * MODIFIES: this
     * EFFECTS: sets new desired grade
     */
    public void setDesiredFinalGrade(double newDesiredGrade) {
        desiredFinalGrade = newDesiredGrade;
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

    public int getCredits() {
        return credits;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }


    // EFFECTS: returns courses in this student record as a JSON array
    public JSONArray categoriesToJson(ArrayList<Category> categories) {
        JSONArray jsonArray = new JSONArray();

        for (Category c : categoryList) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("course name", name);
        json.put("credits", credits);
        json.put("desired final grade", desiredFinalGrade);
        json.put("categories", categoriesToJson(categoryList));
        return json;

    }


}
