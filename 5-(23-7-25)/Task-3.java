/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [23-07-25]
 | Program Definition : 3. reverse an arrary in java
 -------------------------------------------------------------*/

import java.util.Scanner;

class ReverseArray 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter " + n + " elements:");


        for (int a = 0; a < n; a++) 
        {
            arr[a] = sc.nextInt();
        }

        System.out.println("Reversed array :");

        for (int a = n - 1; a >= 0; a--) 
        {
            System.out.print(arr[a] + ",");
           
        }



        sc.close();
    }
}

// Output Example

/* 
Enter number of elements: 5
Enter 5 elements:
8
7
4
3
6
Reversed array :
6,3,4,7,8, 
*/