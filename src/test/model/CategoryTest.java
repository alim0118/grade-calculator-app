package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    private Category testCategory;

    @BeforeEach
    void runBefore() {
        testCategory = new Category("Lecture Ticket", 5, 87.2);
    }

    @Test
    void testConstructor() {
        assertEquals("Lecture Ticket", testCategory.getName());
        assertEquals(5, testCategory.getWeight());
        assertEquals(87.2, testCategory.getMark());
        assertEquals(5 * 87.2, testCategory.getWeightedMark());
    }
}
