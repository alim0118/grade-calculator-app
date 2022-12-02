package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for Event class
public class EventTest {
    private Event e;

    @BeforeEach
    void runBefore() {
        e = new Event("Viewed all courses");
    }

    @Test
    void testGetDescription() {
        assertEquals("Viewed all courses", e.getDescription());
    }
}
