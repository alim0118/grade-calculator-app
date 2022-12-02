package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// Unit tests for EventLog class
public class EventLogTest {
    private Event e1;
    private Event e2;

    @BeforeEach
    void runBefore() {
        e1 = new Event("CPSC 210 added to student record");
        e2 = new Event("Viewed all courses");

        EventLog eventLog = EventLog.getInstance();
        eventLog.logEvent(e1);
        eventLog.logEvent(e2);

    }

    @Test
    void testLogEvent() {
        List<Event> events = new ArrayList<>();

        EventLog eventLog = EventLog.getInstance();
        for (Event e : eventLog) {
            events.add(e);
        }

        assertTrue(events.contains(e1));
        assertTrue(events.contains(e2));

    }

    @Test
    void testClear() {
        EventLog eventLog = EventLog.getInstance();
        eventLog.clear();
        Iterator<Event> itr = eventLog.iterator();
        assertTrue(itr.hasNext());
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }
}
