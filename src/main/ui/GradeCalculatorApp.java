package ui;

import model.Category;
import model.Course;
import model.StudentRecord;
import persistence.JsonReader;
import persistence.JsonWriter;
//import model.Transcript;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// NOTE: any calls related to transcript will not be used in phase 1, therefore commented out

// Grade calculator application
public class GradeCalculatorApp {
    private static int id = 1; // id number of user
    private static final String JSON_STORE = "./data/studentRecord.json";
    Scanner scan; // scanner
    private ArrayList<Course> allCourses = new ArrayList<>(); // all courses
    private Course curCourse; // current course getting input on
    private StudentRecord studentRecord;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // NOTE: not used in phase 1 because it requires the transcript class
    //private Transcript curTranscript;  // transcript of student

    // EFFECTS: runs the grade calculator application
    public GradeCalculatorApp() throws FileNotFoundException {
        scan = new Scanner(System.in);
        studentRecord = new StudentRecord(1);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGradeCalculator();
    }

    // drew inspiration from TellerApp
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGradeCalculator() {
        boolean cont = true;
        String cmd;
        String userType = init();

        if (userType.equals("1.")) {
            String sel;
            System.out.println("Select 1. to load student record from file");
            sel = scan.nextLine();
            if (userType.equals("1.")) {
                loadWorkRoom();
            }
            System.out.println("Hello, " + studentRecord.getId());
            while (cont) {
                cmd = displayReturn();
                if (cmd.equals("0.")) {
                    cont = false;
                } else {
                    userCommandReturning(cmd);
                }
            }
        } else {
            runNewGradeCalculator(cont);
        }
        System.out.println("Goodbye, thank you for using the grade calculator!");
    }

    // drew inspiration from TellerApp
    // REQUIRES: command to not be null
    // MODIFIES: this
    // EFFECTS: processes user input when user is new
    private void runNewGradeCalculator(boolean continuing) {
        boolean cont = continuing;
        String cmd;

        System.out.println("Hello, your user will be " + id);
        while (cont) {
            cmd = displayNew();
            if (cmd.equals("0.")) {
                cont = false;
            } else {
                userCommandNew(cmd);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner, calls on user login and returns user command
    private String init() {
        scan = new Scanner(System.in);
        userLogin();
        return scan.nextLine();
    }

    // EFFECTS: calls on method where returning user chooses from display options, chosen option is returned
    private String displayReturn() {
        displayMenuReturning();
        return scan.nextLine();
    }

    // EFFECTS: calls on method where new user chooses from display options, chosen option is returned
    private String displayNew() {
        displayMenuNew();
        return scan.nextLine();
    }

    // REQUIRES: name to be non-zero length; categoryWeight > 0; 100 >= categoryMark >= 0
    // EFFECTS: creates a category with name, weight, mark, and status
    private Category createCategory(String name, double weight, double mark, boolean status) {
        Category newCategory = new Category(name, weight, mark, status);
        return newCategory;
    }

    // REQUIRES: name to be non-zero length, credits >=1, 100 >= desired >= 0, categories to not be empty
    // MODIFIES: this
    // EFFECTS: creates a course with name, credits, desired grade, and corresponding categories
    private Course createCourse(String name, int credits, double desired, ArrayList<Category> categories) {
        curCourse = new Course(name, credits, desired, categories);
        return curCourse;
    }

    /*
    // NOTE: not used in phase 1 because it requires the transcript class

    // REQUIRES: id >= 1, courses to not be empty
    // MODIFIES: this
    // EFFECTS: creates a transcript with id and corresponding courses
    private void createTranscript(int id, ArrayList<Course> courses) {
        curTranscript = new Transcript(id, courses);
    }
    */

    // REQUIRES: id >= 1
    // MODIFIES: this
    // EFFECTS: creates a student record with id
    private void createStudentRecord(int id, ArrayList<Course> courses) {
        studentRecord = new StudentRecord(id);

        for (Course c : courses) {
            studentRecord.addCourse(c);
        }


    }

    // EFFECTS: displays login option for user
    private void userLogin() {
        System.out.println("\nWelcome! Please select from:");
        System.out.println("\t1. -> returning user");
        System.out.println("\t2. -> new user");
    }

    // EFFECTS: displays menu of options for returning user
    private void displayMenuReturning() {
        System.out.println("\nSelect from: ");
        System.out.println("\t1. -> get overall average");
        System.out.println("\t2. -> get overall average as a letter grade");
        System.out.println("\t3. -> calculate what i need on the final exam");
        System.out.println("\t4. -> view all courses");
        System.out.println("\t5. -> view course outline & rubric view all courses");
        System.out.println("\t0. -> quit");
    }

    // EFFECTS: displays menu of options for new user
    // change commands
    private void displayMenuNew() {
        System.out.println("\nSelect from: ");
        System.out.println("\t1. -> add course");
        System.out.println("\t2. -> edit course information");
        System.out.println("\t3. -> view course outline & rubric");
        System.out.println("\t4. -> calculate what i need on the final exam");
        System.out.println("\t5. -> view all courses");
        System.out.println("\t6. -> save student record to file");
        System.out.println("\t0. -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for returning user
    private void userCommandReturning(String command) {
        if (command.equals("1.")) {
            // NOTE: not used in phase 2 because not added to user story yet

            //doCalculateOverallAverage();

        } else if (command.equals("2.")) {
            // NOTE: not used in phase 2 because not added to user story yet

            //doConvertToLetterGrade();

        } else if (command.equals("3.")) {
            System.out.println("\nWhich course would you like to check for?");
            String choice = scan.nextLine();
            System.out.println("\nWhat is your desired final grade for " + choice + " ?");
            double desired = scan.nextDouble();
            scan.nextLine();
            doCalculateMinFinalScore(choice, desired);

        } else if (command.equals("4.")) {
            doViewAllCourses();

        } else if (command.equals("5.")) {
            doViewCourse();

        } else {
            System.out.println("Invalid selection. Please select again1");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command for new user
    private void userCommandNew(String command) {
        if (command.equals("1.")) {
            System.out.println("How many categories does this course have?");
            int categoryNum = scan.nextInt();
            scan.nextLine();
            doCreateCategory(categoryNum);

        } else if (command.equals("2.")) {
            doEditCourse();

        } else if (command.equals("3.")) {
            doViewCourse();

        } else if (command.equals("4.")) {
            System.out.println("\nWhich course would you like to check for?");
            String choice = scan.nextLine();
            System.out.println("\nWhat is your desired final grade for " + choice + " ?");
            double desired = scan.nextDouble();
            scan.nextLine();
            doCalculateMinFinalScore(choice, desired);

        } else if (command.equals("5.")) {
            doViewAllCourses();

        } else if (command.equals("6.")) {
            saveWorkRoom();
        } else {
            System.out.println("Invalid selection. Please select again2");
        }
    }

    /*
    // NOTE: not used in phase 2 because not added to user story yet
    // EFFECTS: prints overall average
    private void doCalculateOverallAverage() {
        System.out.println("Overall average: " + curTranscript.getAverage());
    }
     */


    /*
    // NOTE: not used in phase 2 because not added to user story yet

    // EFFECTS: prints overall average as letter grade
    private void doConvertToLetterGrade() {
        System.out.println("Overall average as letter grade: " + curTranscript.getLetterGrade());
    }
     */


    // REQUIRES: courseChoice to be non-zero length and included in allCourses, 100 <= desiredGrade <= 0
    // MODIFIES: this
    // EFFECTS: calculates minimum score needed on final to get desired grade for course
    private void doCalculateMinFinalScore(String courseChoice, double desiredGrade) {
        double min;
        for (int i = 0; i <= allCourses.size() - 1; i++) {
            if (allCourses.get(i).getCourseName().equals(courseChoice)) {
                allCourses.get(i).setDesiredFinalGrade(desiredGrade);
                allCourses.get(i).calculateMinFinalScore();
                min = allCourses.get(i).getMinFinalScore();
                System.out.println("You need to score a minimum of " + min + "% on your final exam to get "
                        + desiredGrade + "% as your final grade of course " + courseChoice);
            }
        }
    }

    // EFFECTS: prints all courses
    private void doViewAllCourses() {
        printAllCourses();
    }

    // MODIFIES: this
    // EFFECTS: prints chosen course information
    private void doViewCourse() {
        System.out.println("\nWhich course would you like to view?");
        String choice = scan.nextLine();
        for (int i = 0; i <= allCourses.size() - 1; i++) {
            if (allCourses.get(i).getCourseName().equals(choice)) {
                printCourseOutline(allCourses.get(i));
            }
        }
    }

    // REQUIRES: catNum >= 1
    // MODIFIES: this
    // EFFECTS: conducts creating a category
    private void doCreateCategory(int catNum) {
        ArrayList<Category> allCategories = new ArrayList<>();
        while (catNum >= 1) {
            System.out.println("\nEnter the category name:");
            String name = scan.nextLine();
            System.out.println("\nEnter the weight of " + name);
            double weight = scan.nextDouble();
            scan.nextLine();
            System.out.println("\nEnter the status of " + name + ". True for completed, False for incomplete");
            boolean status = scan.nextBoolean();
            scan.nextLine();
            double mark;

            if (status) {
                System.out.println("\nEnter the mark of " + name);
                mark = scan.nextDouble();
                scan.nextLine();

            } else {
                mark = 0;
            }

            allCategories.add(createCategory(name, weight, mark, status));
            catNum--;
        }

        doCreateCourse(allCategories);

        createStudentRecord(id, allCourses);
    }

    // MODIFIES: this
    // EFFECTS: conducts creating a course
    private void doCreateCourse(ArrayList<Category> categories) {
        System.out.println("\nEnter the course name:");
        String name = scan.nextLine();
        System.out.println("\nEnter the number of credits for " + name);
        int credits = scan.nextInt();
        scan.nextLine();

        System.out.println("\nEnter the status of " + name + ". True for completed, False for incomplete");
        boolean status = scan.nextBoolean();
        scan.nextLine();

        System.out.println("\nEnter the desired grade for " + name);
        double desired = scan.nextDouble();
        scan.nextLine();

        Course temp = createCourse(name, credits, desired, categories);
        for (Category cat : categories) {
            temp.addCategory(cat);
        }
        setCourseUp(temp);
        allCourses.add(temp);
        System.out.println(curCourse.getCourseName() + " has been created");

        studentRecord.addCourse(curCourse);
    }


    // MODIFIES: this
    // EFFECTS: conducts editing a category mark of a course
    private void doEditCourse() {
        Course editCourse;
        String catChoice;
        ArrayList<Category> edit = new ArrayList<>();
        System.out.println("\nWhich course would you like to edit?");
        String choice = scan.nextLine();
        for (int i = 0; i <= allCourses.size() - 1; i++) {
            if (allCourses.get(i).getCourseName().equals(choice)) {
                editCourse = allCourses.get(i);
                System.out.println("\nWhich category mark would you like to edit?");
                catChoice = scan.nextLine();
                for (int k = 0; k <= editCourse.getCategoryList().size() - 1; k++) {
                    if (editCourse.getCategoryList().get(k).getName().equals(catChoice)) {
                        System.out.println("\nEdit mark of " + catChoice + " to:");
                        double editMark = scan.nextDouble();
                        scan.nextLine();
                        editCourse.getCategoryList().get(k).setMark(editMark);
                        System.out.println("The " + catChoice + " category of " + choice + " was successfully edited.");
                    }
                }
            }
        }
    }

    // EFFECTS: prints all courses by name and credits
    private void printAllCourses() {
        List<Course> courses = studentRecord.getCourseList();
        for (Course c : courses) {
            System.out.println("\nCourse Name: " + c.getCourseName());
            System.out.println("\tCredits: " + c.getCredits());
        }
    }


    // REQUIRES: c to not be null
    // EFFECTS: print course information of c
    private void printCourseOutline(Course c) {
        System.out.println("\nCourse Name: " + c.getCourseName());
        System.out.println("\nCredits: " + c.getCredits());
        System.out.println("\nCategories & Weights:");
        for (int i = 0; i <= c.getCategoryList().size() - 1; i++) {
            System.out.println("\tName: " + c.getCategoryList().get(i).getName());
            System.out.println("\tWeight: " + c.getCategoryList().get(i).getWeight());
        }
    }

    // REQUIRES: c to not be null
    // EFFECTS: sets fields of course not from user input
    public void setCourseUp(Course c) {
        c.checkIsCompleted();
        if (c.getIsCompleted()) {
            c.calculateGrade();
        } else {
            c.calculateGrade();
        }
        c.calculateMinFinalScore();
    }

    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(studentRecord);
            jsonWriter.close();
            System.out.println("Saved " + studentRecord.getId() + "'s student record to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            studentRecord = jsonReader.read();
            System.out.println("Loaded " + studentRecord.getId() + "'s student record from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: returns number of courses in this student record
    public int numCourses() {
        return allCourses.size();
    }


}
