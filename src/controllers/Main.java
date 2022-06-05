package controllers;


import grade.GradeService;
import grade.ShowGradeByStudenID;
import grade.ShowListStudentLearnBySubjectID;
import java.util.Scanner;
import student.StudentService;
import subject.SubjectService;


public class Main {
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        String choiceYN;
        GradeService gradeService = new GradeService();
        StudentService stv = new StudentService();
        SubjectService sbv = new SubjectService();
        ShowGradeByStudenID showGradeByStudenID = new ShowGradeByStudenID();
        do {
            showMenu();
            System.out.print("Please input your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    boolean countinueOrNot = true;
                    stv.createStudent();
                    while (countinueOrNot) {
                        System.out.println("Do you want to countinue create new student?[Y/N] ");
                        System.out.print("Your choice: ");
                        choiceYN = sc.next().toUpperCase();
                        switch (choiceYN) {
                            case "Y":
                                stv.createStudent();
                                break;
                            case "N":
                                System.out.println("You choose back to menu.");
                                countinueOrNot = false;
                                break;
                        }
                    }
                    break;

                case 2:
                    stv.updateStudentFunction();
                    break;
                case 3:
                    countinueOrNot = true;
                    sbv.addNewSubject();
                    while (countinueOrNot) {
                        System.out.println("Do you want to countinue create new subject?[Y/N] ");
                        System.out.print("Your choice: ");
                        choiceYN = sc.next().toUpperCase();
                        switch (choiceYN) {
                            case "Y":
                                sbv.addNewSubject();
                                break;
                            case "N":
                                System.out.println("You choose back to menu.");
                                countinueOrNot = false;
                                break;
                        }
                    }
                    break;
                case 4:
                    sbv.updateSubjectFunction();
                    break;
                case 5:
                    gradeService.inputGradeToSubjectId();
                    break;
                case 6:
                    showGradeByStudenID.showStudentGradeReport();
                    break;
                case 7:
                    ShowListStudentLearnBySubjectID showListStudentLearnBySubjectID = new ShowListStudentLearnBySubjectID();
                    showListStudentLearnBySubjectID.subjectGradeReport();
                    break;
                default:
                    System.out.println("Bye!");
                    break;
            }
        } while (choice >= 1 && choice <= 7);
    }

    private static void showMenu() {
        System.out.format("\n|----------STUDENTS MANAGER MENU---------|\n");
        System.out.format("|         1. Add new student             |\n");
        System.out.format("|         2. Update Student              |\n");
        System.out.format("|         3. Add new Subject             |\n");
        System.out.format("|         4. Update Subject              |\n");
        System.out.format("|         5. Enter Grade                 |\n");
        System.out.format("|         6. Student Grade Report        |\n");
        System.out.format("|         7. Subject Grade Report        |\n");
        System.out.format("|         Others. Quit                   |\n");
        System.out.format("|________________________________________|\n");
    }
}
