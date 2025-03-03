// easy
import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " - Age: " + age + ", Salary: $" + salary;
    }
}

public class EmployeeSorting {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 30, 50000),
            new Employee("Bob", 25, 60000),
            new Employee("Charlie", 35, 55000)
        );

        // Sorting employees by salary using Lambda Expression
        employees.sort((e1, e2) -> Double.compare(e1.salary, e2.salary));

        System.out.println("Sorted Employees by Salary:");
        employees.forEach(System.out::println);
    }
}

// medium
import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    int marks;

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " - Marks: " + marks;
    }
}

public class StudentFiltering {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 80),
            new Student("Bob", 70),
            new Student("Charlie", 85),
            new Student("David", 60),
            new Student("Eve", 90)
        );

        // Filtering students scoring above 75%, sorting by marks, and displaying names
        List<Student> filteredStudents = students.stream()
            .filter(s -> s.marks > 75) // Filtering
            .sorted((s1, s2) -> Integer.compare(s2.marks, s1.marks)) // Sorting in descending order
            .collect(Collectors.toList());

        System.out.println("Filtered and Sorted Students:");
        filteredStudents.forEach(System.out::println);
    }
}

// hard
import java.util.*;
import java.util.stream.Collectors;

class Product {
    String name, category;
    double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - Category: " + category + ", Price: $" + price;
    }
}

public class ProductProcessing {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 800),
            new Product("Phone", "Electronics", 500),
            new Product("Shirt", "Clothing", 40),
            new Product("Jeans", "Clothing", 60),
            new Product("TV", "Electronics", 1200),
            new Product("Shoes", "Clothing", 100)
        );

        // Grouping products by category
        Map<String, List<Product>> groupedProducts = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));

        // Finding the most expensive product in each category
        Map<String, Product> mostExpensiveByCategory = products.stream()
            .collect(Collectors.toMap(
                p -> p.category,
                p -> p,
                (p1, p2) -> p1.price > p2.price ? p1 : p2
            ));

        // Calculating the average price of all products
        double avgPrice = products.stream()
            .mapToDouble(p -> p.price)
            .average()
            .orElse(0.0);

        System.out.println("Grouped Products: " + groupedProducts);
        System.out.println("Most Expensive Product in Each Category: " + mostExpensiveByCategory);
        System.out.println("Average Price of All Products: $" + avgPrice);
    }
}
