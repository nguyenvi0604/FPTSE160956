/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grade;

/**
 *
 * @author LEGION
 */
public class Grade {

    private Double gradeLabs;
    private Double gradeProcessTest;
    private Double gradeFinal;

    public Double getAverage() {
        return this.gradeFinal * 0.4 + this.gradeLabs * 0.3 + this.gradeProcessTest * 0.3;
    }

    public Grade() {

    }

    public Grade(Double gradeLabs, Double gradeProcessTest, Double gradeFinal) {
        this.gradeLabs = gradeLabs;
        this.gradeProcessTest = gradeProcessTest;
        this.gradeFinal = gradeFinal;
    }

    public Double getGradeLabs() {
        return gradeLabs;
    }

    public void setGradeLabs(Double gradeLabs) {
        this.gradeLabs = gradeLabs;
    }

    public Double getGradeProcessTest() {
        return gradeProcessTest;
    }

    public void setGradeProcessTest(Double gradeProcessTest) {
        this.gradeProcessTest = gradeProcessTest;
    }

    public Double getGradeFinal() {
        return gradeFinal;
    }

    public void setGradeFinal(Double gradeFinal) {
        this.gradeFinal = gradeFinal;
    }
}
