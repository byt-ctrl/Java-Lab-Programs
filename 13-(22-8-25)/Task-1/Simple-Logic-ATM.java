// simplest ATM logic possible in java

import java.util.Scanner;

class ATM 
{
    private static double balance=25000.0; // initial balance for simplicity
    private static final String PIN="1234"; // fixed PIN for demonstration

    public static void main(String[] args) 
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("========= Welcome to the ATM =========");

        // PIN verification
        System.out.print("Enter your PIN : ");
        String enteredPin=scanner.nextLine();
        if (!enteredPin.equals(PIN)) 
        {
            System.out.println("Incorrect PIN. Exiting.");
            return;
        }

        while (true) 
        {
            System.out.println("\nChoose an option :");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice)  
            {
                case 1 :
                    checkBalance();
                    break;
                case 2 :
                    withdraw(scanner);
                    break;
                case 3 :
                    deposit(scanner);
                    break;
                case 4 :
                    System.out.println("Thank you for using the ATM. Goodbye !");
                    return;
                default :
                    System.out.println("Invalid choice. Please try again.");
            } 
        }
    }

    private static void checkBalance() 
    {
        System.out.println("Your current balance is : rs " + balance);
    }

    private static void withdraw(Scanner scanner) 
    {
        System.out.print("Enter amount to withdraw : rs ");
        double amount=scanner.nextDouble();

        if (amount<=0) 
        {
            System.out.println("Invalid withdrawal amount.");
        } 
        else if (balance>=amount) 
        {
            balance-=amount;
            System.out.println("Withdrawal successful. New balance : rs " + balance);
        } 
        else 
        {
            System.out.println("Insufficient funds.");
        }
    }

    private static void deposit(Scanner scanner) 
    {
        System.out.print("Enter amount to deposit : rs");
        double amount=scanner.nextDouble();

        if (amount<=0) 
        {
            System.out.println("Invalid deposit amount.");
        } 
        else 
        {
            balance+=amount;
            System.out.println("Deposit successful. New balance : rs" + balance);
        }
    }
}




// output example

/* 

========= Welcome to the ATM =========
Enter your PIN : 1234

Choose an option :
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Enter your choice: 1
Your current balance is : rs 25000.0

Choose an option :
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Enter your choice: 2
Enter amount to withdraw : rs 5000
Withdrawal successful. New balance : rs 20000.0

Choose an option :
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Enter your choice: 1
Your current balance is : rs 20000.0

Choose an option :
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Enter your choice: 3
Enter amount to deposit : rs3000
Deposit successful. New balance : rs23000.0

Choose an option :
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Enter your choice: 1
Your current balance is : rs 23000.0

Choose an option :
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Enter your choice: 2
Enter amount to withdraw : rs 25000
Insufficient funds.

Choose an option :
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Enter your choice: 2
Enter amount to withdraw : rs -1000
Invalid withdrawal amount.

Choose an option :
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Enter your choice: 2
Enter amount to withdraw : rs 0
Invalid withdrawal amount.

Choose an option :
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Enter your choice: 3
Enter amount to deposit : rs-500
Invalid deposit amount.

Choose an option :
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Enter your choice: 3
Enter amount to deposit : rs0
Invalid deposit amount.

Choose an option :
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Enter your choice: 5
Invalid choice. Please try again.

Choose an option :
1. Check Balance
2. Withdraw
3. Deposit
4. Exit
Enter your choice: abc
Exception in thread "main" java.util.InputMismatchException
	at java.util.Scanner.throwFor(Scanner.java:864)
	at java.util.Scanner.next(Scanner.java:1485)
	at java.util.Scanner.nextInt(Scanner.java:2117)
	at java.util.Scanner.nextInt(Scanner.java:2076)
	at ATM.main(ATM.java:32)

========= Welcome to the ATM =========
Enter your PIN : 5678
Incorrect PIN. Exiting.

*/