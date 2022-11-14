package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

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

    // keep
    @Test
    void testEquals() {
        assertTrue(testCategory.equals(testCategory));
    }

    // keep
    @Test
    void testEqualsObject() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertTrue(testCategory.equals(testCategory2));
    }

    @Test
    void testEqualsOther() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertTrue(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testEqualsFailsDifferentName() {
        Category testCategory2 = new Category("Midterm", 5, 87.2, true);
        assertFalse(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testEqualsFailsDifferentWeight() {
        Category testCategory2 = new Category("Homework", 10, 87.2, true);
        assertFalse(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testEqualsFailsDifferentMark() {
        Category testCategory2 = new Category("Homework", 5, 60.1, true);
        assertFalse(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testEqualsFailsDifferentStatus() {
        Category testCategory2 = new Category("Homework", 5, 87.2, false);
        assertFalse(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testEqualsFailsDifferentWeightedMarkMark() {
        Category testCategory2 = new Category("Homework", 5, 40, true);
        assertFalse(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testEqualsFailsDifferentWeightedMarkWeight() {
        Category testCategory2 = new Category("Homework", 2, 87.2, true);
        assertFalse(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testEqualsFailsDifferentWeightedMarkMarkStatus() {
        Category testCategory2 = new Category("Homework", 5, 40, false);
        assertFalse(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testEqualsFailsDifferentWeightedMarkWeightStatus() {
        Category testCategory2 = new Category("Homework", 2, 87.2, false);
        assertFalse(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testEqualsFailsAllDifferent() {
        Category testCategory2 = new Category("Midterm", 9, 52, false);
        assertFalse(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    // keep
    @Test
    void testEqualsFailsNull() {
        assertFalse(testCategory.equals(null));
    }

    // keep
    @Test
    void testEqualsWrongtype() {
        String wrongType = "Homework";
        assertFalse(testCategory.equals(wrongType));
    }

    // keep
    @Test
    void testEqualsWeight() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertTrue(testCategory2.getWeight() == testCategory.getWeight());

    }

    // keep
    @Test
    void testEqualsMark() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertTrue(testCategory2.getMark() == testCategory.getMark());

    }

    // keep
    @Test
    void testEqualsWeightedMark() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertTrue(testCategory2.getWeightedMark() == testCategory.getWeightedMark());
    }

    // keep
    @Test
    void testEqualsStatus() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertTrue(testCategory.getCategoryStatus() == testCategory2.getCategoryStatus());

    }

    // keep
    @Test
    void testEqualsName() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertTrue(testCategory.getName().equals(testCategory2.getName()));

    }

    // keep
    @Test
    void testHashCode() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertTrue(testCategory.hashCode() == testCategory2.hashCode());
    }


}
