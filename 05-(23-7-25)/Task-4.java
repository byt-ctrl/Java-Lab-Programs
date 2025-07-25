/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [23-07-25]
 | Program Definition : 4. Java Array program to find transpose in java
 -------------------------------------------------------------*/

import java.util.Scanner;

class ArrayTranspose 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows : ");
        int rows=sc.nextInt();
        System.out.print("Enter number of columns : ");
        int cols=sc.nextInt();

        int[][] original=new int[rows][cols];
        int[][] transpose=new int[cols][rows];

        System.out.println("Enter elements of the array :");

        for (int a=0; a<rows; a++) 
        {
            for (int b=0; b<cols; b++) 
            {
                original[a][b]=sc.nextInt();
            }
        }

        // transpose logic
        for (int a=0; a<rows; a++) 
        {
            for (int b=0; b<cols; b++) 
            {
                transpose[b][a]=original[a][b];
            }
        }

        System.out.println("Transposed array :");
        for (int a=0; a<cols; a++) 
        {
            for (int b=0; b<rows; b++) 
            {
                System.out.print(transpose[a][b] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}

// Output Example

/*

Enter number of rows : 2
Enter number of columns : 3
Enter elements of the array :
1
2
3
4
5
6
Transposed array :
1 4
2 5
3 6

*/