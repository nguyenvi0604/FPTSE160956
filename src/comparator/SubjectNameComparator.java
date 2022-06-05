package comparator;

import subject.Subject;

import java.util.Comparator;
import java.util.Map;

public class SubjectNameComparator implements Comparator<String> {
    private final Map<String, Subject> subjectMap;

    public SubjectNameComparator(Map<String, Subject> subjectMap) {
        this.subjectMap = subjectMap;
    }


    @Override
    public int compare(String o1, String o2) {
        return subjectMap.get(o1).getSubjectName().compareTo(subjectMap.get(o2).getSubjectName());
    }
}
