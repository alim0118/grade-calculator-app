package persistence;


import model.Category;
import model.Course;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// inspiration taken from JsonTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents tester for checking course for JsonReader, JsonWriter
public class JsonTest {

    // EFFECTS: used for checking course
    protected void checkCourse(String name, int credits, double desired, ArrayList<Category> categories,
                               Course course) {
        assertEquals(name, course.getCourseName());
        assertEquals(credits, course.getCredits());
        assertEquals(desired, course.getDesiredFinalGrade());
        assertEquals(categories, course.getCategoryList());
    }
}
