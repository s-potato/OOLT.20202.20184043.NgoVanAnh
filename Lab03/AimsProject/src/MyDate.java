import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyDate {
	private int day;
	private int month;
	private int year;

	public MyDate() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String date_str = format.format(date);
		parseDate(date_str);
	}
	
	public MyDate(int day, int month, int year) {
		super();
		if(checkDate(day, month, year)) {
			this.day = day;
			this.month = month;
			this.year = year;
		}
	}
	
	public MyDate (String date) {
		parseDate(date);
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public boolean accept() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Insert date (dd/MM/yyyy): ");
		String date = scanner.nextLine();
		scanner.close();
		return parseDate(date);
	}
	public boolean print() {
		if (day == 0 || month == 0 || year == 0) {
			//System.out.println("null");
			return false;
		}
		System.out.printf("%02d/%02d/%d%n",day,month,year);
		return true;
	}
	
	private boolean parseDate(String date) {
		String[] dates;
		try {
			dates = date.split("/");
		} catch (Exception e) {
			System.out.println("Wrong date format");
			return false;
		}
		if (dates.length != 3) {
			System.out.println("Wrong date format");
			return false;
		}
		int day, month, year;
		try {
			day = Integer.valueOf(dates[0]);
		} catch (Exception e) {
			System.out.println("Wrong day");
			return false;
		}
		try {
			month = Integer.valueOf(dates[1]);
		} catch (Exception e) {
			System.out.println("Wrong month");
			return false;
		}
		try {
			year = Integer.valueOf(dates[2]);
		} catch (Exception e) {
			System.out.println("Wrong year");
			return false;
		}
		if (!checkDate(day, month, year)) {
			return false;
		}
		this.day = day;
		this.month = month;
		this.year = year;
		return true;
	}
	 private boolean checkDate(int day, int month, int year) {
		 if (year < 0) {
			 System.out.println("Wrong year");
			 return false;
		 }
		 if (day <= 0) {
			 System.out.println("Wrong day");
			 return false;
		 }
		 switch(month) {
		 case 1:
		 case 3:
		 case 5:
		 case 7:
		 case 8:
		 case 10:
		 case 12:
			 if(day > 31) {
				 System.out.println("Wrong day");
				 return false;
			 }
			 return true;
		 case 4:
		 case 6:
		 case 9:
		 case 11:
			 if(day > 30) {
				 System.out.println("Wrong day");
				 return false;
			 }
			 return true;
		 case 2:
			 if(year%4 == 0){
                 if((year%100 == 0) && (year%400 != 0)) {
                	 if(day > 28) {
                		 System.out.println("Wrong day");
        				 return false;
        			 }
                 } else {
                	 if(day > 29) {
             			System.out.println("Wrong day");
        				 return false;
        			 }
                 }
             } else {
            	 if(day > 28) {
         			System.out.println("Wrong day");
    				 return false;
    			 }
             }
			 return true;
		 default:
			System.out.println("Wrong month");
			return false;
		 }
	 }
}
