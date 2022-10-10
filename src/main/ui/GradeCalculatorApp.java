package ui;

import java.util.Scanner;

// Grade calculator application
public class GradeCalculatorApp {
    String id; // id of the user

    public GradeCalculatorApp() {
        runGradeCalculator();
    }

    private void runGradeCalculator() {
        Scanner scan = new Scanner(System.in);
        userLogin();
        id = scan.nextLine();



    }

    private void userLogin() {
        System.out.println("\nWelcome! Please select from:");
        System.out.println("\t1. -> returning user");
        System.out.println("\t2. -> new user");
    }

    private void displayMenu() {
        System.out.println("Hello, " + id);
        System.out.println("\t1. -> returning user");
        System.out.println("\t2. -> new user");
    }
}
