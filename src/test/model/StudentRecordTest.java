package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentRecordTest {
    private int id;
    private StudentRecord testStudentRecord;
    private Course testCourse1;
    private Category testCategory1;
    private Category testCategory2;
    private ArrayList<Category> testCategoryList1;
    private Course testCourse2;
    private Category testCategory3;
    private Category testCategory4;
    private ArrayList<Category> testCategoryList2;


    @BeforeEach
    void runBefore() {
        id = 1;
        testStudentRecord = new StudentRecord(id);
        testCategoryList1 = new ArrayList<>();
        testCategoryList2 = new ArrayList<>();

        testCategory1 = new Category("Project", 40, 100, true);
        testCategory2 = new Category("Final", 60, 75, true);
        testCategoryList1.add(testCategory1);
        testCategoryList1.add(testCategory2);
        testCourse1 = new Course("CPSC 210", 4, 89.5, testCategoryList1);

        testCategory3 = new Category("Homework", 30, 0, true);
        testCategory4 = new Category("Midterm", 70, 0, false);
        testCategoryList2.add(testCategory3);
        testCategoryList2.add(testCategory4);
        testCourse2 = new Course("MATH 200", 3, 77, testCategoryList2);

        //testStudentRecord.addCourse(testCourse1);
        //testStudentRecord.addCourse(testCourse2);

    }

    @Test
    void testConstructor() {
        assertEquals(1, testStudentRecord.getId());
        assertEquals(0, testStudentRecord.numCourses());
        assertTrue(testStudentRecord.getCourseList().isEmpty());
    }
}
