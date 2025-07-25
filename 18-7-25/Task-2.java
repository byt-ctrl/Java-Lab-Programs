
/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [18-07-25]
 | Program Definition : 2. Write  a program to print the multiplication table of given number using loop 
 -------------------------------------------------------------*/

 import java.util.Scanner;

class MultiplicationTable {
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to print its multiplication table : ");
        int number = scanner.nextInt();   
        scanner.close();

        System.out.println("Multiplication Table of " + number + ":");

        for (int a = 1; a <= 10; a++) 
        {
            int result = number * a;
            System.out.println(number + " X " + a + " = " + result);
        }
    }
}
