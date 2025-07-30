/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [30-07-25]
 | Program Definition : 5. Write a program using constructor to calculate volume of Box . Following methods should be part of your program
 |   -> Method to retrieve the box dimensions values .
 |   -> Method to calculate volume of box .
 |   -> Method to check if box share is cube or not 
 -------------------------------------------------------------*/

import java.util.Scanner;

class Box 
{
    double length;
    double breadth;
    double height;

    Box(double l,double b,double h) 
    {
        length=l;
        breadth=b;
        height=h;
    }

    void displayDimensions() 
    {
        System.out.printf("\nDimensions of the box : Length=%.2f, Breadth=%.2f, Height=%.2f\n",length,breadth, height);
    }

    double calculateVolume() 
    {
        return length * breadth * height;
    }

    boolean isCube() 
    {
        return (length == breadth && breadth == height);
    }
}

class BoxVolumeCalculator 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter length of the box : ");
        double l = sc.nextDouble();
        System.out.print("Enter breadth of the box : ");
        double b = sc.nextDouble();
        System.out.print("Enter height of the box : ");
        double h = sc.nextDouble();

        Box box = new Box(l, b, h);
        
        box.displayDimensions();
        
        double volume = box.calculateVolume();
        System.out.printf("Volume of the box : %.2f\n",volume);
        
        if (box.isCube()) 
        {
            System.out.println("The box is a cube.");
        } else 
        {
            System.out.println("The box is not a cube.");
        }
        
        sc.close();
    }
}