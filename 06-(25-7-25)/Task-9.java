/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [25-07-25]
 | Program Definition : 9. Write java program to display common elements in two array
 -------------------------------------------------------------*/

import java.util.Scanner;
class CommonElements 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of first array : ");
        int n1 = sc.nextInt();
        int[] arr1 = new int[n1];

        System.out.print("Enter elements of first array : ");
        for(int a=0; a<n1; a++) arr1[a] = sc.nextInt();

        System.out.print("Enter size of second array : ");
        int n2 = sc.nextInt();
        int[] arr2 = new int[n2];

        System.out.print("Enter elements of second array : ");
        for(int a=0; a<n2; a++) arr2[a] = sc.nextInt();

        System.out.print("Common elements are : ");

        for(int a=0; a<n1; a++) 
        {
            for(int b=0;b<n2;b++) 
            {
                if(arr1[a]==arr2[b]) 
                {
                    System.out.print(arr1[a] + " ");
                    break;
                }
            }
        }
    }
}

// Output Example
/*
Enter size of first array : 5
Enter elements of first array : 1
2
3
4
5
Enter size of second array : 5
Enter elements of second array : 3
4
5
6
7
Common elements are : 3 4 5
*/
