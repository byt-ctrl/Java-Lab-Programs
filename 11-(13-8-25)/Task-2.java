/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [13-08-25]
 | Program Definition : 2. An abstract class called Marks is needed to calculate the percentage of marks earned by students A in three subjects (with each subject out of 100) and student B in four subjects (with each subject out of 100). 
 |                         This class must contain the abstract method getPercentage, which two other classes, “A” and “B,” will inherit. The method getPercentage, which provides the percentage of students, is shared by classes “A” and “B.”
 |                         The constructor of class ‘A’ will accept the marks obtained in three subjects as its parameters and the constructor of class ‘B’ will accept the marks obtained in four subjects as its parameters.
 |                         To test the implementation, objects for both the classes need to be created and the percentage of marks for each student should be printed. (user input)
 -------------------------------------------------------------*/


import java.util.Scanner;

// abstract class
abstract class Marks 
{
    abstract double getPercentage();
}

// class A (3 subjects)
class A extends Marks 
{
    private int mrk1,mrk2,mrk3;

    A(int mrk1 , int mrk2 , int mrk3) 
    {
        this.mrk1=mrk1;
        this.mrk2=mrk2;
        this.mrk3=mrk3;
    }

    @Override
    double getPercentage() 
    {
        return (mrk1+mrk2+mrk3)/3.0;
    }
}

// class B (4 subjects)
class B extends Marks 
{
    private int mrk1 , mrk2 , mrk3 , mrk4;

    B(int mrk1 , int mrk2 , int mrk3 , int mrk4) 
    {
        this.mrk1=mrk1;
        this.mrk2=mrk2;
        this.mrk3=mrk3;
        this.mrk4=mrk4;
    }

    @Override
    double getPercentage() 
    {
        return (mrk1 + mrk2 + mrk3 + mrk4) / 4.0;
    }
}

// main class
class MarksTest 
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);

        // student A
        System.out.println("Enter marks for Student A (3 subjects) :");
        int a1=sc.nextInt();
        int a2=sc.nextInt();
        int a3=sc.nextInt();
        Marks studentA=new A(a1,a2,a3);

        // student B
        System.out.println("Enter marks for Student B (4 subjects) :");
        int b1=sc.nextInt();
        int b2=sc.nextInt();
        int b3=sc.nextInt();
        int b4=sc.nextInt();
        Marks studentB=new B(b1,b2,b3,b4);

        // main output
        System.out.println("\nPercentage of Student A : " + studentA.getPercentage() + "%");
        System.out.println("Percentage of Student B : " + studentB.getPercentage() + "%");

        sc.close();
    }
}


// output exxample

/* 

Enter marks for Student A (3 subjects) :
50
78
40
Enter marks for Student B (4 subjects) :
84
73
26
20

Percentage of Student A : 56.0%
Percentage of Student B : 50.75%

*/