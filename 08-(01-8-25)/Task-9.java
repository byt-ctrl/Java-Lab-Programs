/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [01-08-25]
 | Program Definition : 9. Create a class Shape and having the method overloading concept (ShapeArea method) which will calculate the area for the diffrent shapes 
 |                      i.e . Circle, Square , Rectangle ( must be user input )
 -------------------------------------------------------------*/



import java.util.Scanner;

class Shape 
{

    // method to calculate area of a circle
    public double ShapeArea(double radius) 
    {
        return Math.PI*radius*radius;
    }

    // method to calculate area of a square
    public double ShapeArea(int side) 
    {
        return side*side;
    }

    // method to calculate area of a rectangle
    public double ShapeArea(double length,double width) 
    {
        return length*width;
    }

    public static void main(String[] args) 
    {
        Scanner scanner=new Scanner(System.in);
        Shape shape=new Shape();

        System.out.println("Choose shape to calculate area :");
        System.out.println("1. Circle");
        System.out.println("2. Square");
        System.out.println("3. Rectangle");

        System.out.print("Enter your choice (1-3) : ");
        int choice=scanner.nextInt();

        double area;

        switch (choice) 
        {
            case 1 :
                System.out.print("Enter radius of the circle : ");
                double radius=scanner.nextDouble();
                area=shape.ShapeArea(radius);
                System.out.printf("Area of Circle = %.2f\n",area);
                break;
            case 2 :
                System.out.print("Enter side of the square : ");
                int side=scanner.nextInt();
                area=shape.ShapeArea(side);
                System.out.printf("Area of Square = %.2f\n",area);
                break;
            case 3 :
                System.out.print("Enter length of the rectangle : ");
                double length=scanner.nextDouble();
                System.out.print("Enter width of the rectangle : ");
                double width=scanner.nextDouble();
                area=shape.ShapeArea(length, width);
                System.out.printf("Area of Rectangle = %.2f\n",area);
                break;
            default :
                System.out.println("Invalid choice ....../");
        }

        scanner.close();
    }
}

// output examples of all three cases
/*

Choose shape to calculate area :
1. Circle
2. Square
3. Rectangle
Enter your choice (1-3) : 1
Enter radius of the circle : 5
Area of Circle = 78.54

---------------------------------------------

Choose shape to calculate area :
1. Circle
2. Square
3. Rectangle
Enter your choice (1-3) : 2
Enter side of the square : 4
Area of Square = 16.00

---------------------------------------------

Choose shape to calculate area :
1. Circle
2. Square
3. Rectangle
Enter your choice (1-3) : 3
Enter length of the rectangle : 5
Enter width of the rectangle : 3
Area of Rectangle = 15.00
*/