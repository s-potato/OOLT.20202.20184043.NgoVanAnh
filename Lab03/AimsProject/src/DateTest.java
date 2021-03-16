
public class DateTest {

	public static void main(String[] args) {
		//Date Format: dd/MM/yyyy
		MyDate date1 = new MyDate("12/1/23132");
		assert date1.print() == true : "Contructor string error";
		MyDate date2 = new MyDate();
		assert date2.print() == true : "Contructor default error";
		MyDate date3 = new MyDate(31,12,2021);
		assert date3.print() == true : "Contructor int error";
		assert date2.accept() == true : "Insert error";
		date2.print();
	}
}
