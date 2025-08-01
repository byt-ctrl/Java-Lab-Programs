/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [01-08-25]
 | Program Definition : 10. Create a class Vehicle and perform the contructor overloading to display 
 |                       a vehicle type on screen based on the input. ( must be user input )
 -------------------------------------------------------------*/


import java.util.Scanner;

class Vehicle {
    String vehicleType;

    // default constructor
    public Vehicle() 
    {
        vehicleType="Random Vehicle";
    }

    // constructor with one argument
    public Vehicle(String type) 
    {
        vehicleType=type;
    }

    // constructor with two arguments
    public Vehicle(String category,String model) 
    {
        vehicleType=category + " - " + model;
    }

    // method to display vehicle type
    public void displayType() 
    {
        System.out.println("Vehicle Type : " + vehicleType);
    }

    // main method for testing
    public static void main(String[] args) 
    {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Choose constructor :");
        System.out.println("1. No input (default)");
        System.out.println("2. One input (vehicle type)");
        System.out.println("3. Two inputs (category and model)");

        System.out.print("Enter your choice (1-3) : ");
        int choice=scanner.nextInt();
        scanner.nextLine(); // consume newline

        Vehicle v;

        switch (choice) 
        {
            case 1 :
                v=new Vehicle();
                break;
            case 2 :
                System.out.print("Enter vehicle type : ");
                String type=scanner.nextLine();
                v=new Vehicle(type);
                break;
            case 3 :
                System.out.print("Enter vehicle category (e.g., Car , Bike) : ");
                String category=scanner.nextLine();
                System.out.print("Enter vehicle model (e.g., SUV , Sports) : ");
                String model=scanner.nextLine();
                v=new Vehicle(category,model);
                break;
            default:
                System.out.println("Invalid choice . Using default constructor.");
                v=new Vehicle();
        }

        v.displayType();

        scanner.close();
    }
}

// output example of all three cases

/* 

Choose constructor :
1. No input (default)
2. One input (vehicle type)
3. Two inputs (category and model)
Enter your choice (1-3) : 1
Vehicle Type : Random Vehicle

---------------------------------------------

Choose constructor :
1. No input (default)
2. One input (vehicle type)
3. Two inputs (category and model)
Enter your choice (1-3) : 2
Enter vehicle type : Sedan
Vehicle Type : Sedan

---------------------------------------------

Choose constructor :
1. No input (default)
2. One input (vehicle type)
3. Two inputs (category and model)
Enter your choice (1-3) : 3
Enter vehicle category (e.g., Car , Bike) : Car
Enter vehicle model (e.g., SUV , Sports) : SUV
Vehicle Type : Car - SUV
 
 */
