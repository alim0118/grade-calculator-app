package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(5 * 87.2, testCategory.getWeightedMark());
        assertEquals(true, testCategory.getCategoryStatus());
    }
}
