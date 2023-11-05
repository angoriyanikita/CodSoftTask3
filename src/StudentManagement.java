
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }



    public List<Student> getAllStudents() {
        return students;
    }


}

public class StudentManagement {

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/studentdata","root","shiyaram@12345");
            System.out.println(con);
        }
        catch (Exception e){
            System.out.println("Exception caught");
            System.out.println(e.getMessage());

        }


        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add a student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter student roll number: ");
                    int rollNumber = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter student grade: ");
                    String grade = sc.nextLine();

                    if (name.isEmpty() || grade.isEmpty()) {
                        System.out.println("Name and grade cannot be empty. Try again.");
                    } else {
                        Student student = new Student(name, rollNumber, grade);
                        sms.addStudent(student);
                        System.out.println("Student added successfully.");
                    }
                    break;

                case 2:
                    System.out.print("Enter roll number of the student to remove: ");
                    int rollForRemove = sc.nextInt();
                    sc.nextLine();
                    sms.removeStudent(rollForRemove);
                    System.out.println("Student removed successfully.");
                    break;

                case 3:
                    System.out.print("Enter roll number to search for a student: ");
                    int rollForSearch = sc.nextInt();
                    sc.nextLine();
                    Student foundStudent = sms.searchStudent(rollForSearch);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    List<Student> allStudents = sms.getAllStudents();
                    System.out.println("All Students:");
                    for (Student student : allStudents) {
                        System.out.println(student);
                    }
                    break;

                case 5:
                    System.out.println("Exiting Student Management System.");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Please choose a valid option.");
            }
        }


    }

}
