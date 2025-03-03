// easy
import java.util.*;

public class AutoUnboxingSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();

        System.out.println("Enter numbers (type 'exit' to stop): ");
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equalsIgnoreCase("exit")) break;
            numbers.add(Integer.parseInt(input)); 
        }

        int sum = 0;
        for (Integer num : numbers) {
            sum += num; 
        }

        System.out.println("Sum of numbers: " + sum);
        sc.close();
    }
}


// medium

import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", GPA: " + gpa;
    }
}

public class StudentSerialization {
    public static void main(String[] args) {
        String filename = "student_data.ser";
        
        // Serialize the object
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            Student student = new Student(101, "John Doe", 3.8);
            out.writeObject(student);
            System.out.println("Serialization successful!");
        } catch (IOException e) {
            System.out.println("Serialization Error: " + e.getMessage());
        }

        // Deserialize the object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            Student student = (Student) in.readObject();
            System.out.println("Deserialized Student: " + student);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }
}

// hard


import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name, designation;
    double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: $" + salary;
    }
}

public class EmployeeManagement {
    private static final String FILE_NAME = "employees.dat";
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = loadEmployees();

        while (true) {
            System.out.println("\n1. Add Employee\n2. Display All Employees\n3. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID, Name, Designation, Salary: ");
                    employees.add(new Employee(sc.nextInt(), sc.next(), sc.next(), sc.nextDouble()));
                    saveEmployees(employees);
                    System.out.println("Employee added successfully!");
                    break;
                case 2:
                    System.out.println("\nAll Employees:");
                    employees.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static List<Employee> loadEmployees() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Employee>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(employees);
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }
}
