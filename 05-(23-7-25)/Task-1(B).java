/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [18-07-25]
 | Program Definition : 1. Sum all values in arrary in java 
  - Multi dimensional in java
 -------------------------------------------------------------*/

import java.util.Scanner;
class MultiDimensionalArraySum 
{
    public static void main(String[] args)  
    {
        Scanner scanner=new Scanner(System.in);

        System.out.print("Enter the number of rows in the array : ");
        int rows=scanner.nextInt();
        System.out.print("Enter the number of columns in the array : ");
        int cols=scanner.nextInt();

        int[][] array=new int[rows][cols];
        System.out.println("Enter the elements of the array :");

        for (int a=0; a<rows; a++) 
        {
            for (int b=0; b<cols; b++) 
            {
                array[a][b]=scanner.nextInt();
            }
        }

        int sum=0;

        for (int a=0; a<rows; a++) 
        {
            for (int b=0; b<cols; b++) 
            {
                sum+=array[a][b];
            }
        }
        
        System.out.println("The sum of the array elements is : " + sum);
    }
}

// Output Example

/* 
Enter the number of rows in the array : 3
Enter the number of columns in the array : 3
Enter the elements of the array :
1
2
3 
4
5
6
7
8
9
The sum of the array elements is: 45 
*/