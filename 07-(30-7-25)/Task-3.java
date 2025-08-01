/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [30-07-25]
 | Program Definition : 3. Write a program to calculate the area and perimeter of Square and rectangle using method (Separate Classes , 2 classes for rec and square and third for main method)
 |   (Rectangle Area = L * B and Perimeter = 2(L+B) )
 |   (Square Area = L*L and perimeter = 4*L )
 -------------------------------------------------------------*/

import java.util.Scanner;

// rectangle class
class Rectangle 
{
    private double length;
    private double breadth;

    public void setDimensions(double l,double b) 
    {
        length=l;
        breadth=b;
    }

    public double calculateArea() 
    {
        return length * breadth;
    }

    public double calculatePerimeter() 
    {
        return 2*(length+breadth);
    }
}

// square class
class Square 
{
    private double side;

    public void setSide(double s) 
    {
        side=s;
    }

    public double calculateArea() 
    {
        return side*side;
    }

    public double calculatePerimeter() 
    {
        return 4*side;
    }
}

class AreaPerimeterCalculator 
{
    public static void main(String[] args) 
    {
        Scanner input=new Scanner(System.in);

        // rectangle
        Rectangle rect=new Rectangle();
        System.out.print("Enter length of rectangle : ");
        double length=input.nextDouble();
        System.out.print("Enter breadth of rectangle : ");
        double breadth=input.nextDouble();

        System.out.println(); // for better readability

        rect.setDimensions(length,breadth);
        System.out.println("Rectangle Area : " + rect.calculateArea());
        System.out.println("Rectangle Perimeter : " + rect.calculatePerimeter());

        System.out.println();

        // square
        Square square=new Square();
        System.out.print("Enter side of square : ");
        double side=input.nextDouble();
        square.setSide(side);

        System.out.println(); // for better readability

        System.out.println("Square Area : " + square.calculateArea());
        System.out.println("Square Perimeter : " + square.calculatePerimeter());

        input.close();
    }
}


// output example

/* 

Enter length of rectangle : 5
Enter breadth of rectangle : 10

Rectangle Area : 50.0
Rectangle Perimeter : 30.0

Enter side of square : 4

Square Area : 16.0
Square Perimeter : 16.0 

*/