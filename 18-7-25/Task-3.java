/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [18-07-25]
 | Program Definition : 3. write a program to print the  pattern as given below for given number
 {
    1
    12
    123
    1234 wtc as per user input 
 }
 -------------------------------------------------------------*/

import java.util.Scanner;

class NumberPattern 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to generate the pattern : ");
        int n = scanner.nextInt();
        scanner.close();

        for (int a = 1; a <= n; a++) 
        {
            for (int b = 1; b <= a; b++) 
            {
                System.out.print(b);
               
            }
            System.out.println(); // next line moving 
        }
    }
}
