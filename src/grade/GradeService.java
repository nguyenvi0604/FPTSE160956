/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grade;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import comparator.StudentNameComparator;
import student.StudentService;
import subject.SubjectService;

/**
 *
 * @author LEGION
 */
public class GradeService {

    private static Map<String, Map<String, Grade>> myGrade = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    public Map<String, Grade> getSubjectIdAndGradeByStudentID(String studentID) {
        return myGrade.get(studentID);
    }

    public Double getAverageBySubjectId(String studentId, String subjectId) {
        return myGrade.get(studentId).get(subjectId).getAverage();
    }

    public Map<String, String> getStudentIdAndStudentNameBySubjectId(String subjectId) {
        StudentService studentService = new StudentService();
        Map<String, String> studentIdMapWithStudentName = new HashMap<>();
        for (String studentId : myGrade.keySet()) {
            if (myGrade.get(studentId).containsKey(subjectId)) {
                String studentName = studentService.getNameByStudentID(studentId);
                studentIdMapWithStudentName.put(studentId, studentName);
            }
        }
        StudentNameComparator studentNameComparator = new StudentNameComparator(studentIdMapWithStudentName);
        Map<String,String> softByStudentName = new TreeMap<>(studentNameComparator);
        softByStudentName.putAll(studentIdMapWithStudentName);
        return softByStudentName;
    }

    public String getStatus(String studentId, String subjectId) {
        if (getAverageBySubjectId(studentId, subjectId) >= 5.0) {
            return "Pass!";
        }
        return "Not pass!";
    }

    private boolean checkStudentID(String studentID) {
        StudentService studentService = new StudentService();
        return studentService.searchStID(studentID);
    }

    private boolean checkSubjectID(String subjectID) {
        SubjectService subjectService = new SubjectService();
        return subjectService.searchSubjectID(subjectID);
    }

    private boolean isStudentIdExistInMyGrade(String studentID) {
        return myGrade.containsKey(studentID);
    }

    private String inputStudentId() {
        System.out.println("Input student ID: ");
        return sc.next().trim();
    }

    private String inputSubjectId() {
        System.out.println("Input subject ID: ");
        return sc.next();
    }

    public void inputGradeToSubjectId() {
        StudentService studentService = new StudentService();
        if (studentService.studentIdMapWithStudent().isEmpty()) {
            System.out.println("Have no student input before!");
            return;
        }
        SubjectService subjectService = new SubjectService();
        if (subjectService.subjectIdMapWithSubject().isEmpty()) {
            System.out.println("Have no subject input before!");
            return;
        }
        String studentID = inputStudentId().trim();
        if (!checkStudentID(studentID)) {
            System.out.println("Student does not exist!");
            return;
        }

        String subjectID = inputSubjectId().trim();
        if (!checkSubjectID(subjectID)) {
            System.out.println("Subject does not exist!");
            return;
        }
        if (!isStudentIdExistInMyGrade(studentID)) {
            System.out.println("Input grade of " + subjectID);
            insertNewGrade(studentID, subjectID);
            System.out.println("Input successfully!");
            return;
        }
        if (!myGrade.get(studentID).containsKey(subjectID)) {
            System.out.println("Input grade of " + subjectID);
            insertGrade(studentID, subjectID);
            System.out.println("Input successfully!");
            return;
        }
        overwriteGrade(studentID, subjectID);
    }

    private String overwriteOrNot() {
        System.out.println("Do you want to overwrite grade of this Subject?[Y/N]");
        return sc.next().toUpperCase();
    }

    private void overwriteGrade(String studentId, String subjectId) {
        if (overwriteOrNot().equals("N")) {
            System.out.println("Update student fail.");
            return;
        }
        System.out.println("You choose overwrite!");
        Grade inputGrade = inputGradeToSubject();
        Map<String, Grade> subjectIdMapWithGrade = myGrade.get(studentId);
        subjectIdMapWithGrade.put(subjectId, inputGrade);

        myGrade.put(studentId, subjectIdMapWithGrade);
        System.out.println("Input successfully!");
    }

    private void insertNewGrade(String studentID, String subjectID) {
        Grade inputGrade = inputGradeToSubject();
        Map<String, Grade> subjectIdMapWithGrade = new HashMap<>();
        subjectIdMapWithGrade.put(subjectID, inputGrade);
        myGrade.put(studentID, subjectIdMapWithGrade);
    }

    private void insertGrade(String studentID, String subjectID) {
        Grade inputGrade = inputGradeToSubject();
        Map<String, Grade> subjectIdMapWithGrade = myGrade.get(studentID);
        subjectIdMapWithGrade.put(subjectID, inputGrade);
        myGrade.put(studentID, subjectIdMapWithGrade);
    }

    private boolean checkGradeValid(Double grade){
        return grade >=  0 && grade <= 10;
    }
    
    private Double inputGradeToLabProcessTestAndFinal(){
        Double grade;
        sc = new Scanner(System.in);
        grade = sc.nextDouble();
        while(!checkGradeValid(grade)){
            System.out.println("Please input grade from 1 to 10!");
            System.out.print("Input again: ");
            grade = sc.nextDouble();
        }
        return grade;
    }
    
    private Grade inputGradeToSubject() {
        double gradeLabs, gradeFinal, gradeProcessTest;

        System.out.print("Input grade of labs: ");
        gradeLabs = inputGradeToLabProcessTestAndFinal();

        System.out.print("Input grade of process test:  ");
        gradeProcessTest = inputGradeToLabProcessTestAndFinal();

        System.out.print("Input grade of final: ");
        gradeFinal = inputGradeToLabProcessTestAndFinal();

        Grade subjectGrade = new Grade(gradeLabs, gradeProcessTest, gradeFinal);
        return subjectGrade;
    }
}
