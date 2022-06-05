/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 *
 * @author LEGION
 */
public class StudentValid {

    public boolean checkPhoneNumberValid(String phone) {
        if ((phone.length() >= 10 && phone.length() <= 12) && phone.charAt(0) == '0') {
            for (int i = 2; i < phone.length(); i++) {
                if (phone.charAt(i) < '0' || phone.charAt(i) > '9') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkYearValid(int year) {
        //get current year, month, day
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        return !(year > currentYear || year < 1900);
    }

    private boolean checkMonthValid(int month) {
        return !(month > 12 || month < 1);
    }

    private boolean IsNamNhuan(int year) {
        if (year % 400 == 0) {
            return true;
        } else {
            return year % 4 == 0 && year % 100 != 0;
        }
    }

    public boolean checkDayValid(int year, int month, int day) {
        if(!checkYearValid(year)){
            return false;
        }
        if(!checkMonthValid(month)){
            return false;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                // day 31
                if (day > 31 || day <= 0) {
                    return false;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                //day 30
                if (day > 30 || day <= 0) {
                    return false;
                }
                break;
            case 2:
                if (IsNamNhuan(year)) {
                    if (day > 29 || day <= 0) {
                        return false;
                    }
                } else {
                    if (day > 28 | day <= 0) {
                        return false;
                    }
                }
                break;
            default:
                break;
        }
        return true;
    }
    public boolean checkGenderValid(String gender){
        return ("MALE".equals(gender.toUpperCase()) || "FEMALE".equals(gender.toUpperCase()));
    }
    public boolean checkStudentIdValid(String studentId){
        String regexStudentId = "[A-Z]{2}[0-9]{6}";
        boolean isMatches = Pattern.compile(regexStudentId).matcher(studentId).matches();
        return isMatches;
    }
}
