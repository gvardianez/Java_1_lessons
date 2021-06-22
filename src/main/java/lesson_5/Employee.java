package lesson_5;

public class Employee {
    private String fullName;
    private String position;
    private String email;
    private int phone;
    private int salary;
    private int age;

    public Employee(String fullName, String position, String email, int phone, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void printInfo() {
        System.out.printf("FullName: %s, Position: %s, Email: %s, Phone: %d, Salary: %d, Age: %d\n", fullName, position, email, phone, salary, age);
    }

    public static void printInfo(Employee[] employees) {
        for (Employee empl : employees) {
            if (empl.getAge() > 40) empl.printInfo();
        }
    }

}
