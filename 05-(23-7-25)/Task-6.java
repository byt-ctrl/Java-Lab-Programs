/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [23-07-25]
 | Program Definition : 6. Insert an element in Array
 -------------------------------------------------------------*/

import java.util.Scanner;
class InsertElementArray 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size : ");
        int n = sc.nextInt();
        int[] array = new int[n + 1] ;

        System.out.println("Enter " + n + " elements :");

        for (int a = 0; a < n; a++) array[a] = sc.nextInt();

        System.out.print("Enter element to insert : ");
        int element = sc.nextInt();
        System.out.print("Enter position (0 to " + n + ") : ");
        int pos = sc.nextInt();

        // shift elements to the right

        for (int a = n; a > pos; a--) array[a] = array[a - 1];
        array[pos] = element;

        System.out.println("Array after insertion :");
        for (int a = 0; a <= n; a++) System.out.print(array[a] + " ");

        sc.close();
    }
}

// Output Example

/*

Enter array size : 5
Enter 5 elements :
2
3
4
5
6
Enter element to insert : 7
Enter position (0 to 5) : 3
Array after insertion :
2 3 4 7 5 6  

*/