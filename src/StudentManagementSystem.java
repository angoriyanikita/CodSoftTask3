
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class StudentData {
    private String name;
    private int rollNumber;
    private String grade;

    public StudentData(String name, int rollNumber, String grade) {
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

class StudentData1 {
    private List<StudentData> students;

    public StudentData1() {
        students = new ArrayList<>();
    }

    public void addStudent(StudentData student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public StudentData searchStudent(String name) {
        for (StudentData student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }



    public List<StudentData> getAllStudents() {
        return students;
    }


}

public class StudentManagementSystem {

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/studentdata","root","shiyaram@12345");
            System.out.println(con);
        }
        catch (Exception e){
            System.out.println("Exception caught");
            System.out.println(e.getMessage());

        }


        StudentData1 sms = new StudentData1();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add a student");
            System.out.println("2. Search for a student");
            System.out.println("3. Remove a student ");
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
                        System.out.println("Name and grade must required. Try again.");
                    } else {
                        StudentData student = new StudentData(name, rollNumber, grade);
                        sms.addStudent(student);

                        System.out.println("Student added successfully.");
                    }
                    break;

                    case 2:
                        System.out.print("Enter the name to search");
                        String nameToSearch = sc.nextLine();
                        StudentData student = sms.searchStudent(nameToSearch);
                        if (student != null) {
                            System.out.println("Name: " + student.getName() + ", Roll Number: " + student.getRollNumber() + ", Grade: " + student.getGrade());
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                case 3:
                    System.out.print("Enter roll number to remove: ");
                    int rollForRemove = sc.nextInt();
                    sc.nextLine();
                    sms.removeStudent(rollForRemove);
                    System.out.println("Student removed successfully.");
                    break;

                case 4:
                    List<StudentData> allStudents = sms.getAllStudents();
                    System.out.println(" Show all Students data:");
                    for (StudentData allStudent : allStudents) {
                        System.out.println(allStudent);
                    }
                    break;

                case 5:
                    System.out.println("Exiting Student Management System.");
                    sc.close();
                    break;

                default:
                    System.out.println("Please choose a valid option.");
            }
        }


    }

}
