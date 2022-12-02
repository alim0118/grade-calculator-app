package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.CategoryPanel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Unit tests for StudentRecord class
public class StudentRecordTest {
    private int id;
    private StudentRecord testStudentRecord;
    private ArrayList<Course> testCourseList;
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
        testCourseList = new ArrayList<>();
        testCategoryList1 = new ArrayList<>();
        testCategoryList2 = new ArrayList<>();

        testCategory1 = new Category("Project", 40, 100, true);
        testCategory2 = new Category("Final", 60, 75, true);

        testCourse1 = new Course("CPSC 210", 4, 89.5, testCategoryList1);


        testCategory3 = new Category("Homework", 30, 0, true);
        testCategory4 = new Category("Midterm", 70, 0, false);

        testCourse2 = new Course("MATH 200", 3, 77, testCategoryList2);

    }

    @Test
    void testConstructor() {
        assertEquals(1, testStudentRecord.getId());
        assertEquals(0, testStudentRecord.numCourses());
        assertTrue(testStudentRecord.getCourseList().isEmpty());
    }

    @Test
    void testAddCourse() {
        assertEquals(0, testStudentRecord.numCourses());
        testCourse1.addCategory(testCategory1);
        testCourse1.addCategory(testCategory2);
        testStudentRecord.addCourse(testCourse1);
        assertEquals(1, testStudentRecord.numCourses());
        assertTrue(testStudentRecord.getCourseList().contains(testCourse1));

    }

    @Test
    void testAddMultipleCourses() {
        assertEquals(0, testStudentRecord.numCourses());

        testCourse1.addCategory(testCategory1);
        testCourse1.addCategory(testCategory2);
        testStudentRecord.addCourse(testCourse1);

        testCourse2.addCategory(testCategory3);
        testCourse2.addCategory(testCategory4);
        testStudentRecord.addCourse(testCourse2);


        assertEquals(2, testStudentRecord.numCourses());
        assertTrue(testStudentRecord.getCourseList().contains(testCourse1));
        assertTrue(testStudentRecord.getCourseList().contains(testCourse2));
    }

    @Test
    void testAddDuplicateCourse() {
        assertEquals(0, testStudentRecord.numCourses());
        testCourse1.addCategory(testCategory1);
        testCourse1.addCategory(testCategory2);
        testStudentRecord.addCourse(testCourse1);

        testStudentRecord.addCourse(testCourse1);

        assertEquals(1, testStudentRecord.numCourses());
        assertTrue(testStudentRecord.getCourseList().contains(testCourse1));
    }

    @Test
    void testToJson() {
        JSONObject testJson = testStudentRecord.toJson();

        int userId = testJson.getInt("id");
        JSONArray jsonCourses = testJson.getJSONArray("courses");

        List<Object> temp = jsonCourses.toList();
        ArrayList<Course> coursesTest = new ArrayList<>();
        for (Object o : temp) {
            coursesTest.add((Course) o);
        }

        assertEquals(1, userId);
        assertEquals(testStudentRecord.getCourseList(), coursesTest);

    }

    @Test
    void testGetCompleteCourseList() {
        List<Course> temp;
        assertEquals(0, testStudentRecord.numCourses());

        testCourse1.addCategory(testCategory1);
        testCourse1.addCategory(testCategory2);
        testStudentRecord.addCourse(testCourse1);

        testCourse2.addCategory(testCategory3);
        testCourse2.addCategory(testCategory4);
        testStudentRecord.addCourse(testCourse2);

        temp = testStudentRecord.getCompleteCourseList();
        assertEquals(1, temp.size());
        assertTrue(temp.contains(testCourse1));
        assertTrue(!temp.contains(testCourse2));

    }

    @Test
    void testGetIncompleteCourseList() {
        List<Course> temp;
        assertEquals(0, testStudentRecord.numCourses());

        testCourse1.addCategory(testCategory1);
        testCourse1.addCategory(testCategory2);
        testStudentRecord.addCourse(testCourse1);

        testCourse2.addCategory(testCategory3);
        testCourse2.addCategory(testCategory4);
        testStudentRecord.addCourse(testCourse2);

        temp = testStudentRecord.getIncompleteCourseList();
        assertEquals(1, temp.size());
        assertTrue(temp.contains(testCourse2));
        assertTrue(!temp.contains(testCourse1));

    }


}
