
/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [16-07-25]
 | Program Definition : 3. Write a program to demonstrate all Type casting For Ex :- int to byte , int to Float , long to byte etc ,,,,, please cover internal type casting as well
 -------------------------------------------------------------*/
 
class TypeCastingExamples {
    public static void main(String[] args) {

        System.out.println("===== PRIMITIVE TYPE CASTING =====\n");

        // int to byte (explicit)
        int i = 130;
        byte b = (byte) i;
        System.out.println("int to byte: " + b); // overflow occurs

        // int to float (implicit)
        int i2 = 55;
        float f = i2;
        System.out.println("int to float: " + f);

        // long to byte (explicit)
        long l = 1000L;
        byte b2 = (byte) l;
        System.out.println("long to byte: " + b2); // data loss

        // byte to int (implicit)
        byte b3 = 10;
        int i3 = b3;
        System.out.println("byte to int: " + i3);

        // double to int (explicit)
        double d = 99.99;
        int i4 = (int) d;
        System.out.println("double to int: " + i4); // decimal part lost

        // float to long (explicit)
        float f2 = 123.45f;
        long l2 = (long) f2;
        System.out.println("float to long: " + l2);

        // char to int (implicit)
        char ch = 'A';
        int chr = ch;
        System.out.println("char to int: " + chr);

        // int to char (explicit)
        int num = 66;
        char c2 = (char) num;
        System.out.println("int to char: " + c2);

        System.out.println("\n====== INTERNAL TYPE CASTING IN EXPRESSIONS ======");

        // byte + byte -> int internally
        byte a = 10;
        byte b4 = 20;
        int result = a + b4;
        System.out.println("byte + byte = int: " + result);

        // need explicit cast to store back into byte
        byte sum = (byte) (a + b4);
        System.out.println("Storing result back to byte: " + sum);

        // mixed types in expression
        int intVal = 50;
        double dblVal = 5.5;
        double result2 = intVal + dblVal; // int → double automatically
        System.out.println("int + double = double: " + result2);

        // cast one operand to get accurate division
        int m = 7, n = 2;
        double division = (double) m / n;
        System.out.println("7 / 2 with casting: " + division);

        System.out.println("\n======= END =======");
    }
}
