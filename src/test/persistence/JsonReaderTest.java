package persistence;

import model.Category;
import model.Course;
import model.StudentRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            StudentRecord sr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyStudentRecord() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStudentRecord.json");
        try {
            StudentRecord sr = reader.read();
            assertEquals(1, sr.getId());
            assertEquals(0, sr.getCourseList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralStudentRecord() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStudentRecord.json");
        try {
            StudentRecord sr = reader.read();
            assertEquals(1, sr.getId());
            List<Course> courses = sr.getCourseList();
            assertEquals(1, courses.size());

            ArrayList<Category>  testCategoryList = new ArrayList<>();
            Category testCategory1 = new Category("Homework", 10, 100,
                    true);
            Category testCategory2 = new Category("Midterm", 30, 75,
                    true);
            Category testCategory3 = new Category("Final Exam", 60, 0,
                    true);
            Course testCourse = new Course("CPSC 210", 4, 89.5, testCategoryList);

            testCourse.addCategory(testCategory1);
            testCourse.addCategory(testCategory2);
            testCourse.addCategory(testCategory3);

            checkCourse("CPSC 210",4, 89.5, testCategoryList, courses.get(0)) ;

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
