package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// taken from EventLog in:
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

// represents a lof of grade calculator events
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: constructs EventLog with list of Event
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // MODIFIES: this
    // EFFECTS: if instance of EventLog is null, creates and returns it;
    //          otherwise, returns instance of EventLog
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    // MODIFIES: this
    // EFFECTS: cleats the event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
