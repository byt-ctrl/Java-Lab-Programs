/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [22-08-25]
 | Program Definition : 1. ATM Simulation System
 |    Developed an ATM simulation system. It will provide features of all the banking options
 |
 |   > Display balance
 |   > Generate Pin
 |   > Change Pin
 |   > Withdraw Money (Select Preferred notes currency)
 |   > Deposit Money (Select the currency notes)
 |   > Transfer to another account
 |   > Update Final balance
 |   > Build all the possible validations i.e. low balance check, wrong pin check etc.
 |   > Any other features that you can add
 |
 |   and so on..
 -------------------------------------------------------------*/


// little improved ATM simulator with some more functionality then the simple logic
// compile it and run 'java Main'



// TransactionRecord.java - handle's individual transaction records
class TransactionRecord 
{
    String type;
    double amount;
    String date;
    String accountNumber;
    double balanceAfter;
    
    public TransactionRecord(String type,double amount,String accountNumber,double balanceAfter) 
    {
        this.type=type;
        this.amount=amount;
        this.accountNumber=accountNumber;
        this.balanceAfter=balanceAfter;
        this.date=java.time.LocalDateTime.now().toString().substring(0, 19);
    }
    
    public String toString() 
    {
        if (accountNumber != null && !accountNumber.isEmpty()) {
            return date + " | " + type + " | Rs " + String.format("%.2f", amount) + 
                   " | Account: " + accountNumber + " | Balance: Rs " + String.format("%.2f",balanceAfter);
        } 
        else 
        {
            return date + " | " + type + " | Rs " + String.format("%.2f",amount) + 
                   " | Balance: Rs " + String.format("%.2f",balanceAfter);
        }
    }
}

// Account.java - handle's account data and basic operations
class Account {
    String accountNumber;
    int pin;
    double balance;
    TransactionRecord[] transactionHistory;
    int transactionCount;
    
    public Account(String accNo,int p,double bal) 
    {
        accountNumber=accNo;
        pin=p;
        balance=bal;
        transactionHistory=new TransactionRecord[50];
        transactionCount=0;
        addTransaction("Account Created",balance,"",balance);
    }
    
    public void addTransaction(String type, double amount, String accountNumber, double balanceAfter) 
    {
        if (transactionCount<transactionHistory.length) {
            transactionHistory[transactionCount]=new TransactionRecord(type,amount,accountNumber,balanceAfter);
            transactionCount++;
        }
    }
    
    public void deposit(double amount) 
    {
        balance+=amount;
        addTransaction("Deposit",amount,"",balance);
    }
    
    public boolean withdraw(double amount) 
    {
        if (amount > balance) 
        {
            return false;
        }
        balance-=amount;
        addTransaction("Withdrawal",amount,"",balance);
        return true;
    }
    
    public boolean transfer(Account toAccount, double amount) 
    {
        if (amount>balance) 
        {
            return false;
        }
        this.balance-=amount;
        toAccount.balance+=amount;
        this.addTransaction("Transfer Out",amount,toAccount.accountNumber,this.balance);
        toAccount.addTransaction("Transfer In",amount,this.accountNumber,toAccount.balance);
        return true;
    }
}

// AuthenticationService.java - handle's user authentication

class AuthenticationService {
    public static boolean authenticate(Account account,java.util.Scanner scanner) 
    {
        for (int attempts=0 ; attempts<3 ; attempts++) 
        {
            System.out.print("Enter PIN : ");
            try 
            {
                int enteredPin=Integer.parseInt(scanner.nextLine());
                if (enteredPin==account.pin) 
                {
                    System.out.println("Authentication successful !");
                    return true;
                } 
                else 
                {
                    System.out.println("Incorrect PIN. Attempts left : " + (2 - attempts));
                }
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid PIN format.");
                attempts--;
            }
        }
        System.out.println("Too many failed attempts. Account locked.");
        return false;
    }
}

// BalanceService.java - handle's balance related operations

class BalanceService {
    public static void displayBalance(Account account) 
    {
        System.out.println("\nAccount Number : " + account.accountNumber);
        System.out.printf("Current Balance : Rs %.2f\n",account.balance);
        System.out.println();
    }
}

// PinService.java - handle's PIN related operations

class PinService {
    public static void generatePin(Account account) 
    {
        int newPin=1000 + (int)(Math.random() * 9000);
        account.pin=newPin;
        account.addTransaction("PIN Generated", 0, "",account.balance);
        System.out.println("New PIN generated : " + newPin);
    }
    
    public static void changePin(Account account,java.util.Scanner scanner) 
    {
        System.out.print("Enter current PIN : ");

        try {
            int currentPin=Integer.parseInt(scanner.nextLine());

            if (currentPin != account.pin) 
            {
                System.out.println("Incorrect current PIN!");
                return;
            }
            System.out.print("Enter new PIN (4 digits) : ");
            int newPin=Integer.parseInt(scanner.nextLine());

            if (newPin < 1000 || newPin > 9999) 
            {
                System.out.println("PIN must be 4 digits !");
                return;
            }
            account.pin=newPin;
            account.addTransaction("PIN Changed", 0, "",account.balance);
            System.out.println("PIN changed successfully !");
        } 
        
        catch (NumberFormatException e) 
        {
            System.out.println("Invalid PIN format !");
        }
    }
}


// TransactionService.java - handle's deposit and withdrawal operations
class TransactionService {
    public static void withdrawMoney(Account account, java.util.Scanner scanner) 
    {
        System.out.print("Enter amount to withdraw : ");
        
        try {
            double amount=Double.parseDouble(scanner.nextLine());

            if (amount <= 0) 
            {
                System.out.println("Amount must be positive !");
                return;
            }
            if (amount > account.balance) 
            {
                System.out.println("Insufficient balance !");
                return;
            }

            if (account.withdraw(amount)) 
            {
                System.out.printf("Withdrawal successful! Amount: Rs %.2f\n",amount);
                System.out.printf("Remaining balance: Rs %.2f\n",account.balance);
            } 
            else 
            {
                System.out.println("Withdrawal failed !");
            }

        } 
        
        catch (NumberFormatException e) 
        {
            System.out.println("Invalid amount format !");
        }
    }
    
    public static void depositMoney(Account account,java.util.Scanner scanner) 
    {
        System.out.print("Enter amount to deposit : ");
        try {
            double amount=Double.parseDouble(scanner.nextLine());
            
            if (amount <= 0) 
            {
                System.out.println("Amount must be positive !");
                return;
            }
            account.deposit(amount);
            System.out.printf("Deposit successful! New balance: Rs %.2f\n",account.balance);
        } 
        
        catch (NumberFormatException e) 
        {
            System.out.println("Invalid input format !");
        }
    }
}

// TransferService.java - handle's fund transfer operations

class TransferService {
    public static void transferMoney(Account account , Account[] accounts , int accountCount , java.util.Scanner scanner) 
    {
        System.out.print("Enter recipient account number : ");
        String recipientAccNo=scanner.nextLine();
        Account recipient=null;

        for (int a = 0; a < accountCount; a++) 
        {
            if (accounts[a].accountNumber.equals(recipientAccNo)) 
            {
                recipient=accounts[a];
                break;
            }
        }
        if (recipient==null) 
        {
            System.out.println("Recipient account not found !");
            return;
        }
        if (recipient.accountNumber.equals(account.accountNumber)) 
        {
            System.out.println("Cannot transfer to same account !");
            return;
        }
        System.out.print("Enter amount to transfer : ");

        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) 
            {
                System.out.println("Amount must be positive!");
                return;
            }
            if (amount > account.balance) 
            {
                System.out.println("Insufficient balance !");
                return;
            }
            if (account.transfer(recipient, amount)) {
                System.out.printf("Transfer successful! Rs %.2f transferred to account %s\n", 
                                amount,recipientAccNo);
                System.out.printf("Your new balance : Rs %.2f\n",account.balance);
            } 
            
            else 
            {
                System.out.println("Transfer failed !");
            }
        } 
        
        catch (NumberFormatException e) {
            System.out.println("Invalid amount format !");
        }
    }
}

// StatementService.java - handle's mini statement functionality
// the recent transactions can be change as per need through the loop
class StatementService {
    public static void displayMiniStatement(Account account) {
        System.out.println("\nMini Statement for Account: " + account.accountNumber);
        System.out.println("==================================================");
        System.out.printf("Current Balance: Rs %.2f\n", account.balance);
        System.out.println("--------------------------------------------------");
        System.out.println("Recent Transactions (Last 10 ) :");

        for (int a = 0; a < 10; a++) 
        {
            if (a < account.transactionCount) 
            {
                System.out.println(account.transactionHistory[account.transactionCount - 1 - a]);
            }
        }
        if (account.transactionCount == 0) 
        {
            System.out.println("No transactions found.");
        }
        System.out.println("==================================================\n");
    }
}

// ATMMenu.java - handle's the user interface and menu system
class ATMMenu {
    public static void showMenu(Account account , Account[] accounts , int accountCount , java.util.Scanner scanner) {
        while (true) 
        {
            System.out.println("\nATM Main Menu");
            System.out.println("1. View Balance");
            System.out.println("2. Generate New PIN");
            System.out.println("3. Change PIN");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Deposit Money");
            System.out.println("6. Transfer Money");
            System.out.println("7. Mini Statement");
            System.out.println("8. Exit");
            System.out.print("Choose an option : ");
            
            try {
                int choice=Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 : 
                        BalanceService.displayBalance(account);
                        break;
                    case 2 : 
                        PinService.generatePin(account);
                        break;
                    case 3 : 
                        PinService.changePin(account,scanner);
                        break;
                    case 4 : 
                        TransactionService.withdrawMoney(account,scanner);
                        break;
                    case 5 : 
                        TransactionService.depositMoney(account,scanner);
                        break;
                    case 6 : 
                        TransferService.transferMoney(account,accounts,accountCount,scanner);
                        break;
                    case 7 :
                        StatementService.displayMiniStatement(account);
                        break;
                    case 8 : 
                        System.out.println("Thank you for using ATM service !");
                        return;
                    default : 
                        System.out.println("Invalid option !");
                }
            } 
            
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid input ! Please enter a number.");
            }
        }
    }
}

// ATMSystem.java - main ATM system controller
class ATMSystem {
    Account[] accounts;
    int accountCount;
    java.util.Scanner scanner;
    
    public ATMSystem() {
        accounts=new Account[10];
        accountCount=0;
        scanner=new java.util.Scanner(System.in);
        // this inputs are static and random (can be change as per need)
        addAccount(new Account("12345", 1234, 5000.0));
        addAccount(new Account("67890", 5678, 3000.0));
        addAccount(new Account("11111", 1111, 10000.0));
    }
    
    public void addAccount(Account acc) 
    {
        if (accountCount<accounts.length) 
        {
            accounts[accountCount]=acc;
            accountCount++;
        }
    }
    
    public Account findAccount(String accNo) 
    {
        for (int a = 0 ; a < accountCount ; a++) 
        {
            if (accounts[a].accountNumber.equals(accNo)) 
            {
                return accounts[a];
            }
        }
        return null;
    }
    
    public void start() 
    {
        System.out.println("Welcome to ATM System");


        while (true) 
        {
            System.out.print("\nEnter Account Number (or 'EXIT' to quit) : ");
            String input=scanner.nextLine().trim();

            if ("EXIT".equalsIgnoreCase(input)) 
            {
                System.out.println("Thank you for using ATM service !");
                break;
            }
            if (input.isEmpty()) 
            {
                System.out.println("Account number cannot be empty !");
                continue;
            }
            Account account=findAccount(input);

            if (account==null) {
                System.out.println("Account not found !");
                continue;
            }
            if (AuthenticationService.authenticate(account,scanner)) 
            {
                ATMMenu.showMenu(account,accounts,accountCount,scanner);
            }
        }
    }
}

// main class to run the ATM simulation
class Main {
    public static void main(String[] args) 
    {
        ATMSystem atm=new ATMSystem();
        atm.start();
    }
}




// output example

/*  

Welcome to ATM System

Enter Account Number (or 'EXIT' to quit) : 12345
Enter PIN : 1234
Authentication successful !

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 1

Account Number : 12345
Current Balance : Rs 5000.00


ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 2
New PIN generated : 4827

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 3
Enter current PIN : 4827
Enter new PIN (4 digits) : 5555
PIN changed successfully !

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 4
Enter amount to withdraw : 1000
Withdrawal successful! Amount: Rs 1000.00
Remaining balance: Rs 4000.00

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 5
Enter amount to deposit : 2000
Deposit successful! New balance: Rs 6000.00

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 6
Enter recipient account number : 67890
Enter amount to transfer : 1500
Transfer successful! Rs 1500.00 transferred to account 67890
Your new balance : Rs 4500.00

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 7

Mini Statement for Account: 12345
==================================================
Current Balance: Rs 4500.00
--------------------------------------------------
Recent Transactions (Last 10 ) :
2023-12-07T14:30:25 | Transfer Out | Rs 1500.00 | Account: 67890 | Balance: Rs 4500.00
2023-12-07T14:30:10 | Deposit | Rs 2000.00 | Balance: Rs 6000.00
2023-12-07T14:29:45 | Withdrawal | Rs 1000.00 | Balance: Rs 4000.00
2023-12-07T14:29:20 | PIN Changed | Rs 0.00 | Balance: Rs 5000.00
2023-12-07T14:29:05 | PIN Generated | Rs 0.00 | Balance: Rs 5000.00
2023-12-07T14:28:50 | Account Created | Rs 5000.00 | Balance: Rs 5000.00
==================================================


ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 8
Thank you for using ATM service !

Enter Account Number (or 'EXIT' to quit) : 67890
Enter PIN : 5678
Authentication successful !

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 1

Account Number : 67890
Current Balance : Rs 4500.00


ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 7

Mini Statement for Account: 67890
==================================================
Current Balance: Rs 4500.00
--------------------------------------------------
Recent Transactions (Last 10 ) :
2023-12-07T14:30:25 | Transfer In | Rs 1500.00 | Account: 12345 | Balance: Rs 4500.00
2023-12-07T14:28:50 | Account Created | Rs 3000.00 | Balance: Rs 3000.00
==================================================


ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 8
Thank you for using ATM service !

Enter Account Number (or 'EXIT' to quit) : 99999
Account not found !

Enter Account Number (or 'EXIT' to quit) : 12345
Enter PIN : 1111
Incorrect PIN. Attempts left : 2
Enter PIN : 2222
Incorrect PIN. Attempts left : 1
Enter PIN : 3333
Incorrect PIN. Attempts left : 0
Enter PIN : 5555
Authentication successful !

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 4
Enter amount to withdraw : abc
Invalid amount format !

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 4
Enter amount to withdraw : -500
Amount must be positive !

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 4
Enter amount to withdraw : 10000
Insufficient balance !

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 6
Enter recipient account number : 12345
Cannot transfer to same account !

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 6
Enter recipient account number : 88888
Recipient account not found !

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 3
Enter current PIN : 4444
Incorrect current PIN!

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 3
Enter current PIN : 5555
Enter new PIN (4 digits) : 123
PIN must be 4 digits !

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 3
Enter current PIN : 5555
Enter new PIN (4 digits) : abcd
Invalid PIN format !

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : abc
Invalid input ! Please enter a number.

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 9
Invalid option !

ATM Main Menu
1. View Balance
2. Generate New PIN
3. Change PIN
4. Withdraw Money
5. Deposit Money
6. Transfer Money
7. Mini Statement
8. Exit
Choose an option : 8
Thank you for using ATM service !

Enter Account Number (or 'EXIT' to quit) : EXIT
Thank you for using ATM service !

*/