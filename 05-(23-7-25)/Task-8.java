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
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter number of items in the basket: ");
        int n=sc.nextInt();

        String[] items=new String[n];
        double[] prices=new double[n];
        int[] quantities=new int[n];

        // input item details
        for (int a=0; a<n; a++) 
        {
            System.out.printf("\nItem %d :\n", a + 1);
            System.out.print("Enter item name     : ");
            items[a]=sc.next();
            System.out.print("Enter item price    : ");
            prices[a]=sc.nextDouble();
            System.out.print("Enter item quantity : ");
            quantities[a]=sc.nextInt();
        }

        // print's basket details in table format

        System.out.println("\n============================== Shopping Basket ==============================");
        System.out.printf("%-20s %10s %10s %15s\n", "Item Name", "Price", "Quantity", "Item Total");
        System.out.println("----------------------------------------------------------------------------");

        double total=0;
        for (int a=0; a<n; a++) 
        {
            double itemTotal=prices[a]*quantities[a];
            System.out.printf("%-20s %10.2f %10d %15.2f\n",items[a],prices[a],quantities[a],itemTotal);
            total+=itemTotal;
        }

        System.out.println("----------------------------------------------------------------------------");
        System.out.printf("%-42s %15.2f\n", "Total Basket Amount:",total);
        System.out.println("=============================================================================");

        sc.close();
    }
}


// Output Example


/* 
Enter number of items in the basket: 2

Item 1 :
Enter item name     : apple
Enter item price    : 200
Enter item quantity : 2

Item 2 :
Enter item name     : kiwi
Enter item price    : 150
Enter item quantity : 3

============================== Shopping Basket ==============================
Item Name                 Price   Quantity      Item Total
----------------------------------------------------------------------------
apple                    200.00          2          400.00
kiwi                     150.00          3          450.00
----------------------------------------------------------------------------
Total Basket Amount:                                850.00
=============================================================================
*/