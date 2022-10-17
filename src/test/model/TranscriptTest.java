package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TranscriptTest {
    private Transcript testTranscript;

    private ArrayList<Course> testCourseList = new ArrayList<>();
    private Course testCourse1;
    private Course testCourse2;

    @BeforeEach
    void runBefore() {
        ArrayList<Category> testCategoryList1 = new ArrayList<>();
        ArrayList<Category> testCategoryList2 = new ArrayList<>();

        Category testCategory1 = new Category("Homework", 10, 100, true);
        Category testCategory2 = new Category("Midterm", 40, 75, true);
        Category testCategory3 = new Category("Final Exam", 50, 56, true);

        testCourse1 = new Course("CPSC 210", 4, 89.5, testCategoryList1);
        testCourse1.addCategory(testCategory1);
        testCourse1.addCategory(testCategory2);
        testCourse1.addCategory(testCategory3);
        testCourse1.checkIsCompleted();
        testCourse1.findFinalWeight();
        //testCourse1.getFinalWeight();

        Category testCategory4 = new Category("Midterm", 30, 100, true);
        Category testCategory5 = new Category("Final Exam", 60, 75, true);

        testCourse2 = new Course("MATH 200", 3, 76, testCategoryList2);
        testCourse2.addCategory(testCategory4);
        testCourse2.addCategory(testCategory5);
        testCourse2.checkIsCompleted();
        testCourse2.findFinalWeight();

        testTranscript = new Transcript(1, testCourseList);
    }

    @Test
    void testConstructor() {
        assertEquals(1, testTranscript.getId());
        assertEquals(testCourseList, testTranscript.getCourseList());
    }

    @Test
    void testAddCourses() {
        assertEquals(0, testCourseList.size());
        testCourseList.add(testCourse1);
        testTranscript.addCourses(testCourse1);
        assertEquals(1, testCourseList.size());
        assertTrue(testCourseList.contains(testCourse1));
    }

    @Test
    void testAddMultipleCourses() {
        assertEquals(0, testCourseList.size());
        testCourseList.add(testCourse1);
        testTranscript.addCourses(testCourse1);
        assertEquals(1, testCourseList.size());
        assertTrue(testCourseList.contains(testCourse1));

        testCourseList.add(testCourse2);
        testTranscript.addCourses(testCourse2);
        assertEquals(2, testCourseList.size());
        assertTrue(testCourseList.contains(testCourse2));

    }

    @Test
    void testAddDuplicateCourses() {
        assertEquals(0, testCourseList.size());
        testCourseList.add(testCourse1);
        testTranscript.addCourses(testCourse1);
        assertEquals(1, testCourseList.size());
        assertTrue(testCourseList.contains(testCourse1));

        testTranscript.addCourses(testCourse1);
        assertEquals(1, testCourseList.size());
        assertTrue(testCourseList.contains(testCourse1));

    }

    @Test
    void testCalculateTotalCredits() {
        testCourseList.add(testCourse1);
        testTranscript.addCourses(testCourse1);
        testTranscript.calculateTotalCredits();
        assertEquals(4, testTranscript.getTotalCredits());
    }

    @Test
    void testMultipleCalculateTotalCredits() {
        testCourseList.add(testCourse1);
        testCourseList.add(testCourse2);
        testTranscript.addCourses(testCourse1);
        testTranscript.addCourses(testCourse2);
        testTranscript.calculateTotalCredits();
        assertEquals(7, testTranscript.getTotalCredits());
    }

    @Test
    void testDuplicateCalculateTotalCredits() {
        testCourseList.add(testCourse1);
        testTranscript.addCourses(testCourse1);
        testTranscript.addCourses(testCourse1);
        testTranscript.calculateTotalCredits();
        assertEquals(4, testTranscript.getTotalCredits());
    }

    @Test
    void testCalculateTotalWeightedGrades() {
        double testWeight = 0.0;
        testCourseList.add(testCourse1);
        testCourseList.add(testCourse2);

        testCourse1.calculateActualGrade();
        testWeight += testCourse1.getActualFinalGrade();
        testCourse2.calculateActualGrade();
        testWeight += testCourse2.getActualFinalGrade();

        testTranscript.calculateTotalWeightedGrades();
        assertEquals(testWeight, testTranscript.getTotalWeighted());
    }

    @Test
    void testCalculateAverage() {
        double testWeights = 0.0;
        int testCredits = 0;
        testCourseList.add(testCourse1);
        testCourseList.add(testCourse2);

        testCourse1.calculateActualGrade();
        testCourse2.calculateActualGrade();
        testWeights += testCourse1.getActualFinalGrade();
        testWeights += testCourse2.getActualFinalGrade();

        testCredits += testCourse1.getCredits();
        testCredits += testCourse2.getCredits();

        testTranscript.calculateAverage();
        assertEquals((double) Math.round((testWeights / (double) testCredits) * 100) / 100,
                testTranscript.getAverage());
    }
}