/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [25-07-25]
 | Program Definition : 11. Write java program to check all the possible datatypes output formatting using the printf()
 -------------------------------------------------------------*/

class possibleDataTypes {
    public static void main(String[] args) 
    {
        byte b=10;
        short s=200;
        int i=3000;
        long l=40000L;
        float f=5.5f;
        double d=6.789;
        char c='A';
        boolean bool=true;
        String str="Hello";

        // possible formatting
        System.out.printf("byte as decimal : %d\n",b);
        System.out.printf("short as decimal : %d\n",s);
        System.out.printf("int as decimal : %d\n",i);
        System.out.printf("long as decimal : %d\n",l);
        System.out.printf("float as float : %.2f\n",f);
        System.out.printf("double as double : %.3f\n",d);
        System.out.printf("char as char : %c\n",c);
        System.out.printf("boolean as boolean : %b\n",bool);
        System.out.printf("String as string : %s\n",str);

        // impossible/incorrect formatting (will throw runtime exceptions)

        // uncomment to test error cases

        // System.out.printf("byte as char : %c\n",b); // error if b is not a valid Unicode char
        // System.out.printf("float as int : %d\n",f); // error: %d expects int
        // System.out.printf("boolean as int : %d\n",bool); // error: %d expects int
        // System.out.printf("char as float : %f\n",c); // error: %f expects float/double
        // System.out.printf("String as int : %d\n",str); // error: %d expects int

        // you can test these lines by uncommenting them one by one to see the exceptions.
    }
}

// Output Example

/*
byte as decimal :10
short as decimal :200
int as decimal :3000
long as decimal :40000
float as float :5.50
double as double :6.789
char as char :A
boolean as boolean :true
String as string :Hello
*/
// note: The commented lines will throw runtime exceptions if uncommented , demonstrating incorrect formatting usage.
