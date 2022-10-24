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
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads student record from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads student record from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public StudentRecord read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudentRecord(jsonObject);
    }

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
    // EFFECTS: parses course from JSON object and adds it to student record
    private void addCourse(StudentRecord sr, JSONObject jsonObject) {
        String name = jsonObject.getString("course name");
        int credits = jsonObject.getInt("credits");
        double desiredFinalGrade = jsonObject.getDouble("desired final grade");
        ArrayList<Category> categories = (ArrayList<Category>) jsonObject.get("categories");

        //double currentGrade = jsonObject.getDouble("current grade");
        //double actualFinalGrade = jsonObject.getDouble("final grade");
        boolean courseStatus = jsonObject.getBoolean("course status");

        Course course = new Course(name, credits, desiredFinalGrade, categories);
        sr.addCourse(course);

    }

//    // MODIFIES: sr
//    // EFFECTS: parses category from JSON object and adds it to student record
//    private void addCategory(StudentRecord sr, JSONObject jsonObject) {
//        String name = jsonObject.getString("category name");
//        double catWeight = jsonObject.getDouble("category weight");
//        double weightedMark = jsonObject.getDouble("weighted mark");
//        boolean catStatus = jsonObject.getBoolean("category status");
//
//        Category category = new Category(name, catWeight, weightedMark, catStatus);
//        sr.addCategory(course);
//
//    }


}
