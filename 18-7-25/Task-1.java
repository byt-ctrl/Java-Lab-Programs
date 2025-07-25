
/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [18-07-25]
 | Program Definition : 1. Write a java program to find the factorial of the number in java
 -------------------------------------------------------------*/

 import java.util.Scanner;

class Factorial {
    public static void main(String[] args) 
    {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter a non-negative integer : ");
        int num = scanner.nextInt();
        scanner.close();

        if (num < 0) 
        {
            System.out.println("Factorial is not defined for negative numbers.");
        } 
        else 
        {
            long factorial = 1;
            for (int a = 1; a <= num; a++) 
            {
                factorial *= a;
            }
            System.out.println("Factorial of " + num + " is : " + factorial);
        }
    }
}
