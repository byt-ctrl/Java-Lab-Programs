/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [11-07-25]
 | Program Definition : 6. Compute Square , cube and fourth power in JAVA .
 -------------------------------------------------------------*/

class PowerOperations {
    public static void main(String[] args) {
        // define a number to compute powers for
        int num = 5;

        // compute the square, cube, and fourth power of the number
        int square = num * num;
        int cube = num * num * num;
        int fourthpower = num * num * num * num;

        // output the results
        System.out.println("Number: " + num);
        System.out.println("Square: " + square);
        System.out.println("Cube: " + cube);
        System.out.println("Fourth Power: " + fourthpower);
    }
}
