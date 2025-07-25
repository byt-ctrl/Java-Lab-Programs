/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [18-07-25]
 |
 | Program Definition : 4. write a program to create a resturant billing as describe below.
                    it will show a menu option first allow you to choose action to perform

 i.e ******************************************************
      *************** Om's Resttaurant ******************
    ********************************************************
    ***********                                  ***********
    ***********     1. Menu Card                 ***********
    ***********     2. Add Other's Item Details  ***********
    ***********     3. Print Bill                ***********
    ***********     4. Exit                      ***********
    ***********                                  ***********
    ********************************************************
    ********************************************************
    ********************************************************
 -------------------------------------------------------------*/



import java.util.Scanner;


class RestaurantSystem {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // store up to 10 items manually
        String item1 = "", item2 = "", item3 = "", item4 = "", item5 = "";
        String item6 = "", item7 = "", item8 = "", item9 = "", item10 = "";

        int price1 = 0, price2 = 0, price3 = 0, price4 = 0, price5 = 0;
        int price6 = 0, price7 = 0, price8 = 0, price9 = 0, price10 = 0;

        int total = 0;
        int itemCount = 0;

        boolean running = true;

        while (running) {
            System.out.println("********************************************************");
            System.out.println("*****************  Om's Restaurant  ********************");
            System.out.println("********************************************************");
            System.out.println("*                                                      *");
            System.out.println("*                  1. Menu Card                        *");
            System.out.println("*                  2. Add Item to Bill                 *");
            System.out.println("*                  3. Print Bill                       *");
            System.out.println("*                  4. Remove Last Item                 *");
            System.out.println("*                  5. Exit                             *");
            System.out.println("*                                                      *");
            System.out.println("********************************************************");
            System.out.println("********************************************************");
            System.out.println("********************************************************");

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // clear buffer

            if (choice == 1) 
            {
                System.out.println("\n********** Menu Card **********");
                System.out.println("1. Pizza      - Rs.150");
                System.out.println("2. Burger     - Rs. 80");
                System.out.println("3. Sandwich   - Rs. 60");
                System.out.println("4. Coke       - Rs. 40");
                System.out.println("5. French Fry - Rs. 70");
                System.out.println("6. Tea        - Rs. 20");
                System.out.println("7. Coffee     - Rs. 30");
                System.out.println("*******************************\n");
            }

            else if (choice == 2) 
            {
                if (itemCount >= 10) 
                {
                    System.out.println("You cannot add more than 10 items.");
                    continue;
                }

                System.out.print("Enter item name : ");
                String name = input.nextLine();
                System.out.print("Enter item price : ");
                int price = input.nextInt();

                itemCount++ ;

                if (itemCount == 1) 
                {
                    item1 = name;
                    price1 = price;
                } 
                else if (itemCount == 2) 
                {
                    item2 = name;
                    price2 = price;
                } 
                else if (itemCount == 3) 
                {
                    item3 = name;
                    price3 = price;
                } 
                else if (itemCount == 4) 
                {
                    item4 = name;
                    price4 = price;
                } 
                else if (itemCount == 5) 
                {
                    item5 = name;
                    price5 = price;
                } 
                else if (itemCount == 6) 
                {
                    item6 = name;
                    price6 = price;
                } 
                else if (itemCount == 7) 
                {
                    item7 = name;
                    price7 = price;
                } 
                else if (itemCount == 8) 
                {
                    item8 = name;
                    price8 = price;
                } 
                else if (itemCount == 9) 
                {
                    item9 = name;
                    price9 = price;
                } 
                else if (itemCount == 10) 
                {
                    item10 = name;
                    price10 = price;
                }

                total = price1 + price2 + price3 + price4 + price5 +
                        price6 + price7 + price8 + price9 + price10;

                System.out.println("Item added successfully !\n");
            }

            else if (choice == 3) 
            {
                System.out.println("\n*********** Your Bill ***********");
                if (price1 > 0) System.out.println("1. " + item1 + " - Rs." + price1);
                if (price2 > 0) System.out.println("2. " + item2 + " - Rs." + price2);
                if (price3 > 0) System.out.println("3. " + item3 + " - Rs." + price3);
                if (price4 > 0) System.out.println("4. " + item4 + " - Rs." + price4);
                if (price5 > 0) System.out.println("5. " + item5 + " - Rs." + price5);
                if (price6 > 0) System.out.println("6. " + item6 + " - Rs." + price6);
                if (price7 > 0) System.out.println("7. " + item7 + " - Rs." + price7);
                if (price8 > 0) System.out.println("8. " + item8 + " - Rs." + price8);
                if (price9 > 0) System.out.println("9. " + item9 + " - Rs." + price9);
                if (price10 > 0) System.out.println("10. " + item10 + " - Rs." + price10);
                System.out.println("*********************************");
                System.out.println("Total Amount: Rs." + total + "\n");
            }

            else if (choice == 4) 
            {
                if (itemCount == 0) 
                {
                    System.out.println("No items to remove.");
                } 
                
                else 
                {
                    System.out.println("Removing last added item...");

                    if (itemCount == 10) 
                    {
                        total -= price10;
                        item10 = "";
                        price10 = 0;
                    } 
                    else if (itemCount == 9) 
                    {
                        total -= price9;
                        item9 = "";
                        price9 = 0;
                    } 
                    else if (itemCount == 8) 
                    {
                        total -= price8;
                        item8 = "";
                        price8 = 0;
                    } 
                    else if (itemCount == 7) 
                    {
                        total -= price7;
                        item7 = "";
                        price7 = 0;
                    } 
                    else if (itemCount == 6) 
                    {
                        total -= price6;
                        item6 = "";
                        price6 = 0;
                    } 
                    else if (itemCount == 5) 
                    {
                        total -= price5;
                        item5 = "";
                        price5 = 0;
                    } 
                    else if (itemCount == 4) 
                    {
                        total -= price4;
                        item4 = "";
                        price4 = 0;
                    } 
                    else if (itemCount == 3) 
                    {
                        total -= price3;
                        item3 = "";
                        price3 = 0;
                    } 
                    else if (itemCount == 2) 
                    {
                        total -= price2;
                        item2 = "";
                        price2 = 0;
                    } 
                    else if (itemCount == 1) 
                    {
                        total -= price1;
                        item1 = "";
                        price1 = 0;
                    }

                    itemCount--;
                    System.out.println("Item removed.\n");
                }
            }

            else if (choice == 5) 
            {
                System.out.println("Thank you for visiting Om's Restaurant. Goodbye!");
                running = false;
            }

            else 
            {
                System.out.println("Invalid choice. Please enter 1-5 only.\n");
            }
        }

        input.close();
    }
}
