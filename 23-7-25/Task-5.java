/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [23-07-25]
 | Program Definition : 5. Write a java program to sort an arrary of integers in decresing order without using built-in funtion sorting funtions 
 -------------------------------------------------------------*/

import java.util.Scanner;
class SortArrayDescending 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array : ");
        int n = scanner.nextInt();
        int[] array = new int[n];

        System.out.println("Enter the elements of the array:");


        for (int a = 0; a < n; a++) 
        {
            array[a] = scanner.nextInt();
        }

        // sorting logic
        for (int a = 0; a < n - 1; a++) 
        {
            for (int b = a + 1; b < n; b++) 
            {
                if (array[a] < array[b]) { // change to > for increasing order

                    // swap elements
                    int temp = array[a];
                    array[a] = array[b];
                    array[b] = temp;
                }
            }
        }

        System.out.println("Sorted array in descending order :");
        
        for (int num : array) 
        {
            System.out.print(num + " ");
        }

        scanner.close();
    }
}