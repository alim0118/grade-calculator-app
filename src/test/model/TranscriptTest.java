package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TranscriptTest {
    private Transcript testTranscript;
    private ArrayList<Course> testCourseList;

    private ArrayList<Category> testCategoryList1;
    private Course testCourse1;
    private Category testCategory1;
    private Category testCategory2;
    private Category testCategory3;

    private ArrayList<Category> testCategoryList2;
    private Course testCourse2;
    private Category testCategory4;
    private Category testCategory5;

    @BeforeEach
    void runBefore() {
        testCourseList = new ArrayList<>(); // add all courses
        testCategoryList1 = new ArrayList<>();
        testCategoryList2 = new ArrayList<>();

        testCategory1 = new Category("Homework", 10, 100, true);
        testCategory2 = new Category("Midterm", 40, 75, true);
        testCategory3 = new Category("Final Exam", 50, 56,true);
        //testCategoryList1.addCategory(testCategory1);
        //testCategoryList1.addCategory(testCategory2);
        //testCategoryList1.addCategory(testCategory3);
        testCourse1 = new Course("CPSC 210", 4, 89.5, testCategoryList1);

        testCategory4 = new Category("Midterm", 30, 100, true);
        testCategory5 = new Category("Final Exam", 60, 75, true);
        testCategoryList2.add(testCategory4);
        testCategoryList2.add(testCategory5);
        testCourse2 = new Course("MATH 200", 3, 77, testCategoryList2);


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
        testTranscript.addCourses(testCourse1);
        assertEquals(1, testCourseList.size());
        assertTrue(testCourseList.contains(testCourse1));
    }

    @Test
    void testAddMultipleCourses() {
        assertEquals(0, testCourseList.size());
        testTranscript.addCourses(testCourse1);
        assertEquals(1, testCourseList.size());
        assertTrue(testCourseList.contains(testCourse1));

        testTranscript.addCourses(testCourse2);
        assertEquals(2, testCourseList.size());
        assertTrue(testCourseList.contains(testCourse2));

    }

    @Test
    void testAddDuplicateCourses() {
        assertEquals(0, testCourseList.size());
        testTranscript.addCourses(testCourse1);
        assertEquals(1, testCourseList.size());
        assertTrue(testCourseList.contains(testCourse1));

        testTranscript.addCourses(testCourse1);
        assertEquals(1, testCourseList.size());
        assertTrue(testCourseList.contains(testCourse1));

    }

    @Test
    void testCalculateTotalCredits() {
        testTranscript.addCourses(testCourse1);
        testTranscript.calculateTotalCredits();
        assertEquals(4, testTranscript.getTotalCredits());
    }

    @Test
    void testMultipleCalculateTotalCredits() {
        testTranscript.addCourses(testCourse1);
        testTranscript.addCourses(testCourse2);
        testTranscript.calculateTotalCredits();
        assertEquals(7, testTranscript.getTotalCredits());
    }

    @Test
    void testDuplicateCalculateTotalCredits() {
        testTranscript.addCourses(testCourse1);
        testTranscript.addCourses(testCourse1);
        testTranscript.calculateTotalCredits();
        assertEquals(4, testTranscript.getTotalCredits());
    }

    @Test
    void testCalculateTotalWeightedGrades() {
        double testWeight = 0.0;
        testTranscript.addCourses(testCourse1);
        //testWeight += testCourse1.getActualFinalGrade();
        testTranscript.addCourses(testCourse2);
        //testWeight += testCourse2.getActualFinalGrade();
        testTranscript.calculateTotalWeightedGrades();
        testWeight = 143;

        testTranscript.calculateTotalWeightedGrades();
        assertEquals(testWeight, testTranscript.getTotalWeighted());

    }

    @Test
    void testCalculateAverage() {
        testTranscript.addCourses(testCourse1);
        testTranscript.addCourses(testCourse2);
        //double testWeight = testTranscript.calculateTotalWeightedGrades();
        double testWeight = 143;
        //int testCredits = testTranscript.getTotalCredits();
        int testCredits = 3;

        testTranscript.calculateAverage();
        assertEquals((testWeight / testCredits), testTranscript.getAverage());
    }



}