/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [30-07-25]
 | Program Definition : 2. Write a programto calculate the simple interest (SI = Principle * Rate of interest * Tenure /100) using method
 -------------------------------------------------------------*/


import java.util.Scanner;

class SimpleInterest
{
    double principle;
    double rate;
    double tenure;

    SimpleInterest(double p, double r, double t) 
    {
        principle=p;
        rate=r;
        tenure=t;
    }

    double calculateSI() 
    {
        return (principle*rate*tenure)/ 100;
    }
}

class SimpleInterestCalculatorApp 
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Principle : ");
        double p=sc.nextDouble();
        System.out.print("Enter Rate of Interest : ");
        double r=sc.nextDouble();
        System.out.print("Enter Tenure : ");
        double t=sc.nextDouble();

        SimpleInterest calculator=new SimpleInterest(p,r,t);
        System.out.println("Simple Interest: " + calculator.calculateSI());
    }
}