/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [08-08-25]
 | Program Definition : 
 | 1. Create a class "Vehicle" with a method "start()" that prints "Vehicle Started".
 |   Create a subclass "Car" that extends "Vehicle" and overrides the "start()" method to print "Car Started"
 |   Create an object of the "Vehicle" class and call the "Start()" method .
 |   Create an object of "Car" class and call the "start()" method . 
 -------------------------------------------------------------*/

 // base class
class Vehicle 
{
    void start() 
    {
        System.out.println("Vehicle Started");
    }
}
// subclass that overrides the start() method
class Car extends Vehicle 
{
    @Override
    void start() 
    {
        System.out.println("Car Started");
    }
}

// main class
class TASK_1
{
    public static void main(String[] args) 
    {
        // create an object of Vehicle and call start()
        Vehicle v=new Vehicle();
        v.start();  
        // output: Vehicle Started

        // create an object of Car and call start()
        Car c=new Car();
        c.start();  
        // output: Car Started
    }
}


// output 
/* 
Vehicle Started
Car Started
*/