/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [13-08-25]
 | Program Definition : 1. Develop an abstract class Employee with abstract methods calculateSalary() and displayInfo(). 
 |                         Implement subclasses Manager and Programmer that extend Employee and provide their 
 |                         own salary calculation and information display logic. (user input)
 -------------------------------------------------------------*/


 import java.util.Scanner;

// abstract Class
abstract class Employee 
{
    abstract void calculateSalary();
    abstract void displayInfo();
}

// manager class
class Manager extends Employee 
{
    private double baseSalary;
    private double bonus;

    Manager(double baseSalary , double bonus) 
    {
        this.baseSalary=baseSalary;
        this.bonus=bonus;
    }

    @Override
    void calculateSalary() {
        System.out.println("Manager Salary: ₹ " + (baseSalary + bonus));
    }

    @Override
    void displayInfo() {
        System.out.println("Manager Info: Base = ₹ " + baseSalary + ", Bonus = ₹" + bonus);
    }
}

// Programmer class
class Programmer extends Employee 
{
    private double baseSalary;
    private double bonus;

    Programmer(double baseSalary, double bonus) 
    {
        this.baseSalary=baseSalary;
        this.bonus=bonus;
    }

    @Override
    void calculateSalary() 
    {
        System.out.println("Programmer Salary : ₹ " + (baseSalary+bonus));
    }

    @Override
    void displayInfo() 
    {
        System.out.println("Programmer Info : Base = ₹ " + baseSalary + ", Bonus = ₹" + bonus);
    }
}

class EmployeeTest 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        // Manager
        System.out.println("Enter Manager base salary:");
        double mBase=sc.nextDouble();
        System.out.println("Enter Manager bonus :");
        double mBonus=sc.nextDouble();
        Employee manager=new Manager(mBase,mBonus);

        // programmer
        System.out.println("Enter Programmer base salary :");
        double pBase=sc.nextDouble();
        System.out.println("Enter Programmer bonus :");
        double pBonus = sc.nextDouble();
        Employee programmer = new Programmer(pBase,pBonus);

        System.out.println("\n========== Manager Salary Information ==========");
        manager.displayInfo();
        manager.calculateSalary();
       


        System.out.println("\n======== Programmer Salary Information ==========");
        programmer.displayInfo();
        programmer.calculateSalary();
        


        sc.close();
    }
}

// output example

/* 

Enter Manager base salary:
10000
Enter Manager bonus :
200
Enter Programmer base salary :
40000
Enter Programmer bonus :
500 

========== Manager Salary Information ==========
Manager Info: Base = ₹ 10000.0, Bonus = ₹200.0
Manager Salary: ₹ 10200.0

======== Programmer Salary Information ==========
Programmer Info : Base = ₹ 40000.0, Bonus = ₹500.0
Programmer Salary : ₹ 40500.0 

*/