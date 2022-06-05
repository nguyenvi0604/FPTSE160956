package comparator;

import java.util.Comparator;
import java.util.Map;

public class StudentNameComparator implements Comparator<String> {

    private final Map<String,String> studentIdMapWithStudentName;

    public StudentNameComparator(Map<String, String> studentIdMapWithStudentName) {
        this.studentIdMapWithStudentName = studentIdMapWithStudentName;
    }

    @Override
    public int compare(String studentId1, String studentId2) {
        return studentIdMapWithStudentName.get(studentId1).compareTo(studentIdMapWithStudentName.get(studentId2));
    }
}
