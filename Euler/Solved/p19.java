import java.util.GregorianCalendar;
import java.util.Calendar;

class p19{
    

    /*
	You are given the following information, but you may prefer to do some research for yourself.

	1 Jan 1900 was a Monday.
	Thirty days has September,
	April, June and November.
	All the rest have thirty-one,
	Saving February alone,
	Which has twenty-eight, rain or shine.
	And on leap years, twenty-nine.

	A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
	How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
    */

	static public void main(String args[]){
		GregorianCalendar cal = new GregorianCalendar(1901, Calendar.JANUARY, 1);

		// System.out.println("DATE: " + cal.get(Calendar.DATE));
 	// 	System.out.println("DAY_OF_MONTH: " + cal.get(Calendar.DAY_OF_MONTH));
		// System.out.println("DAY_OF_YEAR: " + cal.get(Calendar.DAY_OF_YEAR));
 	// 	System.out.println("DAY_OF_WEEK: " + cal.get(Calendar.DAY_OF_WEEK));
	 // 	System.out.println("DAY_OF_WEEK_IN_MONTH: "
	 //                    + cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));

	 	int sundays = 0;
	 	while(cal.get(Calendar.YEAR) <= 2000 
	 		  && cal.get(Calendar.MONTH) <= Calendar.DECEMBER 
	 		  && cal.get(Calendar.DAY_OF_MONTH) <= 31){
	 		// System.out.print(cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR));
	 		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && cal.get(Calendar.DAY_OF_MONTH) == 1){
	 			// System.out.println(" SUNDAY");
	 			sundays++;
	 		} else {
	 			// System.out.println("");
	 		}
	 		cal.add(Calendar.DAY_OF_YEAR,1);
	 	}
	 	System.out.println("AMMOUNT OF SUNDAYS: " + sundays);
	}
}