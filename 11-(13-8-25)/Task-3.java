/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [13-08-25]
 | Program Definition : 3. Write a Java program to create an abstract class BankAccount with abstract methods deposit() and withdraw(). 
 |                         Create subclasses: SavingsAccount and CurrentAccount that extend the BankAccount class 
 |                         and implement the respective methods to handle deposits and withdrawals for each account type.
 -------------------------------------------------------------*/


import java.util.Scanner;

abstract class BankAccount 
{
    protected double balance;

    // abstract methods to be implemented in subclasses
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    
    // method to check account balance
    public void checkBalance() 
    {
        System.out.println("Current Balance: " + balance);
    }
}

class SavingsAccount extends BankAccount 
{

    public SavingsAccount() 
    {
        balance=0.0;
    }

    // implementation of deposit for SavingsAccount
    public void deposit(double amount) 
    {
        if (amount > 0) 
        {
            balance+=amount;
            System.out.println("Deposited " + amount + " to Savings Account.");
        } 
        else 
        {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // implementation of withdraw for SavingsAccount
    public void withdraw(double amount) 
    {
        if (amount > 0 && amount <= balance) 
        {
            balance -= amount;
            System.out.println("Withdrew " + amount + " from Savings Account.");
        } 
        else 
        {
            System.out.println("Insufficient balance or invalid withdrawal amount.");
        }
    }
}

class CurrentAccount extends BankAccount 
{

    public CurrentAccount() 
    {
        balance = 0.0;
    }

    // implementation of deposit for CurrentAccount
    public void deposit(double amount) 
    {
        if (amount > 0) 
        {
            balance += amount;
            System.out.println("Deposited " + amount + " to Current Account.");
        } 
        else 
        {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // implementation of withdraw for CurrentAccount
    public void withdraw(double amount) 
    {
        if (amount > 0 && amount <= balance) 
        {
            balance -= amount;
            System.out.println("Withdrew " + amount + " from Current Account.");
        } 
        else 
        {
            System.out.println("Insufficient balance or invalid withdrawal amount.");
        }
    }
}

class BankApp 
{
    public static void main(String[] args) 
    {
        Scanner scanner=new Scanner(System.in);
        BankAccount savings=new SavingsAccount();
        BankAccount current=new CurrentAccount();
        
        while (true) 
        {
            System.out.println("\n========= Bank Menu =========");
            System.out.println("1. Deposit to Savings Account");
            System.out.println("2. Withdraw from Savings Account");
            System.out.println("3. Deposit to Current Account");
            System.out.println("4. Withdraw from Current Account");
            System.out.println("5. Check Savings Account Balance");
            System.out.println("6. Check Current Account Balance");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int choice=scanner.nextInt();
            double amount;
            
            switch (choice) 
            {
                case 1:
                    System.out.print("Enter deposit amount for Savings Account: ");
                    amount=scanner.nextDouble();
                    savings.deposit(amount);
                    break;
                
                case 2 :
                    System.out.print("Enter withdrawal amount from Savings Account : ");
                    amount=scanner.nextDouble();
                    savings.withdraw(amount);
                    break;
                
                case 3 :
                    System.out.print("Enter deposit amount for Current Account : ");
                    amount=scanner.nextDouble();
                    current.deposit(amount);
                    break;
                
                case 4 :
                    System.out.print("Enter withdrawal amount from Current Account : ");
                    amount=scanner.nextDouble();
                    current.withdraw(amount);
                    break;
                
                case 5 :
                    savings.checkBalance();
                    break;
                
                case 6 :
                    current.checkBalance();
                    break;
                
                case 7 :
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                
                default :
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


// output example

/* 

 ========= Bank Menu =========
1. Deposit to Savings Account
2. Withdraw from Savings Account
3. Deposit to Current Account
4. Withdraw from Current Account
5. Check Savings Account Balance
6. Check Current Account Balance
7. Exit
Enter your choice: 1
Enter deposit amount for Savings Account: 1000
Deposited 1000.0 to Savings Account.

========= Bank Menu =========
1. Deposit to Savings Account
2. Withdraw from Savings Account
3. Deposit to Current Account
4. Withdraw from Current Account
5. Check Savings Account Balance
6. Check Current Account Balance
7. Exit
Enter your choice: 2
Enter withdrawal amount from Savings Account : 500
Withdrew 500.0 from Savings Account.

========= Bank Menu =========
1. Deposit to Savings Account
2. Withdraw from Savings Account
3. Deposit to Current Account
4. Withdraw from Current Account
5. Check Savings Account Balance
6. Check Current Account Balance
7. Exit
Enter your choice: 5
Current Balance: 500.0

========= Bank Menu =========
1. Deposit to Savings Account
2. Withdraw from Savings Account
3. Deposit to Current Account
4. Withdraw from Current Account
5. Check Savings Account Balance
6. Check Current Account Balance
7. Exit
Enter your choice: 3
Enter deposit amount for Current Account : 2000
Deposited 2000.0 to Current Account.

========= Bank Menu =========
1. Deposit to Savings Account
2. Withdraw from Savings Account
3. Deposit to Current Account
4. Withdraw from Current Account
5. Check Savings Account Balance
6. Check Current Account Balance
7. Exit
Enter your choice: 4
Enter withdrawal amount from Current Account : 2500
Insufficient balance or invalid withdrawal amount.

========= Bank Menu =========
1. Deposit to Savings Account
2. Withdraw from Savings Account
3. Deposit to Current Account
4. Withdraw from Current Account
5. Check Savings Account Balance
6. Check Current Account Balance
7. Exit
Enter your choice: 4
Enter withdrawal amount from Current Account : 1500
Withdrew 1500.0 from Current Account.

========= Bank Menu =========
1. Deposit to Savings Account
2. Withdraw from Savings Account
3. Deposit to Current Account
4. Withdraw from Current Account
5. Check Savings Account Balance
6. Check Current Account Balance
7. Exit
Enter your choice: 6
Current Balance: 500.0

========= Bank Menu =========
1. Deposit to Savings Account
2. Withdraw from Savings Account
3. Deposit to Current Account
4. Withdraw from Current Account
5. Check Savings Account Balance
6. Check Current Account Balance
7. Exit
Enter your choice: 1
Enter deposit amount for Savings Account: -100
Deposit amount must be positive.

========= Bank Menu =========
1. Deposit to Savings Account
2. Withdraw from Savings Account
3. Deposit to Current Account
4. Withdraw from Current Account
5. Check Savings Account Balance
6. Check Current Account Balance
7. Exit
Enter your choice: 7
Exiting the program.

*/