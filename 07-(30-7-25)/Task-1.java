/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [30-07-25]
 | Program Definition : 1. Write a program to define a Circle class in java and method to calculate the area (area = PI*R*R)
 -------------------------------------------------------------*/

import java.util.Scanner;

class Circle {
    // instance variable for radius
    private double radius;

    // method to set the radius
    public void setRadius(double r) 
    {
        radius=r;
    }

    // method to calculate the area of the circle
    public double calculateArea() 
    {
        return Math.PI*radius*radius;
    }

    // main method
    public static void main(String[] args) 
    {
        // create Scanner object to read input
        Scanner input=new Scanner(System.in);

        // create a circle object
        Circle circle=new Circle();

        System.out.print("Enter the radius of the circle: ");
        double r = input.nextDouble();

        // set the radius
        circle.setRadius(r);

        double area=circle.calculateArea();
        System.out.println("Area of the circle is : " + area);

        input.close();
    }
}


// output example

/* 

Enter the radius of the circle: 10
Area of the circle is : 314.1592653589793 

*/