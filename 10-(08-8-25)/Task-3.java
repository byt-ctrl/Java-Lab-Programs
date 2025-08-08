/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [08-08-25]
 | Program Definition : 
 | 3   Create a class "BankAccount" with fields "accountNumber", "balance", and "interestRate" and a method "deposit()" that adds an amount to the balance.
 |  Create a subclass "SavingsAccount" that extends "BankAccount" and adds a field "minimumBalance" and a method "withdraw()" that subtracts an amount from the balance.
 |   Create an object of the "BankAccount" class and call the "deposit()" method.    
 |   Create an object of the "SavingsAccount" class and call the "deposit()" and "withdraw()" methods.
 -------------------------------------------------------------*/

import java.util.Scanner;

// parent class
class BankAccount 
{
    private int accountNumber;
    private double balance;
    private double interestRate;

    // constructor
    public BankAccount(int accountNumber , double balance , double interestRate) 
    {
        this.accountNumber=accountNumber;
        this.balance=balance;
        this.interestRate=interestRate;
    }

    // deposit method
    public void deposit(double amount) 
    {
        if (amount > 0) 
        {
            balance += amount;
            System.out.println("Deposited : " + amount);
            System.out.println("New Balance : " + balance);
        } 
        else 
        {
            System.out.println("Invalid deposit amount!");
        }
    }

    // getter and Setter for balance
    public double getBalance() 
    {
        return balance;
    }

    public void setBalance(double balance) 
    {
        this.balance=balance;
    }
}

// subclass
class SavingsAccount extends BankAccount 
{
    private double minimumBalance;

    public SavingsAccount(int accountNumber,double balance,double interestRate,double minimumBalance) 
    {
        super(accountNumber , balance , interestRate);
        this.minimumBalance=minimumBalance;
    }

    public void withdraw(double amount)  
    {
        if (amount > 0 && (getBalance() - amount) >= minimumBalance) 
        {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawn : " + amount);
            System.out.println("New Balance : " + getBalance());
        }
        else 
        {
            System.out.println("Withdrawal failed !! Not enough balance.");
        }
    }
}

// main class
class TASK_3 
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);

        // bank account
        System.out.println("======= BankAccount Details =======");
        System.out.print("Enter account number : ");
        int accNum=sc.nextInt();
        System.out.print("Enter initial balance : ");
        double bal=sc.nextDouble();
        System.out.print("Enter interest rate : ");
        double rate=sc.nextDouble();

        BankAccount acc1=new BankAccount(accNum,bal,rate);
        System.out.print("Enter deposit amount : ");
        double dep=sc.nextDouble();
        acc1.deposit(dep);

        System.out.println();

        // savings account
        System.out.println("\n======= SavingsAccount Details =======");
        System.out.print("Enter account number : ");
        int sAccNum=sc.nextInt();
        System.out.print("Enter initial balance : ");
        double sBal=sc.nextDouble();
        System.out.print("Enter interest rate : ");
        double sRate=sc.nextDouble();
        System.out.print("Enter minimum balance : ");
        double minBal=sc.nextDouble();

        SavingsAccount acc2=new SavingsAccount(sAccNum,sBal,sRate,minBal);

        System.out.print("Enter deposit amount : ");
        double sDep=sc.nextDouble();
        acc2.deposit(sDep);

        System.out.print("Enter withdraw amount : ");
        double sWith=sc.nextDouble();
        acc2.withdraw(sWith);

        sc.close();
    }
}

// output example

/* 
======= BankAccount Details =======
Enter account number : 24000112
Enter initial balance : 5000
Enter interest rate : 2
Enter deposit amount : 120
Deposited : 120.0
New Balance : 5120.0


======= SavingsAccount Details =======
Enter account number : 2412568
Enter initial balance : 5460
Enter interest rate : 3.5
Enter minimum balance : 200
Enter deposit amount : 1020
Deposited : 1020.0
New Balance : 6480.0
Enter withdraw amount : 50
Withdrawn : 50.0
New Balance : 6430.0 
*/