/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [11-07-25]
 | Program Definition : 4. Convert days to seconds , minutes , hours in JAVA  .
 -------------------------------------------------------------*/


class ConvertDays {
    public static void main(String[] args) {
        // define the number of days
        int days = 5;

        // convert days to hours, minutes, and seconds
        int hours = days * 24;
        int minutes = hours * 60;
        int seconds = minutes * 60;

        // print the results
        System.out.println(days + " days is equal to:");
        System.out.println(hours + " hours");
        System.out.println(minutes + " minutes");
        System.out.println(seconds + " seconds");
    }
}
