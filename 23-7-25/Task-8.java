/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [23-07-25]
 | Program Definition : 8. Create a progarm that will create an online shopping basket for 
 |                     you to print the details format with the basket totals using Array
 -------------------------------------------------------------*/

import java.util.Scanner;

class ShoppingBasket 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items in the basket : ");

        int n=sc.nextInt();
        String[] items=new String[n];
        double[] prices=new double[n];
        int[] quantities=new int[n];

        // input item details
        for (int a = 0; a < n; a++) 
        {
            System.out.print("Enter item name : ");
            items[a]=sc.next();
            System.out.print("Enter item price : ");
            prices[a]=sc.nextDouble();
            System.out.print("Enter item quantity : ");
            quantities[a]=sc.nextInt();
        }

        // print basket details
        System.out.println("\nShopping Basket Details :");
        double total=0;

        for (int a=0; a<n; a++) 
        {
            double itemTotal = prices[a] * quantities[a];
            System.out.printf("%s - Price : %.2f, Quantity : %d, Total : %.2f\n", items[a], prices[a], quantities[a], itemTotal);
            total += itemTotal;
        }
        System.out.printf("\nTotal Basket Amount : %.2f\n",total);

        sc.close();
    }
}


