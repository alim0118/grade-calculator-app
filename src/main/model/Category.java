package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// Represents a category having a category name, weight (in percent), mark (in percent), and weighted mark (in percent)
public class Category implements Writable {
    private String name; // category name
    private double weight; // weight of the category
    private double mark; // received mark of the category
    private double weightedMark; // weighted mark of the category
    private boolean marked; // status of category being marked

    /*
     * REQUIRES: categoryName has a non-zero length; categoryWeight > 0; 100 >= categoryMark >= 0
     * EFFECTS: name of category is set to categoryName; weight of category is set to categoryWeight;
     *          mark of category is set to categoryMark;
     *          weightedMark is a positive rational number, set to the product of the weight and mark of the category
     */
    public Category(String categoryName, double categoryWeight, double categoryMark, boolean categoryStatus) {
        name = categoryName;
        weight = categoryWeight;
        mark = categoryMark;
        weightedMark = (weight * mark) / 100;
        marked = categoryStatus;
    }

    // REQUIRES: 0 <= newMark <= 100
    // MODIFIES: this
    // EFFECTS: sets category to new mark and changes weighted mark
    public void setMark(double newMark) {
        mark = newMark;
        setWeightedMark(newMark);
    }

    // REQUIRES: 0 <= newMark <= 100
    // MODIFIES: this
    // EFFECTS: set weighted mark with new mark
    public void setWeightedMark(double newMark) {
        weightedMark = (weight * newMark) / 100;
    }

    // EFFECTS: returns true if this object is the same as the obj argument,
    //          false otherwise
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return Double.compare(category.weight, weight) == 0
                && Double.compare(category.mark, mark) == 0
                && Double.compare(category.weightedMark, weightedMark) == 0
                && marked == category.marked
                && Objects.equals(name, category.name);
    }

    // EFFECTS: returns a hash code value for the object
    @Override
    public int hashCode() {
        return Objects.hash(name, weight, mark, weightedMark, marked);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category name", name);
        json.put("category weight", weight);
        json.put("category mark", mark);
        json.put("category status", marked);
        return json;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getMark() {
        return mark;
    }

    public double getWeightedMark() {
        return weightedMark;
    }

    public boolean getCategoryStatus() {
        return marked;
    }

}
