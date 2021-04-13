package hust.soict.hedspi.test.utils;

import hust.soict.hedspi.aims.utils.DateUtils;
import hust.soict.hedspi.aims.utils.MyDate;

public class DateTest {

	public static void main(String[] args) {
		//Date Format: dd/MM/yyyy
		MyDate[] d = new MyDate[7];
		d[0] = new MyDate("December","twenty-second","twenty twenty");
		d[1] = new MyDate("December","twenty-first","twenty ten");
		d[2] = new MyDate("January","twenty-second","twenty twenty-three");
		d[3] = new MyDate("December","second","two hundred");
		d[4] = new MyDate("February","twelfth","twenty-one twenty");
		d[5] = new MyDate("December","twenty-second","twenty twenty");
		d[6] = new MyDate("December","thirty-first","fourteen oh seven");
		assert DateUtils.compare(d[0],d[1]) > 0 : "Wrong compare";
		assert DateUtils.compare(d[0],d[5]) == 0 : "Wrong compare";
		DateUtils.sort(d);
		for (MyDate myDate : d) {
			myDate.print();
			myDate.printShort();
		}
	}
}
