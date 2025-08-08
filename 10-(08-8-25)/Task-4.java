/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [08-08-25]
 | Program Definition : 
 |4  Create a class "Person" with fields "name" and "age" and a method "display()" that prints the name and age of the person.
 |   Create a subclass "Employee" that extends "Person" and adds a field "salary" and a method "display()" that prints the name, age, and salary of the employee.
 |   Create a subclass "Manager" that extends "Employee" and adds a field "department" and a method "display()" that prints the name, age, salary, and department of the manager.
 |   Create an object of the "Person" class and call the "display()" method.
 |   Create an object of the "Employee" class and call the "display()" method.
 -------------------------------------------------------------*/

import java.util.Scanner;

// base class
class Person 
{
    protected String name;
    protected int age;

    public Person(String name,int age) 
    {
        this.name=name;
        this.age=age;
    }

    public void display() 
    {
        System.out.println("Name : " + name);
        System.out.println("Age  : " + age);
    }
}

// subclass of Person
class Employee extends Person 
{
    protected double salary;

    public Employee(String name,int age,double salary) 
    {
        super(name,age);
        this.salary=salary;
    }

    @Override
    public void display() 
    {
        super.display();  // call parent display()
        System.out.println("Salary : " + salary);
    }
}

// subclass of Employee
class Manager extends Employee 
{
    private String department;

    public Manager(String name , int age , double salary , String department) 
    {
        super(name , age , salary);
        this.department=department;
    }

    @Override
    public void display() 
    {
        super.display();  // call Employee display()
        System.out.println("Department : " + department);
    }
}

// main class
class TASK_4 
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);

        // person object (static values)
        System.out.println("\n-------- Person Details --------");
        Person p1=new Person("Om Patel", 18);
        p1.display();

        // employee object (user input)
        System.out.println("\n------- Enter Employee Details -------");
        System.out.print("Enter name : ");
        String name=sc.nextLine();
        System.out.print("Enter age : ");
        int age=sc.nextInt();
        System.out.print("Enter salary : ");
        double salary=sc.nextDouble();

        Employee e1=new Employee(name , age , salary);
        System.out.println("\n------- Employee Details -------");
        e1.display();

        sc.close();
    }
}


// output example

/* 

-------- Person Details --------
Name : Om Patel
Age  : 18

------- Enter Employee Details -------
Enter name : Roy
Enter age : 20
Enter salary : 15000

------- Employee Details -------
Name : Roy
Age  : 20
Salary : 15000.0


*/