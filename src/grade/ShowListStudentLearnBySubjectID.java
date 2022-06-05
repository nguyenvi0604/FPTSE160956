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

/**
 *
 * @author LEGION
 */
public class ShowListStudentLearnBySubjectID {

    SubjectService subjectService = new SubjectService();
    Scanner sc = new Scanner(System.in);

    private String inputSubjectID() {
        System.out.println("Input subject ID: ");
        String subjectID = sc.next();

        if (subjectService.searchSubjectID(subjectID)) {
            return subjectID;
        }
        return null;
    }

    public void subjectGradeReport() {
        StudentService studentService = new StudentService();
        if (studentService.studentIdMapWithStudent().isEmpty()) {
            System.out.println("Have no student input before!");
            return;
        }
        if (subjectService.subjectIdMapWithSubject().isEmpty()) {
            System.out.println("Have no subject input before!");
            return;
        }
        String subjectId = inputSubjectID();
        if (subjectId == null) {
            System.out.println("Subject does not exist");
            return;
        }
        GradeService gradeService = new GradeService();
        System.out.print("Subject ID: " + subjectId);
        System.out.println("\nSubject name: " + subjectService.getSubjectNameBySubjectId(subjectId));
        System.out.println("List of student by Subject ID: ");
        Map<String, String> studentIdMapWithStudentName = gradeService.getStudentIdAndStudentNameBySubjectId(subjectId);
        if (studentIdMapWithStudentName.isEmpty()) {
            System.out.println("No student have learn this course!");
            return;
        }
        System.out.println("|++Student ID++|++++++Student name++++++|++Average mark++|++Status++|");
        for (Map.Entry<String, String> studentId : studentIdMapWithStudentName.entrySet()) {
            System.out.format("|%14s|%24s|%9.2f       |%10s|\n", studentId.getKey(),
                    studentId.getValue(), gradeService.getAverageBySubjectId(studentId.getKey(), subjectId), gradeService.getStatus(studentId.getKey(), subjectId));
        }
    }
}
