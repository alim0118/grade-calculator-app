package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    private Course testCourse;
    private ArrayList<Category> testCategoryList;
    private Category testCategory1;
    private Category testCategory2;
    private Category testCategory3;
    private Category testCategory4;

    @BeforeEach
    void runBefore() {
        testCategoryList = new ArrayList<>();
        testCategory1 = new Category("Homework", 15, 100, true);
        testCategory2 = new Category("Midterm", 30, 75, true);
        testCategory3 = new Category("Final Exam", 55, 0, false);
        testCategory4 = new Category("Final Exam", 55, 0, true);

        testCourse = new Course("CPSC 210", 4, 89.5, testCategoryList);
    }

    @Test
    void testConstructor() {
        assertEquals("CPSC 210", testCourse.getCourseName());
        assertEquals(4, testCourse.getCredits());
        assertEquals(89.5, testCourse.getDesiredFinalGrade());
        assertEquals(testCategoryList, testCourse.getCategoryList());
    }

    @Test
    void testAddCategory() {
        assertEquals(0, testCategoryList.size());
        testCourse.addCategory(testCategory1);
        assertEquals(1, testCategoryList.size());
        assertTrue(testCategoryList.contains(testCategory1));

    }

    @Test
    void testAddMultipleCategories() {
        assertEquals(0, testCategoryList.size());
        testCourse.addCategory(testCategory1);
        assertEquals(1, testCategoryList.size());
        assertTrue(testCategoryList.contains(testCategory1));

        testCourse.addCategory(testCategory2);
        assertEquals(2, testCategoryList.size());
        assertTrue(testCategoryList.contains(testCategory2));

        testCourse.addCategory(testCategory3);
        assertEquals(3, testCategoryList.size());
        assertTrue(testCategoryList.contains(testCategory3));
    }

    @Test
    void testAddDuplicateCategory() {
        assertEquals(0, testCategoryList.size());
        testCourse.addCategory(testCategory1);
        assertEquals(1, testCategoryList.size());
        assertTrue(testCategoryList.contains(testCategory1));

        testCourse.addCategory(testCategory1);
        assertEquals(1, testCategoryList.size());
        assertTrue(testCategoryList.contains(testCategory1));
    }

    @Test
    void testIsCompletedTrue() {
        testCourse.addCategory(testCategory1);
        testCourse.addCategory(testCategory2);
        testCourse.addCategory(testCategory4);

        testCourse.isCompleted();
        assertTrue(testCourse.getIsCompleted());
    }

    @Test
    void testIsCompletedFalse() {
        testCourse.addCategory(testCategory1);
        testCourse.addCategory(testCategory2);
        testCourse.addCategory(testCategory3);

        testCourse.isCompleted();
        assertFalse(testCourse.getIsCompleted());
    }

    @Test
    void testFindFinalWeight() {
        testCourse.addCategory(testCategory1);
        testCourse.addCategory(testCategory2);
        testCourse.addCategory(testCategory3);

        testCourse.isCompleted();
        assertFalse(testCourse.getIsCompleted());

        testCourse.findFinalWeight();
        assertEquals(55, testCourse.getFinalWeight());
    }

    @Test
    void testCalculateCurrentGrade() {
        double sumGrade = 0.0;
        testCourse.addCategory(testCategory1);
        sumGrade += testCategoryList.get(0).getWeightedMark();
        testCourse.addCategory(testCategory2);
        sumGrade += testCategoryList.get(1).getWeightedMark();
        testCourse.addCategory(testCategory3);
        sumGrade += testCategoryList.get(2).getWeightedMark();

        assertFalse(testCourse.getIsCompleted());
        assertEquals(15, testCategory1.getWeightedMark());
        assertEquals(22.5, testCategory2.getWeightedMark());
        assertFalse(testCategory3.getCategoryStatus());
        assertEquals(0, testCategory3.getWeightedMark());

        testCourse.calculateCurrentGrade();
        assertEquals(sumGrade, testCourse.getCurrentGrade());
    }

    @Test
    void testCalculateActualGrade() {
        double sumGrade = 0.0;
        testCourse.addCategory(testCategory1);
        sumGrade += testCategoryList.get(0).getWeightedMark();
        testCourse.addCategory(testCategory2);
        sumGrade += testCategoryList.get(1).getWeightedMark();
        testCourse.addCategory(testCategory4);
        sumGrade += testCategoryList.get(2).getWeightedMark();

        testCourse.isCompleted();
        assertTrue(testCourse.getIsCompleted());

        testCourse.calculateActualGrade();
        assertEquals(sumGrade, testCourse.getActualFinalGrade());
    }

    @Test
    void testCalculateMinFinalScore() {
        testCourse.addCategory(testCategory1);
        testCourse.addCategory(testCategory2);
        testCourse.addCategory(testCategory3);

        double desired = testCourse.getDesiredFinalGrade();
        double current = testCourse.getCurrentGrade();
        double weight = testCourse.getFinalWeight();
        double testScore = (desired - current * (100 - weight)) / weight;

        testCourse.calculateMinFinalScore();
        assertEquals(testScore, testCourse.getMinFinalScore());
    }

    @Test
    void testCalculateChangedMinFinalScore() {
        testCourse.addCategory(testCategory1);
        testCourse.addCategory(testCategory2);
        testCourse.addCategory(testCategory3);

        double desired = testCourse.setDesiredFinalGrade(72);
        double current = testCourse.getCurrentGrade();
        double weight = testCourse.getFinalWeight();
        double testScore = (desired - current * (100 - weight)) / weight;

        testCourse.calculateMinFinalScore();
        assertEquals(testScore, testCourse.getMinFinalScore());
    }
}
