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

    @Test
    void testEquals() {
        assertTrue(testCategory.equals(testCategory));
    }

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
    void testEqualsFailsAllDifferent() {
        Category testCategory2 = new Category("Midterm", 9, 52, false);
        assertNotNull(testCategory.equals(testCategory2));
        assertFalse(testCategory.equals(testCategory2) && testCategory2.equals(testCategory));
    }

    @Test
    void testEqualsFailsNull() {
        assertFalse(testCategory.equals(null));
    }

    @Test
    void testEqualsWrongtype() {
        String wrongType = "Final";
        assertFalse(testCategory.equals(wrongType));
    }

    @Test
    void testEqualsTypeName() {
        String type = "Homework";
        assertFalse(testCategory.equals(type));
    }

    @Test
    void testEqualstypeWeight() {
        double type = 5;
        assertFalse(testCategory.equals(type));

        assertTrue(testCategory.getWeightedMark() == ((5 * 87.2) / 100));
    }

    @Test
    void testEqualsTypeMark() {
        double type = 87.2;
        assertFalse(testCategory.equals(type));
    }

    @Test
    void testEqualsTypeStatus() {
        boolean type = true;
        assertFalse(testCategory.equals(type));
    }

    @Test
    void testEqualsStatus() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertTrue(testCategory.getCategoryStatus() == true);
        assertEquals(testCategory.getCategoryStatus(), testCategory2.getCategoryStatus());
    }

    @Test
    void testEqualsPassNullCheck() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertNotNull(testCategory.equals(testCategory2));
    }

    @Test
    void testEqualsFailsNullCheck() {
        Category testCategory2 = null;
        assertFalse(testCategory.equals(testCategory2));
    }

    @Test
    void testEqualsReturns() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        boolean checkWeight = (testCategory.getWeight() == testCategory2.getWeight());
        assertTrue(checkWeight);
        boolean checkMark = (testCategory.getMark() == testCategory2.getMark());
        assertTrue(checkMark);
        boolean checkWeighted = (testCategory.getWeightedMark() == testCategory2.getWeightedMark());
        assertTrue(checkWeighted);
        boolean checkName = (testCategory.getName().equals(testCategory2.getName()));
        assertTrue(checkName);

        assertEquals(checkWeight && checkMark && checkWeighted && checkName,
                testCategory.equals(testCategory2));

        assertEquals(0, Double.compare(testCategory2.getWeight(), testCategory.getWeight()));
        assertEquals(0, Double.compare(testCategory2.getMark(), testCategory.getMark()));
        assertEquals(testCategory2.getMark(), testCategory.getMark());
        assertEquals(0, Double.compare(testCategory2.getWeightedMark(), testCategory.getWeightedMark()));
        assertEquals(testCategory2.getName(), testCategory.getName());

    }

    @Test
    void testEqualsNewInstance() {
        assertTrue(testCategory.equals(
                new Category("Homework", 5, 87.2, true)));
    }

    @Test
    void testEqualsReturnTrue() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);

        boolean bool1 = (Double.compare(testCategory2.getWeight(), testCategory.getWeight()) == 0);
        boolean bool2 = (Double.compare(testCategory2.getMark(), testCategory.getMark()) == 0);
        boolean bool3 = (Double.compare(testCategory2.getWeightedMark(), testCategory.getWeightedMark()) == 0);
        boolean bool4 = (testCategory.getCategoryStatus() == testCategory2.getCategoryStatus());
        boolean bool5 = (Objects.equals(testCategory.getName(), testCategory2.getName()));


        assertEquals(true, bool1 && bool2 && bool3 && bool4 && bool5);
    }

    @Test
    void testEqualsReturnFalse() {
        Category testCategory2 = new Category("Homework", 5, 87.2, false);

        boolean bool1 = (Double.compare(testCategory2.getWeight(), testCategory.getWeight()) == 0);
        boolean bool2 = (Double.compare(testCategory2.getMark(), testCategory.getMark()) == 0);
        boolean bool3 = (Double.compare(testCategory2.getWeightedMark(), testCategory.getWeightedMark()) == 0);
        boolean bool4 = (testCategory.getCategoryStatus() == testCategory2.getCategoryStatus());
        boolean bool5 = (Objects.equals(testCategory.getName(), testCategory2.getName()));


        assertEquals(false, bool1 && bool2 && bool3 && bool4 && bool5);
    }

    @Test
    void testEqualsWeightedMark() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertEquals(testCategory.getWeightedMark(), testCategory2.getWeightedMark());
        assertTrue(testCategory.getWeightedMark() == testCategory2.getWeightedMark());
    }


    @Test
    void testHashCode() {
        Category testCategory2 = new Category("Homework", 5, 87.2, true);
        assertTrue(testCategory.hashCode() == testCategory2.hashCode());
    }


}
