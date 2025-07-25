/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [23-07-25]
 | Program Definition : 1. Sum all values in arrary in java 
  - One dimensional  in java
 -------------------------------------------------------------*/
import java.util.Scanner;

class OneDimensionalArraySum {
    public static void main(String[] args)  
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array : ");

        int n = scanner.nextInt();
        int array[] = new int[n];
        
        System.out.println("Enter the elements of the array:");


        for (int a = 0; a < n; a++) 
        {
            array[a] = scanner.nextInt();
        }
        int sum = 0 ;
        
        for (int a = 0; a < n; a++) 
        {
            sum += array[a];
        }
        System.out.println("The sum of the array elements is : " + sum);
    }
}

// Output Example

/* Enter the number of elements in the array : 5
Enter the elements of the array:
2
4
8
7
9
The sum of the array elements is : 30 
*/