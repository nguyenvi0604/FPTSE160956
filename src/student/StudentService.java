/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 *
 * @author LEGION
 */
public class StudentService {

    Scanner sc = new Scanner(System.in);
    StudentValid studentValid = new StudentValid();
    private static Map<String, Student> myStudents = new HashMap<>();

    /*
    Support for function show 6 7
     */
    public String getNameByStudentID(String studentID) {
        Student student = myStudents.get(studentID);
        return student.getFirstName() + " " + student.getLastName();
    }
    /*
    Support for check null function 5 6 7
    */
    public Map studentIdMapWithStudent(){
        return myStudents;
    }

    public String inputStID() {
        String newStudentId;
        boolean flag = true;
        do {
            System.out.print("Input student Id(EXAMPLE: SE160956): ");
            newStudentId = sc.next().trim().toUpperCase();
            if (!studentValid.checkStudentIdValid(newStudentId)) {
                System.out.println("Your student Id must like form Example!");
                System.out.println("Please Input again!");
            } else if (!searchStID(newStudentId)) {
                flag = false;
            } else {
                System.out.println("Your student Id is duplicate.\nPlease input different Student id!");
            }
        } while (flag);
        return newStudentId;
    }

    /*
    Support for search student id
     */
    public boolean searchStID(String studentID) {
        return myStudents.containsKey(studentID);
    }

    /*
    Check string is null or not -> support function input
     */
    private String returnStringSIfValueIsNotEmpty(String s) {
        while (s.isEmpty()) {
            System.out.println("Please do not leave this section blank!");
            System.out.print("Input again: ");
            s = sc.nextLine();
        }
        return s;
    }

    private String inputFName() {
        String firstName;
        sc = new Scanner(System.in);
        System.out.print("Input first name: ");
        firstName = sc.nextLine().trim();

        return returnStringSIfValueIsNotEmpty(firstName);
    }

    private String inputLName() {
        String lastName;
        sc = new Scanner(System.in);
        System.out.print("Input your last name: ");
        lastName = sc.nextLine().trim();
        return returnStringSIfValueIsNotEmpty(lastName);
    }

    private String inputGender() {
        String gender;
        sc = new Scanner(System.in);
        boolean flag = true;
        do {
            System.out.print("Input your gender(MALE or FEMALE): ");
            gender = sc.nextLine().toUpperCase();
            returnStringSIfValueIsNotEmpty(gender);
            if (studentValid.checkGenderValid(gender)) {
                flag = false;
            } else {
                System.out.print("\nOnly input MALE or FEMALE!");
            }
        } while (flag);
        return gender;
    }

    private Date inputDateOfBirth() {

        boolean check = true;
        Date date = null;
        int day, month, year;
        while (check) {
            String dateFormat = "\\d{2}/\\d{2}/\\d{4}";
            System.out.print("Input your date of birth (Ex: dd/MM/yyyy): ");
            String data = sc.nextLine();
            if (data.matches(dateFormat)) {
                try {
                    StudentValid validation = new StudentValid();
                    /*
                    StringTokenizer ho tro cat chuoi data thanh nhung chuoi nho tai vi tri "/"
                     */
                    StringTokenizer st = new StringTokenizer(data, "/");
                    day = Integer.parseInt(st.nextToken());
                    month = Integer.parseInt(st.nextToken());
                    year = Integer.parseInt(st.nextToken());
                    if (validation.checkDayValid(year, month, day)) {
                        date = getDate(data);
                        check = false;
                    }else{
                        /*
                        truong hop nay sai khi nhap fake ngay sinh
                         */
                        System.out.println("Please input your real date!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }else{
                /*
                sai khi nhap sai dinh dang
                 */
                System.out.println("Please input like form example(dd/MM/yyyy)!");
            }
        }
        return date;
    }

    /*
    Ho tro ham Input date ( chuyen string s thanh date)
     */
    private Date getDate(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatter.parse(s);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return date;
    }

    private String inputEmail() {
        boolean check = true;
        String regexPattern = "^(.+)@(\\S+)$";
        String email = null;
        while (check) {
            System.out.print("Input your email: ");
            email = sc.next();
            boolean isMatches = Pattern.compile(regexPattern)
                    .matcher(email)
                    .matches();
            check = !isMatches;
            if (check) {
                System.out.println("Please input your email again!");
            }
        }
        return email;
    }

    private String inputPhoneNumber() {
        /*
        check so dau tien la so 0
         */
        String phoneNumber;
        sc = new Scanner(System.in);
        System.out.println("Input your phone number: ");
        phoneNumber = sc.next();
        while ((phoneNumber.length() < 9 || phoneNumber.length() > 12) || phoneNumber.charAt(0)!='0') {
            System.out.print("Pleases input your real phone number: ");
            phoneNumber = sc.next();
        }
        return phoneNumber;
    }

    public void createStudent() {

        Student st = new Student();
        String newStuID = this.inputStID();
        st.setStudentID(newStuID);

        String firstName = inputFName();
        st.setFirstName(firstName);

        String lastName = inputLName();
        st.setLastName(lastName);

        String yourGender = inputGender();
        st.setGender(yourGender);

        Date dOB = inputDateOfBirth();
        st.setDateOfBirth(dOB);

        String email = inputEmail();
        st.setEmail(email);

        String phoneNumber = inputPhoneNumber();
        st.setPhoneNumber(phoneNumber);
        myStudents.put(newStuID, st);
    }

    public void updateStudent(String id) {

        Student st = new Student();
        st.setStudentID(id);

        String firstName = inputFName();
        st.setFirstName(firstName);

        String lastName = inputLName();
        st.setLastName(lastName);

        String yourGender = inputGender();
        st.setGender(yourGender);

        Date dOB = inputDateOfBirth();
        st.setDateOfBirth(dOB);

        String email = inputEmail();
        st.setEmail(email);

        String phoneNumber = inputPhoneNumber();
        st.setPhoneNumber(phoneNumber);
        myStudents.put(id, st);
        System.out.println("Successfully!");
    }

    private void deleteStudent(String id) {
        myStudents.remove(id);
        System.out.println("Delete successfully!");
    }

    private void showMiniMenu2() {
        // get options for menu
        System.out.format("|----------2.UPDATE STUDENT----------|\n");
        System.out.format("|     1. Update student information  |\n");
        System.out.format("|     2. Delete student              |\n");
        System.out.format("|     Others. Back to main menu      |\n");
        System.out.format("|____________________________________|\n");
        System.out.print("|- Your choice: ");
    }

    public void updateStudentFunction() {
        System.out.print("Input student ID need to update: ");
        String stIDUpdate = sc.next().trim().toUpperCase();
        boolean flag = true;
        do {
            boolean flagCheckStudentID = searchStID(stIDUpdate);
            // else kho hieu
            if (flagCheckStudentID == true) {
                showMiniMenu2();
                int userChoice = sc.nextInt();
                switch (userChoice) {
                    case 1:
                        updateStudent(stIDUpdate);
                        break;
                    case 2:
                        System.out.print("Confirm delete student?[Y/N]: ");
                        String checkConfirm = sc.next().toUpperCase();
                        switch (checkConfirm) {
                            case "Y":
                                deleteStudent(stIDUpdate);
                                flag = false;
                                break;
                            case "N":
                                System.out.println("Delete fail! Back to update student menu!");
                        }
                        break;
                    default:
                        System.out.println("Back to main menu!");
                        flag = false;
                        break;

                }
            } else {
                System.out.println("Student does not exist");
                flag = false;
            }
        } while (flag);
    }
}
