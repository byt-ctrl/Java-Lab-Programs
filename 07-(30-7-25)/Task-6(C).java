/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [30-07-25]
 | Program Definition : 3. Write a program to calculate the area and perimeter of Square and rectangle using constructor (Separate Classes , 2 classes for rec and square and third for main method)
 |   (Rectangle Area = L * B and Perimeter = 2(L+B) )
 |   (Square Area = L*L and perimeter = 4*L )
 -------------------------------------------------------------*/

import java.util.Scanner;
class Rectangle {
    double length;
    double breadth;

    Rectangle(double l,double b) 
    {
        length=l;
        breadth=b;
    }

    double area() 
    {
        return length*breadth;
    }

    double perimeter() 
    {
        return 2*(length+breadth);
    }
}


class Square {
    double side;

    Square(double s) 
    {
        side = s;
    }

    double area() {
        return side * side;
    }

    double perimeter() {
        return 4 * side;
    }
}


class AreaPerimeterCalculator 
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter length of Rectangle : ");
        double l=sc.nextDouble();
        System.out.print("Enter breadth of Rectangle : ");
        double b=sc.nextDouble();
        Rectangle rectangle=new Rectangle(l,b);

        System.out.println(); // for better readability

        System.out.println("Area of Rectangle : " + rectangle.area());
        System.out.println("Perimeter of Rectangle : " + rectangle.perimeter());

        System.out.println(); // for better readability

        System.out.print("Enter side of Square : ");
        double s=sc.nextDouble();
        Square square=new Square(s);

        System.out.println(); // for better readability

        System.out.println("Area of Square : " + square.area());
        System.out.println("Perimeter of Square : " + square.perimeter());
    }
}

// output example

/* 

Enter length of Rectangle : 5
Enter breadth of Rectangle : 4

Area of Rectangle : 20.0
Perimeter of Rectangle : 18.0

Enter side of Square : 6

Area of Square : 36.0
Perimeter of Square : 24.0 

*/