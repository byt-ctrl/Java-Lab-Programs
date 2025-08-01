/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [01-08-25]
 | Program Definition : 8. Create a class person with private instance variables name and age . Implement public getter and setter methods for these variables . Demonstrate how to access
 | and modify these varibles from another class . Add few more variables into the same with the diffrent access specifier and test the results (all user input).
 -------------------------------------------------------------*/

import java.util.Scanner;

class Person {
    // private variables
    private String name;
    private int age;

    // other access specifiers
    protected String address;
    public String email;
    String phoneNumber; // default (package-private)

    // getter and Setter for 'name'
    public String getName() 
    {
        return name;
    }
    public void setName(String name) 
    {
        this.name=name;
    }

    // getter and Setter for 'age'
    public int getAge() 
    {
        return age;
    }
    public void setAge(int age) 
    {
        this.age=age;
    }

    // method to display all information
    public void displayInfo() {
        System.out.println("\n------- Person Details -------");
        System.out.println("Name         : " + name);
        System.out.println("Age          : " + age);
        System.out.println("Address      : " + address);
        System.out.println("Email        : " + email);
        System.out.println("Phone Number : " + phoneNumber);
    }

    // main method (acts as another class to test)
    public static void main(String[] args) 
    {
        Scanner scanner=new Scanner(System.in);
        Person person=new Person();

        // getting user input for private variables
        System.out.print("Enter name : ");
        person.setName(scanner.nextLine());

        System.out.print("Enter age : ");
        person.setAge(scanner.nextInt());
        scanner.nextLine(); // consume newline

        // directly accessing public, protected, and default variables
        System.out.print("Enter address : ");
        person.address = scanner.nextLine();

        System.out.print("Enter email : ");
        person.email = scanner.nextLine();

        System.out.print("Enter phone number : ");
        person.phoneNumber = scanner.nextLine();

        // display the info
        person.displayInfo();

        scanner.close();
    }
}

//output example
/*
Enter name : Om Patel
Enter age : 18
Enter address : 123 Main St
Enter email : om.patel@example.com
Enter phone number : 123-456-7890



------- Person Details -------
Name         : Om Patel
Age          : 18
Address      : 123 Main St
Email        : om.patel@example.com
Phone Number : 123-456-7890
*/
