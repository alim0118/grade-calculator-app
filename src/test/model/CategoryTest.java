package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        double markTest = testJson.getDouble("weighted mark");
        boolean statusTest = testJson.getBoolean("category status");

        assertEquals("Homework", nameTest);
        assertEquals(5, weightTest);
        assertEquals((5 * 87.2) / 100, markTest);
        assertEquals(true, statusTest);
    }

}
