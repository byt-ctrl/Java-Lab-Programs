
/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [11-07-25]
 | Program Definition : 1. Convert Fahrenheit to Celsius in JAVA  .
 -------------------------------------------------------------*/

class FahrenheitToCelsius {
    public static void main(String[] args) {

        // define Fahrenheit value directly
        double fahrenheit = 97.6;

        // convert Fahrenheit to Celsius
        double celsius = (fahrenheit - 32) * 5 / 9;

        // output the result
        System.out.println("Temperature in Fahrenheit : " + fahrenheit);
        System.out.println("Temperature in Celsius : " + celsius);
    }
}