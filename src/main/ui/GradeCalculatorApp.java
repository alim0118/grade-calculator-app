package ui;

import model.Category;
import model.Course;
import model.Transcript;

import java.util.ArrayList;
import java.util.Scanner;

// Grade calculator application
public class GradeCalculatorApp {
    Scanner scan;
    private static int id = 0; // id number of user

    private ArrayList<Course> allCourses = new ArrayList<>();
    private ArrayList<Category> allCategories = new ArrayList<>();
    private Course curCourse;
    private Transcript curTranscript;

    // EFFECTS: runs the grade calculator application
    public GradeCalculatorApp() {
        runGradeCalculator();
    }

    // drew inspiration from TellerApp
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGradeCalculator() {
        boolean cont = true;
        String cmd = null;
        String userType = init();

        if (userType.equals("1.")) {
            System.out.println("Hello, " + id);
            while (cont) {
                cmd = displayReturn();
                if (cmd.equals("0.")) {
                    cont = false;
                } else {
                    userCommandReturning(cmd);
                }
            }
        } else {
            runNewGradeCalculator(cont, cmd);
        }
        System.out.println("Goodbye, thank you for using the grade calculator!");
    }

    // for new users
    private void runNewGradeCalculator(boolean continuing, String command) {
        boolean cont = continuing;
        String cmd = command;

        System.out.println("Hello, your user will be " + id++);
        while (cont) {
            cmd = displayNew();
            if (cmd.equals("0.")) {
                cont = false;
            } else {
                userCommandNew(cmd);
            }
        }
    }

    // MODIFIES: this;
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

    //
    private Category createCategories(String name, double weight, double mark, boolean status) {
        Category newCategory = new Category(name, weight, mark, status);
        return newCategory;
    }

    private Course createCourse(String name, int credits, double desired, ArrayList<Category> categories) {
        //ArrayList<Category> addedCategories = new ArrayList<>();
        curCourse = new Course(name, credits, desired, categories);
        return curCourse;
    }

    private Transcript createTranscript(int id, ArrayList<Course> courses) {
        //ArrayList<Course> addedCourses = new ArrayList<>();
        curTranscript = new Transcript(id, courses);
        return curTranscript;
    }

    private void userLogin() {
        System.out.println("\nWelcome! Please select from:");
        System.out.println("\t1. -> returning user");
        System.out.println("\t2. -> new user");
    }

    private void displayMenuReturning() {
        System.out.println("\nSelect from: ");
        System.out.println("\t1. -> get overall average");
        System.out.println("\t2. -> get overall average as a letter grade");
        System.out.println("\t3. -> calculate what i need on the final exam");
        System.out.println("\t4. -> view all courses");
        System.out.println("\t5. -> view categories and weight of a course");
        System.out.println("\t0. -> quit");
    }

    private void displayMenuNew() {
        System.out.println("\nSelect from: ");
        System.out.println("\t1. -> add course");
        System.out.println("\t0. -> quit");
    }

    private void userCommandReturning(String command) {
        if (command.equals("1.")) {
            doCalculateOverallAverage();

        } else if (command.equals("2.")) {
            doConvertToLetterGrade();

        } else if (command.equals("3.")) {
            System.out.println("\nWhich course would you like to check for?");
            String choice = scan.nextLine();
            System.out.println("\nWhat is your desired final grade for " + choice + " ?");
            double desired = scan.nextDouble();
            doCalculateMinFinalScore(choice, desired);

        } else if (command.equals("4.")) {
            doViewAllCourses();

        } else if (command.equals("5.")) {
            System.out.println("\nWhich course would you like to view?");
            String choice = scan.nextLine();
            doViewCourse(choice);

        } else {
            System.out.println("Invalid selection. Please select again1");
        }

    }

    private void doCalculateOverallAverage() {
        System.out.println("Overall average: " + curTranscript.getAverage());
    }

    private void doConvertToLetterGrade() {
        System.out.println("Overall average as letter grade: " + curTranscript.getLetterGrade());
    }

    private void doCalculateMinFinalScore(String courseChoice, double desiredGrade) {
        double min = 0.0;
        for (int i = 0; i < allCourses.size() - 1; i++) {
            if (allCourses.get(i).getCourseName().equals(courseChoice)) {
                allCourses.get(i).setDesiredFinalGrade(desiredGrade);
                min = allCourses.get(i).getMinFinalScore();
                System.out.println("You need to score a minimum of " + min + "% on your final exam to get "
                        + desiredGrade + "% as your final grade of course " + courseChoice);
            }
        }
    }

    private void doViewAllCourses() {
        printAllCourses();
    }

    private void doViewCourse(String courseChoice) {
        for (int i = 0; i < allCourses.size() - 1; i++) {
            if (allCourses.get(i).getCourseName().equals(courseChoice)) {
                printCourseOutline(allCourses.get(i));
            }
        }
    }

    private void userCommandNew(String command) {
        if (command.equals("1.")) {
            System.out.println("How many categories does this course have?");
            int categoryNum = scan.nextInt();
            scan.nextLine();
            while (categoryNum >= 1) {
                doCreateCategory();
                categoryNum--;
            }
            doCreateCourse();
            createTranscript(id, allCourses);
            System.out.println(curCourse.getCourseName() + " has been created and added to" + id + "'s transcript.");

        } else {
            System.out.println("Invalid selection. Please select again2");
        }


    }

    private void doCreateCategory() {
        System.out.println("\nEnter the category name:");
        String name = scan.nextLine();
        System.out.println("\nEnter the weight of " + name);
        double weight = scan.nextDouble();
        scan.nextLine();
        // if course is completed then ask for mark otherwise ?
        System.out.println("\nEnter the status of " + name + ". True for completed, False for incomplete");
        boolean status = scan.nextBoolean();
        scan.nextLine();
        double mark = 0.0;
        if (status) {
            System.out.println("\nEnter the mark of " + name);
            mark = scan.nextDouble();
            scan.nextLine();
        }
        mark = 0;
        //Category newCat = new Category(name, weight, mark, status);
        //curCourse.addCategory(newCat);
        allCategories.add(createCategories(name, weight, mark, status));
    }

    private void doCreateCourse() {
        System.out.println("\nEnter the course name:");
        String name = scan.nextLine();
        System.out.println("\nEnter the number of credits for " + name);
        int credits = scan.nextInt();
        scan.nextLine();
        System.out.println("\nEnter the desired grade for the course " + name);
        double desired = scan.nextDouble();
        scan.nextLine();

        allCourses.add(createCourse(name, credits, desired, allCategories));
    }

    private void printAllCourses() {
        for (int i = 0; i < allCourses.size() - 1; i++) {
            System.out.println("\nCourse Name: " + allCourses.get(i).getCourseName());
            System.out.println("\tCredits: " + allCourses.get(i).getCredits());
        }

    }

    private void printCourseOutline(Course c) {
        System.out.println("\nCourse Name: " + c.getCourseName());
        System.out.println("\nCredits: " + c.getCredits());
        System.out.println("\nCategories & Weights:");
        for (int i = 0; i < c.getCategoryList().size() - 1; i++) {
            System.out.println("\tName: " + c.getCategoryList().get(i).getName());
            System.out.println("\tWeight: " + c.getCategoryList().get(i).getWeight());
        }
    }
}
