package persistence;

import model.Category;
import model.Course;
import model.StudentRecord;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads student record from JSON data stored in file
public class JsonReader {
    private String source;

    // method was taken from JsonReader in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // method was taken from JsonReader in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads student record from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public StudentRecord read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudentRecord(jsonObject);
    }

    // method was taken from JsonReader in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses student record from JSON object and returns it
    private StudentRecord parseStudentRecord(JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        StudentRecord sr = new StudentRecord(id);
        addCourses(sr, jsonObject);
        return sr;
    }

    // MODIFIES: sr
    // EFFECTS: parses courses from JSON object and adds them to student record
    private void addCourses(StudentRecord sr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(sr, nextCourse);
        }
    }

    // MODIFIES: sr
    // EFFECTS: parses course from JSON object and adds them to student record
    private void addCourse(StudentRecord sr, JSONObject jsonObject) {
        String name = jsonObject.getString("course name");
        int credits = jsonObject.getInt("credits");
        double desired = jsonObject.getDouble("desired final grade");
        JSONArray jsonCategories = jsonObject.getJSONArray("categories");


        Course course = new Course(name, credits, desired);
        categoryToJson(jsonCategories, course);
        sr.addCourse(course);

    }

    // MODIFIES: course
    // EFFECTS: parses category from JSON object
    private void categoryToJson(JSONArray jsonCategories, Course course) {
        for (Object json : jsonCategories) {
            JSONObject nextCategory = (JSONObject) json;
            addCategory(nextCategory, course);
        }
    }

    // MODIFIES: course
    // EFFECTS: parses category from JSON object and adds them to student record
    private void addCategory(JSONObject jsonObject, Course course) {
        String name = jsonObject.getString("category name");
        double catWeight = jsonObject.getDouble("category weight");
        double catMark = jsonObject.getDouble("category mark");
        boolean catStatus = jsonObject.getBoolean("category status");

        Category category = new Category(name, catWeight, catMark, catStatus);
        course.addCategory(category);
    }


}
