/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subject;

/**
 *
 * @author LEGION
 */
public class Subject {

    private String subjectID;
    private String subjectName;
    private int credit;

    public Subject() {
    }

    public Subject(String isSubjectID, String isSubjectName, int isCredit) {
        this.subjectID = isSubjectID;
        this.subjectName = isSubjectName;
        this.credit = isCredit;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
