
/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [16-07-25]
 | Program Definition : Write a Program in java to find the result of the following expression (Assume a=10 , b=5). Also follow the assignment operators for the same operations
 |
 |       1 . (a<<2) + (b>>2) 
 |			2. (a) | (b>>0)
 |			3. (a+b/100) /10
 |			4. a&b
 -------------------------------------------------------------*/

class ExpressionEvaluator {
    public static void main(String[] args) {
        int a = 10, b = 5;

        // 1. (a << 2) + (b >> 2)
        int expr1 = (a << 2) + (b >> 2);
        System.out.println("1. (a << 2) + (b >> 2) = " + expr1);

        // using assignment operators
        a = 10; b = 5;
        a <<= 2;
        b >>= 2;
        int expr1Assign = a + b;
        System.out.println("   Using assignment operators = " + expr1Assign);

        // 2. a | (b >> 0)
        a = 10; b = 5;
        int expr2 = a | (b >> 0);
        System.out.println("2. a | (b >> 0) = " + expr2);

        // assignment operator version
        a |= (b >> 0);
        System.out.println("   Using assignment operators = " + a);

        // 3. (a + b / 100) / 10
        a = 10; b = 5;
        int expr3 = (a + b / 100) / 10;
        System.out.println("3. (a + b / 100) / 10 = " + expr3);

        // assignment operator version
        b /= 100;  // b becomes 0
        a += b;    // a stays 10
        a /= 10;   // a becomes 1
        System.out.println("   Using assignment operators = " + a);

        // 4. a & b
        a = 10; b = 5;
        int expr4 = a & b;
        System.out.println("4. a & b = " + expr4);

        // assignment operator version
        a &= b;
        System.out.println("   Using assignment operators = " + a);
    }
}




// Output.

/*1. (a << 2) + (b >> 2) = 41
   Using assignment operators = 41
2. a | (b >> 0) = 15
   Using assignment operators = 15
3. (a + b / 100) / 10 = 1
   Using assignment operators = 1
4. a & b = 0
   Using assignment operators = 0*/


