/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [30-07-25]
 | Program Definition : 1. Write a program to define a Circle class in java and method to calculate the area (area = PI*R*R)
 -------------------------------------------------------------*/

import java.util.Scanner;

class Circle
{
    double radius;

    Circle(double r) 
    {
        radius = r;
    }

    double area() 
    {
        return Math.PI * radius * radius;
    }
}

class CircleAreaCalculator {
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter radius : ");
        double r=sc.nextDouble();
        Circle c=new Circle(r);
        System.out.println("Area of Circle: " + c.area());
    }
}