/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grade;

import java.util.Map;
import java.util.Scanner;
import student.StudentService;
import subject.Subject;
import subject.SubjectService;

public class ShowGradeByStudenID {

    StudentService studentService = new StudentService();
    SubjectService subjectService = new SubjectService();
    GradeService gradeService = new GradeService();

    Scanner sc = new Scanner(System.in);

    private String inputStudentId() {
        System.out.println("Input student ID: ");
        return sc.next();
    }

    private boolean checkStudentID(String studentID) {
        return studentService.searchStID(studentID);
    }

    public void showStudentGradeReport() {
        if (studentService.studentIdMapWithStudent().isEmpty()) {
            System.out.println("Have no student input before!");
            return;
        }
        if (subjectService.subjectIdMapWithSubject().isEmpty()) {
            System.out.println("Have no subject input before!");
            return;
        }
        String studentID = inputStudentId().toUpperCase();
        if (!checkStudentID(studentID)) {
            System.out.println("Student does not exist!");
            return;
        }
        int count = 1;
        System.out.println("Student name: " + studentService.getNameByStudentID(studentID));
        System.out.println("| ++No++ | +++++++Subject name+++++++ | ++Average mark++ | ++Status++ |");
        Map<String, Grade> subjectsAndGradeByStudentID = gradeService.getSubjectIdAndGradeByStudentID(studentID);
//        if (subjectsAndGradeByStudentID.size() == 1) {
//            for (String subjectId : subjectsAndGradeByStudentID.keySet()) {
//                System.out.format("|%5d   | %24s   | %8.1f         |  %s     |\n", count, subjectService.getSubjectNameBySubjectId(subjectId), gradeService.getAverageBySubjectId(studentID, subjectId), gradeService.getStatus(studentID, subjectId));
//                count++;
//            }
//            return;
//        }

        Map<String, Subject> sortedBySubjectName = subjectService.sortBySubjectName(subjectsAndGradeByStudentID.keySet());

        for (String subjectId : sortedBySubjectName.keySet()) {
            System.out.format("|%5d   | %24s   | %8.1f         |  %s     |\n", count, subjectService.getSubjectNameBySubjectId(subjectId), gradeService.getAverageBySubjectId(studentID, subjectId), gradeService.getStatus(studentID, subjectId));
            count++;
        }
        System.out.println();
    }
}
