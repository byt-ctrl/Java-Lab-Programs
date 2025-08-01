/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [30-07-25]
 | Program Definition : 2. Write a programto calculate the simple interest (SI = Principle * Rate of interest * Tenure /100) using method
 -------------------------------------------------------------*/


import java.util.Scanner;

class SimpleInterestCalculator {

    // method to calculate simple interest
    public double calculateSimpleInterest(double principal,double rate,double time) 
    {
        return (principal*rate*time)/100;
    }

    // main method
    public static void main(String[] args) 
    {
        Scanner input=new Scanner(System.in);

        // create object of SimpleInterestCalculator
        SimpleInterestCalculator siCalc=new SimpleInterestCalculator();
        

        System.out.print("Enter Principal amount : ");
        double principal=input.nextDouble();

        System.out.print("Enter Rate of Interest : ");
        double rate=input.nextDouble();

        System.out.print("Enter Time (in years) : ");
        double time=input.nextDouble();

        // calculate simple interest
        double si=siCalc.calculateSimpleInterest(principal,rate,time);

        System.out.println("Simple Interest is : " + si);

        input.close();
    }
}


// output example

/* 

Enter Principal amount : 15000
Enter Rate of Interest : 2
Enter Time (in years) : 15
Simple Interest is : 4500.0 

*/