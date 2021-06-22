package lesson_5;

public class HomeWork4 {

    public static void main(String[] args) {
        Employee [] employees = new Employee[5];
        employees [0] = new Employee("Vasya Pupkin","Teacher","Cas@mail.ru",809036306,110000,25);
        employees [1] = new Employee("Vasya Petkin","Engineer","asds@mail.ru",54363346,400000,35);
        employees [2] = new Employee("Ivan Durakov","Doctor","ffa@mail.ru",543453046,20000,32);
        employees [3] = new Employee("Alex Alov","Driver","fhjsa@mail.ru",876637646,40000,42);
        employees [4] = new Employee("Ivan Petrov","Cok","ahjtfr@mail.ru",23363346,70000,55);
        Employee.printInfo(employees);
    }

}
