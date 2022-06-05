/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subject;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author LEGION
 */
public class SubjectService {

    private static HashMap<String, Subject> mySubjectList = new HashMap<>();

    private Scanner sc = new Scanner(System.in);

    public String getSubjectNameBySubjectId(String subjectId) {
        return mySubjectList.get(subjectId).getSubjectName();
    }
    public HashMap subjectIdMapWithSubject(){
        return mySubjectList;
    }
    public String addNewSubjectID() {
        String newSubjectID;
        boolean checkDuplicatedSubject;
        do {
            /*
            tach ham nhap
             */
            System.out.print("Input new subject ID: ");
            newSubjectID = sc.next();
            checkDuplicatedSubject = mySubjectList.containsKey(newSubjectID);
            if (checkDuplicatedSubject) {
                System.out.println("This ID subject already have!");
                System.out.println("Please input different ID subject");
            }
        } while (checkDuplicatedSubject);
        return newSubjectID;
    }

    public String addNewSubjectName() {
        String newSubjectName;
        sc = new Scanner(System.in);
        boolean checkNull = true;
        do {
            System.out.println("Input new subject name: ");
            newSubjectName = sc.nextLine().trim();
            checkNull = "".equals(newSubjectName) || newSubjectName == null;
            if (checkNull) {
                System.out.println("Subject name is not emtpy! Please input again!");
            }
        } while (checkNull);
        return newSubjectName;
    }

    public int addNewCredit() {
        int newCredit;
        sc = new Scanner(System.in);
        boolean checkPositive = true;
        do {
            System.out.println("Input new credit of subject: ");
            newCredit = sc.nextInt();
            // to do dem condition trong if thanh mot bien va xoa else
            if (newCredit <= 0) {
                System.out.println("Credit must be a positive integer number!");
                System.out.println("Please input credit again!");

            } else {
                checkPositive = false;
            }
        } while (checkPositive);
        return newCredit;
    }

    public void addNewSubject() {
        Subject sb = new Subject();
        String newSubjectID = addNewSubjectID();
        sb.setSubjectID(newSubjectID);

        String newSubjectName = addNewSubjectName();
        sb.setSubjectName(newSubjectName);

        int addNewCredit = addNewCredit();
        sb.setCredit(addNewCredit);

        mySubjectList.put(newSubjectID, sb);
        System.out.println("Delete successfully!");
    }

    private void miniDeleteSubjectMenu() {
        System.out.println("|----------4.DELETE SUBJECT----------|");
        System.out.println("|         1. Update Subject.         |");
        System.out.println("|         2. Delete Subject.         |");
        System.out.println("|         Others. Back to main menu  |");
        System.out.println("|____________________________________|");
        System.out.print("|- Your choice: ");
    }

    public void deleteSubject(String subjectID) {
        // to do
        System.out.println("Confirm to delete this subject?[Y/N]");
        String confirmYN = sc.next().toUpperCase();
        switch (confirmYN) {
            case "Y":
                mySubjectList.remove(subjectID);
                break;
            case "N":
                System.out.println("Delete fail!");
                System.out.println("Back to Update subject function!");
                break;
        }
    }

    public void updateSubjectInformation(String subjectID) {
        Subject sb = new Subject();
        sb.setSubjectID(subjectID);

        String newSubjectName = addNewSubjectName();
        sb.setSubjectName(newSubjectName);

        int addNewCredit = addNewCredit();
        sb.setCredit(addNewCredit);

        mySubjectList.put(subjectID, sb);
        System.out.println("Update information successfully");
    }

    public boolean searchSubjectID(String subjectID) {
        return mySubjectList.containsKey(subjectID);
    }

    public void updateSubjectFunction() {
        System.out.println("Input the subject ID need to update information: ");
        String subjectID = sc.next();
        boolean checkTF = searchSubjectID(subjectID);
        if (!checkTF) {
            System.out.println("Subject does not exist.");
            return;
        }
        do {
            int userChoice;
            miniDeleteSubjectMenu();
            userChoice = sc.nextInt();
            switch (userChoice) {
                case 1:
                    updateSubjectInformation(subjectID);
                    break;
                case 2:
                    deleteSubject(subjectID);
                    System.out.println("Back to main menu!");
                    checkTF = false;
                    break;
                default:
                    System.out.println("Back to main menu!");
                    checkTF = false;
            }
        } while (checkTF);
    }
}
