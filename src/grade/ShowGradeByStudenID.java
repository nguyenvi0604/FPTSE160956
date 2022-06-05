/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grade;

import java.util.Map;
import java.util.Scanner;
import student.StudentService;
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
        String studentID = inputStudentId();
        if (!checkStudentID(studentID)) {
            System.out.println("Student does not exist!");
            return;
        }

        System.out.println("Student name: " + studentService.getNameByStudentID(studentID));
        System.out.println("| ++No++ | +++++++Subject name+++++++ | ++Average mark++ | ++Status++ |");
        Map<String, Grade> subjectsAndGradeByStudentID = gradeService.getSubjectIdAndGradeByStudentID(studentID);
        int count = 1;
        for (String subjectId : subjectsAndGradeByStudentID.keySet()) {
            //System.out.println("     "+ count + "             "+ subjectService.getSubjectNameBySubjectId(subjectId)+"     " + gradeService.getAverageBySubjectId(studentID, subjectId) );
            System.out.format("|%5d   | %24s   | %8.1f         |  %s     |\n", count, subjectService.getSubjectNameBySubjectId(subjectId), gradeService.getAverageBySubjectId(studentID, subjectId), gradeService.getStatus(studentID, subjectId));
            count++;
        }
        System.out.println();
    }

    public void showStudentListBySubjectId() {
        String subjectId = "sb1";
        System.out.println("Subject: " + subjectService.getSubjectNameBySubjectId(subjectId));
        Map<String, String> studentIdMapWithStudentName = gradeService.getStudentIdAndStudentNameBySubjectId(subjectId);
        studentIdMapWithStudentName.entrySet().forEach((x) -> {
            System.out.println(x);
        });

    }
}
