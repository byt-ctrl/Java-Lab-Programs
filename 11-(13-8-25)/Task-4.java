/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [13-08-25]
 | Program Definition : 4. Extend Bank class and design a payment gateway system as mentioned below
 |                           Abstract class: Paymentposter
 |                          Common methods: validatePayment() and recordTransaction()
 |                          Abstract methods: chargeCustomer() and refundCustomer()
 |
 |                          Subclasses :
 |
 |                          CreditCardPayment: Implements chargeCustomer() and refundCustomer()
 |                          DebitCardPayment: Implements chargeCustomer() and refundCustomer()
 |                          UPIPayment: Implements chargeCustomer() and refundCustomer() 
 -------------------------------------------------------------*/

import java.util.Scanner;

// abstract class defining the payment poster
abstract class PaymentPoster 
{
    
    // concrete method to validate a payment
    public void validatePayment(double amount) 
    {
        if (amount<=0) 
        {
            System.out.println("Invalid payment amount. Must be greater than 0.");
        } 
        else 
        {
            System.out.println("Payment validated: Amount = " + amount);
        }
    }

    // concrete method to record a transaction
    public void recordTransaction(String type, double amount) 
    {
        System.out.println("Transaction recorded : " + type + " of amount ₹" + amount);
    }

    // abstract methods to be implemented in subclasses
    public abstract void chargeCustomer(double amount);
    public abstract void refundCustomer(double amount);
}

// credit-card-payment class
class CreditCardPayment extends PaymentPoster 
{

    public void chargeCustomer(double amount) 
    {
        if (amount > 0) 
        {
            validatePayment(amount);
            System.out.println("Charged ₹" + amount + " to Credit Card.");
            recordTransaction("Credit Card Charge", amount);
        } 
        else 
        {
            System.out.println("Charge amount must be greater than 0.");
        }
    }

    public void refundCustomer(double amount) 
    {
        if (amount > 0) 
        {
            System.out.println("Refunded ₹" + amount + " to Credit Card.");
            recordTransaction("Credit Card Refund", amount);
        } 
        else 
        {
            System.out.println("Refund amount must be greater than 0.");
        }
    }
}

// debit-card-payment class
class DebitCardPayment extends PaymentPoster 
{

    public void chargeCustomer(double amount) 
    {
        if (amount>0) 
        {
            validatePayment(amount);
            System.out.println("Charged ₹" + amount + " to Debit Card.");
            recordTransaction("Debit Card Charge",amount);
        } 
        else 
        {
            System.out.println("Charge amount must be greater than 0.");
        }
    }

    public void refundCustomer(double amount) 
    {
        if (amount > 0) 
        {
            System.out.println("Refunded ₹" + amount + " to Debit Card.");
            recordTransaction("Debit Card Refund",amount);
        } 
        else 
        {
            System.out.println("Refund amount must be greater than 0.");
        }
    }
}

// upi-payment class
class UPIPayment extends PaymentPoster 
{

    public void chargeCustomer(double amount) 
    {
        if (amount>0) 
        {
            validatePayment(amount);
            System.out.println("Charged ₹" + amount + " via UPI.");
            recordTransaction("UPI Charge", amount);
        } 
        else 
        {
            System.out.println("Charge amount must be greater than 0.");
        }
    }

    public void refundCustomer(double amount) 
    {
        if (amount > 0) 
        {
            System.out.println("Refunded ₹" + amount + " via UPI.");
            recordTransaction("UPI Refund", amount);
        } 
        else 
        {
            System.out.println("Refund amount must be greater than 0.");
        }
    }
}

// main class 
class PaymentGatewayApp 
{

    public static void main(String[] args) 
    {
        Scanner scanner=new Scanner(System.in);

        PaymentPoster credit=new CreditCardPayment();
        PaymentPoster debit=new DebitCardPayment();
        PaymentPoster upi=new UPIPayment();

        int choice;
        double amount;

        do {
            System.out.println("\n========= Payment Gateway Menu =========");
            System.out.println("1. Credit Card - Charge");
            System.out.println("2. Credit Card - Refund");
            System.out.println("3. Debit Card - Charge");
            System.out.println("4. Debit Card - Refund");
            System.out.println("5. UPI - Charge");
            System.out.println("6. UPI - Refund");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice=scanner.nextInt();

            switch (choice) 
            {
                case 1 :
                    System.out.print("Enter amount to charge via Credit Card : ₹");
                    amount=scanner.nextDouble();
                    credit.chargeCustomer(amount);
                    break;
                case 2 :
                    System.out.print("Enter amount to refund to Credit Card : ₹");
                    amount=scanner.nextDouble();
                    credit.refundCustomer(amount);
                    break;
                case 3 :
                    System.out.print("Enter amount to charge via Debit Card : ₹");
                    amount=scanner.nextDouble();
                    debit.chargeCustomer(amount);
                    break;
                case 4 :
                    System.out.print("Enter amount to refund to Debit Card : ₹");
                    amount=scanner.nextDouble();
                    debit.refundCustomer(amount);
                    break;
                case 5 :
                    System.out.print("Enter amount to charge via UPI : ₹");
                    amount=scanner.nextDouble();
                    upi.chargeCustomer(amount);
                    break;
                case 6 :
                    System.out.print("Enter amount to refund via UPI : ₹");
                    amount=scanner.nextDouble();
                    upi.refundCustomer(amount);
                    break;
                case 7 :
                    System.out.println("Exiting Payment Gateway . Goodbye!");
                    break;
                default :
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice!=7);

        scanner.close();
    }
}


// output example

/* 

========= Payment Gateway Menu =========
1. Credit Card - Charge
2. Credit Card - Refund
3. Debit Card - Charge
4. Debit Card - Refund
5. UPI - Charge
6. UPI - Refund
7. Exit
Enter your choice: 1
Enter amount to charge via Credit Card: ₹1500
Payment validated: Amount = 1500.0
Charged ₹1500.0 to Credit Card.
Transaction recorded: Credit Card Charge of amount ₹1500.0

========= Payment Gateway Menu =========
Enter your choice: 2
Enter amount to refund to Credit Card: ₹500
Refunded ₹500.0 to Credit Card.
Transaction recorded: Credit Card Refund of amount ₹500.0

========= Payment Gateway Menu =========
Enter your choice: 5
Enter amount to charge via UPI: ₹1000
Payment validated: Amount = 1000.0
Charged ₹1000.0 via UPI.
Transaction recorded: UPI Charge of amount ₹1000.0

========= Payment Gateway Menu =========
Enter your choice: 6
Enter amount to refund via UPI: ₹300
Refunded ₹300.0 via UPI.
Transaction recorded: UPI Refund of amount ₹300.0

========= Payment Gateway Menu =========
Enter your choice: 7
Exiting Payment Gateway. Goodbye!



*/