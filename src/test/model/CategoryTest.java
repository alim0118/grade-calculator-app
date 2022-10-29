package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for Category class
public class CategoryTest {
    private Category testCategory;

    @BeforeEach
    void runBefore() {
        testCategory = new Category("Homework", 5, 87.2, true);
    }

    @Test
    void testConstructor() {
        assertEquals("Homework", testCategory.getName());
        assertEquals(5, testCategory.getWeight());
        assertEquals(87.2, testCategory.getMark());
        assertEquals((5 * 87.2) / 100, testCategory.getWeightedMark());
        assertTrue(testCategory.getCategoryStatus());
    }

    @Test
    void testSetMark() {
        double changedMark = 55.6;
        testCategory.setMark(changedMark);
        assertEquals(changedMark, testCategory.getMark());
        testCategory.setWeightedMark(changedMark);
        assertEquals((5 * changedMark) / 100, testCategory.getWeightedMark());
    }

    @Test
    void testToJson() {
        JSONObject testJson = testCategory.toJson();
        String nameTest = testJson.getString("category name");
        double weightTest = testJson.getDouble("category weight");
        double markTest = testJson.getDouble("category mark");
        boolean statusTest = testJson.getBoolean("category status");

        assertEquals("Homework", nameTest);
        assertEquals(5, weightTest);
        assertEquals(87.2, markTest);
        assertEquals(true, statusTest);
    }

    @Test
    void testEquals() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertTrue(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testEqualsFailsDifferent() {
        Category testCategory2 = new Category("Midterm", 5, 87.2, true);
        assertFalse(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testEqualsFailsNull() {
        Category testCategory2 = null;
        assertFalse(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testHashCode() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertTrue(testCategory.hashCode() == testCategory2.hashCode());
    }


}
