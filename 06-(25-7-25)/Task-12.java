/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [25-07-25]
 | Program Definition : 
 |
 |   12. create a following arrays to print each student marksheet 
 |     
 |   MAIN CRITERIA's 
 |     -> User input , and user input of how many students also at starting etc
 |     -> enrollemnt no , student name ,  its result , student descriptions like subject description for english maths etc , we have to make in all things tabular formatting,
 |   tabular marksheetwise , 
 |
 |     ->Name of university
 |     ->grades (A (above 80) , A- (70 above) ,A+ (90 above),B (60 above ),B+ (70 above),B- (50 above ),C for pass i.e 40 above ) , Pass or Fail criteria etc
 |     ->Date of result etc 
 |     -> Proper formatting of tabular marksheet
 |     -> Option to view individual marksheet 
 |
 |
 |   Final apporch (make code using arrays (1D and 2D array) for storing details , and make porper tabular format for printing all the details)
 -------------------------------------------------------------*/




import java.util.Scanner;

class StudentMarksheetApp 
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);

        // university and result date
        String university="Navrcahna University";
        String resultDate="25-07-2025"; // static date (can be change as per need)

        // subjects
        String[] subjects={"English","Maths","Science","Computer","Hindi"};
        int subjectCount=subjects.length;

        // input number of students
        System.out.print("Enter total number of students : ");
        int n=sc.nextInt();
        sc.nextLine(); // clear input buffer

        // declare arrays
        String[] names=new String[n];
        String[] enroll=new String[n];
        int[][] marks=new int[n][subjectCount];
        int[] totalMarks=new int[n];
        float[] percent=new float[n];
        String[] grade=new String[n];
        String[] result=new String[n];

        // input for each student
        for (int a=0; a<n; a++) 
        {
            System.out.println("\n----- Enter Details for Student " + (a+1) + " -----");

            System.out.print("Enrollment Number : ");
            enroll[a]=sc.nextLine().trim();

            System.out.print("Name : ");
            names[a]=sc.nextLine().trim();

            int total=0;
            boolean pass=true;

            for (int b=0; b<subjectCount; b++) 
            {
                int mark=-1;
                while (mark<0 || mark>100) 
                {
                    System.out.print("Marks in " + subjects[b] + " (0-100) : ");
                    mark=sc.nextInt();
                    if (mark<0 || mark>100) 
                    {
                        System.out.println("Invalid marks !! Please enter between 0 and 100.");
                    }
                }
                marks[a][b]=mark;
                total+=mark;
                if (mark<40) 
                {
                    pass=false;
                }
            }

            sc.nextLine(); // clear buffer

            totalMarks[a]=total;
            percent[a]=total/(float) subjectCount;

            // grade calculation
            if (percent[a]>=90) grade[a]="A+";
            else if (percent[a]>=80) grade[a]="A";
            else if (percent[a]>=70) grade[a]="A-";
            else if (percent[a]>=60) grade[a]="B";
            else if (percent[a]>=50) grade[a]="B-";
            else if (percent[a]>=40) grade[a]="C";
            else grade[a]="F";

            result[a]=pass ? "PASS" : "FAIL";
        }

        // menu-driven system
        int choice;
        do {
            System.out.println("\n============= MENU =============");
            System.out.println("1. View All Students' Marksheets");
            System.out.println("2. View Individual Marksheet");
            System.out.println("3. Exit");
            System.out.print("Enter your choice : ");
            choice=sc.nextInt();
            sc.nextLine(); // clear buffer

            if (choice==1) 
            {
                System.out.println("\n==============================================================================================================================");
                System.out.println("                                 " + university);
                System.out.println("                                 Result Date: " + resultDate);
                System.out.println("==============================================================================================================================");
                System.out.printf("%-15s %-20s", "Enrollment No","Name");
                for (String subject : subjects) 
                {
                    System.out.printf("%-10s", subject);
                }
                System.out.printf("%-12s %-10s %-8s %-8s\n", "Total","Percent","Grade","Result");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------");
                for (int a=0; a<n; a++) 
                {
                    System.out.printf("%-15s %-20s",enroll[a],names[a]);
                    for (int b=0; b<subjectCount; b++) 
                    {
                        System.out.printf("%-10d",marks[a][b]);
                    }
                    System.out.printf("%-12d %-10.2f %-8s %-8s\n",totalMarks[a],percent[a],grade[a],result[a]);
                }
                System.out.println("==============================================================================================================================");

            } 
            
            else if (choice==2) 
            {
                // individual student marksheet
                System.out.print("Enter Enrollment Number to view : ");
                String searchEnroll=sc.nextLine().trim();   // search for enrollment number (taken help from online for this concept)
                boolean found=false;

                for (int a=0; a<n; a++) 
                {
                    if (enroll[a].equalsIgnoreCase(searchEnroll))  
                    // the (equalsIgnoreCase) concept is taken and learned from online , used to avoid case sensitivity
                    {
                        found=true;
                        System.out.println("\n=======================================================");
                        System.out.println("                 " + university);
                        System.out.println("                 Student Marksheet");
                        System.out.println("=======================================================");
                        System.out.println("Result Date   : " + resultDate);
                        System.out.println("Enrollment No : " + enroll[a]);
                        System.out.println("Name          : " + names[a]);
                        System.out.println("-------------------------------------------------------");
                        System.out.printf("%-15s : %s\n", "Subject", "Marks");
                        System.out.println("-------------------------------------------------------");
                        for (int b=0; b<subjectCount; b++) 
                        {
                            System.out.printf("%-15s : %d\n",subjects[b],marks[a][b]);
                        }
                        System.out.println("--------------------------------------------------------");
                        System.out.printf("Total Marks     : %d\n",totalMarks[a]);
                        System.out.printf("Percentage      : %.2f%%\n",percent[a]);
                        System.out.println("Grade           : " + grade[a]);
                        System.out.println("Result          : " + result[a]);
                        System.out.println("=======================================================");
                        break;
                    }
                }

                if (!found) 
                {
                    System.out.println("Enrollment number not found !! ");
                }

            } 
            
            
            else if (choice==3) 
            {
                System.out.println("Thank you! Program exited.");
            } 
            else 
            {
                System.out.println("Invalid choice. Try again.");
            }

        } while (choice!=3);
    }
}



// Output Example

/*  
    Enter total number of students : 2

    ----- Enter Details for Student 1 -----
    Enrollment Number : 268462
    Name : Om
    Marks in English (0-100) : 58
    Marks in Maths (0-100) : 45
    Marks in Science (0-100) : 75
    Marks in Computer (0-100) : 95
    Marks in Hindi (0-100) : 62

    ----- Enter Details for Student 2 -----
    Enrollment Number : 143572
    Name : Roy
    Marks in English (0-100) : 75
    Marks in Maths (0-100) : 85
    Marks in Science (0-100) : 98
    Marks in Computer (0-100) : 48
    Marks in Hindi (0-100) : 57

    ============= MENU =============
    1. View All Students' Marksheets
    2. View Individual Marksheet
    3. Exit
    Enter your choice: 1

    ==============================================================================================================================
                                     Navrcahna University
                                     Result Date: 25-07-2025
    ==============================================================================================================================
    Enrollment No   Name                English   Maths     Science   Computer  Hindi     Total       Percent    Grade    Result
    ------------------------------------------------------------------------------------------------------------------------------
    268462          Om                  58        45        75        95        62        335         67.00      B        PASS    
    143572          Roy                 75        85        98        48        57        363         72.60      A-       PASS
    ===============================================================================================================================

    ============= MENU =============
    1. View All Students' Marksheets
    2. View Individual Marksheet
    3. Exit
    Enter your choice: 2
    Enter Enrollment Number to view : 143572

    =======================================================
                     Navrcahna University
                     Student Marksheet
    =======================================================
    Result Date   : 25-07-2025
    Enrollment No : 143572
    Name          : Roy
    -------------------------------------------------------
    Subject         : Marks
    -------------------------------------------------------
    English         : 75
    Maths           : 85
    Science         : 98
    Computer        : 48
    Hindi           : 57
    -------------------------------------------------------
    Total Marks     : 363
    Percentage      : 72.60%
    Grade           : A-
    Result          : PASS
    =======================================================

    ============= MENU =============
    1. View All Students' Marksheets
    2. View Individual Marksheet
    3. Exit
    Enter your choice: 3
    Thank you! Program exited.
*/


/* END OF PROGRAM */