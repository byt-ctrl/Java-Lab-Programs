/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [25-07-25]
 | Program Definition : 10. Write java program to display array is palidrome or not
 -------------------------------------------------------------*/

import java.util.Scanner;

class ArrayPalindrome 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of array : ");

        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter array elements : ");
        for(int a=0; a<n; a++) arr[a] = sc.nextInt();
        boolean isPalindrome = true;
        
        for(int a=0; a<n/2; a++) 
        {
            if(arr[a] != arr[n-1-a]) 
            {
                isPalindrome=false;
                break;
            }
        }
        System.out.println(isPalindrome ? "Array is palindrome." : "Array is not palindrome.");
    }
}

// Output Example

/*  
Enter size of array : 5
Enter array elements : 1
2
3
2
1
Array is palindrome.
 */