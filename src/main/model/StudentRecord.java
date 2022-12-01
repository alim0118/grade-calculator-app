package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// represents a student record having an id and collection of courses
public class StudentRecord implements Writable {
    private int id;
    private List<Course> courseList;

    // EFFECTS: creates a student record with id and an empty list of courses
    public StudentRecord(int id) {
        this.id = id;
        courseList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds course to course list (with no duplicates)
    public void addCourse(Course course) {
        if (!courseList.contains(course)) {

            // log course added to student record
            EventLog.getInstance().logEvent(new Event(course.getCourseName() + " added to student record"));
            courseList.add(course);
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("courses", coursesToJson());
        return json;
    }

    // EFFECTS: returns courses in this student record as a JSON array
    public JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : courseList) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

    //getters
    public int getId() {
        return id;
    }

    // EFFECTS: returns number of courses in course list
    public int numCourses() {
        return courseList.size();
    }

    // method was taken from WorkRoom in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns an unmodifiable list of courses in this student record
    public List<Course> getCourseList() {
        return Collections.unmodifiableList(courseList);
    }

    // have a get complete course list?
    public List<Course> getCompleteCourseList() {
        List<Course> tempCourses = new ArrayList<>();
        for (Course c : Collections.unmodifiableList(courseList)) {
            c.checkIsCompleted();
            if (c.getIsCompleted()) {
                tempCourses.add(c);
            }
        }
        return tempCourses;
    }

    // have a get incomplete course list?
    public List<Course> getIncompleteCourseList() {
        List<Course> tempCourses = new ArrayList<>();
        for (Course c : Collections.unmodifiableList(courseList)) {
            c.checkIsCompleted();
            if (!c.getIsCompleted()) {
                tempCourses.add(c);
            }
        }
        return tempCourses;
    }

}
