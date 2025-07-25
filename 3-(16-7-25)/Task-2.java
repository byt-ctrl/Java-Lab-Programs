
/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [16-07-25]
 | Program Definition : 2. write a java program that prints a table with a list of at least 5 students together with their grades earned (lab points , bonus points , total points obtain , percentage & grade assume total points is 60  ) in the format below 
 |
 |		////////////\\\\\\\\\\\\
 |		  ==Students Points==
 |		\\\\\\\\\\\\////////////
 |
 |		Name LAb bonus total per%  Garde
 |		Table type format
 |              Name :- Lets take Joe , William , Mary Sue , Roy, Hitler
 |
 |
 | 
 |
 |
 |
 |		................................
 |
 |	please use the concept of constant , vriable , conditions , opretions etc
-------------------------------------------------------------*/

class StudentInfo{
	public static void main(String[] args) {
	
	final int TOTAL_POINTS = 60;
	String[] names = {"Joe","William","Mary Sue","Roy","Hitler"};
	int[] labPoints = {40, 48, 50, 35, 25};
        int[] bonusPoints = {5, 2, 4, 3, 5};
	
  	// Header

        System.out.println("\n\t\t////////////\\\\\\\\\\\\\\\\");
        System.out.println("\t\t  ==Students Points==");
        System.out.println("\t\t\\\\\\\\\\\\\\\\////////////");
        System.out.println();
        System.out.printf("%-10s %-5s %-6s %-6s %-6s %-6s\n","Name","Lab","Bonus","Total","Per%","Grade");
        System.out.println("---------------------------------------------------");

	for (int a = 0; a < names.length; a++) {
            int total = labPoints[a] + bonusPoints[a];
            double percent = (double) total / TOTAL_POINTS * 100;



	String grade;
            if (percent >= 90) {
                grade = "A";
            } else if (percent >= 80) {
                grade = "B";
            } else if (percent >= 70) {
                grade = "C";
            } else if (percent >= 60) {
                grade = "D";
	       
            } 
	      else if (percent >= 40) {
                grade = "E";
	    }
	      else {
                grade = "F";
            }
        
	System.out.printf("%-10s %-5d %-6d %-6d %-6.2f %-6s\n",
                    names[a], labPoints[a], bonusPoints[a], total, percent, grade);
        }

      
        System.out.println("---------------------------------------------------");
        System.out.println("\n............................................................\n");
    }
}
	
