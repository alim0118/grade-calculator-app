package persistence;


import model.Category;
import model.Course;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkCourse(String name, int credits, double desired, ArrayList<Category> categories,
                               Course course) {
        assertEquals(name, course.getCourseName());
        assertEquals(credits, course.getCredits());
        assertEquals(desired, course.getDesiredFinalGrade());
        assertEquals(categories, course.getCategoryList());
        //override equals method
    }
}
