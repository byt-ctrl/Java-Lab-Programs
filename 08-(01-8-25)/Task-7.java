/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [01-08-25]
 | Program Definition : 7. Static variables and instance variables
 |  -> Create a student class with instance variablesi.e Student name , Roll Number & DOB ,
 |  and static variable a.e collegeName.
 |  -> Create a static method to change the college name and roll number. Observe how chnages to it affect all instances.
 |  -> Include a static int counter that keeps track of the number of the Student objects created.
 |  -> Initialize counter in a static block and increment it in te consructor.
 |  -> Display the counter value in the main method.
 |  -> Write a non static method to access the static variable into the same .  (all must be user input)
 ------------------------------------------------------------*/

import java.util.Scanner;

class Student 
{
    // instance variables
    String name;
    int rollNumber;
    String dob;

    // static variables
    static String collegeName;
    static int counter;

    // static block to initialize counter
    static 
    {
        counter=0;
        collegeName="Navrachana University";
    }

    // constructor
    Student(String name,int rollNumber,String dob) 
    {
        this.name=name;
        this.rollNumber=rollNumber;
        this.dob=dob;
        counter++; // increment on each object creation
    }

    // static method to change college name
    static void changeCollegeName(String newCollegeName) 
    {
        collegeName=newCollegeName;
    }

    // non-static method to display student info and access static variable
    void displayInfo() 
    {
        System.out.println("Name : " + name);
        System.out.println("Roll Number : " + rollNumber);
        System.out.println("DOB : " + dob);
        System.out.println("College Name : " + collegeName); // accessing static variable
        System.out.println("----------------------");
    }

    // main method
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter number of students : ");
        int n=sc.nextInt();
        sc.nextLine(); // consume newline

        // optional : change college name first
        System.out.print("Enter new college name : ");
        String newCollege=sc.nextLine();
        Student.changeCollegeName(newCollege);

        // create array of students
        Student[] students=new Student[n];

        for (int a=0; a<n; a++) 
        {
            System.out.println("\nEnter details for Student " + (a + 1));
            System.out.print("Name : ");
            String name=sc.nextLine();

            System.out.print("Roll Number : ");
            int roll=sc.nextInt();
            sc.nextLine(); // consume newline

            System.out.print("DOB (dd-mm-yyyy) : ");
            String dob=sc.nextLine();

            students[a]=new Student(name,roll,dob);
        }

        // display's all student info
        System.out.println("\n----- Student Details -----");
        for (Student s : students) {
            s.displayInfo();
        }

        // display counter
        System.out.println("Total number of students created : " + Student.counter);
        
        sc.close();
    }
}


// output example
/*
Enter number of students: 2
Enter new college name: MIT
Enter details for Student 1
Name : Om Patel
Roll Number : 24000112
DOB (dd-mm-yyyy) : 01-08-2000

Enter details for Student 2
Name : Roy Sharma
Roll Number : 24000113
DOB (dd-mm-yyyy) : 01-08-2001


----- Student Details -----
Name : Om Patel
Roll Number : 24000112
DOB : 01-08-2000
College Name : MIT
----------------------
Name : Roy Sharma
Roll Number : 24000113
DOB : 01-08-2001
College Name : MIT
----------------------
Total number of students created : 2
*/