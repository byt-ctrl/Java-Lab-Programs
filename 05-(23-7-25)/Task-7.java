/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [23-07-25]
 | Program Definition : 7. Remove specific element from Array
 -------------------------------------------------------------*/

import java.util.Scanner;
class RemoveElementArray 
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter array size : ");
        int n=sc.nextInt();
        int[] array=new int[n];

        System.out.println("Enter " + n + " elements :");

        for (int a=0; a<n; a++) array[a]=sc.nextInt();

        System.out.print("Enter element to remove : ");
        int element=sc.nextInt();

        System.out.println("Array after removal :");
        

        for (int a=0; a<n; a++) 
        {
            if (array[a] != element) System.out.print(array[a] + " ");
        }
        sc.close();
    }
}

// Output Example

/*
Enter array size : 5
Enter 5 elements :
1
2
3
4
5
Enter element to remove : 3
Array after removal :
1 2 4 5
*/
