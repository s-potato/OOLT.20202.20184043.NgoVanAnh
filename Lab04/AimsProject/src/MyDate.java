import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyDate {
	private static final String[] monthArr = {
		"",
		"January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
	};
	private static final String[] numberArr = {
		"oh","one","two","three","four","five","six","seven","eight","nine","ten",
		"eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen",
		"twenty","thirty","fourty","fifty","sixty","seventy","eighty","ninety","hundred"
	};
	private static final String[] ordialArr = {
		"","first","second","third","fourth","fifth","sixth","seventh","eighth","ninth","tenth",
		"eleventh","twelfth","thirteenth","fourteenth","fifteenth","sixteenth","seventeenth","eighteenth","nineteenth",
		"twentieth","thirtieth"
	};

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
		this();
		if(checkDate(day, month, year)) {
			this.day = day;
			this.month = month;
			this.year = year;
		} else {
			System.out.println("Date is set to defaut: today.");
		}
	}
	
	public MyDate (String date) {
		this();
		if(!parseDate(date)) {
			System.out.println("Date is set to defaut: today.");
		}
	}

	public MyDate (String month, String day, String year) {
		this();
		int iMonth = intMonth(month);
		int iDay = intDay(day);
		int iYear = intYear(year);
		if(checkDate(iDay, iMonth, iYear)) {
			this.day = iDay;
			this.month = iMonth;
			this.year = iYear;
		} else {
			System.out.println("Date is set to defaut: today.");
		}
	}
	
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	public void setDate(int day, int month, int year) {
		if(checkDate(day, month, year)) {
			this.day = day;
			this.month = month;
			this.year = year;
		}
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
			System.out.println("null");
			return false;
		}
		System.out.printf("%s, %s %d%n",strMonth(month),strDay(day),year);
		return true;
	}
	public boolean printShort() {
		if (day == 0 || month == 0 || year == 0) {
			System.out.println("null");
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
		 if (year <= 0) {
			 System.out.println("Wrong year.");
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

	private String strDay(int day) {
		switch(day) {
		case 1:
		case 21:
		case 31:
			return String.valueOf(day) + "st";
		case 2:
		case 22:
			return String.valueOf(day) + "nd";
		case 3:
		case 23:
			return String.valueOf(day) + "rd";
		default:
			return String.valueOf(day) + "th";
		}
	}

	private String strMonth(int month) {
		return monthArr[month];
	}

	private int intDay(String day) {
		String[] strArr = day.split("-");
		if (strArr.length == 1) {
			for (int i = 1; i < ordialArr.length; i++) {
				if (strArr[0].equals(ordialArr[i])) {
					if (i == 21) {
						return 30;
					}
					return i;
				}
			}
		}
		if (strArr.length == 2) {
			int tens = 0;
			if (strArr[0].equals(numberArr[20])){
				tens = 20;
			} else if (strArr[0].equals(numberArr[21])){
				tens = 30;
			} else {
				return 0;
			}
			for (int i = 1; i <= 9; i++) {
				if (strArr[1].equals(ordialArr[i])){
					return i + tens;
				}
			}
		}
		return 0;
	}
	private int intMonth(String month) {
		for (int i = 1; i < monthArr.length; i++) {
			if (month.equals(monthArr[i])) {
				return i;
			}
		}
		return 0;
	}
	private int intYear(String year) {
		int hundreds, ones;
		String[] strArr = year.split(" ");
		switch (strArr.length) {
			case 1:
				return stringTo2DigitNumber(strArr[0]);
			case 2:
				hundreds = stringTo2DigitNumber(strArr[0]);
				if (hundreds == 0){
					return 0;
				}
				if(strArr[1].equals(numberArr[28])) {
					ones = 0;
				} else {
					ones = stringTo2DigitNumber(strArr[1]);
					if (ones < 10) {
						return 0;
					}
				}
				return hundreds*100 + ones;
			case 3:
				if (!strArr[1].equals(numberArr[0])) {
					return 0;
				}
				hundreds = stringTo2DigitNumber(strArr[0]);
				if (hundreds == 0) {
					return 0;
				}
				ones = stringTo2DigitNumber(strArr[2]);
				if (ones == 0 || ones > 9) {
					return 0;
				}
				return hundreds*100 + ones;
			default:
				return 0;
		}
	}
	private int stringTo2DigitNumber(String str) {
		String[] strArr = str.split("-");
		if (strArr.length == 1) {
			for (int i = 1; i < numberArr.length-1; i++) {
				if (strArr[0].equals(numberArr[i])) {
					if (i >= 21) {
						return 20 + (i%20)*10;
					}
					return i;
				}
			}
		}
		if (strArr.length == 2) {
			int tens = 0;
			for (int i = 20; i < numberArr.length-1; i++) {
				if (strArr[0].equals(numberArr[i])) {
					tens = 20 + (i%20)*10;
				}
			}
			if (tens == 0) {
				return 0;
			}
			for (int i = 1; i <= 9; i++) {
				if (strArr[1].equals(numberArr[i])){
					return i + tens;
				}
			}
		}
		return 0;
	}
}
