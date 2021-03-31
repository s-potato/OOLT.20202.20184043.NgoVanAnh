package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.disc.DigitalVideoDisc;
import hust.soict.hedspi.aims.order.Order;

public class Aims {
	public static void main(String[] args) {
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King");
		dvd1.setCategory("Animation");
		dvd1.setCost(19.95f);
		dvd1.setDirector("Roger Allers");
		dvd1.setLength(87);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars");
		dvd2.setCategory("Science Fiction");
		dvd2.setCost(24.95f);
		dvd2.setDirector("George Lucas");
		dvd2.setLength(124);
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin");
		dvd3.setCategory("Animation");
		dvd3.setCost(18.99f);
		dvd3.setDirector("John Musker");
		dvd3.setLength(90);
		DigitalVideoDisc dvd4 = new DigitalVideoDisc("Cinderella");
		dvd4.setCategory("Animation");
		dvd4.setCost(100.99f);
		dvd4.setDirector("John Cena");
		dvd4.setLength(120);
		DigitalVideoDisc dvd5 = new DigitalVideoDisc("Jungle");
		dvd5.setCategory("Animation");
		dvd5.setCost(28.99f);
		dvd5.setDirector("John Cena");
		dvd5.setLength(95);
		
		Order order1 = Order.createOrder();
		order1.addDigitalVideoDisc(dvd2);
		sumTest(order1, dvd2);
		order1.print();

		Order order2 = Order.createOrder();
		order2.addDigitalVideoDisc(dvd1, dvd2);
		sumTest(order2, dvd1, dvd2);
		order2.print();
		
		Order order3 = Order.createOrder();
		order3.addDigitalVideoDisc(dvd3, dvd4, dvd5, dvd3, dvd4, dvd5, dvd3, dvd4, dvd5);
		sumTest(order3, dvd3, dvd4, dvd5, dvd3, dvd4, dvd5, dvd3, dvd4, dvd5);
		order3.print();
		order3.addDigitalVideoDisc(dvd3, dvd4);
		sumTest(order3, dvd3, dvd4, dvd5, dvd3, dvd4, dvd5, dvd3, dvd4, dvd5, dvd3);

		Order order4 = Order.createOrder();
		order4.addDigitalVideoDisc(dvd3, dvd4, dvd5, dvd3, dvd4, dvd5, dvd3, dvd4, dvd5);
		sumTest(order4, dvd3, dvd4, dvd5, dvd3, dvd4, dvd5, dvd3, dvd4, dvd5);
		order4.addDigitalVideoDisc(dvd3, dvd4, dvd5);
		sumTest(order4, dvd3, dvd4, dvd5, dvd3, dvd4, dvd5, dvd3, dvd4, dvd5);

		Order order5 = Order.createOrder();
		order5.addDigitalVideoDisc(dvd1);
		Order order6 = Order.createOrder();
		assert order6 == null : "Create more than 5 order.";
	}

	private static void sumTest(Order order, DigitalVideoDisc... dvd) {
		float sum = 0;
		for (DigitalVideoDisc digitalVideoDisc : dvd) {
			sum += digitalVideoDisc.getCost();
		}
		System.out.println("" + sum + " " + order.totalCost());
		assert sum==order.totalCost() : "Wrong total";
		System.out.println("Correct sum!");
	}
}
