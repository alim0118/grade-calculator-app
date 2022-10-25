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

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            StudentRecord sr = new StudentRecord(1);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyStudentRecord() {
        try {
            StudentRecord sr = new StudentRecord(1);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStudentRecord.json");
            writer.open();
            writer.write(sr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStudentRecord.json");
            sr = reader.read();
            assertEquals(1, sr.getId());
            assertEquals(0, sr.getCourseList().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralStudentRecord() {
        try {
            StudentRecord sr = new StudentRecord(1);
            ArrayList<Category> testCategoryList = new ArrayList<>();
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
            sr.addCourse(new Course("CPSC 210", 4, 89.5, testCategoryList));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStudentRecord.json");
            writer.open();
            writer.write(sr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralStudentRecord.json");
            sr = reader.read();
            assertEquals(1, sr.getId());
            List<Course> courses = sr.getCourseList();
            assertEquals(1, courses.size());

            checkCourse("CPSC 210", 4, 89.5, testCategoryList, courses.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
