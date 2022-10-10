package model;

// Represents a category having a category name, weight (in percent), mark (in percent), and weighted mark (in percent)
public class Category {
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
