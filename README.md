# GradeCalculatorApp

## Grade Calculator and Summary

### What will the application do?

This application will calculate what percentage you will need to score on the final exam to get your desired final grade in a course, calculate your average, and keep track of your marks and their corresponding categories for a given course.

### Who will use it?
This application is for students who would like to keep track of their classes and grades and utilize a grade calculator that also saves the categories and weights of their courses. This app can help students save time retyping, editing, and viewing their marks each time they want to utilize an online grade calculator while checking their overall average within the same application.

### Why is this project of interest to you?
As a student, I constantly check my grades and calculate what I need to score on my final to receive my desired grade. Because these websites never save data on previously entered marks and weights of a course, using an online grade calculator can be tedious. Given that I frequently use this website while using the UBC grades summary function, I see myself using this application with more functions, simplicity, and convenience. 

## User Stories
- As a user, I want to be able to add categories to my course
- As a user, I want to be able to add a course to my course list 
- As a user, I want to be able to be able to select a course and see their categories and weights
- As a user, I want to be able to be able to view all the courses I am enrolled in
- As a user, I want to be able to edit the mark for a category of my course 
- As a user, I want to be able to calculate the minimum mark I need to receive on the final to have an overall x% in my course 
- As a user, I want to be able to be able to calculate my average
- As a user, I want to be able to save my course list to file
- As a user, when I start the application, I want to be given the option to reload my course list

## Instructions for Grader
- You can add a course to your student record by clicking on the "Add Course" button, then specifying the number of categories in your course in the pop-up window and clicking the "Okay" button, then filling out your category information and clicking the "Next" button, and then filling our your course information and clicking the "Create" button
- You can view a subset of your student record consisting of either all, only complete, or only incomplete courses by selecting the criterion and clicking the "View" button
- You can locate an image on the main screen when my application starts or an image as an icon for the reload option pop-up window
- You can save the state of my application by clicking the "Save and Exit" button
- You can reload the state of my application by clicking the "Returning" button, then clicking the "Yes" button on the pop-up window

## Phase 4: Task 2
- The loaded courses from json is added to the log as my persistence code for loading StudentRecord calls on the addCourse method, which has been instructed to generate the course added event in the log. Therefore, you will see a bunch of added course events added to the log.
Thu Dec 01 01:39:40 PST 2022</br>
MATH 200 added to student record

Thu Dec 01 01:39:40 PST 2022</br>
CPSC 210 added to student record

Thu Dec 01 01:39:40 PST 2022</br>
ENGL 110 added to student record

Thu Dec 01 01:39:40 PST 2022</br>
WRDS 150 added to student record

Thu Dec 01 01:39:43 PST 2022</br>
Viewed all courses

Thu Dec 01 01:39:46 PST 2022</br>
Viewed completed courses

Thu Dec 01 01:39:48 PST 2022</br>
Viewed incomplete courses

Thu Dec 01 01:41:19 PST 2022</br>
PSYC 102 added to student record

Thu Dec 01 01:41:25 PST 2022</br>
Viewed all courses


Process finished with exit code 0

## Phase 4: Task 3
- 