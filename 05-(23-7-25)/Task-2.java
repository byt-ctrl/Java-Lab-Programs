/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [23-07-25]
 | Program Definition : 2. Find Max and MIN in an arrary in java
 -------------------------------------------------------------*/

import java.util.Scanner;

class ArrayMinMax  
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements : ");
        int n=sc.nextInt();
        int[] numbers=new int[n];

        System.out.println("Enter " + n + " elements :");

        for (int a=0; a<n; a++) 
        {
            numbers[a]=sc.nextInt();
        }

        int min=numbers[0];
        int max=numbers[0];


/* Main minimum , maximum logic */
        for (int a=1; a<numbers.length; a++) 
        {
            if (numbers[a]<min) 
            {
                min=numbers[a];
            }
            if (numbers[a]>max) 
            {
                max=numbers[a];
            }
        }



        System.out.println("Minimum element : " + min);
        System.out.println("Maximum element : " + max);

        sc.close();
    }
}


 // Output Example

 /* 
Enter number of elements : 5
Enter 5 elements :
5
8
9
7
5
Minimum element : 5
Maximum element : 9 
*/