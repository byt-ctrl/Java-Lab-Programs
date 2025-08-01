/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [30-07-25]
 | Program Definition : 4. Write a Program to demonstrate the bank funtionalities such as create account , deposits amount , withdraw amount, display balance , simple interest for 2 yrs
 |                      Note :- Please use constructor in java 
 -------------------------------------------------------------*/

import java.util.Scanner;

class BankAccount 
{
    String name;
    double balance;

    BankAccount(String name, double initialBalance) 
    {
        this.name = name;
        this.balance = initialBalance;
        System.out.println("\nAccount Created Successfully!");
        System.out.println("Account Holder : " + name);
        System.out.printf("Initial Balance : %.2f\n", balance);
    }

    void deposit(double amount) 
    {
        balance+=amount;
        System.out.printf("Deposited : %.2f\n", amount);
        System.out.printf("Balance after deposit: %.2f\n", balance);
    }

    void withdraw(double amount) 
    {
        if (amount <= balance) 
        {
            balance -= amount;
            System.out.printf("Withdrawn : %.2f\n", amount);
            System.out.printf("Balance after withdrawal : %.2f\n",balance);
        } else 
        {
            System.out.println("Insufficient balance !!!");
        }
    }

    void displayBalance() 
    {
        System.out.printf("Current Balance : %.2f\n", balance);
    }

    void simpleInterest(double rate) 
    {
        double si = (balance * rate * 2) / 100;
        System.out.printf("Simple Interest for 2 years at %.2f%% : %.2f\n",rate,si);
    }
}

class DemoBank 
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("--------- Welcome to Simple Bank ---------");
        System.out.print("Enter your name : ");
        String name = sc.nextLine();
        System.out.print("Enter initial balance : ");
        double bal = sc.nextDouble();

        BankAccount acc = new BankAccount(name,bal);

        int choice;
        do {
            System.out.println("\nChoose an option :");
            System.out.println("1. Deposit Amount");
            System.out.println("2. Withdraw Amount");
            System.out.println("3. Display Balance");
            System.out.println("4. Calculate Simple Interest for 2 years");
            System.out.println("5. Exit");
            System.out.print("Enter your choice : ");
            choice = sc.nextInt();

            switch(choice) 
            {
                case 1:
                    System.out.print("Enter deposit amount : ");
                    double dep = sc.nextDouble();
                    acc.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter withdraw amount : ");
                    double wd = sc.nextDouble();
                    acc.withdraw(wd);
                    break;
                case 3:
                    acc.displayBalance();
                    break;
                case 4:
                    System.out.print("Enter interest rate : ");
                    double rate = sc.nextDouble();
                    acc.simpleInterest(rate);
                    break;
                case 5:
                    System.out.println("\n--------- Thank you for using Simple Bank ---------");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while(choice != 5);
    }
}

// output example of all five functionalities

/*

--------- Welcome to Simple Bank ---------
Enter your name : Om Patel
Enter initial balance : 50000
Account Created Successfully!
Account Holder : Om Patel
Initial Balance : 50000.00


Choose an option :
1. Deposit Amount
2. Withdraw Amount
3. Display Balance
4. Calculate Simple Interest for 2 years
5. Exit
Enter your choice : 1
Enter deposit amount : 10000
Deposited : 10000.00
Balance after deposit: 60000.00


Choose an option :
1. Deposit Amount
2. Withdraw Amount
3. Display Balance
4. Calculate Simple Interest for 2 years
5. Exit
Enter your choice : 2
Enter withdraw amount : 20000
Withdrawn : 20000.00
Balance after withdrawal: 40000.00


Choose an option :
1. Deposit Amount
2. Withdraw Amount
3. Display Balance
4. Calculate Simple Interest for 2 years
5. Exit
Enter your choice : 3
Current Balance: 40000.00


Choose an option :
1. Deposit Amount
2. Withdraw Amount
3. Display Balance
4. Calculate Simple Interest for 2 years
5. Exit
Enter your choice : 4
Enter interest rate : 5
Simple Interest for 2 years at 5.00% : 4000.00


Choose an option :
1. Deposit Amount
2. Withdraw Amount
3. Display Balance
4. Calculate Simple Interest for 2 years
5. Exit
Enter your choice : 5
--------- Thank you for using Simple Bank ---------

*/
